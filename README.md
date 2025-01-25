# Documentação do Projeto: Sistema de Agendamento de Transferências

## Visão Geral

### Objetivo do Projeto
O projeto "Agendamento de Transferências" é uma aplicação desenvolvida para gerenciar transferências financeiras agendadas. O sistema permite que os usuários agendem transferências entre contas bancárias, calculando automaticamente as taxas de acordo com a data da transferência. Este sistema visa simplificar o processo de agendamento, garantindo precisão e segurança nas transações financeiras.

### Benefícios para o Negócio
- **Automação do Agendamento:** Facilita o agendamento de transferências, reduzindo o trabalho manual e minimizando erros humanos.
- **Cálculo Dinâmico de Taxas:** Oferece flexibilidade na aplicação de taxas, permitindo que o sistema calcule com base em regras de negócios predefinidas.
- **Centralização de Dados:** Armazena todas as informações em uma base de dados central, permitindo fácil acesso e gerenciamento das transferências.

## Estrutura e Organização do Projeto

O projeto está organizado da seguinte forma:

- **Pasta Principal:** `agendamento-transferencias`
  - **`src/main/java/com/empresa/transferencias`**
    - **Main Application:** `AgendamentoTransferenciasApplication.java`
    - **Controller:** `TransferenciaController.java`
    - **Service:** `TransferenciaService.java`
    - **Model:** `Transferencia.java`
    - **Repository:** `TransferenciaRepository.java`
    - **Exception Handling:** `GlobalExceptionHandler.java`
  - **`src/main/resources`**
    - **Configuration:** `application.properties`
  - **`src/test/java/com/empresa/transferencias/agendamento_transferencias`**
    - **Testes:** `AgendamentoTransferenciasApplicationTests.java`

## Componentes Principais

### 1. **`AgendamentoTransferenciasApplication`**
- Classe principal que inicializa o aplicativo Spring Boot.
- Entry point do sistema.

### 2. **`TransferenciaController`**
- Controlador REST responsável pela interface HTTP, gerenciando endpoints para agendar e listar transferências.
- Endpoints principais:
  - `POST /api/transferencias`: Agendar uma nova transferência.
  - `GET /api/transferencias`: Listar todas as transferências.
  - `GET /api/transferencias/data?data={data}`: Buscar transferências por data.

### 3. **`TransferenciaService`**
- Contém a lógica de negócios para o agendamento de transferências.
- Métodos principais:
  - `agendarTransferencia(Transferencia transferencia)`: Agenda uma nova transferência e calcula a taxa correspondente.
  - `listarTransferencias()`: Retorna todas as transferências.
  - `buscarPorDataTransferencia(LocalDate data)`: Busca transferências pela data especificada.

### 4. **`TransferenciaRepository`**
- Interface que extende `JpaRepository` para interagir com a base de dados.
- Métodos principais:
  - `findByDataTransferencia(LocalDate dataTransferencia)`: Busca transferências por data.

### 5. **`Transferencia` (Modelo)**
- Entidade que representa uma transferência financeira.
- Atributos principais: `id`, `contaOrigem`, `contaDestino`, `valorTransferencia`, `taxa`, `dataTransferencia`, `dataAgendamento`.

### 6. **`GlobalExceptionHandler`**
- Classe de tratamento global de exceções, capturando erros comuns e retornando mensagens amigáveis ao usuário.

## Dependências e Configurações

### Dependências Principais
- **Spring Boot**: Framework principal para criar aplicações Java.
- **Spring Data JPA**: Para persistência e acesso a dados.
- **H2 Database**: Banco de dados em memória para teste e desenvolvimento.
- **Lombok**: Simplificação do código através da geração automática de getters, setters, e outras funções.
- **Spring Validation**: Para validação de dados dos modelos.

### Arquivo `application.properties`
Contém configurações do banco de dados H2, porta da aplicação e outras propriedades do Spring Boot. *As credenciais e detalhes de segurança foram omitidos.*

## Guia de Início Rápido

1. **Configurar o Ambiente**
   - Certifique-se de ter o Maven e Java (versão 11) instalados.

2. **Clonar o Repositório**
   - Clone o repositório em sua máquina local.

3. **Construção do Projeto**
   - Navegue até a raiz do projeto e execute o comando `mvn clean install` para compilar o projeto e resolver dependências.

