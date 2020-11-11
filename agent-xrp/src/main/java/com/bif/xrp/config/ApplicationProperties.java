package com.bif.xrp.config;

import com.bif.config.AbstractProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "application", ignoreUnknownFields = true)
public class ApplicationProperties extends AbstractProperties {

}
