
            # Documentação do Projeto: Sistema de Agendamento de Transferências Bancárias

## Visão Geral

O projeto "Sistema de Agendamento de Transferências Bancárias" é uma aplicação desenvolvida em Java utilizando o framework Spring Boot. O objetivo principal é fornecer um sistema robusto e eficiente para o agendamento de transferências financeiras entre contas. Este sistema é projetado para ser escalável e seguro, integrando-se com serviços de gerenciamento de segredos da AWS para proteger informações sensíveis.

## Objetivo do Projeto

O principal objetivo do projeto é facilitar o agendamento de transferências financeiras, permitindo que os usuários programem transferências para datas futuras. O sistema calcula automaticamente as taxas de transferência com base na diferença de dias entre a data de agendamento e a data efetiva da transferência.

## Benefícios para o Negócio

1. **Automação de Processos**: O sistema automatiza o processo de agendamento de transferências, reduzindo a necessidade de intervenção manual e minimizando erros.
   
2. **Cálculo Automático de Taxas**: Com base na data de agendamento e na data de transferência, o sistema calcula automaticamente a taxa aplicável, garantindo precisão e transparência.

3. **Segurança**: Utiliza o AWS Secrets Manager para gerenciar credenciais de banco de dados de forma segura, protegendo informações sensíveis contra acessos não autorizados.

4. **Escalabilidade**: Construído sobre o Spring Boot, o sistema é altamente escalável, podendo ser facilmente integrado a outras aplicações e serviços.

5. **Flexibilidade**: Oferece endpoints RESTful que permitem fácil integração com outras plataformas e serviços, facilitando a expansão e adaptação do sistema a diferentes necessidades de negócios.

## Estrutura do Projeto

### Configurações e Dependências

- **Spring Boot**: Utilizado como o framework principal para a construção do aplicativo, fornecendo uma estrutura robusta para o desenvolvimento de aplicações Java.
- **Spring Data JPA**: Facilita a interação com o banco de dados PostgreSQL, permitindo operações CRUD eficientes.
- **Lombok**: Reduz a verbosidade do código através de anotações que geram automaticamente getters, setters e outros métodos comuns.
- **AWS SDK**: Integrado para acessar o AWS Secrets Manager, garantindo que as credenciais do banco de dados sejam gerenciadas de forma segura.

### Componentes Principais

- **AgendamentoTransferenciasApplication**: Classe principal que inicializa a aplicação Spring Boot.
- **AwsSecretsConfig**: Configuração para acessar segredos armazenados no AWS Secrets Manager.
- **CorsConfig e CorsFilter**: Configurações para permitir requisições CORS, facilitando o desenvolvimento e a integração com front-ends.
- **TransferenciaController**: Controlador REST que expõe endpoints para agendar e listar transferências.
- **TransferenciaService**: Serviço que contém a lógica de negócios para validação e agendamento de transferências.
- **TransferenciaRepository**: Interface de repositório para interagir com o banco de dados.
- **GlobalExceptionHandler**: Manipulador global de exceções que captura e trata erros de validação e exceções inesperadas.

### Segurança e Configuração

- **AWS Secrets Manager**: Utilizado para armazenar e recuperar credenciais do banco de dados de forma segura.
- **Configurações de Banco de Dados**: Utiliza PostgreSQL como banco de dados, com configurações específicas para ambientes de desenvolvimento e produção.

### Testes

- **Testes Unitários**: Implementados para garantir a funcionalidade correta dos serviços e controladores, utilizando frameworks de teste como JUnit e Mockito.

## Conclusão

O "Sistema de Agendamento de Transferências Bancárias" é uma solução completa para o gerenciamento de transferências financeiras, oferecendo automação, segurança e flexibilidade. Com sua arquitetura baseada em microserviços e integração com AWS, o sistema está preparado para atender às demandas atuais e futuras do negócio.
            
            ## Arquitetura
            
            Sistema de Agendamento de Transferências - Java

```
├── Aplicação Principal
│   └── AgendamentoTransferenciasApplication.java
├── Configuração
│   ├── AwsSecretsConfig.java
│   ├── CorsConfig.java
│   ├── CorsFilter.java
│   └── SecretsManagerConfig.java
├── Controlador
│   └── TransferenciaController.java
├── Exceção
│   └── GlobalExceptionHandler.java
├── Modelo
│   └── Transferencia.java
├── Repositório
│   └── TransferenciaRepository.java
├── Serviço
│   └── TransferenciaService.java
├── Recursos
│   ├── application-dev.properties
│   └── application.properties
└── Testes
    ├── AgendamentoTransferenciasApplicationTests.java
    ├── controller
    │   └── TransferenciaControllerTest.java
    └── service
        └── TransferenciaServiceTest.java
```

### Detalhamento da Arquitetura do Sistema

- **Aplicação Principal**: 
  - `AgendamentoTransferenciasApplication.java`: Classe principal que inicia a aplicação Spring Boot.

