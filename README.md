
            # Documentação do Sistema de Agendamento de Transferências Bancárias

## Visão Geral

O projeto "Sistema de Agendamento de Transferências Bancárias" é uma aplicação desenvolvida em Java com o framework Spring Boot. O objetivo principal é permitir o agendamento de transferências financeiras entre contas bancárias, oferecendo uma interface RESTful para que os usuários possam agendar e consultar transferências de forma eficiente e segura.

## Objetivo do Projeto

O sistema foi projetado para atender a necessidade de agendamento de transferências financeiras, permitindo que os usuários possam programar transferências para datas futuras. Isso é especialmente útil para empresas e indivíduos que precisam gerenciar suas finanças de forma planejada, evitando atrasos e garantindo que os pagamentos sejam realizados na data correta.

## Benefícios para o Negócio

1. **Automação de Processos Financeiros**: O sistema permite que transferências sejam agendadas automaticamente, reduzindo a necessidade de intervenção manual e minimizando erros.

2. **Eficiência Operacional**: Com a possibilidade de agendar transferências, as empresas podem planejar melhor seus fluxos de caixa, garantindo que os pagamentos sejam feitos pontualmente.

3. **Segurança e Confiabilidade**: Utilizando tecnologias modernas e seguras, como o Spring Boot e o PostgreSQL, o sistema garante a integridade e confidencialidade dos dados financeiros.

4. **Flexibilidade e Escalabilidade**: A arquitetura do sistema permite fácil adaptação e escalabilidade, suportando um número crescente de usuários e transações sem comprometer o desempenho.

## Estrutura do Projeto

### Configuração do Projeto

- **Maven**: O projeto utiliza o Maven como ferramenta de gerenciamento de dependências e construção. As dependências incluem bibliotecas para desenvolvimento web, acesso a dados, validação, e integração com o AWS Secrets Manager para gerenciamento de credenciais.

### Principais Componentes

1. **Controladores REST**: 
   - `TransferenciaController`: Gerencia as requisições para agendamento e listagem de transferências. Oferece endpoints para criar novas transferências e consultar transferências existentes.

2. **Serviços**:
   - `TransferenciaService`: Contém a lógica de negócios para agendamento de transferências, incluindo validação de dados e cálculo de taxas.

3. **Repositórios**:
   - `TransferenciaRepository`: Interface para interação com o banco de dados, permitindo operações CRUD sobre a entidade `Transferencia`.

4. **Modelos**:
   - `Transferencia`: Representa uma transferência financeira, incluindo informações como contas de origem e destino, valor, taxa, e datas de agendamento e transferência.

5. **Configurações**:
   - `CorsConfig` e `CorsFilter`: Configurações para permitir requisições CORS, facilitando o desenvolvimento e integração com outras aplicações.

6. **Tratamento de Exceções**:
   - `GlobalExceptionHandler`: Captura e trata exceções globais, retornando mensagens amigáveis para o cliente.

### Configuração de Ambiente

- **Banco de Dados**: O sistema utiliza PostgreSQL, com configurações específicas para ambientes de desenvolvimento e produção. As credenciais de acesso são gerenciadas pelo AWS Secrets Manager.

- **Perfil de Desenvolvimento**: Configurações específicas para o ambiente de desenvolvimento, incluindo permissões de CORS e detalhes de conexão com o banco de dados.

### Testes

O projeto inclui testes unitários para os componentes principais, garantindo a qualidade e confiabilidade do código. Testes são realizados para verificar o comportamento dos controladores e serviços, assegurando que as funcionalidades atendam aos requisitos esperados.

## Conclusão

O Sistema de Agendamento de Transferências Bancárias é uma solução robusta e eficiente para o gerenciamento de transferências financeiras. Com sua arquitetura bem definida e uso de tecnologias modernas, o sistema oferece uma plataforma segura e escalável para atender às necessidades de negócios que demandam automação e planejamento financeiro.
            
            ## Arquitetura
            
            Sistema de Agendamento de Transferências - Java

```
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
├── Camada de Aplicação
│   └── AgendamentoTransferenciasApplication.java
├── Camada de Testes
│   ├── AgendamentoTransferenciasApplicationTests.java
│   ├── TransferenciaControllerTest.java
│   └── TransferenciaServiceTest.java
└── Arquivos de Configuração
    ├── application-dev.properties
    ├── application.properties
    └── pom.xml
```

**Descrição das Camadas:**

- **Camada de Configuração:** 
  - Contém classes responsáveis pela configuração do sistema, como o gerenciamento de CORS (Cross-Origin Resource Sharing) para permitir ou restringir requisições de diferentes origens.

- **Camada de Controle:**
  - Contém controladores REST que expõem endpoints para interagir com o sistema. No caso, o `TransferenciaController` gerencia as operações de agendamento e listagem de transferências.

