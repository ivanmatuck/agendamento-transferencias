package com.empresa.transferencias;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import com.empresa.transferencias.config.AwsSecretsConfig;

@SpringBootApplication
@EnableConfigurationProperties(AwsSecretsConfig.class)
public class AgendamentoTransferenciasApplication {
	public static void main(String[] args) {
		SpringApplication.run(AgendamentoTransferenciasApplication.class, args);
	}
}