4. **Iniciar a Aplicação**
   - Execute `mvn spring-boot:run` para iniciar o servidor na porta configurada (padrão 8080).

5. **Interagir via API**
   - Utilize ferramentas como Postman ou Insomnia para testar os endpoints fornecidos pelo `TransferenciaController`. 


## Detalhamento da Arquitetura

Para construir uma visão clara e detalhada da arquitetura do sistema descrito, vamos organizar e explicar cada componente e sua função no sistema.

```
+----------------------------------------------------+
| Controllers                                        |
|----------------------------------------------------|
| - Recebem as requisições HTTP.                     |
| - Validam os dados recebidos das requisições.      |
| - Chamam os services e passam os dados processados.|
| - Formatam e enviam as respostas para o cliente.   |
+----------------------------------------------------+
                |
                V
+----------------------------------------------------+
| Services                                           |
|----------------------------------------------------|
| - Implementam a lógica de negócio.                 |
| - Servem como intermediários entre controllers     |
|   e models.                                        |
| - Não têm acesso direto ao banco de dados.         |
| - Realizam chamadas aos métodos dos models para    |
|   manipulação de dados.                            |
+----------------------------------------------------+
                |
                V
+----------------------------------------------------+
| Models                                             |
|----------------------------------------------------|
| - Representam as tabelas do banco de dados.        |
| - Definem os métodos para interagir com os dados,  |
|   como salvar, buscar, atualizar e deletar registros|
|   específicos.                                     |
| - Delegam o acesso direto ao banco para o          |
|   repository.                                      |
+----------------------------------------------------+
                |
                V
+----------------------------------------------------+
| Repository                                         |
|----------------------------------------------------|
| - Responsável pelo acesso direto ao banco de dados.|
| - Contém todos os métodos de manipulação de dados, |
|   como queries puras, operações CRUD (Create, Read,|
|   Update, Delete), etc.                            |
| - Usado exclusivamente pelos models, permitindo    |
|   que eles realizem operações sem conhecer         |
|   diretamente a lógica do banco de dados.          |
+----------------------------------------------------+
```

### Detalhamento de cada camada:

- **Controllers**:
  - A camada de Controllers é responsável por gerenciar as requisições e respostas do sistema. Seu papel é garantir que os dados de entrada sejam válidos e possam ser processados de forma segura. Os controllers recebem os dados das requisições HTTP, validam esses dados, e então invocam os métodos de serviços. Após a realização das operações desejadas, eles também formatam a resposta antes de enviá-la de volta ao cliente.

- **Services**:
  - Os Services são a camada onde reside a lógica de negócio. Eles coordenam a interação entre controllers e models, encapsulando a regra de negócio que não pertence aos controllers ou ao models diretamente. Essa camada não interage diretamente com o banco de dados, mas faz chamadas aos models para operações de manipulação de dados.

- **Models**:
  - A camada de Models é focada na representação das tabelas do banco de dados e na definição de métodos para interação com essas tabelas. Cada model representa uma tabela e define métodos que permitem salvar, buscar, atualizar e excluir registros. Os models são desenhados para não dependerem diretamente da estrutura do banco de dados; eles usam os repositories para isso.

- **Repository**:
  - O Repository é a camada responsável pelo acesso direto ao banco de dados. É aqui que são definidas as queries puras e as operações CRUD (Create, Read, Update, Delete) necessárias para a manipulação de dados. Os repositories são usados exclusivamente pelos models, permitindo uma separação clara entre a lógica de aplicação e o acesso aos dados. 

## Detalhamento do Fluxo de Endpoints

Vamos detalhar o fluxo de cada endpoint exposto no `TransferenciaController` do projeto Java fornecido. Este projeto é um sistema de agendamento de transferências financeiras.

### 1. Endpoint `POST /api/transferencias` - Agendar Transferência

**Entrada:**
- Recebe um JSON representando uma `Transferencia` no corpo da requisição.
- Campos obrigatórios:
  - `contaOrigem`: código de 6 caracteres da conta de origem.
  - `contaDestino`: código de 6 caracteres da conta de destino.
  - `valorTransferencia`: valor a ser transferido (deve ser maior que zero).
  - `dataTransferencia`: data futura para a transferência.

