
# Documentação do Projeto: Sistema de Agendamento de Transferências Bancárias

## Visão Geral

O Sistema de Agendamento de Transferências Bancárias é uma aplicação desenvolvida em Java, utilizando o framework Spring Boot, com o objetivo de gerenciar o agendamento de transferências financeiras entre contas bancárias. Este sistema permite que os usuários agendem transferências para datas futuras, calculem taxas baseadas na data de agendamento e na data de transferência, e consultem transferências agendadas.

## Objetivo do Projeto

O principal objetivo do projeto é fornecer uma solução eficiente e segura para o agendamento de transferências bancárias. A aplicação visa automatizar o processo de agendamento, garantindo que as transferências sejam realizadas de acordo com as regras de negócio estabelecidas, como validação de contas e cálculo de taxas. Além disso, o sistema oferece funcionalidades para listar transferências agendadas e buscar transferências por data específica.

## Benefícios para o Negócio

1. **Eficiência Operacional**: Automatiza o processo de agendamento de transferências, reduzindo a necessidade de intervenção manual e minimizando erros humanos.

2. **Flexibilidade**: Permite que os usuários agendem transferências para datas futuras, oferecendo maior controle sobre o fluxo de caixa.

3. **Transparência e Controle**: O sistema fornece relatórios detalhados das transferências agendadas, permitindo que os usuários acompanhem suas transações financeiras de forma clara e organizada.

4. **Segurança**: Implementa validações rigorosas para garantir que as transferências sejam realizadas de forma segura, respeitando as regras de negócio definidas.

5. **Escalabilidade**: Construído com tecnologias modernas, o sistema pode ser facilmente escalado para atender a um número crescente de usuários e transações.

## Estrutura do Projeto

### Configuração do Projeto

- **Maven**: Utilizado para gerenciamento de dependências e construção do projeto.
- **Spring Boot**: Framework principal para desenvolvimento da aplicação, facilitando a criação de aplicações Java robustas e escaláveis.
- **Banco de Dados**: Utiliza PostgreSQL para armazenamento de dados, com configuração de conexão segura.

### Principais Componentes

1. **AgendamentoTransferenciasApplication**: Classe principal que inicializa a aplicação Spring Boot.

2. **Configurações de CORS**: Implementadas nas classes `CorsConfig` e `CorsFilter` para permitir requisições de origens específicas, garantindo segurança nas comunicações.

3. **Controlador de Transferências**: `TransferenciaController` expõe endpoints REST para agendar e listar transferências, além de buscar transferências por data.

4. **Modelo de Transferência**: `Transferencia` representa a entidade de transferência financeira, com validações para garantir a integridade dos dados.

5. **Repositório de Transferências**: `TransferenciaRepository` fornece métodos para interação com o banco de dados, permitindo operações CRUD.

6. **Serviço de Transferências**: `TransferenciaService` contém a lógica de negócios para agendamento de transferências, incluindo validação de dados e cálculo de taxas.

7. **Tratamento de Exceções**: `GlobalExceptionHandler` captura e trata exceções, fornecendo respostas amigáveis ao usuário.

### Testes

- Testes unitários são implementados para garantir a funcionalidade correta dos componentes principais, como o controlador e o serviço de transferências.

### Configurações de Ambiente

- **Perfil de Desenvolvimento**: Configurado no arquivo `application-dev.properties` para uso local.
- **Conexão com Banco de Dados**: Configurações de URL, nome de usuário e senha são definidas no arquivo `application.properties`.

## Considerações Finais

O Sistema de Agendamento de Transferências Bancárias é uma solução completa para o gerenciamento de transferências financeiras, oferecendo funcionalidades avançadas e segurança para os usuários. Com uma arquitetura bem definida e uso de tecnologias modernas, o sistema está preparado para atender às necessidades do mercado financeiro.

## Arquitetura

Sistema de Agendamento de Transferências - Java

```
Sistema de Agendamento de Transferências - Java
├── Aplicação
│   └── AgendamentoTransferenciasApplication.java
├── Configuração
│   ├── CorsConfig.java
│   └── CorsFilter.java
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
    ├── TransferenciaControllerTest.java
    └── TransferenciaServiceTest.java
```

### Detalhamento da Arquitetura

- **Aplicação**: Contém o ponto de entrada principal do aplicativo (`AgendamentoTransferenciasApplication.java`). Essa classe inicializa a aplicação Spring Boot.

