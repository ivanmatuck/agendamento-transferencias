package com.empresa.transferencias.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;
import software.amazon.awssdk.regions.Region;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

import java.util.Properties;

@Configuration
@RequiredArgsConstructor
public class SecretsManagerConfig {

    private final AwsSecretsConfig awsSecretsConfig;

    @Bean
    public void loadDatabaseSecrets(ConfigurableEnvironment environment) {
        try {
            SecretsManagerClient client = SecretsManagerClient.builder()
                    .region(Region.of(awsSecretsConfig.getRegion()))
                    .build();

            GetSecretValueRequest request = GetSecretValueRequest.builder()
                    .secretId(awsSecretsConfig.getSecretName())
                    .build();

            GetSecretValueResponse result = client.getSecretValue(request);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(result.secretString());

            Properties properties = new Properties();
            properties.put("DB_USER", jsonNode.get("username").asText());
            properties.put("DB_PASSWORD", jsonNode.get("password").asText());
            properties.put("DB_HOST", jsonNode.get("host").asText());
            properties.put("DB_PORT", jsonNode.get("port").asText());
            properties.put("DB_NAME", jsonNode.get("dbInstanceIdentifier").asText());

            MutablePropertySources propertySources = environment.getPropertySources();
            propertySources.addFirst(new PropertiesPropertySource("awsSecrets", properties));

            System.out.println("AWS Secrets Loaded: " + result.secretString());

        } catch (Exception e) {
            throw new RuntimeException("Erro ao carregar credenciais do Secrets Manager", e);
        }
    }
}