**Processamento Interno:**
- O controlador valida os dados recebidos (`@Valid`).
- Chama o método `agendarTransferencia` do `TransferenciaService`.
  - **Validação:** Verifica se a conta de origem é igual à de destino e se o valor é positivo.
  - **Cálculo de Taxa:** Calcula a taxa com base na diferença de dias entre a data de agendamento e a data de transferência.
  - **Persistência:** Salva a transferência no banco de dados usando `TransferenciaRepository`.

**Saída:**
- Em caso de sucesso, retorna `200 OK` com um mapa contendo uma mensagem de sucesso e a transferência agendada com a taxa calculada.
- Em caso de falha de validação, retorna `400 Bad Request` com detalhes do erro.
- Em caso de erro interno, retorna `500 Internal Server Error` com uma mensagem genérica de erro.

### 2. Endpoint `GET /api/transferencias` - Listar Transferências

**Entrada:**
- Não requer parâmetros.

**Processamento Interno:**
- Chama o método `listarTransferencias` do `TransferenciaService`, que recupera todas as transferências do banco de dados.

**Saída:**
- Em caso de sucesso, retorna `200 OK` com um mapa contendo a quantidade de transferências e a lista completa de transferências.
- Em caso de erro, retorna `500 Internal Server Error` com uma mensagem de erro.

### 3. Endpoint `GET /api/transferencias/data` - Buscar Transferências por Data

**Entrada:**
- Requer o parâmetro `data` na query string, no formato `yyyy-MM-dd`.

**Processamento Interno:**
- Converte o parâmetro de string para `LocalDate`.
- Chama o método `buscarPorDataTransferencia` do `TransferenciaService`, que recupera transferências agendadas para a data especificada.

**Saída:**
- Em caso de sucesso, retorna `200 OK` com a lista de transferências para a data especificada.
- Em caso de erro de formatação de data ou outro erro, retorna `400 Bad Request`.

### Tratamento de Exceções

A classe `GlobalExceptionHandler` gerencia exceções globais, fornecendo mensagens apropriadas:
- Captura e trata exceções de validação (`MethodArgumentNotValidException`).
  - Retorna `400 Bad Request` com detalhes dos campos inválidos.
- Captura exceções genéricas (`Exception`).
  - Retorna `500 Internal Server Error` com uma mensagem padrão.

### Modelagem de Dados

A entidade `Transferencia` é usada para mapear as informações de transferência persistidas no banco de dados H2. Possui validações de atributos com anotações de `javax.validation` para garantir a integridade dos dados.

### Configuração do Sistema

No arquivo `application.properties`, está configurado o uso do banco de dados H2 em memória e a porta do servidor é definida como `8080`.

Este é um resumo do fluxo dos endpoints e a estrutura principal do sistema. Se precisar de mais detalhes em alguma parte específica, sinta-se à vontade para perguntar!

## Detalhamento dos Models e Banco de Dados


### Estrutura de Dados e Relacionamentos

#### Tabela `transferencias`

A tabela `transferencias` armazena informações sobre cada transferência financeira agendada. Não há menção de outras tabelas ou relacionamentos entre tabelas neste projeto, o que sugere que a tabela `transferencias` é independente.

**Colunas:**
- **id**: (UUID) Identificador único gerado automaticamente para cada transferência.
- **conta_origem**: (String) Conta bancária de origem. Deve ter exatamente 6 caracteres.
- **conta_destino**: (String) Conta bancária de destino. Deve ter exatamente 6 caracteres.
- **valor_transferencia**: (BigDecimal) Valor da transferência. Deve ser maior que 0.
- **taxa**: (BigDecimal) Taxa calculada para a transferência.
- **data_transferencia**: (LocalDate) Data futura para a qual a transferência está agendada.
- **data_agendamento**: (LocalDate) Data em que a transferência foi agendada (normalmente, a data atual).

### Tipo de Servidor de Banco de Dados Utilizado

O projeto utiliza o banco de dados **H2** no modo em memória, conforme configurado no arquivo `application.properties`.

**Configurações do Banco de Dados:**
- URL: `jdbc:h2:mem:agendamentos`
- Driver: `org.h2.Driver`
- Username: `sa`
- Password: `password`

### Forma de Conexão com o Banco de Dados

