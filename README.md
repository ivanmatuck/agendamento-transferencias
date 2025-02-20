
            # Documentação do Projeto: Sistema de Agendamento de Transferências Bancárias

## Visão Geral do Projeto

O projeto "Sistema de Agendamento de Transferências Bancárias" é uma aplicação desenvolvida em Java, utilizando o framework Spring Boot, com o objetivo de gerenciar o agendamento de transferências financeiras entre contas. O sistema permite que os usuários agendem transferências para datas futuras, calcule taxas com base na data de agendamento e na data de transferência, e liste transferências agendadas.

### Objetivo do Projeto

O principal objetivo do sistema é fornecer uma solução eficiente e segura para o agendamento de transferências financeiras. Através de uma interface RESTful, o sistema permite que os usuários agendem transferências, consultem transferências agendadas e busquem transferências por data específica. 

### Benefícios para o Negócio

1. **Automação do Processo de Transferência**: O sistema automatiza o processo de agendamento de transferências, reduzindo a necessidade de intervenção manual e minimizando erros humanos.

2. **Cálculo Automático de Taxas**: O sistema calcula automaticamente as taxas de transferência com base na diferença de dias entre o agendamento e a data de transferência, garantindo precisão e conformidade com as políticas financeiras.

3. **Flexibilidade e Escalabilidade**: Construído sobre o Spring Boot, o sistema é altamente escalável e pode ser facilmente integrado a outras soluções bancárias e financeiras.

4. **Segurança e Confiabilidade**: Utilizando práticas recomendadas de segurança, como o uso do AWS Secrets Manager para gerenciamento de credenciais, o sistema garante a proteção dos dados sensíveis.

## Estrutura do Projeto

### Configuração do Projeto

O projeto utiliza o Maven para gerenciamento de dependências e construção. As principais dependências incluem:

- **Spring Boot**: Framework principal para construção da aplicação.
- **Spring Data JPA**: Para integração com o banco de dados PostgreSQL.
- **AWS SDK**: Para integração com o AWS Secrets Manager.
- **Lombok**: Para reduzir a verbosidade do código com geração automática de getters, setters e outros métodos comuns.

### Arquitetura da Aplicação

1. **Camada de Controle**: Implementada pelo `TransferenciaController`, expõe endpoints REST para agendamento e listagem de transferências.

2. **Camada de Serviço**: Implementada pelo `TransferenciaService`, contém a lógica de negócios para validação e cálculo de taxas de transferências.

3. **Camada de Persistência**: Implementada pelo `TransferenciaRepository`, interage com o banco de dados para operações de CRUD.

4. **Configurações de CORS**: Gerenciadas pelas classes `CorsConfig` e `CorsFilter`, asseguram que a aplicação possa ser acessada de origens permitidas.

5. **Tratamento de Exceções**: A classe `GlobalExceptionHandler` fornece tratamento global de exceções, garantindo respostas amigáveis ao usuário em caso de erros.

### Modelo de Dados

A entidade `Transferencia` representa uma transferência financeira, contendo informações como:

- Identificador único (UUID)
- Conta de origem e destino
- Valor e taxa da transferência
- Datas de transferência e agendamento

### Configurações de Ambiente

O projeto utiliza perfis de configuração para gerenciar diferentes ambientes (desenvolvimento, produção). As configurações incluem detalhes de conexão com o banco de dados PostgreSQL e integração com o AWS Secrets Manager para gerenciamento de credenciais.

### Testes

O projeto inclui testes unitários para as classes de serviço e controlador, garantindo a integridade e funcionalidade do sistema. Os testes são implementados utilizando JUnit e Mockito.

## Conclusão

O "Sistema de Agendamento de Transferências Bancárias" é uma solução robusta e eficiente para o gerenciamento de transferências financeiras, oferecendo automação, segurança e flexibilidade para atender às necessidades de negócios bancários e financeiros.
            
            ## Arquitetura
            
            Sistema de Agendamento de Transferências - Java

```
Sistema de Agendamento de Transferências - Java
├── Camada de Aplicação
│   ├── AgendamentoTransferenciasApplication.java
│   └── AgendamentoTransferenciasApplicationTests.java
├── Camada de Configuração
│   ├── CorsConfig.java
│   └── CorsFilter.java
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
├── Camada de Recursos
│   ├── application-dev.properties
│   └── application.properties
└── Configuração do Projeto
    └── pom.xml
```

### Detalhamento das Camadas

- **Camada de Aplicação**: Contém a classe principal `AgendamentoTransferenciasApplication.java`, responsável por iniciar a aplicação Spring Boot. Inclui também testes de contexto da aplicação em `AgendamentoTransferenciasApplicationTests.java`.

