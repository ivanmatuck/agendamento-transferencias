package com.empresa.transferencias;

import com.empresa.transferencias.config.AwsSecretsConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AwsSecretsConfig.class)
public class AgendamentoTransferenciasApplication {
	public static void main(String[] args) {
		SpringApplication.run(AgendamentoTransferenciasApplication.class, args);
	}
}