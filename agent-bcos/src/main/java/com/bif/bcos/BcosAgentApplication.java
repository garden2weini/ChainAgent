package com.bif.bcos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 注意：推荐使用OracleJDK！！！
 * FISCO BCOS作为联盟链，其SDK连接区块链节点需要通过证书(ca.crt、sdk.crt)和私钥(sdk.key)进行双向认证。
 * 因此需要将节点所在目录nodes/${ip}/sdk下的ca.crt、sdk.crt和sdk.key文件拷贝到项目的资源目录，供SDK与节点建立连接时使用。
 * （低于2.1版本的FISCO BCOS节点目录下只有node.crt和node.key，需将其重命名为sdk.crt和sdk.key以兼容最新的SDK）
 * REF：https://github.com/FISCO-BCOS/spring-boot-starter
 */
@EnableScheduling
@SpringBootApplication
@EnableConfigurationProperties
public class BcosAgentApplication {
    //public static Service service = null;

    public static void main(String[] args) {
        //ApplicationContext applicationContext = SpringApplication.run(BcosAgentApplication.class, args);
        SpringApplication.run(BcosAgentApplication.class, args);
    }

}
