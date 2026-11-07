package com.wetech.demo.web3j.contracts.kado1token;

import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/LFDT-web3j/web3j/tree/main/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.14.0.
 */
@SuppressWarnings("rawtypes")
public class KADO1TOKEN extends Contract {
    public static final String BINARY = "60c0604052600a60809081526925a0a22798aa27a5a2a760b11b60a0525f906100289082610125565b5060408051808201909152600f81526e4a525a32303234333035353037313360881b602082015260019061005c9082610125565b506002805460ff19166012179055348015610075575f5ffd5b50600480546001600160a01b031916331790556101df565b634e487b7160e01b5f52604160045260245ffd5b600181811c908216806100b557607f821691505b6020821081036100d357634e487b7160e01b5f52602260045260245ffd5b50919050565b601f82111561012057805f5260205f20601f840160051c810160208510156100fe5750805b601f840160051c820191505b8181101561011d575f815560010161010a565b50505b505050565b81516001600160401b0381111561013e5761013e61008d565b6101528161014c84546100a1565b846100d9565b6020601f821160018114610184575f831561016d5750848201515b5f19600385901b1c1916600184901b17845561011d565b5f84815260208120601f198516915b828110156101b35787850151825560209485019460019092019101610193565b50848210156101d057868401515f19600387901b60f8161c191681555b50505050600190811b01905550565b610a23806101ec5f395ff3fe608060405234801561000f575f5ffd5b50600436106100b1575f3560e01c806342966c681161006e57806342966c681461015457806370a08231146101675780638da5cb5b1461018f57806395d89b41146101ba578063a9059cbb146101c2578063dd62ed3e146101d5575f5ffd5b806306fdde03146100b5578063095ea7b3146100d357806318160ddd146100f657806323b872dd1461010d578063313ce5671461012057806340c10f191461013f575b5f5ffd5b6100bd61020d565b6040516100ca91906107ae565b60405180910390f35b6100e66100e13660046107fe565b610298565b60405190151581526020016100ca565b6100ff60035481565b6040519081526020016100ca565b6100e661011b366004610826565b610304565b60025461012d9060ff1681565b60405160ff90911681526020016100ca565b61015261014d3660046107fe565b6104c8565b005b610152610162366004610860565b610603565b6100ff610175366004610877565b6001600160a01b03165f9081526005602052604090205490565b6004546101a2906001600160a01b031681565b6040516001600160a01b0390911681526020016100ca565b6100bd6106d3565b6100e66101d03660046107fe565b6106e0565b6100ff6101e3366004610897565b6001600160a01b039182165f90815260066020908152604080832093909416825291909152205490565b5f8054610219906108c8565b80601f0160208091040260200160405190810160405280929190818152602001828054610245906108c8565b80156102905780601f1061026757610100808354040283529160200191610290565b820191905f5260205f20905b81548152906001019060200180831161027357829003601f168201915b505050505081565b335f8181526006602090815260408083206001600160a01b038716808552925280832085905551919290917f8c5be1e5ebec7d5bd14f71427d1e84f3dd0314c0f7b2291e5b200ac8c7c3b925906102f29086815260200190565b60405180910390a35060015b92915050565b5f6001600160a01b0383166103345760405162461bcd60e51b815260040161032b90610900565b60405180910390fd5b6001600160a01b0384165f9081526005602052604090205482111561036b5760405162461bcd60e51b815260040161032b90610948565b6001600160a01b0384165f9081526006602090815260408083203384529091529020548211156103f35760405162461bcd60e51b815260206004820152602d60248201527f4b41444f31544f4b454e3a207472616e7366657220616d6f756e74206578636560448201526c65647320616c6c6f77616e636560981b606482015260840161032b565b6001600160a01b0384165f908152600660209081526040808320338452909152812080548492906104259084906109a7565b90915550506001600160a01b0384165f90815260056020526040812080548492906104519084906109a7565b90915550506001600160a01b0383165f908152600560205260408120805484929061047d9084906109ba565b92505081905550826001600160a01b0316846001600160a01b03165f5160206109ce5f395f51905f52846040516104b691815260200190565b60405180910390a35060019392505050565b6004546001600160a01b0316331461052e5760405162461bcd60e51b815260206004820152602360248201527f4b41444f31544f4b454e3a2063616c6c6572206973206e6f7420746865206f776044820152623732b960e91b606482015260840161032b565b6001600160a01b0382166105905760405162461bcd60e51b8152602060048201526024808201527f4b41444f31544f4b454e3a206d696e7420746f20746865207a65726f206164646044820152637265737360e01b606482015260840161032b565b8060035f8282546105a191906109ba565b90915550506001600160a01b0382165f90815260056020526040812080548392906105cd9084906109ba565b90915550506040518181526001600160a01b038316905f905f5160206109ce5f395f51905f529060200160405180910390a35050565b335f908152600560205260409020548111156106715760405162461bcd60e51b815260206004820152602760248201527f4b41444f31544f4b454e3a206275726e20616d6f756e7420657863656564732060448201526662616c616e636560c81b606482015260840161032b565b335f908152600560205260408120805483929061068f9084906109a7565b925050819055508060035f8282546106a791906109a7565b90915550506040518181525f9033905f5160206109ce5f395f51905f529060200160405180910390a350565b60018054610219906108c8565b5f6001600160a01b0383166107075760405162461bcd60e51b815260040161032b90610900565b335f908152600560205260409020548211156107355760405162461bcd60e51b815260040161032b90610948565b335f90815260056020526040812080548492906107539084906109a7565b90915550506001600160a01b0383165f908152600560205260408120805484929061077f9084906109ba565b90915550506040518281526001600160a01b0384169033905f5160206109ce5f395f51905f52906020016102f2565b602081525f82518060208401528060208501604085015e5f604082850101526040601f19601f83011684010191505092915050565b80356001600160a01b03811681146107f9575f5ffd5b919050565b5f5f6040838503121561080f575f5ffd5b610818836107e3565b946020939093013593505050565b5f5f5f60608486031215610838575f5ffd5b610841846107e3565b925061084f602085016107e3565b929592945050506040919091013590565b5f60208284031215610870575f5ffd5b5035919050565b5f60208284031215610887575f5ffd5b610890826107e3565b9392505050565b5f5f604083850312156108a8575f5ffd5b6108b1836107e3565b91506108bf602084016107e3565b90509250929050565b600181811c908216806108dc57607f821691505b6020821081036108fa57634e487b7160e01b5f52602260045260245ffd5b50919050565b60208082526028908201527f4b41444f31544f4b454e3a207472616e7366657220746f20746865207a65726f604082015267206164647265737360c01b606082015260800190565b6020808252602b908201527f4b41444f31544f4b454e3a207472616e7366657220616d6f756e74206578636560408201526a6564732062616c616e636560a81b606082015260800190565b634e487b7160e01b5f52601160045260245ffd5b818103818111156102fe576102fe610993565b808201808211156102fe576102fe61099356feddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3efa2646970667358221220cda94402184bd5c26a1d55d084be1221e49d9be39509cb3363f12e597bbc444664736f6c634300081d0033";