- **Camada de Configuração**: Define configurações específicas da aplicação, como o `CorsConfig.java` para configurar CORS em ambiente de desenvolvimento, e `CorsFilter.java` que implementa um filtro para gerenciar requisições CORS.

- **Camada de Controle**: Implementa controladores REST, como `TransferenciaController.java`, que expõe endpoints para operações de agendamento e listagem de transferências. Inclui testes unitários em `TransferenciaControllerTest.java`.

- **Camada de Exceção**: `GlobalExceptionHandler.java` fornece tratamento global de exceções, capturando erros de validação e exceções inesperadas para retornar mensagens amigáveis ao cliente.

- **Camada de Modelo**: Define a entidade `Transferencia.java`, que representa uma transferência financeira com validações para garantir a consistência dos dados.

- **Camada de Repositório**: `TransferenciaRepository.java` fornece métodos para interagir com o banco de dados, utilizando Spring Data JPA.

- **Camada de Serviço**: `TransferenciaService.java` contém a lógica de negócios para agendamento de transferências, incluindo validações e cálculo de taxas. Os testes de serviço são implementados em `TransferenciaServiceTest.java`.

- **Camada de Recursos**: Contém arquivos de configuração de propriedades da aplicação, como `application-dev.properties` e `application.properties`, que definem configurações de banco de dados, CORS e outros parâmetros de ambiente.

- **Configuração do Projeto**: `pom.xml` é o arquivo de configuração do Maven, especificando dependências, plugins e a estrutura do projeto.
            
            
            ## Endpoints
            
            ### Detalhamento dos Endpoints do Projeto Java

#### 1. Endpoint para Agendar Transferência

- **Método HTTP**: POST
- **Caminho**: `/api/transferencias`

##### Entradas
- **Corpo da Requisição**: Um objeto JSON representando uma transferência, que deve incluir:
  - `contaOrigem`: String de 6 caracteres representando a conta de origem.
  - `contaDestino`: String de 6 caracteres representando a conta de destino.
  - `valorTransferencia`: Valor decimal maior que 0 representando o valor da transferência.
  - `dataTransferencia`: Data futura no formato ISO-8601 (yyyy-MM-dd) representando a data da transferência.

##### Processamento Interno e Validações
1. **Validação dos Dados**:
   - Verifica se `contaOrigem` e `contaDestino` têm exatamente 6 caracteres.
   - Verifica se `valorTransferencia` é maior que 0.
   - Verifica se `dataTransferencia` é uma data futura.
   - Verifica se `contaOrigem` e `contaDestino` são diferentes.
2. **Cálculo da Taxa**:
   - Calcula a diferença em dias entre a data de agendamento (data atual) e `dataTransferencia`.
   - Calcula a taxa de acordo com a diferença de dias e o valor da transferência.
3. **Persistência**:
   - Salva a transferência no banco de dados com a data de agendamento atual e a taxa calculada.

##### Saídas
- **Resposta de Sucesso (200 OK)**:
  - Corpo da resposta contendo uma mensagem de sucesso e os dados da transferência agendada.
- **Erros**:
  - **400 Bad Request**: Em caso de erro de validação, retorna uma mensagem de erro detalhando o problema.
  - **500 Internal Server Error**: Em caso de erro inesperado, retorna uma mensagem de erro genérica.

#### 2. Endpoint para Listar Transferências

- **Método HTTP**: GET
- **Caminho**: `/api/transferencias`

##### Entradas
- Nenhuma entrada específica é necessária.

##### Processamento Interno e Validações
1. **Consulta ao Banco de Dados**:
   - Recupera todas as transferências cadastradas no banco de dados.

##### Saídas
- **Resposta de Sucesso (200 OK)**:
  - Corpo da resposta contendo a quantidade e a lista de todas as transferências registradas.
- **Erros**:
  - **500 Internal Server Error**: Em caso de erro ao acessar o banco de dados, retorna uma mensagem de erro genérica.

#### 3. Endpoint para Buscar Transferências por Data

- **Método HTTP**: GET
- **Caminho**: `/api/transferencias/data`

##### Entradas
- **Parâmetro de Consulta**: `data` (String no formato ISO-8601 yyyy-MM-dd) representando a data de transferência desejada.

##### Processamento Interno e Validações
1. **Validação da Data**:
   - Verifica se a data fornecida está no formato correto.
2. **Consulta ao Banco de Dados**:
   - Busca transferências agendadas para a data especificada.

##### Saídas
- **Resposta de Sucesso (200 OK)**:
  - Corpo da resposta contendo a quantidade e a lista de transferências agendadas para a data especificada.
- **Erros**:
  - **400 Bad Request**: Em caso de erro de formatação da data, retorna uma mensagem de erro detalhando o problema.
  - **500 Internal Server Error**: Em caso de erro ao acessar o banco de dados, retorna uma mensagem de erro genérica.
            
            ## Models
            
            ### Models