- **Configuração**: 
  - `AwsSecretsConfig.java`: Configurações para acessar o AWS Secrets Manager.
  - `CorsConfig.java`: Configurações de CORS para o ambiente de desenvolvimento.
  - `CorsFilter.java`: Filtro de CORS para gerenciar requisições HTTP.
  - `SecretsManagerConfig.java`: Classe para gerenciar o acesso aos segredos armazenados no AWS Secrets Manager.

- **Controlador**: 
  - `TransferenciaController.java`: Controlador REST que expõe endpoints para agendar e listar transferências.

- **Exceção**: 
  - `GlobalExceptionHandler.java`: Tratamento global de exceções, capturando erros de validação e exceções inesperadas.

- **Modelo**: 
  - `Transferencia.java`: Entidade que representa uma transferência financeira, com validações de dados.

- **Repositório**: 
  - `TransferenciaRepository.java`: Interface de repositório para interagir com o banco de dados usando Spring Data JPA.

- **Serviço**: 
  - `TransferenciaService.java`: Lógica de negócios para agendamento de transferências, incluindo validações e cálculo de taxas.

- **Recursos**: 
  - `application-dev.properties`: Configurações específicas para o ambiente de desenvolvimento.
  - `application.properties`: Configurações gerais da aplicação, incluindo banco de dados e AWS.

- **Testes**: 
  - `AgendamentoTransferenciasApplicationTests.java`: Testes de contexto da aplicação.
  - `TransferenciaControllerTest.java`: Testes unitários para o controlador de transferências.
  - `TransferenciaServiceTest.java`: Testes unitários para o serviço de transferências.
            
            
            ## Endpoints
            
            ### Detalhamento dos Endpoints

#### Endpoint: Agendar Transferência

- **Método HTTP:** POST
- **Caminho:** `/api/transferencias`

##### Entradas:
- **Corpo da Requisição:** Um objeto JSON representando uma transferência, contendo os seguintes campos obrigatórios:
  - `contaOrigem`: String de exatamente 6 caracteres.
  - `contaDestino`: String de exatamente 6 caracteres.
  - `valorTransferencia`: Decimal maior que 0.
  - `dataTransferencia`: Data no futuro no formato ISO-8601 (yyyy-MM-dd).

##### Processamento Interno e Validações:
1. **Validação dos Dados:** 
   - Verifica se `contaOrigem` e `contaDestino` são diferentes.
   - Verifica se `valorTransferencia` é maior que zero.
2. **Cálculo da Taxa:** 
   - Calcula a diferença de dias entre a data atual e `dataTransferencia`.
   - Aplica regras de cálculo de taxa baseadas na diferença de dias.
3. **Persistência:** 
   - Define a `dataAgendamento` como a data atual.
   - Salva a transferência no banco de dados usando `TransferenciaRepository`.

##### Saídas:
- **Resposta de Sucesso (200 OK):** 
  - Corpo: JSON contendo uma mensagem de sucesso e os dados da transferência agendada.
- **Resposta de Erro de Validação (400 Bad Request):** 
  - Corpo: JSON com detalhes do erro de validação.
- **Resposta de Erro Interno (500 Internal Server Error):** 
  - Corpo: JSON com mensagem de erro genérica.

#### Endpoint: Listar Transferências

- **Método HTTP:** GET
- **Caminho:** `/api/transferencias`

##### Entradas:
- **Nenhuma entrada específica.**

##### Processamento Interno e Validações:
1. **Recuperação de Dados:** 
   - Busca todas as transferências cadastradas no banco de dados usando `TransferenciaRepository`.

##### Saídas:
- **Resposta de Sucesso (200 OK):** 
  - Corpo: JSON contendo a quantidade e a lista de todas as transferências.
- **Resposta de Erro Interno (500 Internal Server Error):** 
  - Corpo: JSON com mensagem de erro genérica.

#### Endpoint: Buscar Transferências por Data

- **Método HTTP:** GET
- **Caminho:** `/api/transferencias/data`

##### Entradas:
- **Parâmetro de Consulta:** 
  - `data`: String representando a data de transferência no formato ISO-8601 (yyyy-MM-dd).

##### Processamento Interno e Validações:
1. **Conversão de Data:** 
   - Converte o parâmetro `data` para um objeto `LocalDate`.
2. **Recuperação de Dados:** 
   - Busca transferências agendadas para a data especificada usando `TransferenciaRepository`.

##### Saídas:
- **Resposta de Sucesso (200 OK):** 
  - Corpo: JSON contendo a lista de transferências agendadas para a data especificada.
- **Resposta de Erro de Validação (400 Bad Request):** 
  - Corpo: JSON com mensagem de erro se a data for inválida.
- **Resposta de Erro Interno (500 Internal Server Error):** 
  - Corpo: JSON com mensagem de erro genérica.
            
            ## Models
            
            ### Models

#### Transferencia

A entidade `Transferencia` representa uma transferência financeira e está mapeada para a tabela `transferencias` no banco de dados. A estrutura da tabela e suas colunas são as seguintes:

- **id**: `UUID`
  - Tipo: `UUID`
  - Estratégia de geração: `AUTO`
  - Descrição: Identificador único da transferência.
  