    private static String librariesLinkedBinary;

    public static final String FUNC_ALLOWANCE = "allowance";

    public static final String FUNC_APPROVE = "approve";

    public static final String FUNC_BALANCEOF = "balanceOf";

    public static final String FUNC_BURN = "burn";

    public static final String FUNC_DECIMALS = "decimals";

    public static final String FUNC_MINT = "mint";

    public static final String FUNC_NAME = "name";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_SYMBOL = "symbol";

    public static final String FUNC_TOTALSUPPLY = "totalSupply";

    public static final String FUNC_TRANSFER = "transfer";

    public static final String FUNC_TRANSFERFROM = "transferFrom";

    public static final Event APPROVAL_EVENT = new Event("Approval", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event TRANSFER_EVENT = new Event("Transfer", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected KADO1TOKEN(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected KADO1TOKEN(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected KADO1TOKEN(String contractAddress, Web3j web3j, TransactionManager transactionManager,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected KADO1TOKEN(String contractAddress, Web3j web3j, TransactionManager transactionManager,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<ApprovalEventResponse> getApprovalEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(APPROVAL_EVENT, transactionReceipt);
        ArrayList<ApprovalEventResponse> responses = new ArrayList<ApprovalEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ApprovalEventResponse typedResponse = new ApprovalEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.spender = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static ApprovalEventResponse getApprovalEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(APPROVAL_EVENT, log);
        ApprovalEventResponse typedResponse = new ApprovalEventResponse();
        typedResponse.log = log;
        typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.spender = (String) eventValues.getIndexedValues().get(1).getValue();
        typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<ApprovalEventResponse> approvalEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getApprovalEventFromLog(log));
    }

    public Flowable<ApprovalEventResponse> approvalEventFlowable(DefaultBlockParameter startBlock,
            DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(APPROVAL_EVENT));
        return approvalEventFlowable(filter);
    }

    public static List<TransferEventResponse> getTransferEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(TRANSFER_EVENT, transactionReceipt);
        ArrayList<TransferEventResponse> responses = new ArrayList<TransferEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            TransferEventResponse typedResponse = new TransferEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static TransferEventResponse getTransferEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(TRANSFER_EVENT, log);
        TransferEventResponse typedResponse = new TransferEventResponse();
        typedResponse.log = log;
        typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
        typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<TransferEventResponse> transferEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getTransferEventFromLog(log));
    }

    public Flowable<TransferEventResponse> transferEventFlowable(DefaultBlockParameter startBlock,
            DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(TRANSFER_EVENT));
        return transferEventFlowable(filter);
    }

