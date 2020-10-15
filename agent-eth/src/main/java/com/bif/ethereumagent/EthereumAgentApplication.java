package com.bif.ethereumagent;

import com.bif.ethereumagent.config.ApplicationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@EnableConfigurationProperties({ApplicationProperties.class})
public class EthereumAgentApplication {

    public static void main(String[] args) {
        SpringApplication.run(EthereumAgentApplication.class, args);
    }

}