- **Configuração**: Inclui classes para configurar aspectos transversais do sistema, como CORS (`CorsConfig.java` e `CorsFilter.java`), que permitem ou restringem o acesso de diferentes origens ao sistema.

- **Controlador**: A camada de controlador (`TransferenciaController.java`) gerencia as requisições HTTP e define os endpoints REST para operações de transferência, como agendamento e listagem.

- **Exceção**: A classe `GlobalExceptionHandler.java` lida com o tratamento global de exceções, capturando erros de validação e exceções inesperadas, fornecendo respostas apropriadas para o cliente.

- **Modelo**: A classe `Transferencia.java` representa a entidade de transferência financeira, incluindo atributos como contas de origem e destino, valor, taxa e datas relevantes.

- **Repositório**: A interface `TransferenciaRepository.java` fornece métodos para interagir com o banco de dados, utilizando o Spring Data JPA.

- **Serviço**: A classe `TransferenciaService.java` contém a lógica de negócios para agendamento de transferências, incluindo validação de dados e cálculo de taxas.

- **Recursos**: Arquivos de configuração (`application-dev.properties` e `application.properties`) definem propriedades do sistema, como configurações de banco de dados e perfil ativo.

- **Testes**: Inclui testes unitários para a aplicação (`AgendamentoTransferenciasApplicationTests.java`), controlador (`TransferenciaControllerTest.java`) e serviço (`TransferenciaServiceTest.java`), garantindo a funcionalidade correta do sistema.


## Endpoints

### Detalhamento dos Endpoints do Projeto Java

#### Endpoint: Agendar Transferência

- **Método HTTP**: `POST`
- **URL**: `/api/transferencias`
- **Entradas**:
  - Um objeto JSON representando uma transferência, contendo:
    - `contaOrigem`: String de 6 caracteres (obrigatório).
    - `contaDestino`: String de 6 caracteres (obrigatório).
    - `valorTransferencia`: Decimal maior que 0 (obrigatório).
    - `dataTransferencia`: Data futura no formato ISO-8601 (obrigatório).

- **Processamento Interno e Validações**:
  1. O objeto `Transferencia` é validado para garantir que todos os campos obrigatórios estejam presentes e corretos.
  2. As contas de origem e destino não podem ser iguais.
  3. O valor da transferência deve ser maior que zero.
  4. A data de transferência deve ser no futuro.
  5. Calcula a diferença de dias entre a data de agendamento (data atual) e a data de transferência.
  6. Calcula a taxa de transferência com base na diferença de dias.
  7. Salva a transferência no banco de dados.

- **Saídas**:
  - Em caso de sucesso: `ResponseEntity` com status 200 contendo uma mensagem de sucesso e os dados da transferência agendada.
  - Em caso de erro de validação: `ResponseEntity` com status 400 contendo detalhes do erro.
  - Em caso de erro interno: `ResponseEntity` com status 500 contendo uma mensagem de erro genérica.

#### Endpoint: Listar Transferências

- **Método HTTP**: `GET`
- **URL**: `/api/transferencias`
- **Entradas**: Nenhuma.

- **Processamento Interno e Validações**:
  1. Recupera todas as transferências cadastradas no banco de dados.

- **Saídas**:
  - Em caso de sucesso: `ResponseEntity` com status 200 contendo a quantidade e a lista de transferências.
  - Em caso de erro interno: `ResponseEntity` com status 500 contendo uma mensagem de erro genérica.

#### Endpoint: Buscar Transferências por Data

- **Método HTTP**: `GET`
- **URL**: `/api/transferencias/data`
- **Entradas**:
  - Parâmetro de consulta `data`: String representando uma data no formato ISO-8601 (obrigatório).

- **Processamento Interno e Validações**:
  1. Converte a string de data recebida para um objeto `LocalDate`.
  2. Busca transferências agendadas para a data especificada no banco de dados.

- **Saídas**:
  - Em caso de sucesso: `ResponseEntity` com status 200 contendo a lista de transferências para a data especificada.
  - Em caso de erro de formatação de data ou outro erro: `ResponseEntity` com status 400 contendo uma mensagem de erro.

### Observações Adicionais

