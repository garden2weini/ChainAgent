package com.bif.ontology;

import com.bif.ontology.config.ApplicationProperties;
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
