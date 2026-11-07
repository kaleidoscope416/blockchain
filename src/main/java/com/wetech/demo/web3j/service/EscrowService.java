package com.wetech.demo.web3j.service;

import com.wetech.demo.web3j.contracts.escrow.Escrow;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.web3j.abi.EventEncoder;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.gas.ContractGasProvider;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class EscrowService {

    private static final String[] STATUS = {"Created", "Paid", "Completed", "Refunded",
            "Disputed"};

    private final Web3j web3j;
    private final Credentials credentials;
    private final ContractGasProvider gasProvider;

    private Escrow contract;

    @Getter
    private String contractAddress;

    @Getter
    private BigInteger deployBlock;

    @Getter
    private String deployTransactionHash;

    public CompletableFuture<Escrow> deploy() {
        // 仲裁人就是后端账户自己
        return Escrow.deploy(web3j, credentials, gasProvider, credentials.getAddress())
                .sendAsync()
                .thenApply(c -> {
                    this.contract = c;
                    this.contractAddress = c.getContractAddress();
                    this.deployBlock = c.getTransactionReceipt()
                            .map(TransactionReceipt::getBlockNumber).orElse(BigInteger.ZERO);
                    this.deployTransactionHash = c.getTransactionReceipt()
                            .map(TransactionReceipt::getTransactionHash).orElse(null);
                    log.info("Escrow deployed at {} (block {}, tx {})",
                            contractAddress, deployBlock, deployTransactionHash);
                    return c;
                });
    }

    public void load(String address, BigInteger fromBlock) {
        this.contract = Escrow.load(address, web3j, credentials, gasProvider);
        this.contractAddress = address;
        this.deployBlock = fromBlock;
        this.deployTransactionHash = null;
    }

    public String getArbiter() throws Exception {
        requireContract();
        return contract.arbiter().send();
    }

    public CompletableFuture<Map<String, String>> getOrder(BigInteger id) {
        requireContract();
        return contract.getOrder(id).sendAsync().thenApply(o -> Map.of(
                "buyer", o.buyer,
                "seller", o.seller,
                "token", o.token,
                "amount", o.amount.toString(),
                "deadline", o.deadline.toString(),
                "status", STATUS[o.status.intValue()],
                "description", o.description));
    }

    /**
     * 从 OrderCreated 事件日志重建订单列表。
     * <p>
     * 注意：POTOS 测试网的 eth_getLogs 一次只扫 fromBlock 起的 10 个区块，超出部分
     * 静默丢弃，所以不能一次 deployBlock → LATEST 拉完，要按 10 个区块一段分批查再拼起来。
     */
    public List<Map<String, String>> listOrders() throws Exception {
        requireContract();
        BigInteger latest = web3j.ethBlockNumber().send().getBlockNumber();
        BigInteger step = BigInteger.TEN;
        List<Log> logs = new ArrayList<>();
        for (BigInteger from = deployBlock; from.compareTo(latest) <= 0; from = from.add(step)) {
            BigInteger to = from.add(step).subtract(BigInteger.ONE).min(latest);
            EthFilter filter = new EthFilter(
                    DefaultBlockParameter.valueOf(from),
                    DefaultBlockParameter.valueOf(to),
                    contractAddress);
            filter.addSingleTopic(EventEncoder.encode(Escrow.ORDERCREATED_EVENT));
            web3j.ethGetLogs(filter).send().getLogs().forEach(r -> logs.add((Log) r.get()));
        }
        return logs.stream().map(l -> {
            Escrow.OrderCreatedEventResponse e = Escrow.getOrderCreatedEventFromLog(l);
            return Map.of(
                    "id", e.id.toString(),
                    "buyer", e.buyer,
                    "seller", e.seller,
                    "amount", e.amount.toString(),
                    "blockNumber", l.getBlockNumber().toString(),
                    "transactionHash", l.getTransactionHash());
        }).toList();
    }

    public CompletableFuture<TransactionReceipt> resolve(BigInteger id, boolean sellerWins) {
        requireContract();
        return contract.resolve(id, sellerWins).sendAsync();
    }

    private void requireContract() {
        if (contract == null) {
            throw new IllegalStateException("Contract not deployed or loaded, call /deploy or /load first");
        }
    }
}