- **Configuração CORS**: A aplicação possui configuração de CORS para permitir requisições de origens específicas, configuradas no perfil de desenvolvimento.
- **Tratamento de Exceções**: A aplicação possui um manipulador global de exceções que captura erros de validação e exceções genéricas, retornando mensagens apropriadas ao cliente.
- **Persistência de Dados**: Utiliza Spring Data JPA com PostgreSQL para persistência de dados. As transferências são armazenadas em uma tabela chamada `transferencias`.
- **Testes**: Existem testes unitários para os serviços e controladores, garantindo que as funcionalidades principais funcionem conforme esperado.

## Models

### Models

#### Transferencia

A entidade `Transferencia` representa uma transferência financeira no sistema. Ela é mapeada para a tabela `transferencias` no banco de dados. A estrutura da tabela e os detalhes dos campos são os seguintes:

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
  - Descrição: Data em que a transferência deve ocorrer, deve ser uma data futura.

- **dataAgendamento**: `LocalDate`
  - Tipo: `DATE`
  - Anotação: `@Column(nullable = false)`
  - Descrição: Data em que a transferência foi agendada.

#### Relacionamentos

Atualmente, o modelo `Transferencia` não possui relacionamentos explícitos com outras entidades no sistema. Ele é uma entidade independente que armazena informações sobre transferências financeiras. Se houver necessidade de relacionamentos futuros, como vincular transferências a usuários ou contas bancárias, esses relacionamentos podem ser definidos através de novas entidades e mapeamentos JPA.

## Configurações

### Configurações do Projeto

#### Packages Utilizados

O projeto está organizado em vários pacotes, cada um com uma responsabilidade específica:

- `com.empresa.transferencias`: Contém a classe principal `AgendamentoTransferenciasApplication` que inicia a aplicação.
- `com.empresa.transferencias.config`: Inclui classes de configuração, como `CorsConfig` e `CorsFilter`, que configuram o CORS para a aplicação.
- `com.empresa.transferencias.controller`: Contém o controlador REST `TransferenciaController` que gerencia as operações de transferência.
- `com.empresa.transferencias.exception`: Inclui o `GlobalExceptionHandler` para tratamento global de exceções.
- `com.empresa.transferencias.model`: Define a entidade `Transferencia` que representa uma transferência financeira.
- `com.empresa.transferencias.repository`: Contém a interface `TransferenciaRepository` para operações de persistência de dados.
- `com.empresa.transferencias.service`: Inclui o serviço `TransferenciaService` que contém a lógica de negócios para agendamento de transferências.

#### Dependências Específicas

O projeto utiliza o Maven para gerenciamento de dependências. As principais dependências incluídas no arquivo `pom.xml` são:

- **Spring Boot Starter Web**: Para desenvolvimento de aplicações web com Spring MVC.
- **Spring Boot Starter Data JPA**: Para integração com JPA e persistência de dados.
- **PostgreSQL Driver**: Para conexão com o banco de dados PostgreSQL.
- **Lombok**: Para reduzir o código boilerplate, como getters e setters.
- **Spring Boot Starter Validation**: Para validação de dados.
- **Spring Boot Starter Test**: Para suporte a testes unitários e de integração.

#### Instruções para Inicialização Local

Para executar o projeto localmente, siga as instruções abaixo:

1. **Pré-requisitos**:
   - Certifique-se de ter o JDK 11 instalado.
   - Instale o Maven para gerenciar as dependências e construir o projeto.

2. **Configuração do Banco de Dados**:
   - O projeto está configurado para usar um banco de dados PostgreSQL. As credenciais e a URL do banco de dados estão definidas no arquivo `src/main/resources/application.properties`.
   - Certifique-se de que o banco de dados PostgreSQL esteja em execução e acessível com as credenciais fornecidas.

3. **Construção do Projeto**:
   - Navegue até o diretório raiz do projeto onde o arquivo `pom.xml` está localizado.
   - Execute o comando `mvn clean install` para construir o projeto e resolver as dependências.

4. **Execução da Aplicação**:
   - Após a construção bem-sucedida, execute o comando `mvn spring-boot:run` para iniciar a aplicação.
   - A aplicação estará disponível na porta 8080, conforme configurado no arquivo `application.properties`.

5. **Acesso à Aplicação**:
   - Utilize um cliente HTTP (como Postman) ou um navegador para interagir com os endpoints REST expostos pela aplicação, por exemplo, `http://localhost:8080/api/transferencias`.

Essas instruções devem permitir que você configure e execute o projeto localmente para desenvolvimento e testes.

