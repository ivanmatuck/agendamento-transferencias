
            # Documentação do Sistema de Agendamento de Transferências Bancárias

## Visão Geral do Projeto

O Sistema de Agendamento de Transferências Bancárias é uma aplicação desenvolvida em Java, utilizando o framework Spring Boot, com o objetivo de gerenciar transferências financeiras de forma eficiente e segura. Este sistema permite que os usuários agendem transferências entre contas bancárias, especificando a conta de origem, a conta de destino, o valor a ser transferido e a data da transferência. Além disso, o sistema calcula automaticamente a taxa de transferência com base na diferença de dias entre a data de agendamento e a data da transferência.

## Objetivo do Projeto

O principal objetivo deste projeto é fornecer uma solução robusta e escalável para o agendamento de transferências bancárias, permitindo que instituições financeiras e seus clientes realizem operações financeiras de forma planejada e com controle sobre as taxas aplicadas. O sistema é projetado para ser integrado a sistemas bancários existentes, oferecendo uma interface RESTful para facilitar a comunicação e a troca de dados.

## Benefícios para o Negócio

1. **Automação de Processos**: O sistema automatiza o cálculo de taxas e o agendamento de transferências, reduzindo a necessidade de intervenção manual e minimizando erros.

2. **Flexibilidade e Escalabilidade**: Desenvolvido com tecnologias modernas, o sistema é altamente escalável e pode ser facilmente adaptado para atender a diferentes volumes de transações e requisitos de negócios.

3. **Segurança**: Utiliza o AWS Secrets Manager para gerenciar credenciais de banco de dados de forma segura, garantindo que informações sensíveis estejam protegidas.

4. **Integração Simples**: A arquitetura baseada em serviços RESTful permite fácil integração com outros sistemas bancários e financeiros.

5. **Transparência e Controle**: Oferece funcionalidades de auditoria e logging detalhado, permitindo que as instituições financeiras monitorem e auditem todas as operações realizadas.

## Estrutura do Projeto

### Dependências

O projeto utiliza várias bibliotecas e frameworks para suportar suas funcionalidades:

- **Spring Boot**: Framework principal para desenvolvimento da aplicação.
- **Spring Data JPA**: Para interações com o banco de dados PostgreSQL.
- **Lombok**: Para simplificar a escrita de código Java, reduzindo boilerplate.
- **AWS SDK**: Para integração com o AWS Secrets Manager.
- **JUnit e Mockito**: Para testes unitários e de integração.

### Configurações de Segurança

O sistema utiliza o AWS Secrets Manager para gerenciar credenciais de banco de dados, garantindo que senhas e informações sensíveis não estejam expostas no código fonte. As credenciais são recuperadas dinamicamente em tempo de execução.

### Funcionalidades Principais

- **Agendamento de Transferências**: Permite que os usuários agendem transferências especificando detalhes como contas de origem e destino, valor e data.
- **Cálculo de Taxas**: Calcula automaticamente a taxa de transferência com base na diferença de dias entre o agendamento e a data da transferência.
- **Listagem de Transferências**: Oferece endpoints para listar todas as transferências agendadas ou buscar transferências por data específica.
- **Tratamento de Exceções**: Implementa um manipulador global de exceções para capturar e tratar erros de validação e exceções inesperadas de forma amigável.

### Arquitetura

O projeto segue uma arquitetura baseada em camadas:

- **Camada de Apresentação**: Implementada com controladores REST que expõem endpoints para interação com o sistema.
- **Camada de Serviço**: Contém a lógica de negócios para validação e processamento de transferências.
- **Camada de Persistência**: Utiliza Spring Data JPA para interagir com o banco de dados PostgreSQL.
- **Configurações**: Inclui configurações para CORS, segurança e integração com AWS Secrets Manager.

### Testes

O projeto inclui testes unitários e de integração para garantir a qualidade e a confiabilidade do código. Os testes verificam a funcionalidade dos controladores, serviços e repositórios, assegurando que o sistema se comporte conforme o esperado em diferentes cenários.

## Conclusão

O Sistema de Agendamento de Transferências Bancárias é uma solução completa para o gerenciamento de transferências financeiras, oferecendo automação, segurança e flexibilidade para instituições financeiras. Com sua arquitetura moderna e integração com serviços de nuvem, o sistema está preparado para atender às necessidades atuais e futuras do mercado financeiro.
            
            ## Arquitetura
            
            Sistema de Agendamento de Transferências Bancárias - Java

```
├── Camada de Aplicação
│   ├── AgendamentoTransferenciasApplication.java
│   └── AgendamentoTransferenciasApplicationTests.java
├── Camada de Configuração
│   ├── AwsSecretsConfig.java
│   ├── CorsConfig.java
│   ├── CorsFilter.java
│   └── SecretsManagerConfig.java
├── Camada de Controle
│   ├── TransferenciaController.java
│   └── TransferenciaControllerTest.java
├── Camada de Exceção
│   └── GlobalExceptionHandler.java
├── Camada de Modelo
│   └── Transferencia.java
├── Camada de Repositório
│   └── TransferenciaRepository.java
├── Camada de Serviço
│   ├── TransferenciaService.java
│   └── TransferenciaServiceTest.java
└── Camada de Recursos
    ├── application-dev.properties
    └── application.properties
```