O projeto utiliza o Spring Boot e o Spring Data JPA para gerenciar a conexão com o banco de dados. A conexão é configurada através das propriedades no arquivo `application.properties`. O Hibernate é utilizado para o mapeamento objeto-relacional (ORM).

### Classes Correspondentes aos Models e Suas Propriedades

#### Classe `Transferencia`

Pacote: `com.empresa.transferencias.model`

**Atributos:**
- `UUID id`: Identificador único da transferência.
- `String contaOrigem`: Conta de origem da transferência.
- `String contaDestino`: Conta de destino da transferência.
- `BigDecimal valorTransferencia`: Valor a ser transferido.
- `BigDecimal taxa`: Taxa calculada para a transferência.
- `LocalDate dataTransferencia`: Data em que a transferência deverá ocorrer.
- `LocalDate dataAgendamento`: Data em que o agendamento foi feito.

**Anotações Específicas:**
- `@Entity`: Marca a classe como uma entidade JPA.
- `@Table(name = "transferencias")`: Especifica o nome da tabela.
- `@Id`, `@GeneratedValue(strategy = GenerationType.AUTO)`: Indica o campo `id` como chave primária com geração automática.
- Anotações de validação como `@NotNull`, `@Size`, `@DecimalMin`, `@Future` que asseguram a integridade dos dados conforme regras de negócio especificadas.


## Configuração do Projeto e Testes Locais

Para configurar e executar o projeto `agendamento-transferencias` localmente, siga as instruções detalhadas abaixo:

### Configurações do Projeto

#### Packages Utilizados

O projeto está organizado nas seguintes packages principais:

- `com.empresa.transferencias`: Package raiz do projeto.
- `com.empresa.transferencias.controller`: Contém os controladores REST que expõem os endpoints do sistema.
- `com.empresa.transferencias.exception`: Gerencia exceções de forma centralizada.
- `com.empresa.transferencias.model`: Inclui as classes de modelo representando as entidades do sistema.
- `com.empresa.transferencias.repository`: Interage com o banco de dados através dos repositórios JPA.
- `com.empresa.transferencias.service`: Contém a lógica de negócios para o agendamento e processamento de transferências.

#### Dependências Específicas

O projeto utiliza as seguintes dependências, configuradas no arquivo `pom.xml`:

- **Spring Boot Starter Web**: Para criar aplicativos web RESTful.
- **Spring Boot Starter Data JPA**: Para a integração com o JPA e gerenciamento de persistência.
- **H2 Database**: Banco de dados em memória usado no ambiente de desenvolvimento.
- **Lombok**: Para reduzir o código boilerplate nas classes de modelo.
- **Spring Boot Starter Validation**: Para validação das entradas no projeto.
- **Spring Boot Starter Test**: Para suporte a testes de unidade e integração.

#### Instruções detalhadas para Configurar o Ambiente Local

1. **Pré-requisitos**:
   - Java Development Kit (JDK) 11.
   - Apache Maven 3.6+.

2. **Clone o Repositório**:
   ```bash
   git clone <URL-DO-REPOSITORIO>
   cd agendamento-transferencias
   ```

3. **Configurar o Banco de Dados**:
   - O projeto usa o banco de dados H2, configurado para rodar em memória. 
   - As configurações específicas do banco de dados podem ser alteradas no arquivo `src/main/resources/application.properties`.

4. **Build do Projeto**:
   - Navegue até o diretório do projeto e execute o Maven para construir o mesmo:
   ```bash
   mvn clean install
   ```

#### Etapas para Executar Testes Locais

1. **Executar Testes**:
   - Para rodar os testes, utilize o Maven da seguinte forma:

   ```bash
   mvn test
   ```

2. **Verificar Logs de Teste**:
   - Para analisar resultados, verifique os relatórios de teste que serão gerados no diretório `target/surefire-reports`.

3. **Acesso ao Console H2** (opcional):
   - O console web do H2 está habilitado por padrão e pode ser acessado via navegador.
   - Acesse `http://localhost:8080/h2-console` e conecte-se utilizando as credenciais configuradas em `application.properties`.

Ao seguir essas etapas, você deve ser capaz de configure e executar o projeto `agendamento-transferencias` em seu ambiente local, bem como executar testes para verificar sua funcionalidade.