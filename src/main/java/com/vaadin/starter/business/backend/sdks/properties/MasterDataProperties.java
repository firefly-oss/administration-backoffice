package com.vaadin.starter.business.backend.sdks.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration properties.
 * Maps the properties defined in application.yaml under api-configuration.
 */
@Configuration
@ConfigurationProperties(prefix = "api-configuration.master-data")
@Getter
@Setter
public class MasterDataProperties {

    private String basePath;

}