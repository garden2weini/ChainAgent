package com.bif.bcos.autoconfigure;

import com.bif.bcos.constants.ConnectConstants;
import org.fisco.bcos.channel.client.Service;

import org.fisco.bcos.channel.handler.ChannelConnections;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.channel.ChannelEthereumService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class Web3jConfig {

    @Bean
    public Web3j getWeb3j(Service service) throws Exception {
        //读取配置文件，SDK与区块链节点建立连接
        ChannelEthereumService channelEthereumService = new ChannelEthereumService();
        /*
        List<ChannelConnections> connsList = channelEthereumService.getChannelService().getAllChannelConnections().getAllChannelConnections();
        for (ChannelConnections conns: connsList) {
            int groupId = conns.getGroupId();
            System.out.println("GroupID:" + groupId);
            List<String> connStrs = conns.getConnectionsStr();
            for (String connsStr: connStrs) {
                System.out.println("Connection Str:" + connsStr);
            }
        }
        */

        service.run();
        channelEthereumService.setChannelService(service);
        channelEthereumService.setTimeout(ConnectConstants.TIME_OUT);
        return Web3j.build(channelEthereumService, service.getGroupId());
    }
}
