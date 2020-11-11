package com.bif.xrp;

import com.bif.xrp.config.ApplicationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@EnableConfigurationProperties({ApplicationProperties.class})
public class OntologyAgentApplication {
    public static void main(String[] args) {
        SpringApplication.run(OntologyAgentApplication.class, args);
    }

}