Com base nos arquivos **README.md** e **saida_conteudo_arquivos.txt**, a API Java foi configurada na AWS utilizando o **Spring Boot** e o **PostgreSQL no Amazon RDS**. A seguir, detalho a documentação específica da implementação.

---

# **Documentação da Configuração da API Java no AWS**

## **1. Visão Geral**
A API foi desenvolvida em **Java 11** com **Spring Boot 2.5.14** e implantada em uma instância **Amazon EC2**. O banco de dados utilizado é um **PostgreSQL** hospedado no **Amazon RDS**, acessível pela aplicação através de uma conexão segura.

---

## **2. Infraestrutura AWS**

### **2.1. EC2 - Servidor da Aplicação**
A API foi implantada em uma **instância EC2** que executa o sistema operacional **Amazon Linux**. As configurações incluem:
- **Tipo de instância**: `t2.micro`
- **Região**: `us-east-2`
- **Segurança**: Grupo de segurança configurado para permitir acesso à porta `8080` (aplicação) e `5432` (banco de dados).

### **2.2. RDS - Banco de Dados PostgreSQL**
O banco de dados foi criado no **Amazon RDS** e suas configurações incluem:
- **Engine**: PostgreSQL `15.10`
- **Endpoint**: `agendamento.crkwqmw80nnj.us-east-2.rds.amazonaws.com`
- **Porta**: `5432`
- **Usuário**: `postgres`
- spring.datasource.password=`SUA_SENHA`
- **Banco de dados**: `agendamento`
- **Grupo de Segurança**: Apenas a instância EC2 pode acessá-lo.

---

## **3. Configuração da Aplicação Java**

A aplicação foi configurada no **Spring Boot** para se conectar ao banco RDS utilizando o driver do **PostgreSQL**.

### **3.1. Dependências no `pom.xml`**
No arquivo `pom.xml`, foram adicionadas as seguintes dependências essenciais:

```xml
<dependencies>
    <!-- Spring Web -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <!-- Spring Data JPA -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>

    <!-- Driver PostgreSQL -->
    <dependency>
        <groupId>org.postgresql</groupId>
        <artifactId>postgresql</artifactId>
        <version>42.7.3</version>
    </dependency>

    <!-- Lombok -->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <version>1.18.24</version>
        <optional>true</optional>
    </dependency>

    <!-- Spring Boot Test -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```

### **3.2. Configuração do `application.properties`**
O arquivo `src/main/resources/application.properties` contém as configurações do banco de dados:

```properties
# Configuração do Banco de Dados PostgreSQL
spring.datasource.url=jdbc:postgresql://agendamento.crkwqmw80nnj.us-east-2.rds.amazonaws.com:5432/agendamento
spring.datasource.username=postgres
spring.datasource.password=SUA_SENHA
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.database=POSTGRESQL
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update

# Configuração da Porta do Servidor
server.port=8080
```

---

## **4. Implementação da API**

### **4.1. Classe Principal - `AgendamentoTransferenciasApplication.java`**
```java
package com.empresa.transferencias;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AgendamentoTransferenciasApplication {
    public static void main(String[] args) {
        SpringApplication.run(AgendamentoTransferenciasApplication.class, args);
    }
}
```

### **4.2. Controlador - `TransferenciaController.java`**
A API expõe um endpoint para agendamento de transferências:

```java
@RestController
@RequestMapping("/api/transferencias")
public class TransferenciaController {
    
    @Autowired
    private TransferenciaService service;

    @PostMapping
    public ResponseEntity<?> agendarTransferencia(@Valid @RequestBody Transferencia transferencia) {
        Transferencia agendada = service.agendarTransferencia(transferencia);
        return ResponseEntity.ok(Map.of("mensagem", "Transferência agendada com sucesso.", "dados", agendada));
    }

    @GetMapping
    public ResponseEntity<?> listarTransferencias() {
        return ResponseEntity.ok(service.listarTransferencias());
    }
}
```

---

## **5. Configuração de Segurança e Rede**
### **5.1. Grupo de Segurança da AWS**
- O **Security Group** da instância EC2 permite tráfego nas portas:
  - `8080` (API)
  - `22` (SSH)
  - `5432` (somente para a instância EC2)

- O **Security Group** do RDS permite conexões apenas da EC2.

---