- **conta_origem**: `String`
  - Tipo: `VARCHAR(6)`
  - Restrições: Não nulo, tamanho fixo de 6 caracteres.
  - Descrição: Conta de origem da transferência.

- **conta_destino**: `String`
  - Tipo: `VARCHAR(6)`
  - Restrições: Não nulo, tamanho fixo de 6 caracteres.
  - Descrição: Conta de destino da transferência.

- **valor_transferencia**: `BigDecimal`
  - Tipo: `NUMERIC`
  - Restrições: Não nulo, valor mínimo de 0.01.
  - Descrição: Valor da transferência.

- **taxa**: `BigDecimal`
  - Tipo: `NUMERIC`
  - Restrições: Não nulo.
  - Descrição: Taxa aplicada à transferência.

- **data_transferencia**: `LocalDate`
  - Tipo: `DATE`
  - Restrições: Não nulo, deve ser uma data futura.
  - Descrição: Data em que a transferência está agendada para ocorrer.

- **data_agendamento**: `LocalDate`
  - Tipo: `DATE`
  - Restrições: Não nulo.
  - Descrição: Data em que a transferência foi agendada.

#### Relacionamentos

Atualmente, a entidade `Transferencia` não possui relacionamentos explícitos com outras tabelas ou entidades no projeto. A tabela `transferencias` é independente e armazena informações sobre cada transferência financeira de forma isolada.
            
            ## Configurações
            
            ### Configurações do Projeto

#### Packages Utilizados

O projeto está estruturado em diversos pacotes, cada um com responsabilidades específicas:

- **com.empresa.transferencias**: Contém a classe principal `AgendamentoTransferenciasApplication` que inicia o aplicativo Spring Boot.
- **com.empresa.transferencias.config**: Inclui classes de configuração como `AwsSecretsConfig`, `CorsConfig`, `CorsFilter`, e `SecretsManagerConfig`.
- **com.empresa.transferencias.controller**: Abriga o controlador REST `TransferenciaController` para gerenciar endpoints de transferências.
- **com.empresa.transferencias.exception**: Contém a classe `GlobalExceptionHandler` para tratamento global de exceções.
- **com.empresa.transferencias.model**: Define a entidade `Transferencia` que representa uma transferência financeira.
- **com.empresa.transferencias.repository**: Inclui a interface `TransferenciaRepository` que estende `JpaRepository` para operações de banco de dados.
- **com.empresa.transferencias.service**: Contém a classe `TransferenciaService` que implementa a lógica de negócios para transferências.

#### Dependências Específicas

O projeto utiliza o Maven para gerenciamento de dependências. As principais dependências são:

- **Spring Boot**: 
  - `spring-boot-starter-web`: Para construir aplicações web RESTful.
  - `spring-boot-starter-data-jpa`: Para integração com JPA e banco de dados.
  - `spring-boot-starter-validation`: Para validação de dados.
  - `spring-boot-starter-test`: Para testes unitários e de integração.

- **Banco de Dados**:
  - `org.postgresql:postgresql:42.7.3`: Driver para conexão com PostgreSQL.

- **AWS SDK**:
  - `software.amazon.awssdk:secretsmanager:2.30.17`: Para integração com AWS Secrets Manager.
  - `software.amazon.awssdk:sts:2.30.17`: Para integração com AWS Security Token Service (apenas para testes).

- **Lombok**:
  - `org.projectlombok:lombok:1.18.24`: Para reduzir a verbosidade do código com anotações como `@Getter`, `@Setter`, e `@Data`.

#### Instruções para Inicialização Local

Para executar o projeto localmente, siga as instruções abaixo:

1. **Pré-requisitos**:
   - Certifique-se de ter o JDK 11 instalado.
   - Instale o Maven para gerenciamento de dependências e construção do projeto.
   - Configure um banco de dados PostgreSQL acessível.

2. **Configuração do Banco de Dados**:
   - Configure as variáveis de ambiente `DB_HOST`, `DB_PORT`, `DB_NAME`, `DB_USER`, e `DB_PASSWORD` com as credenciais do seu banco de dados PostgreSQL.
   - Alternativamente, edite o arquivo `src/main/resources/application-dev.properties` com as informações do banco de dados.

3. **Configuração do AWS Secrets Manager**:
   - Configure as variáveis de ambiente `aws.secretsmanager.secretName` e `aws.secretsmanager.region` conforme necessário, ou edite diretamente no arquivo `src/main/resources/application.properties`.

4. **Executar o Projeto**:
   - Navegue até o diretório do projeto: `cd C:\Particular\Testes_Vagas_Empregos\Sistema_Transferencias_Bancarias\agendamento-transferencias`.
   - Execute o comando Maven para iniciar a aplicação: `mvn spring-boot:run`.
   - A aplicação estará disponível na porta configurada (por padrão, 8080).

5. **Testes**:
   - Para executar os testes, utilize o comando: `mvn test`.

Essas configurações devem permitir que você execute e teste o projeto localmente. Certifique-se de ajustar as configurações conforme necessário para o seu ambiente específico.
            