#### Transferencia

A tabela `transferencias` é representada pela classe `Transferencia` no projeto. A estrutura da tabela e seus relacionamentos são descritos a seguir:

- **Estrutura da Tabela `transferencias`:**
  - `id`: Identificador único para cada transferência. Tipo: `UUID`. Gerado automaticamente.
  - `conta_origem`: Conta de origem da transferência. Tipo: `String`. Deve ter exatamente 6 caracteres. Não nulo.
  - `conta_destino`: Conta de destino da transferência. Tipo: `String`. Deve ter exatamente 6 caracteres. Não nulo.
  - `valor_transferencia`: Valor da transferência. Tipo: `BigDecimal`. Deve ser maior que 0. Não nulo.
  - `taxa`: Taxa aplicada à transferência. Tipo: `BigDecimal`. Não nulo.
  - `data_transferencia`: Data em que a transferência deve ocorrer. Tipo: `LocalDate`. Deve ser uma data futura. Não nulo.
  - `data_agendamento`: Data em que a transferência foi agendada. Tipo: `LocalDate`. Não nulo.

- **Relacionamentos:**
  - Não há relacionamentos explícitos definidos entre a tabela `transferencias` e outras tabelas no código fornecido. A classe `Transferencia` é uma entidade autônoma que não possui chaves estrangeiras ou associações com outras entidades no contexto do projeto atual.
            
            ## Configurações
            
            ### Configurações do Projeto

#### Packages Utilizados
O projeto está estruturado em diversos pacotes, cada um com uma responsabilidade específica:

- `com.empresa.transferencias`: Contém a classe principal do aplicativo, `AgendamentoTransferenciasApplication`, responsável por iniciar a aplicação Spring Boot.
- `com.empresa.transferencias.config`: Inclui classes de configuração, como `CorsConfig` e `CorsFilter`, para gerenciar as políticas de CORS.
- `com.empresa.transferencias.controller`: Contém o controlador REST `TransferenciaController` que gerencia as requisições HTTP relacionadas a transferências.
- `com.empresa.transferencias.exception`: Abriga o `GlobalExceptionHandler`, que trata exceções globais na aplicação.
- `com.empresa.transferencias.model`: Define a entidade `Transferencia`, que representa uma transferência financeira.
- `com.empresa.transferencias.repository`: Inclui a interface `TransferenciaRepository`, que estende `JpaRepository` para interagir com o banco de dados.
- `com.empresa.transferencias.service`: Contém a classe `TransferenciaService`, responsável pela lógica de negócios das transferências.

#### Dependências Específicas
O projeto utiliza várias dependências gerenciadas pelo Maven, especificadas no arquivo `pom.xml`:

- **Spring Boot Starter Web**: Para criar aplicações web com Spring MVC.
- **Spring Boot Starter Data JPA**: Para integração com JPA e Hibernate.
- **Spring Boot Starter Validation**: Para validação de dados.
- **PostgreSQL Driver**: Para conectar-se a um banco de dados PostgreSQL.
- **AWS SDK (Secrets Manager e STS)**: Para integração com serviços AWS.
- **AWS Advanced JDBC Wrapper**: Para melhorar a conexão com o banco de dados em ambientes AWS.
- **Lombok**: Para reduzir a verbosidade do código com anotações que geram código boilerplate.
- **H2 Database**: Para testes em memória.
- **Spring Boot Starter Test**: Para suporte a testes unitários e de integração.

#### Instruções para Inicialização Local
Para executar o projeto localmente, siga as instruções abaixo:

1. **Pré-requisitos**:
   - Certifique-se de ter o JDK 11 instalado.
   - Instale o Maven para gerenciar as dependências do projeto.
   - Configure um banco de dados PostgreSQL local com as credenciais especificadas no arquivo `application-dev.properties`.

2. **Configuração do Banco de Dados**:
   - As configurações do banco de dados estão no arquivo `src/main/resources/application-dev.properties`.
   - Certifique-se de que o banco de dados PostgreSQL está rodando na porta `5432` com o nome `brain`, usuário `postgres` e senha `BrainTeste1234*%`.

3. **Executar a Aplicação**:
   - Navegue até o diretório raiz do projeto onde o arquivo `pom.xml` está localizado.
   - Execute o comando Maven para compilar e iniciar a aplicação:
     ```bash
     mvn spring-boot:run
     ```
   - A aplicação estará disponível na porta `8080`.

4. **Testes**:
   - Para executar os testes, utilize o comando:
     ```bash
     mvn test
     ```

5. **Perfis de Configuração**:
   - O perfil de desenvolvimento (`dev`) está configurado no arquivo `application.properties`. Para ativá-lo, descomente a linha `spring.profiles.active=dev`.

Estas instruções devem permitir que você configure e execute o projeto localmente com sucesso.
            