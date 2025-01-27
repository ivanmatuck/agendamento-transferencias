# Documentação do Projeto: Sistema de Agendamento de Transferências Financeiras

## Visão Geral

O projeto "Sistema de Agendamento de Transferências Financeiras" é uma aplicação desenvolvida em Java utilizando o framework Spring Boot. O objetivo principal deste sistema é permitir o agendamento de transferências financeiras entre contas, oferecendo uma interface RESTful para que os usuários possam agendar, listar e buscar transferências por data.

## Objetivo do Projeto

O sistema foi projetado para automatizar o processo de agendamento de transferências financeiras, proporcionando uma maneira eficiente e segura de gerenciar transações entre contas. A aplicação é capaz de calcular automaticamente as taxas de transferência com base na diferença de dias entre a data de agendamento e a data da transferência.

## Benefícios para o Negócio

1. **Automação de Processos**: Reduz a necessidade de intervenção manual, diminuindo erros e aumentando a eficiência operacional.
2. **Flexibilidade**: Permite aos usuários agendar transferências para datas futuras, facilitando o planejamento financeiro.
3. **Cálculo Automático de Taxas**: As taxas de transferência são calculadas automaticamente, garantindo precisão e conformidade com as políticas de cobrança.
4. **Segurança e Confiabilidade**: Utiliza validações rigorosas e tratamento de exceções para garantir a integridade dos dados e a segurança das operações.
5. **Escalabilidade**: Construído sobre o Spring Boot, o sistema pode ser facilmente escalado para atender a um número crescente de usuários e transações.

## Estrutura do Projeto

### Configuração do Projeto

- **Maven**: O projeto utiliza o Maven para gerenciamento de dependências e construção. As principais dependências incluem Spring Boot, Spring Data JPA, H2 Database, e Lombok.
- **Java 11**: A aplicação é desenvolvida usando a versão 11 do Java.

### Componentes Principais

1. **AgendamentoTransferenciasApplication**: Classe principal que inicializa a aplicação Spring Boot.

2. **Configurações de CORS**:
    - `CorsConfig` e `CorsFilter` configuram o CORS para permitir requisições de origens específicas, essencial para o ambiente de desenvolvimento.

3. **Controladores REST**:
    - `TransferenciaController`: Exponibiliza endpoints para agendar, listar e buscar transferências por data.

4. **Modelo de Dados**:
    - `Transferencia`: Entidade JPA que representa uma transferência financeira, com atributos validados para garantir consistência.

5. **Repositório**:
    - `TransferenciaRepository`: Interface que estende `JpaRepository` para interagir com o banco de dados.

6. **Serviço**:
    - `TransferenciaService`: Contém a lógica de negócios para agendamento e listagem de transferências, incluindo o cálculo de taxas.

7. **Tratamento de Exceções**:
    - `GlobalExceptionHandler`: Captura e trata exceções de validação e erros inesperados, retornando mensagens amigáveis ao cliente.

### Configurações de Ambiente

- **Banco de Dados H2**: Utilizado em modo memória para facilitar o desenvolvimento e testes.
- **Perfis de Aplicação**: Configurações específicas para o ambiente de desenvolvimento são definidas em `application-dev.properties`.

### Testes

- **Testes de Unidade**: Testes para `TransferenciaController` e `TransferenciaService` garantem que a lógica de negócios e os endpoints funcionem conforme o esperado.

## Conclusão

O "Sistema de Agendamento de Transferências Financeiras" oferece uma solução robusta e escalável para o gerenciamento de transferências financeiras, com foco em automação, segurança e eficiência.

## Arquitetura

Sistema de Agendamento de Transferências - Java

```
Sistema de Agendamento de Transferências - Java
├── Camada de Aplicação
│   ├── AgendamentoTransferenciasApplication.java
├── Camada de Configuração
│   ├── CorsConfig.java
│   └── CorsFilter.java
├── Camada de Controle
│   └── TransferenciaController.java
├── Camada de Exceção
│   └── GlobalExceptionHandler.java
├── Camada de Modelo
│   └── Transferencia.java
├── Camada de Repositório
│   └── TransferenciaRepository.java
├── Camada de Serviço
│   └── TransferenciaService.java
├── Camada de Recursos
│   ├── application-dev.properties
│   └── application.properties
└── Camada de Testes
    ├── AgendamentoTransferenciasApplicationTests.java
    ├── TransferenciaControllerTest.java
    └── TransferenciaServiceTest.java
```

**Camada de Aplicação:**
- `AgendamentoTransferenciasApplication.java`: Classe principal que inicia a aplicação Spring Boot.

**Camada de Configuração:**
- `CorsConfig.java`: Configurações de CORS para permitir requisições de origens específicas durante o desenvolvimento.
- `CorsFilter.java`: Filtro para gerenciar requisições CORS, permitindo ou bloqueando acessos baseados na origem.

**Camada de Controle:**
- `TransferenciaController.java`: Controlador REST que expõe endpoints para agendar e listar transferências financeiras.

**Camada de Exceção:**
- `GlobalExceptionHandler.java`: Trata exceções globais, fornecendo respostas amigáveis para erros de validação e exceções inesperadas.

