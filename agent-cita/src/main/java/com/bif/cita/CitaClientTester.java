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
 * ref:
 * https://github.com/citahub/cita-sdk-java/
 * https://github.com/citahub/cita-sdk-java/blob/develop/docs/jsonrpc.md
 * https://docs.citahub.com/zh-CN/cita/rpc-guide/rpc
 * docs.citahub.com
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

        // TODO: 待确认
        List<String> accounts = service.appAccounts().send().getAccounts();
        if(accounts != null) {
            for(String account: accounts) {
                System.out.println("Account: " + account);
            }
        } else {
            System.out.println("Accounts is null.");
        }

        // NOTE: 获取当前 CITA 软件的版本号，该接口设置了使能开关，需要在链创建时通过使能选项开启该功能，才能正常使用
        String version = service.getVersion().send().getVersion().softwareVersion;
        System.out.println("Version: " + version);
        // 获取当前块高度
        BigInteger blockNum = service.appBlockNumber().send().getBlockNumber();
        System.out.println("BlockNumber: " + blockNum);

        // 获取指定块高的元数据MetaData
        AppMetaData.AppMetaDataResult metaDataResult = service.appMetaData(DefaultBlockParameter.valueOf(blockNum)).send().getAppMetaDataResult();
        if(metaDataResult != null) {
            System.out.println("Meta:ChainName:" + metaDataResult.getChainName());
            System.out.println("Meta:ChainId:" + metaDataResult.getChainId());
            System.out.println("Meta:ChainIdV1:" + metaDataResult.getChainIdV1());
            System.out.println("Meta:TokenName:" + metaDataResult.getTokenName());
            // 运营方名称
            System.out.println("Meta:Operator:" + metaDataResult.getOperator());
            System.out.println("Meta:TokenSymbol:" + metaDataResult.getTokenSymbol());
            // 经济模型。CITA 中存在两种经济模型，Quota(默认) 和 Charge。
            // 0 表示 Quota 模型交易只需不超过限额即可，限额由超级管理员设置；1 表示 Charge型，交易需要手续费，针对交易的每一步执行进行单步扣费模式，扣除余额
            System.out.println("Meta:EconomicalModel:" + metaDataResult.getEconomicalModel());
            System.out.println("Meta:Website:" + metaDataResult.getWebsite());
            // 出块间隔
            System.out.println("Meta:BlockInterval:" + metaDataResult.getBlockInterval());
            System.out.println("Meta:GenesisTimestamp:" + metaDataResult.getGenesisTimestamp());
            System.out.println("Meta:TokenAvatar:" + metaDataResult.getTokenAvatar());
            System.out.println("Meta:Version:" + metaDataResult.getVersion());

            // 验证者地址集合
            Address[] addresses = metaDataResult.getValidators();
            // NOTE: 有相同ip是部署在同一个主机上或同防火墙后
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
        // 上一个块的 Keccak 256-bit 哈希值
        System.out.println("Block.PrevHash:" + block.getHeader().getPrevHash());
        System.out.println("Block.GasUsedDec:" + block.getHeader().getGasUsedDec());
        // Proof 结构，出块人签名
        System.out.println("Block.Proof:" + block.getHeader().getProof());
        System.out.println("Block.Proposer:" + block.getHeader().getProposer());
        System.out.println("Block.QuotaUsed:" + block.getHeader().getQuotaUsed());
        // 交易列表 root
        System.out.println("Block.TransactionsRoot:" + block.getHeader().getTransactionsRoot());
        // 交易回执 root
        System.out.println("Block.ReceiptsRoot:" + block.getHeader().getReceiptsRoot());
        // 状态 root
        System.out.println("Block.StateRoot:" + block.getHeader().getStateRoot());

        List<AppBlock.TransactionObject> txList = block.getBody().getTransactions();
        for(AppBlock.TransactionObject tx: txList) {
            System.out.println("Block:" + block.getHash() + ". TxHash:" + tx.getHash());
            // 交易内容
            System.out.println("Block:" + block.getHash() + ". TxContent:" + tx.getContent());
            // 交易发送者
            System.out.println("Block:" + block.getHash() + ". TxFrom:" + tx.getFrom());
            System.out.println("Block:" + block.getHash() + ". TxIndex:" + tx.getIndex());

            TransactionReceipt txReceipt = service.appGetTransactionReceipt(tx.getHash()).send().getTransactionReceipt();
            System.out.println("Block:" + block.getHash() + ". TxReceipt.CumulativeGasUsed:" + txReceipt.getCumulativeGasUsed());
            // 块中该交易之前(包含该交易)的所有交易消耗的 quota 总量
            System.out.println("Block:" + block.getHash() + ". TxReceipt.CumulativeQuotaUsed:" + txReceipt.getCumulativeQuotaUsed());
            System.out.println("Block:" + block.getHash() + ". TxReceipt.ErrorMessage:" + txReceipt.getErrorMessage());
            System.out.println("Block:" + block.getHash() + ". TxReceipt.GasUsed:" + txReceipt.getGasUsed());
            // 交易消耗的 quota 数量
            System.out.println("Block:" + block.getHash() + ". TxReceipt.QuotaUsed:" + txReceipt.getQuotaUsed());
            System.out.println("Block:" + block.getHash() + ". TxReceipt.LogsBloom:" + txReceipt.getLogsBloom());
            System.out.println("Block:" + block.getHash() + ". TxReceipt.Status:" + txReceipt.getStatus());
            System.out.println("Block:" + block.getHash() + ". TxReceipt.Root:" + txReceipt.getRoot());
            System.out.println("Block:" + block.getHash() + ". TxReceipt.To:" + txReceipt.getTo());
            // NOTE: 如果是部署合约, 这个地址指的是新创建出来的合约地址. 否则为空
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

    }

    /**
     * 获取地址余额/获取账户余额
     * @param address 所要查询的地址
     * @param blockParameter 块高度的接口：数字或者关键字
     * @return 在指定高度的账户余额
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
     * @param address 所要查询的账户地址
     * @param blockParameter 块高度的接口：数字或者关键字
     * @return 获取指定账户从块高 0 到指定高度所发送的交易总量
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