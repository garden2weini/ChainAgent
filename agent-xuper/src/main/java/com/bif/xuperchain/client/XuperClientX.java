package com.bif.xuperchain.client;

import com.baidu.xuperunion.api.Common;
import com.baidu.xuperunion.api.XuperClient;
import com.baidu.xuperunion.pb.XchainGrpc;
import com.baidu.xuperunion.pb.XchainOuterClass;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.List;

/**
 * https://xuperchain.readthedocs.io/zh/latest/commands_reference.html
 */
public class XuperClientX extends XuperClient {
    private final ManagedChannel channel2;
    private final XchainGrpc.XchainBlockingStub blockingClient2;
    private String chainName2 = "xuper";

    /**
     * @param target the address of xchain node, like 127.0.0.1:37101
     */
    public XuperClientX(String target) {
        super(target);
        this.channel2 = ManagedChannelBuilder.forTarget(target).usePlaintext().build();
        blockingClient2 = XchainGrpc.newBlockingStub(this.channel2);
    }

    /**
     * rpc GetAccountByAK(AK2AccountRequest) returns (AK2AccountResponse) 查询包含指定地址的所有合约账号
     * @param address
     */
    public List<String> getAccountByAK(String address) {
        XchainOuterClass.AK2AccountRequest request = XchainOuterClass.AK2AccountRequest.newBuilder()
                .setHeader(Common.newHeader())
                .setBcname(chainName2)
                .setAddress(address)
                .build();
        XchainOuterClass.AK2AccountResponse response = blockingClient2.getAccountByAK(request);
        System.out.println("ErrorValue:" + response.getHeader().getErrorValue());
        List<String> list = response.getAccountList();

        return list;
    }

    /**
     * rpc GetAccountContracts(GetAccountContractsRequest) returns (GetAccountContractsResponse) 获取合约账号下部署的智能合约
     * @param address
     * @return
     */
    public List<XchainOuterClass.ContractStatus> getAccountContracts(String address) {
        XchainOuterClass.GetAccountContractsRequest request = XchainOuterClass.GetAccountContractsRequest.newBuilder()
                .setHeader(Common.newHeader())
                .setAccount(address)
                .setBcname(chainName2)
                .build();
        XchainOuterClass.GetAccountContractsResponse response = blockingClient2.getAccountContracts(request);
        List<XchainOuterClass.ContractStatus> list = response.getContractsStatusList();
        return list;
    }

    /**
     * rpc QueryACL(AclStatus) returns (AclStatus) 查询合约账号/合约方法的Acl
     * @param accountName
     * @return
     */
    public XchainOuterClass.Acl queryACL(String accountName) {
        XchainOuterClass.AclStatus request = XchainOuterClass.AclStatus.newBuilder()
                .setHeader(Common.newHeader())
                .setAccountName(accountName)
                .setBcname(chainName2)
                .build();
        XchainOuterClass.AclStatus response = blockingClient2.queryACL(request);
        return response.getAcl();
    }

}