**Camada de Modelo:**
- `Transferencia.java`: Entidade que representa uma transferência financeira, com validações para garantir a consistência dos dados.

**Camada de Repositório:**
- `TransferenciaRepository.java`: Interface que fornece métodos para interagir com o banco de dados, utilizando Spring Data JPA.

**Camada de Serviço:**
- `TransferenciaService.java`: Contém a lógica de negócios para agendamento de transferências, incluindo validações e cálculo de taxas.

**Camada de Recursos:**
- `application-dev.properties`: Configurações específicas para o ambiente de desenvolvimento, incluindo CORS e logs.
- `application.properties`: Configurações gerais da aplicação, como perfil ativo e configurações do banco de dados H2.

**Camada de Testes:**
- `AgendamentoTransferenciasApplicationTests.java`: Testes de contexto da aplicação.
- `TransferenciaControllerTest.java`: Testes unitários para o controlador de transferências.
- `TransferenciaServiceTest.java`: Testes unitários para o serviço de transferências.


## Endpoints

### Detalhamento dos Endpoints

#### Endpoint: Agendar Transferência

- **Método HTTP**: `POST`
- **URL**: `/api/transferencias`

##### Entradas:
- **Corpo da Requisição**: Um objeto JSON representando uma transferência, com os seguintes campos obrigatórios:
    - `contaOrigem`: String de 6 caracteres representando a conta de origem.
    - `contaDestino`: String de 6 caracteres representando a conta de destino.
    - `valorTransferencia`: Valor decimal maior que 0 representando o valor a ser transferido.
    - `dataTransferencia`: Data futura no formato ISO-8601 (yyyy-MM-dd) representando a data da transferência.

##### Processamento Interno e Validações:
1. **Validações de Entrada**:
    - Verifica se `contaOrigem` e `contaDestino` possuem exatamente 6 caracteres.
    - Verifica se `valorTransferencia` é maior que 0.
    - Verifica se `dataTransferencia` é uma data futura.
    - Verifica se `contaOrigem` e `contaDestino` são diferentes.

2. **Cálculo da Taxa**:
    - Calcula a diferença em dias entre a data de agendamento (data atual) e `dataTransferencia`.
    - Calcula a taxa com base na diferença de dias:
        - 0 dias: 2.5% do valor + R$3,00.
        - 1-10 dias: R$12,00.
        - 11-20 dias: 8.2% do valor.
        - 21-30 dias: 6.9% do valor.
        - 31-40 dias: 4.7% do valor.
        - 41-50 dias: 1.7% do valor.
        - Mais de 50 dias: Não permitido.

3. **Persistência**:
    - Salva a transferência no banco de dados com a taxa calculada e a data de agendamento.

##### Saídas:
- **Resposta de Sucesso** (`200 OK`):
    - Corpo: JSON contendo uma mensagem de sucesso e os dados da transferência agendada.
- **Erros de Validação** (`400 Bad Request`):
    - Corpo: JSON detalhando os erros de validação.
- **Erro Interno** (`500 Internal Server Error`):
    - Corpo: JSON com mensagem de erro genérica.

#### Endpoint: Listar Transferências

- **Método HTTP**: `GET`
- **URL**: `/api/transferencias`

##### Entradas:
- Nenhuma entrada específica.

##### Processamento Interno:
1. **Consulta ao Banco de Dados**:
    - Recupera todas as transferências cadastradas.

##### Saídas:
- **Resposta de Sucesso** (`200 OK`):
    - Corpo: JSON contendo a quantidade e a lista de todas as transferências.
- **Erro Interno** (`500 Internal Server Error`):
    - Corpo: JSON com mensagem de erro genérica.

#### Endpoint: Buscar Transferências por Data

- **Método HTTP**: `GET`
- **URL**: `/api/transferencias/data`

##### Entradas:
- **Parâmetro de Consulta**: `data` (String no formato ISO-8601 yyyy-MM-dd).

##### Processamento Interno:
1. **Conversão de Data**:
    - Converte o parâmetro `data` para um objeto `LocalDate`.

2. **Consulta ao Banco de Dados**:
    - Busca transferências agendadas para a data especificada.

##### Saídas:
- **Resposta de Sucesso** (`200 OK`):
    - Corpo: JSON contendo a lista de transferências para a data especificada.
- **Erro de Validação** (`400 Bad Request`):
    - Corpo: JSON detalhando erros de entrada inválida.
- **Erro Interno** (`500 Internal Server Error`):
    - Corpo: JSON com mensagem de erro genérica.

## Models

### Models

#### Transferencia

A tabela `transferencias` é representada pela classe `Transferencia`. A estrutura da tabela e suas colunas são definidas da seguinte forma:

- **id**: Identificador único para cada transferência, do tipo `UUID`. É a chave primária da tabela.
- **conta_origem**: Representa a conta de origem da transferência. É uma string de exatamente 6 caracteres, não nula.
- **conta_destino**: Representa a conta de destino da transferência. É uma string de exatamente 6 caracteres, não nula.
- **valor_transferencia**: Valor da transferência, do tipo `BigDecimal`. Deve ser maior que 0 e não pode ser nulo.
- **taxa**: Taxa aplicada à transferência, do tipo `BigDecimal`. Não pode ser nula.
- **data_transferencia**: Data em que a transferência está agendada para ocorrer, do tipo `LocalDate`. Deve ser uma data futura e não pode ser nula.
- **data_agendamento**: Data em que a transferência foi agendada, do tipo `LocalDate`. Não pode ser nula.

#### Relacionamentos

Atualmente, a tabela `transferencias` não possui relacionamentos explícitos com outras tabelas. A classe `Transferencia` é uma entidade independente no contexto do sistema de agendamento de transferências financeiras.

## Configurações

### Configurações do Projeto

#### Packages Utilizados

O projeto está estruturado em vários pacotes, cada um com uma responsabilidade específica:

- **com.empresa.transferencias**: Contém a classe principal `AgendamentoTransferenciasApplication` que inicia a aplicação.
- **com.empresa.transferencias.config**: Inclui classes de configuração, como `CorsConfig` e `CorsFilter`, para gerenciar configurações de CORS.
- **com.empresa.transferencias.controller**: Contém controladores REST, como `TransferenciaController`, que expõem endpoints para operações de transferência.
- **com.empresa.transferencias.exception**: Inclui a classe `GlobalExceptionHandler` para tratamento global de exceções.
- **com.empresa.transferencias.model**: Define as entidades do modelo de dados, como `Transferencia`.
- **com.empresa.transferencias.repository**: Contém interfaces de repositório, como `TransferenciaRepository`, para interação com o banco de dados.
- **com.empresa.transferencias.service**: Inclui serviços, como `TransferenciaService`, que implementam a lógica de negócios.

#### Dependências Específicas

O projeto utiliza várias dependências gerenciadas pelo Maven, especificadas no arquivo `pom.xml`:

- **Spring Boot Starter Web**: Para criar aplicações web RESTful.
  ```xml
  <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-web</artifactId>
  </dependency>
  ```

- **Spring Data JPA**: Para persistência de dados usando JPA.
  ```xml
  <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-data-jpa</artifactId>
  </dependency>
  ```

- **H2 Database**: Banco de dados em memória para desenvolvimento e testes.
  ```xml
  <dependency>
      <groupId>com.h2database</groupId>
      <artifactId>h2</artifactId>
      <scope>runtime</scope>
  </dependency>
  ```

- **Lombok**: Para reduzir o código boilerplate, como getters e setters.
  ```xml
  <dependency>
      <groupId>org.projectlombok</groupId>
      <artifactId>lombok</artifactId>
      <version>1.18.24</version>
      <optional>true</optional>
  </dependency>
  ```

- **Spring Boot Starter Validation**: Para validação de dados.
  ```xml
  <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-validation</artifactId>
  </dependency>
  ```

- **Spring Boot Starter Test**: Para testes unitários e de integração.
  ```xml
  <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-test</artifactId>
      <scope>test</scope>
  </dependency>
  ```

#### Instruções para Inicialização Local

Para executar o projeto localmente, siga as etapas abaixo:

1. **Pré-requisitos**:
    - Certifique-se de ter o Java 11 instalado.
    - Instale o Maven para gerenciar as dependências do projeto.

2. **Clonar o Repositório**:
    - Clone o repositório do projeto para sua máquina local.

   ```bash
   git clone https://github.com/ivanmatuck/agendamento-transferencias.git
   cd agendamento-transferencias
   ```

3. **Configuração do Banco de Dados**:
    - O projeto está configurado para usar um banco de dados H2 em memória. As configurações estão no arquivo `src/main/resources/application.properties`:
      ```properties
      spring.datasource.url=jdbc:h2:mem:agendamentos
      spring.datasource.driver-class-name=org.h2.Driver
      spring.datasource.username=sa
      spring.datasource.password=password
      spring.h2.console.enabled=true
      spring.jpa.show-sql=true
      spring.jpa.hibernate.ddl-auto=update
      ```

4. **Perfil de Desenvolvimento**:
    - O perfil `dev` está ativo por padrão, conforme especificado em `application.properties`:
      ```properties
      spring.profiles.active=dev
      ```

5. **Executar a Aplicação**:
    - Navegue até o diretório raiz do projeto onde o arquivo `pom.xml` está localizado.
    - Execute o seguinte comando Maven para iniciar a aplicação:
      ```bash
      mvn spring-boot:run
      ```

6. **Acessar a Aplicação**:
    - A aplicação estará disponível na porta 8080. Você pode acessar os endpoints REST através de um cliente HTTP ou navegador web, por exemplo:
      ```
      http://localhost:8080/api/transferencias
      ```

7. **Testes**:
    - Para executar os testes, utilize o seguinte comando Maven:

   ```bash
   mvn test
   ```

Essas instruções devem ajudá-lo a configurar e executar o projeto "Agendamento de Transferências" em seu ambiente local. Se você encontrar problemas, verifique os logs de erro para obter mais detalhes sobre o que pode estar errado.
            