**Descrição das Camadas:**

- **Camada de Aplicação:** Contém a classe principal que inicia a aplicação Spring Boot e os testes de contexto da aplicação.

- **Camada de Configuração:** Define configurações específicas da aplicação, como configurações de CORS, integração com AWS Secrets Manager, e outras propriedades de configuração.

- **Camada de Controle:** Implementa os controladores REST que expõem os endpoints para operações relacionadas a transferências financeiras. Inclui testes para os controladores.

- **Camada de Exceção:** Gerencia o tratamento global de exceções, fornecendo respostas adequadas para erros de validação e exceções inesperadas.

- **Camada de Modelo:** Define as entidades de domínio, neste caso, a entidade `Transferencia`, que representa uma transferência financeira.

- **Camada de Repositório:** Fornece interfaces para interagir com o banco de dados, permitindo operações CRUD na entidade `Transferencia`.

- **Camada de Serviço:** Implementa a lógica de negócios para o agendamento de transferências, incluindo validações e cálculos de taxas. Inclui testes para os serviços.

- **Camada de Recursos:** Contém arquivos de configuração da aplicação, como propriedades de conexão com o banco de dados e configurações de ambiente.
            
            
            ## Endpoints
            
            ### Detalhamento dos Endpoints do Projeto de Agendamento de Transferências

#### Endpoint: Agendar Transferência

- **Caminho**: `/api/transferencias`
- **Método HTTP**: `POST`
- **Entradas**:
  - Um objeto JSON representando uma transferência, contendo:
    - `contaOrigem`: String (6 caracteres)
    - `contaDestino`: String (6 caracteres)
    - `valorTransferencia`: Decimal (maior que 0)
    - `dataTransferencia`: Data no futuro (formato ISO-8601)
- **Processamento Interno e Validações**:
  1. O controlador `TransferenciaController` recebe a requisição e valida os dados usando anotações de validação.
  2. O método `agendarTransferencia` do `TransferenciaService` é chamado.
  3. No serviço, a transferência é validada:
     - As contas de origem e destino não podem ser iguais.
     - O valor da transferência deve ser maior que zero.
  4. A data de agendamento é definida como a data atual.
  5. A diferença de dias entre a data de agendamento e a data de transferência é calculada.
  6. A taxa é calculada com base na diferença de dias:
     - 0 dias: 2.5% do valor + R$3,00
     - 1-10 dias: R$12,00
     - 11-20 dias: 8.2% do valor
     - 21-30 dias: 6.9% do valor
     - 31-40 dias: 4.7% do valor
     - 41-50 dias: 1.7% do valor
     - Mais de 50 dias: não permitido
  7. A transferência é salva no banco de dados.
- **Saídas**:
  - Em caso de sucesso: `ResponseEntity` com status 200 e um JSON contendo a mensagem de sucesso e os dados da transferência agendada.
  - Em caso de erro de validação: `ResponseEntity` com status 400 e um JSON detalhando o erro.
  - Em caso de erro interno: `ResponseEntity` com status 500 e uma mensagem de erro genérica.

#### Endpoint: Listar Transferências

- **Caminho**: `/api/transferencias`
- **Método HTTP**: `GET`
- **Entradas**: Nenhuma
- **Processamento Interno e Validações**:
  1. O controlador `TransferenciaController` recebe a requisição.
  2. O método `listarTransferencias` do `TransferenciaService` é chamado.
  3. O serviço busca todas as transferências no repositório.
- **Saídas**:
  - Em caso de sucesso: `ResponseEntity` com status 200 e um JSON contendo a quantidade e a lista de transferências.
  - Em caso de erro interno: `ResponseEntity` com status 500 e uma mensagem de erro genérica.

#### Endpoint: Buscar Transferências por Data

- **Caminho**: `/api/transferencias/data`
- **Método HTTP**: `GET`
- **Entradas**:
  - Parâmetro de consulta `data`: String representando a data (formato ISO-8601)
- **Processamento Interno e Validações**:
  1. O controlador `TransferenciaController` recebe a requisição.
  2. A data é convertida de String para `LocalDate`.
  3. O método `buscarPorDataTransferencia` do `TransferenciaService` é chamado com a data convertida.
  4. O serviço busca transferências agendadas para a data especificada no repositório.
- **Saídas**:
  - Em caso de sucesso: `ResponseEntity` com status 200 e uma lista de transferências agendadas para a data especificada.
  - Em caso de erro de validação (ex: data inválida): `ResponseEntity` com status 400.
  - Em caso de erro interno: `ResponseEntity` com status 500 e uma mensagem de erro genérica.
            
            ## Models
            
            ### Models

#### Transferencia

A tabela `transferencias` é representada pela classe `Transferencia` e possui a seguinte estrutura:

- **id**: `UUID`
  - Tipo: `UUID`
  - Anotação: `@Id`, `@GeneratedValue(strategy = GenerationType.AUTO)`
  - Descrição: Identificador único para cada transferência.

