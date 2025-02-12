package com.empresa.transferencias.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import lombok.Getter;
import lombok.Setter;

@Configuration
@ConfigurationProperties(prefix = "aws.secretsmanager")
@Getter
@Setter
public class AwsSecretsConfig {
    private String region;
    private String secretName;
}
