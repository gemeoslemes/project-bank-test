# Projeto Bank Teste (API REST)

## Descrição

Este é um projeto de sistema bancário desenvolvido como uma API REST, que permite aos usuários realizar transferências e criar carteiras. Existem dois tipos de usuários: usuário comum e comerciante. O usuário comum pode realizar transferências e receber fundos, enquanto o comerciante pode apenas receber fundos.

## Tecnologias Utilizadas

### Linguagem de Programação
- Java 17

### Frameworks e Bibliotecas
- Spring Boot (v3.3.0)
- Spring Cloud (v2023.0.1)
- Spring Boot Starter Data JPA
- Spring Boot Starter Validation
- Spring Boot Starter Web
- Spring Cloud Starter OpenFeign
- Spring Boot DevTools
- Lombok
- MySQL Connector/J

### Testes
- Spring Boot Starter Test

## Funcionalidades

### Transferências
- Os usuários podem realizar transferências entre contas.
- Apenas os usuários comuns podem enviar e receber transferências.
- Os comerciantes podem apenas receber transferências.
- Para realizar uma transferência, o saldo da conta do remetente deve ser maior que 0.01.
- As entradas de dados são validadas para garantir que não sejam nulas ou vazias.

### Carteiras
- Os usuários podem criar carteiras em suas contas.
- Cada conta pode ter várias carteiras associadas a ela.

### Notificações
- Após a realização de uma transferência bem-sucedida, uma notificação é enviada ao usuário.

### Validadores
- Os campos de entrada, como email e CPF/CNPJ, são únicos e validados para garantir a integridade dos dados.
