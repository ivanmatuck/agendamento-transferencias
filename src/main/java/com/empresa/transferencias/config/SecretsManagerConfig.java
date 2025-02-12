package com.empresa.transferencias.config;

import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;
import software.amazon.awssdk.regions.Region;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class SecretsManagerConfig {

    private final AwsSecretsConfig awsSecretsConfig;

    public Map<String, String> loadDatabaseSecrets() {
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

            return Map.of(
                    "DB_USER", jsonNode.get("username").asText(),
                    "DB_PASSWORD", jsonNode.get("password").asText(),
                    "DB_HOST", jsonNode.get("host").asText(),
                    "DB_PORT", jsonNode.get("port").asText(),
                    "DB_NAME", jsonNode.get("dbInstanceIdentifier").asText()
            );
        } catch (Exception e) {
            throw new RuntimeException("Erro ao carregar credenciais do Secrets Manager", e);
        }
    }
}
