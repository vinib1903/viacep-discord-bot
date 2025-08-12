# 🤖 ViaCEP Discord Bot

[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://openjdk.java.net/projects/jdk/21/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.4-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![JDA](https://img.shields.io/badge/JDA-5.0.0--beta.20-blue.svg)](https://github.com/DV8FromTheWorld/JDA)

> 🇧🇷 Bot do Discord para consulta de CEPs brasileiros usando a API ViaCEP

## 📋 Sobre o Projeto

O **ViaCEP Discord Bot** é uma aplicação Spring Boot que integra o Discord com a API ViaCEP, permitindo consultar informações de endereços brasileiros através de comandos no Discord.

### ✨ Funcionalidades

- 🔍 **Busca por CEP**: Consulta informações de endereço através do código postal
- 📍 **Busca por Endereço**: Encontra CEPs através de estado, cidade e logradouro
- 🆘 **Sistema de Ajuda**: Comandos interativos com GIFs explicativos

## 🚀 Tecnologias

- **Java 21** + **Spring Boot 3.5.4**
- **JDA 5.0.0** - Java Discord API
- **Spring WebFlux** - Programação reativa
- **ViaCEP API** - Consulta de CEPs

## 📦 Instalação

### Pré-requisitos
- Java 21+
- Maven 3.6+
- Bot Token do Discord

### Configuração

1. **Clone o repositório**
   ```bash
   git clone https://github.com/seu-usuario/viacep-bot.git
   cd viacep-bot
   ```

2. **Configure o token do Discord**
   
   Crie `src/main/resources/application.properties`:
   ```properties
   discord.token=SEU_TOKEN_AQUI
   ```

3. **Execute a aplicação**
   ```bash
   ./mvnw spring-boot:run
   ```

## 🎮 Comandos

### 🔢 `!cep <CEP>`
Busca informações de endereço através do CEP.

**Exemplo:** `!cep 01310100`

**Resposta:**
```
🏠 Logradouro: Avenida Paulista
🏘️ Bairro: Bela Vista
🏙️ Cidade: São Paulo - SP
```

### 🔍 `!find <estado>, <cidade>, <rua>`
Busca CEPs através de informações de endereço.

**Exemplo:** `!find rs, novo hamburgo, rua sarandi`

### 🆘 `!help`
Exibe a lista de comandos disponíveis.

## ⚙️ Configuração do Discord Bot

1. Acesse o [Discord Developer Portal](https://discord.com/developers/applications)
2. Crie uma nova aplicação e bot
3. Copie o token e configure no projeto
4. Convide o bot para seu servidor com permissões de enviar mensagens

## 🤝 Contribuindo

1. Fork o projeto
2. Crie uma branch (`git checkout -b feature/nova-feature`)
3. Commit suas mudanças (`git commit -m 'Add nova feature'`)
4. Push para a branch (`git push origin feature/nova-feature`)
5. Abra um Pull Request