- **Camada de Exceção:**
  - Lida com o tratamento global de exceções, capturando erros de validação e exceções inesperadas para retornar mensagens amigáveis ao cliente.

- **Camada de Modelo:**
  - Define as entidades do sistema, como `Transferencia`, que representa uma transferência financeira com suas propriedades e validações.

- **Camada de Repositório:**
  - Fornece interfaces para interagir com o banco de dados, permitindo operações CRUD (Create, Read, Update, Delete) sobre as entidades.

- **Camada de Serviço:**
  - Implementa a lógica de negócios do sistema, como validação de transferências, cálculo de taxas e agendamento de transferências.

- **Camada de Aplicação:**
  - Contém a classe principal que inicia a aplicação Spring Boot.

- **Camada de Testes:**
  - Inclui testes unitários para verificar o comportamento das classes de controle e serviço, garantindo que a lógica de negócios e os endpoints funcionem conforme esperado.

- **Arquivos de Configuração:**
  - Contêm configurações do sistema, como propriedades de conexão com o banco de dados, configurações de perfil de ambiente e dependências do projeto Maven.
            
            
            ## Endpoints
            
            ### Detalhamento dos Endpoints

#### Endpoint: Agendar Transferência

- **Método HTTP:** `POST`
- **Caminho:** `/api/transferencias`

##### Entradas:
- **Corpo da Requisição:** Um objeto JSON representando uma transferência, contendo:
  - `contaOrigem`: String de 6 caracteres (obrigatório).
  - `contaDestino`: String de 6 caracteres (obrigatório).
  - `valorTransferencia`: Decimal maior que 0 (obrigatório).
  - `dataTransferencia`: Data futura no formato ISO-8601 (obrigatório).

##### Processamento Interno e Validações:
1. **Validação de Dados:** Utiliza anotações de validação para garantir que os campos obrigatórios estejam presentes e corretos.
2. **Validações Específicas:**
   - Conta de origem e destino não podem ser iguais.
   - O valor da transferência deve ser maior que zero.
3. **Cálculo da Taxa:** 
   - Calcula a taxa com base na diferença de dias entre a data de agendamento (data atual) e a data de transferência.
   - Diferentes regras de cálculo de taxa são aplicadas dependendo do número de dias de diferença.
4. **Persistência:** Salva a transferência no banco de dados usando o `TransferenciaRepository`.

##### Saídas:
- **Sucesso:** Retorna um `ResponseEntity` com status 200 contendo uma mensagem de sucesso e os dados da transferência agendada.
- **Erro de Validação:** Retorna um `ResponseEntity` com status 400 contendo detalhes do erro.
- **Erro Interno:** Retorna um `ResponseEntity` com status 500 com uma mensagem de erro genérica.

#### Endpoint: Listar Transferências

- **Método HTTP:** `GET`
- **Caminho:** `/api/transferencias`

##### Entradas:
- Não há entradas específicas além da requisição HTTP.

##### Processamento Interno e Validações:
1. **Recuperação de Dados:** Utiliza o `TransferenciaRepository` para buscar todas as transferências registradas no banco de dados.

##### Saídas:
- **Sucesso:** Retorna um `ResponseEntity` com status 200 contendo a quantidade e os dados de todas as transferências.
- **Erro Interno:** Retorna um `ResponseEntity` com status 500 com uma mensagem de erro genérica.

#### Endpoint: Buscar Transferências por Data

- **Método HTTP:** `GET`
- **Caminho:** `/api/transferencias/data`

##### Entradas:
- **Parâmetro de Consulta:** `data` no formato ISO-8601 (yyyy-MM-dd).

##### Processamento Interno e Validações:
1. **Conversão de Data:** Converte a string da data fornecida para um objeto `LocalDate`.
2. **Recuperação de Dados:** Utiliza o `TransferenciaRepository` para buscar transferências agendadas para a data especificada.

##### Saídas:
- **Sucesso:** Retorna um `ResponseEntity` com status 200 contendo a lista de transferências para a data especificada.
- **Erro de Conversão:** Retorna um `ResponseEntity` com status 400 se a data fornecida for inválida.
- **Erro Interno:** Retorna um `ResponseEntity` com status 500 com uma mensagem de erro genérica.

### Considerações Gerais

- **CORS:** Configurado para permitir requisições de origens específicas durante o desenvolvimento.
- **Tratamento de Exceções:** Implementado através de um `GlobalExceptionHandler` que captura erros de validação e exceções genéricas, retornando respostas apropriadas.
- **Persistência:** Utiliza Spring Data JPA para interagir com um banco de dados PostgreSQL.
- **Testes:** Existem testes unitários para os serviços e controladores, simulando o comportamento esperado dos métodos.
            
            ## Models
            
            ### Models