- **contaOrigem**: `String`
  - Tipo: `VARCHAR(6)`
  - Anotação: `@NotNull`, `@Size(min = 6, max = 6)`
  - Descrição: Conta de origem da transferência, deve ter exatamente 6 caracteres.

- **contaDestino**: `String`
  - Tipo: `VARCHAR(6)`
  - Anotação: `@NotNull`, `@Size(min = 6, max = 6)`
  - Descrição: Conta de destino da transferência, deve ter exatamente 6 caracteres.

- **valorTransferencia**: `BigDecimal`
  - Tipo: `DECIMAL`
  - Anotação: `@NotNull`, `@DecimalMin(value = "0.01")`
  - Descrição: Valor da transferência, deve ser maior que 0.

- **taxa**: `BigDecimal`
  - Tipo: `DECIMAL`
  - Anotação: `@Column(nullable = false)`
  - Descrição: Taxa aplicada à transferência.

- **dataTransferencia**: `LocalDate`
  - Tipo: `DATE`
  - Anotação: `@NotNull`, `@Future`
  - Descrição: Data em que a transferência está agendada para ocorrer, deve ser uma data futura.

- **dataAgendamento**: `LocalDate`
  - Tipo: `DATE`
  - Anotação: `@Column(nullable = false)`
  - Descrição: Data em que a transferência foi agendada.

#### Relacionamentos

Atualmente, a estrutura apresentada não define relacionamentos explícitos com outras tabelas, pois apenas a tabela `transferencias` foi detalhada. Não há menção de chaves estrangeiras ou outras entidades que se relacionem com `Transferencia` no código fornecido.
            
            ## Configurações
            
            ### Configurações do Projeto

#### Packages Utilizados
O projeto está organizado em vários pacotes, cada um com uma responsabilidade específica:

- **com.empresa.transferencias**: Contém a classe principal `AgendamentoTransferenciasApplication` que inicia a aplicação.
- **com.empresa.transferencias.config**: Inclui classes de configuração, como `AwsSecretsConfig`, `CorsConfig`, `CorsFilter`, e `SecretsManagerConfig`.
- **com.empresa.transferencias.controller**: Contém o controlador REST `TransferenciaController` responsável por expor endpoints para agendamento e listagem de transferências.
- **com.empresa.transferencias.exception**: Inclui o `GlobalExceptionHandler` para tratamento global de exceções.
- **com.empresa.transferencias.model**: Define a entidade `Transferencia` que representa uma transferência financeira.
- **com.empresa.transferencias.repository**: Contém o repositório `TransferenciaRepository` que interage com o banco de dados.
- **com.empresa.transferencias.service**: Inclui o serviço `TransferenciaService` que contém a lógica de negócios para agendamento de transferências.

#### Dependências Específicas
O projeto utiliza as seguintes dependências, conforme definido no arquivo `pom.xml`:

- **Spring Boot**: 
  - `spring-boot-starter-web`: Para construir aplicações web RESTful.
  - `spring-boot-starter-data-jpa`: Para integração com JPA e bancos de dados.
  - `spring-boot-starter-validation`: Para validação de dados.
  - `spring-boot-starter-test`: Para suporte a testes.
  - `spring-boot-configuration-processor`: Para processamento de configurações.
  
- **Banco de Dados**:
  - `org.postgresql:postgresql:42.7.3`: Driver JDBC para PostgreSQL.

- **AWS SDK**:
  - `software.amazon.awssdk:secretsmanager:2.30.17`: Para acessar o AWS Secrets Manager.
  - `software.amazon.awssdk:sts:2.30.17`: Para suporte a testes.

- **Lombok**:
  - `org.projectlombok:lombok:1.18.24`: Para reduzir o código boilerplate.

#### Instruções para Inicialização Local
Para executar o projeto localmente, siga os passos abaixo:

1. **Pré-requisitos**:
   - Certifique-se de ter o Java 11 instalado.
   - Instale o Maven para gerenciar as dependências do projeto.
   - Configure o PostgreSQL localmente ou tenha acesso a um banco de dados PostgreSQL.

2. **Configuração do Banco de Dados**:
   - Atualize as propriedades do banco de dados no arquivo `src/main/resources/application-dev.properties` com as credenciais corretas do seu ambiente de desenvolvimento.

3. **Perfil de Desenvolvimento**:
   - Certifique-se de que o perfil de desenvolvimento está ativo. No arquivo `src/main/resources/application.properties`, descomente a linha `spring.profiles.active=dev` se necessário.

4. **Executar a Aplicação**:
   - Navegue até o diretório raiz do projeto.
   - Execute o comando `mvn spring-boot:run` para iniciar a aplicação.

5. **Acesso à Aplicação**:
   - A aplicação estará disponível na porta `8080` por padrão. Acesse `http://localhost:8080` para interagir com os endpoints REST expostos pelo `TransferenciaController`.

6. **Testes**:
   - Para executar os testes, utilize o comando `mvn test`.

Certifique-se de que todas as dependências estão corretamente configuradas e que o banco de dados está acessível para evitar erros durante a execução.
            