package com.wetech.demo.web3j.controller;

import com.wetech.demo.web3j.service.EscrowService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@RequestMapping("/api/escrow")
@RequiredArgsConstructor
public class EscrowController {

    private final EscrowService escrowService;

    /**
     * Deploy a new Escrow contract (arbiter = backend account)
     * @return contract address, deployment tx hash and block number
     */
    @PostMapping("/deploy")
    public CompletableFuture<ResponseEntity<Map<String, String>>> deployContract() {
        return escrowService.deploy()
                .thenApply(c -> {
                    Map<String, String> response = new HashMap<>();
                    response.put("contractAddress", escrowService.getContractAddress());
                    response.put("transactionHash", escrowService.getDeployTransactionHash());
                    response.put("deployBlock", escrowService.getDeployBlock().toString());
                    return ResponseEntity.ok(response);
                });
    }

    /**
     * Load an existing Escrow contract
     * @param address   the deployed contract address
     * @param fromBlock block to start scanning OrderCreated events from
     */
    @PostMapping("/load")
    public ResponseEntity<Map<String, String>> loadContract(@RequestParam String address,
                                                            @RequestParam BigInteger fromBlock) {
        escrowService.load(address, fromBlock);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Contract loaded successfully");
        response.put("contractAddress", address);
        return ResponseEntity.ok(response);
    }

    /**
     * Get the address of the currently loaded contract and its arbiter
     */
    @GetMapping("/address")
    public ResponseEntity<Map<String, String>> getContractAddress() throws Exception {
        Map<String, String> response = new HashMap<>();
        String address = escrowService.getContractAddress();
        if (address == null) {
            response.put("message", "No contract loaded");
            return ResponseEntity.ok(response);
        }
        response.put("contractAddress", address);
        response.put("arbiter", escrowService.getArbiter());
        return ResponseEntity.ok(response);
    }

    /**
     * Get a single order by id
     */
    @GetMapping("/orders/{id}")
    public CompletableFuture<ResponseEntity<Map<String, String>>> getOrder(@PathVariable String id) {
        return escrowService.getOrder(new BigInteger(id))
                .thenApply(ResponseEntity::ok);
    }

    /**
     * List all orders rebuilt from OrderCreated event logs
     */
    @GetMapping("/orders")
    public ResponseEntity<List<Map<String, String>>> listOrders() throws Exception {
        return ResponseEntity.ok(escrowService.listOrders());
    }

    /**
     * Arbitrate a disputed order
     * @param sellerWins true: funds go to the seller; false: refunded to the buyer
     */
    @PostMapping("/orders/{id}/resolve")
    public CompletableFuture<ResponseEntity<Map<String, String>>> resolve(
            @PathVariable String id,
            @RequestParam boolean sellerWins) {
        return escrowService.resolve(new BigInteger(id), sellerWins)
                .thenApply(receipt -> {
                    Map<String, String> response = new HashMap<>();
                    response.put("transactionHash", receipt.getTransactionHash());
                    response.put("blockNumber", receipt.getBlockNumber().toString());
                    response.put("status", receipt.getStatus());
                    return ResponseEntity.ok(response);
                });
    }
}
