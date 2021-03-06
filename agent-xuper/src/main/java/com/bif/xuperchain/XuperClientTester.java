package com.bif.xuperchain;

import com.baidu.xuperunion.api.XuperClient;
import com.baidu.xuperunion.pb.XchainOuterClass;
import com.bif.xuperchain.client.XuperClientX;
import com.google.protobuf.ByteString;
import org.bouncycastle.util.encoders.Hex;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assume.assumeNoException;

/**
 * https://xuperchain.readthedocs.io/zh/latest/test_network/guides.html
 */
public class XuperClientTester {
    // test connection
    //private String address = "14.215.179.74:37101";
    // open net
    private String address = "39.156.69.83:37100";
    private XuperClientX client;

    public XuperClientTester() {
        try {
            client = new XuperClientX(address);
        } catch (Exception e) {
            assumeNoException(e);
        }
    }

    public static void main(String[] args) {
        XuperClientTester test = new XuperClientTester();
        try {
            //test.apiExample();
            test.getAccountByAK("gxz77GP5zpTTKeXdxbPn8V5WAsW1n9iWe");
            test.getAccountContracts("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void apiExample() throws Exception {
        getSystemStatus();
    }

    public void getAccountByAK(String address) {
        System.out.println("getAccountByAK start");
        List<String> list = this.client.getAccountByAK(address);
        for(String item: list) {
            System.out.println(item);
        }
        System.out.println("getAccountByAK end.");

    }

    public void getAccountContracts(String address) {
        System.out.println("getAccountContracts start");
        List<XchainOuterClass.ContractStatus> list = this.client.getAccountContracts(address);
        for(XchainOuterClass.ContractStatus item: list) {
            System.out.println(item.getContractName());
        }
        System.out.println("getAccountContracts end.");
    }

    public void getBlockByHeigth() {
    }


    public void getBalance() throws Exception {

    }


    public void queryContract() throws Exception {

    }


    public void queryBlock(String blockId) throws Exception {
        XchainOuterClass.InternalBlock block = client.queryBlock(blockId);
        System.out.println("BlockID" + blockId);
        System.out.println("PreHash" + Hex.toHexString(block.getPreHash().toByteArray()));
        XchainOuterClass.InternalBlock preBlock = client.queryBlock(Hex.toHexString(block.getPreHash().toByteArray()));
        System.out.println("PRE Block Height:" + preBlock.getHeight());
        System.out.println("NextHash" + Hex.toHexString(block.getNextHash().toByteArray()));
        System.out.println("Proposer" + Hex.toHexString(block.getProposer().toByteArray()));
        System.out.println("PubKey" + Hex.toHexString(block.getPubkey().toByteArray()));
        List<XchainOuterClass.Transaction> txList = block.getTransactionsList();
        for(XchainOuterClass.Transaction tx: txList) {
            ByteString txId = tx.getTxid();
            List<String> authReqList = tx.getAuthRequireList();
            ByteString desc = tx.getDesc();
            String initiator = tx.getInitiator();
            String nonce = tx.getNonce();
            List<XchainOuterClass.TxInput> inputList = tx.getTxInputsList();
            List<XchainOuterClass.TxInputExt> inputExtList = tx.getTxInputsExtList();

        }
    }

    public void getSystemStatus() throws Exception {
        XchainOuterClass.SystemsStatus status = client.getSystemStatus();
        assertEquals(1, status.getBcsStatusCount());
        System.out.println("blockchain count:" + status.getBcsStatusCount());
        // 遍历所有的链
        for (int i = 0; i < status.getBcsStatusCount(); i++) {
            XchainOuterClass.BCStatus bcs = status.getBcsStatusList().get(i);

            this.getBlockchainStatus(bcs.getBcname());

        }
    }

    public void getBlockchainStatus(String chainName) throws Exception {
        XchainOuterClass.BCStatus bcs = client.getBlockchainStatus(chainName);
        // 打印链名
        System.out.println("blockchain name=" + bcs.getBcname());
        // 链上当前主干高度
        System.out.println("---- Height: " + bcs.getMeta().getTrunkHeight());
        // 链上最新的块ID
        System.out.println("---- TipBlockId: " + Hex.toHexString(bcs.getMeta().getTipBlockid().toByteArray()));
        // XchainOuterClass.InternalBlock block =  client.queryBlock(Hex.toHexString(bcs.getMeta().getTipBlockid().toByteArray()));
        this.queryBlock(Hex.toHexString(bcs.getMeta().getTipBlockid().toByteArray()));
        // 链上创世块ID
        System.out.println("---- RootBlockId: " + Hex.toHexString(bcs.getMeta().getRootBlockid().toByteArray()));
    }
}