## **6. Processo de Deploy**
Para fazer o deploy da API no **EC2**, foram seguidos os seguintes passos:

### **6.1. Conectar na Instância EC2 via SSH**
```sh
ssh -i "chave.pem" ec2-user@seu-endereco-ec2.amazonaws.com
```

### **6.2. Instalar Java e PostgreSQL Client**
```sh
sudo yum update -y
sudo amazon-linux-extras enable corretto11
sudo yum install java-11-amazon-corretto -y
sudo yum install postgresql -y
```

### **6.3. Transferir o Arquivo `.jar` para a Instância**
```sh
scp -i "chave.pem" target/agendamento-transferencias-1.0.0.jar ec2-user@seu-endereco-ec2.amazonaws.com:/home/ec2-user/
```

### **6.4. Executar a Aplicação na EC2**
```sh
nohup java -jar agendamento-transferencias-1.0.0.jar > log.out 2>&1 &
```

---

## **7. Testes e Validação**
### **7.1. Verificar Logs**
```sh
tail -f log.out
```

### **7.2. Testar API**
Testar a criação de uma transferência:
```sh
curl -X POST "http://seu-endereco-ec2.amazonaws.com:8080/api/transferencias" \
     -H "Content-Type: application/json" \
     -d '{
          "contaOrigem": "123456",
          "contaDestino": "654321",
          "valorTransferencia": 1000.00,
          "dataTransferencia": "2025-02-15"
         }'
```

Listar transferências:
```sh
curl -X GET "http://seu-endereco-ec2.amazonaws.com:8080/api/transferencias"
```

Listar transferências por data:
```sh
curl -X GET "http://seu-endereco-ec2.amazonaws.com:8080/api/transferencias/data/?data=2025-02-22"
```

---

## **8. Conclusão**
A API foi configurada e implantada na AWS com **EC2** para execução da aplicação e **RDS** para o banco de dados **PostgreSQL**. Foram aplicadas boas práticas de segurança e configuração, garantindo que a API esteja acessível e operacional.

---

## **9. Configuração da Pipeline AWS CodePipeline**

Para garantir que a API seja automaticamente implantada quando houver mudanças na **branch master** do repositório GitHub, foi configurada uma **AWS CodePipeline** com os seguintes passos:

### **9.1. Conexão com o GitHub**
A CodePipeline foi configurada para monitorar o repositório:
- **Repositório GitHub**: `ivanmatuck/agendamento-transferencias`
- **Branch monitorada**: `master`
- **Conexão AWS CodeConnections** foi utilizada para autenticação segura com o GitHub.

### **9.2. Etapas da Pipeline**
A pipeline foi configurada com três etapas principais:

#### **1. Origem (Source)**
- **Provedor**: GitHub (versão 2)
- **Branch**: master
- **Formato do artefato de saída**: CodePipeline padrão

#### **2. Build (Compilação)**
Foi configurado um **AWS CodeBuild** para construir o projeto utilizando Maven.
O arquivo de build foi definido diretamente na configuração da pipeline com o seguinte **buildspec.yaml**:

```yaml
version: 0.2

phases:
  build:
    commands:
      - echo "Starting maven build"
      - mvn -B package --file pom.xml
      - echo "Completed maven build successfully!"
```

#### **3. Deploy (Implantação)**
Após o build bem-sucedido, a API é automaticamente implantada na instância EC2:
- **Comandos executados no EC2**:
  ```sh
  # Parar a aplicação antiga
  sudo pkill -f 'java -jar'
  
  # Mover o novo JAR para a pasta do projeto
  mv target/agendamento-transferencias-1.0.0.jar /home/ec2-user/

  # Iniciar a aplicação novamente
  nohup java -jar /home/ec2-user/agendamento-transferencias-1.0.0.jar > log.out 2>&1 &
  ```

- **Acesso à API**:
  - O novo build estará disponível no endereço público da EC2:  
    `http://seu-endereco-ec2.amazonaws.com:8080/api/transferencias`

### **9.3. Benefícios da Automação**
- **CI/CD automático**: Qualquer mudança na branch master aciona automaticamente o build e a implantação.
- **Zero downtime**: O deploy ocorre de forma rápida, sem necessidade de intervenção manual.
- **Segurança**: A conexão entre GitHub e AWS é autenticada via AWS CodeConnections.

---

Essa configuração garante que o **deploy** da API seja feito de forma automática e segura.
