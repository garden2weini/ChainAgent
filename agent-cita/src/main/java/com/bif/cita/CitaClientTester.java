package com.bif.cita;

import com.citahub.cita.abi.datatypes.Address;
import com.citahub.cita.protocol.CITAj;
import com.citahub.cita.protocol.core.DefaultBlockParameter;
import com.citahub.cita.protocol.core.methods.response.*;
import com.citahub.cita.protocol.http.HttpService;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 * ref: https://github.com/citahub/cita-sdk-java/
 * ref: https://github.com/citahub/cita-sdk-java/blob/develop/docs/jsonrpc.md
 */
public class CitaClientTester {
    // test connection
    private String address = "https://testnet.citahub.com";
    private static CITAj service;

    public static void main(String[] args) throws IOException {
        CitaClientTester tester = new CitaClientTester();
        tester.test();
    }

    public void test() throws IOException {
        // 根据 CITAjService 类型实例化 CITAj
        service = CITAj.build(new HttpService(this.address));

        String version = service.getVersion().send().getVersion().softwareVersion;
        System.out.println("Version: " + version);
        // 获取当前块高度
        BigInteger blockNum = service.appBlockNumber().send().getBlockNumber();
        System.out.println("BlockNumber: " + blockNum);

        // MetaData
        AppMetaData.AppMetaDataResult metaDataResult = service.appMetaData(DefaultBlockParameter.valueOf(blockNum)).send().getAppMetaDataResult();
        if(metaDataResult != null) {
            System.out.println("Meta:ChainName:" + metaDataResult.getChainName());
            System.out.println("Meta:TokenName:" + metaDataResult.getTokenName());
            System.out.println("Meta:Operator:" + metaDataResult.getOperator());
            System.out.println("Meta:TokenSymbol:" + metaDataResult.getTokenSymbol());
            System.out.println("Meta:EconomicalModel:" + metaDataResult.getEconomicalModel());
            System.out.println("Meta:Website:" + metaDataResult.getWebsite());
            Address[] addresses = metaDataResult.getValidators();
            for (Address address : addresses) {
                System.out.println("Meta:Validators.address:" + address.getValue());
                System.out.println("Meta:Validators.addressType:" + address.getTypeAsString());
            }
        } else {
            System.out.println("NOTE: Meta is null for block " + blockNum);
        }
        // 根据块高度查询块信息
        DefaultBlockParameter blockParameter = DefaultBlockParameter.valueOf(blockNum);
        AppBlock.Block block = service.appGetBlockByNumber(blockParameter, true).send().getBlock();
        System.out.println("Block.Hash:" + block.getHash());
        System.out.println("Block.PrevHash:" + block.getHeader().getPrevHash());
        System.out.println("Block.GasUsedDec:" + block.getHeader().getGasUsedDec());
        System.out.println("Block.Proof:" + block.getHeader().getProof());
        System.out.println("Block.Proposer:" + block.getHeader().getProposer());
        System.out.println("Block.QuotaUsed:" + block.getHeader().getQuotaUsed());
        System.out.println("Block.ReceiptsRoot:" + block.getHeader().getReceiptsRoot());
        System.out.println("Block.StateRoot:" + block.getHeader().getStateRoot());

        List<AppBlock.TransactionObject> txList = block.getBody().getTransactions();
        for(AppBlock.TransactionObject tx: txList) {
            System.out.println("Block:" + block.getHash() + ". TxHash:" + tx.getHash());
            System.out.println("Block:" + block.getHash() + ". TxContent:" + tx.getContent());
            System.out.println("Block:" + block.getHash() + ". TxFrom:" + tx.getFrom());
            System.out.println("Block:" + block.getHash() + ". TxIndex:" + tx.getIndex());

            TransactionReceipt txReceipt = service.appGetTransactionReceipt(tx.getHash()).send().getTransactionReceipt();
            System.out.println("Block:" + block.getHash() + ". TxReceipt.CumulativeGasUsed:" + txReceipt.getCumulativeGasUsed());
            System.out.println("Block:" + block.getHash() + ". TxReceipt.CumulativeQuotaUsed:" + txReceipt.getCumulativeQuotaUsed());
            System.out.println("Block:" + block.getHash() + ". TxReceipt.ErrorMessage:" + txReceipt.getErrorMessage());
            System.out.println("Block:" + block.getHash() + ". TxReceipt.GasUsed:" + txReceipt.getGasUsed());
            System.out.println("Block:" + block.getHash() + ". TxReceipt.QuotaUsed:" + txReceipt.getQuotaUsed());
            System.out.println("Block:" + block.getHash() + ". TxReceipt.LogsBloom:" + txReceipt.getLogsBloom());
            System.out.println("Block:" + block.getHash() + ". TxReceipt.Status:" + txReceipt.getStatus());
            System.out.println("Block:" + block.getHash() + ". TxReceipt.Root:" + txReceipt.getRoot());
            System.out.println("Block:" + block.getHash() + ". TxReceipt.To:" + txReceipt.getTo());
            System.out.println("Block:" + block.getHash() + ". TxReceipt.ContractAddress:" + txReceipt.getContractAddress());
        }
        // 获取当前连接节点数
        // NOTE: 当前节点探查到的peers节点及数量，不是链上所有的peers节点
        NetPeerCount netPeerCount = service.netPeerCount().send();
        BigInteger peerCount = netPeerCount.getQuantity();
        System.out.println("当前连接节点数:" + peerCount);
        // PeersInfo
        Map<String, String> peers = service.netPeersInfo().send().getPeersInfo().peers;
        for(String key: peers.keySet()) {
            System.out.println("PeersInfo.Key:" + key);
            System.out.println("PeersInfo.Value:" + peers.get(key));
        }
        // 获取指定块高的元数据
        DefaultBlockParameter defaultParam = DefaultBlockParameter.valueOf("latest");
        AppMetaData.AppMetaDataResult result = service.appMetaData(defaultParam).send().getResult();
        int chainId = result.getChainId().intValue();
        String chainName = result.getChainName();
        String genesisTS = result.getGenesisTimestamp();
        System.out.println("指定块高的元数据-chainId:" + chainId);
        System.out.println("指定块高的元数据-chainName:" + chainName);
        System.out.println("指定块高的元数据-genesisTS:" + genesisTS);
    }