    public RemoteFunctionCall<BigInteger> allowance(String _owner, String spender) {
        final Function function = new Function(FUNC_ALLOWANCE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _owner), 
                new org.web3j.abi.datatypes.Address(160, spender)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> approve(String spender, BigInteger value) {
        final Function function = new Function(
                FUNC_APPROVE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, spender), 
                new org.web3j.abi.datatypes.generated.Uint256(value)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> balanceOf(String account) {
        final Function function = new Function(FUNC_BALANCEOF, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, account)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> burn(BigInteger value) {
        final Function function = new Function(
                FUNC_BURN, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(value)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> decimals() {
        final Function function = new Function(FUNC_DECIMALS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> mint(String to, BigInteger value) {
        final Function function = new Function(
                FUNC_MINT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, to), 
                new org.web3j.abi.datatypes.generated.Uint256(value)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> name() {
        final Function function = new Function(FUNC_NAME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> owner() {
        final Function function = new Function(FUNC_OWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> symbol() {
        final Function function = new Function(FUNC_SYMBOL, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> totalSupply() {
        final Function function = new Function(FUNC_TOTALSUPPLY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> transfer(String to, BigInteger value) {
        final Function function = new Function(
                FUNC_TRANSFER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, to), 
                new org.web3j.abi.datatypes.generated.Uint256(value)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> transferFrom(String from, String to,
            BigInteger value) {
        final Function function = new Function(
                FUNC_TRANSFERFROM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, from), 
                new org.web3j.abi.datatypes.Address(160, to), 
                new org.web3j.abi.datatypes.generated.Uint256(value)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static KADO1TOKEN load(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return new KADO1TOKEN(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static KADO1TOKEN load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new KADO1TOKEN(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static KADO1TOKEN load(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return new KADO1TOKEN(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static KADO1TOKEN load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new KADO1TOKEN(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<KADO1TOKEN> deploy(Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return deployRemoteCall(KADO1TOKEN.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), "");
    }

    public static RemoteCall<KADO1TOKEN> deploy(Web3j web3j, TransactionManager transactionManager,
            ContractGasProvider contractGasProvider) {
        return deployRemoteCall(KADO1TOKEN.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<KADO1TOKEN> deploy(Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(KADO1TOKEN.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<KADO1TOKEN> deploy(Web3j web3j, TransactionManager transactionManager,
            BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(KADO1TOKEN.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    public static void linkLibraries(List<Contract.LinkReference> references) {
        librariesLinkedBinary = linkBinaryWithReferences(BINARY, references);
    }

    private static String getDeploymentBinary() {
        if (librariesLinkedBinary != null) {
            return librariesLinkedBinary;
        } else {
            return BINARY;
        }
    }

    public static class ApprovalEventResponse extends BaseEventResponse {
        public String owner;

        public String spender;

        public BigInteger value;
    }

    public static class TransferEventResponse extends BaseEventResponse {
        public String from;

        public String to;

        public BigInteger value;
    }
}