#### Transferencia

A tabela `transferencias` representa uma transferência financeira e possui a seguinte estrutura:

- **id**: UUID, chave primária gerada automaticamente. Representa o identificador único de cada transferência.
- **conta_origem**: String, tamanho fixo de 6 caracteres, não nulo. Representa a conta de origem da transferência.
- **conta_destino**: String, tamanho fixo de 6 caracteres, não nulo. Representa a conta de destino da transferência.
- **valor_transferencia**: BigDecimal, não nulo. Representa o valor da transferência, que deve ser maior que 0.
- **taxa**: BigDecimal, não nulo. Representa a taxa aplicada à transferência.
- **data_transferencia**: LocalDate, não nulo. Representa a data da transferência, que deve ser uma data futura.
- **data_agendamento**: LocalDate, não nulo. Representa a data em que a transferência foi agendada.

#### Relacionamentos

Atualmente, o modelo `Transferencia` não possui relacionamentos explícitos com outras tabelas ou entidades. Ele é uma entidade autônoma que armazena informações sobre cada transferência financeira agendada no sistema.
            
            ## Configurações
            
            ### Configurações do Projeto

#### Packages Utilizados

O projeto está estruturado em vários pacotes, cada um com uma responsabilidade específica:

- `com.empresa.transferencias`: Contém a classe principal `AgendamentoTransferenciasApplication`, responsável por iniciar a aplicação.
- `com.empresa.transferencias.config`: Inclui classes de configuração, como `CorsConfig` e `CorsFilter`, que configuram o CORS para a aplicação.
- `com.empresa.transferencias.controller`: Contém o controlador REST `TransferenciaController`, que gerencia as requisições relacionadas a transferências.
- `com.empresa.transferencias.exception`: Inclui o `GlobalExceptionHandler`, que trata exceções globais na aplicação.
- `com.empresa.transferencias.model`: Define a entidade `Transferencia`, que representa uma transferência financeira.
- `com.empresa.transferencias.repository`: Contém a interface `TransferenciaRepository`, que interage com o banco de dados.
- `com.empresa.transferencias.service`: Inclui a classe `TransferenciaService`, que contém a lógica de negócios para agendamento de transferências.

#### Dependências Específicas

O projeto utiliza as seguintes dependências, conforme especificado no arquivo `pom.xml`:

- **Spring Boot**:
  - `spring-boot-starter-web`: Para criar aplicações web RESTful.
  - `spring-boot-starter-data-jpa`: Para integração com JPA e Hibernate.
  - `spring-boot-starter-validation`: Para validação de dados.
  - `spring-boot-starter-test`: Para suporte a testes.

- **Banco de Dados**:
  - `org.postgresql:postgresql:42.7.3`: Driver JDBC para PostgreSQL.

- **AWS**:
  - `software.amazon.awssdk:secretsmanager:2.30.17`: Para integração com AWS Secrets Manager.
  - `com.amazonaws.secretsmanager:aws-secretsmanager-jdbc:1.0.15`: Para uso do AWS Secrets Manager com JDBC.

- **Utilitários**:
  - `org.projectlombok:lombok:1.18.24`: Para reduzir o boilerplate de código Java.

#### Instruções para Inicialização Local

Para executar o projeto localmente, siga as instruções abaixo:

1. **Pré-requisitos**:
   - Certifique-se de ter o Java 11 instalado.
   - Instale o Maven para gerenciar as dependências do projeto.
   - Configure as variáveis de ambiente necessárias para o banco de dados PostgreSQL:
     - `DB_HOST`: Host do banco de dados.
     - `DB_PORT`: Porta do banco de dados.
     - `DB_NAME`: Nome do banco de dados.
     - `DB_USER`: Usuário do banco de dados.
     - `DB_PASSWORD`: Senha do banco de dados.

2. **Configuração do Perfil de Desenvolvimento**:
   - No arquivo `src/main/resources/application.properties`, descomente a linha `spring.profiles.active=dev` para ativar o perfil de desenvolvimento.

3. **Compilação e Execução**:
   - Navegue até o diretório raiz do projeto onde o arquivo `pom.xml` está localizado.
   - Execute o comando `mvn clean install` para compilar o projeto e resolver as dependências.
   - Inicie a aplicação com o comando `mvn spring-boot:run`.

4. **Acesso à Aplicação**:
   - A aplicação estará disponível em `http://localhost:8080`.
   - Utilize ferramentas como Postman ou cURL para testar os endpoints REST expostos pelo `TransferenciaController`.

5. **Testes**:
   - Para executar os testes, utilize o comando `mvn test`. Isso executará os testes unitários definidos nos pacotes de teste.
            