    /**
     * 获取地址余额
     * @param address 所要查询的地址
     * @param blockParameter 块高度的接口：数字或者关键字
     * @return
     * @throws IOException
     */
    public BigInteger GetBalance(String address, DefaultBlockParameter blockParameter) throws IOException {
        // addr = "{hex cita address starting with 0x}";
        //DefaultBlockParameter defaultBlockParameter = DefaultBlockParameter.valueOf("latest");
        AppGetBalance getBalance = service.appGetBalance(address, blockParameter).send();
        BigInteger balance = getBalance.getBalance();
        return balance;
    }


    /**
     * 获取合约的Abi
     * @param contractAddress 所要查询Abi的合约地址
     * @param blockParameter 块高度的接口：数字或者关键字
     * @return
     */
    public String getAbi(String contractAddress, DefaultBlockParameter blockParameter) throws IOException {
        //addr = "{hex cita address starting with 0x}";
        AppGetAbi getAbi = service.appGetAbi(contractAddress, blockParameter).send();
        String abi = getAbi.getAbi();
        return abi;
    }

    /**
     * 获取账户发送合约数量
     * @param address 所要查询的地址
     * @param blockParameter 块高度的接口：数字或者关键字
     * @return
     */
    public BigInteger getTransactionCount(String address, DefaultBlockParameter blockParameter) throws IOException {
        AppGetTransactionCount getTransactionCount = service.appGetTransactionCount(address, blockParameter).send();
        BigInteger txCount = getTransactionCount.getTransactionCount();
        return txCount;
    }

    /**
     * 获取合约代码
     * @param address
     * @param blockParameter
     * @return
     * @throws IOException
     */
    public String getCode(String address, DefaultBlockParameter blockParameter) throws IOException {
        AppGetCode getCode = service.appGetCode(address, blockParameter).send();
        String code = getCode.getCode();
        return code;
    }

    /**
     * 根据哈希查询交易信息
     * @param txHash
     * @return
     * @throws IOException
     */
    public Transaction getTransactionByHash(String txHash) throws IOException {
        Transaction responseTx = service.appGetTransactionByHash(txHash).send().getTransaction();
        return responseTx;
    }

}