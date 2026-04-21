# 🐾 Cadastro de Pet 3000

![Java](https://img.shields.io/badge/Java-17%2B-ED8B00?style=flat&logo=openjdk&logoColor=white)
![Status](https://img.shields.io/badge/status-conclu%C3%ADdo-4CAF50?style=flat)
![Desafio](https://img.shields.io/badge/desafio-%40devmagro-blue?style=flat)
![Commits](https://img.shields.io/badge/conventional%20commits-✓-6a0dad?style=flat)

Sistema de gerenciamento de pets para abrigos de animais, via **CLI (linha de comando)**, com persistência em arquivos `.txt`. Projeto desenvolvido como solução do [Desafio de Cadastro](https://github.com/karilho/desafioCadastro) criado por [@devmagro](https://www.linkedin.com/in/karilho/).

---

## 📋 Funcionalidades

| # | Funcionalidade | Status |
|---|---|---|
| 1 | Cadastrar novo pet com validações completas | ✅ |
| 2 | Buscar/listar pets por critério (nome, raça, idade, tipo) | ✅ |
| 3 | Alterar dados de um pet cadastrado | ✅ |
| 4 | Deletar pet com confirmação | ✅ |
| 5 | Persistência em arquivos `.txt` individuais | ✅ |

---

## 🗂️ Estrutura do Projeto

```
desafioCadastro/
├── src/
│   ├── data/
│   │   ├── formulario.txt              ← Perguntas lidas pela aplicação
│   │   └── petsCadastrados/            ← Pets salvos (gerado automaticamente)
│   │       └── 2024-01-15T14-30-BOLT.txt
│   └── br/com/desafio/
│       ├── Main.java                   ← Ponto de entrada e menu principal
│       ├── Service/
│       │   └── PetService.java         ← Operações de CRUD e persistência
│       ├── model/
│       │   ├── Pet.java                ← Entidade principal
│       │   └── Endereco.java           ← Endereço do pet
│       ├── enums/
│       │   ├── TipoPet.java            ← ENUM: Cachorro, Gato
│       │   └── SexoPet.java            ← ENUM: Macho, Femea
│       ├── exception/
│       │   ├── IdadeInvalidaException.java
│       │   └── PesoInvalidoException.java
│       └── util/
│           └── InputHelper.java        ← Validações e leitura do terminal
```

---

## 🚀 Como Executar

### Pré-requisitos

- [Java JDK 17+](https://www.oracle.com/java/technologies/downloads/) instalado
- Terminal (Linux, macOS ou Windows CMD/PowerShell)

### Compilar

```bash
# Clone o repositório
git clone https://github.com/Junionuno/desafioCadastro.git
cd desafioCadastro

# Compile todos os arquivos Java
find src/br -name "*.java" > sources.txt
javac -d out @sources.txt
```

**Windows (CMD):**
```cmd
dir /s /b src\br\*.java > sources.txt
javac -d out @sources.txt
```

### Executar

```bash
# Execute a partir da raiz do projeto
java -cp out br.com.desafio.Main
```

> ⚠️ **Importante:** execute sempre da raiz do projeto, onde está a pasta `src/`, pois os caminhos dos arquivos dependem disso.

---

## 💻 Exemplos de Uso

### Menu principal

```
==============================================
============ CADASTRO DE PET 3000 ============
==============================================
Bem vindo ao sistema de Cadastro de Pet 3000!

[1] Cadastrar um novo pet
[2] Listar pets por algum critério
[3] Alterar os dados do pet cadastrado
[4] Deletar um pet cadastrado
[5] Sair

>>
```

### Cadastrando um novo pet

```
===== Cadastro de Pets =====
1 - Qual o nome do pet?
>> Bolt
2 - Qual o tipo do pet (Cachorro/Gato)?
>> Cachorro
3 - Qual o sexo do animal?
>> Macho
4 - Qual bairro que ele foi encontrado?
Número: (opcional)
>> 42
Cidade:
>> Belo Horizonte
Rua:
>> Rua das Acácias
5 - Qual a idade aproximada do pet?
>> 3
6 - Qual o peso aproximado do pet?
>> 12
7 - Qual a raça do pet?
>> Labrador

Pet cadastrado com sucesso!
```

### Arquivo gerado em `petsCadastrados/`

```
Nome: 2024-01-15T14-30-BOLT.txt

Conteúdo:
 - Bolt
 - Cachorro
 - Macho
 - Rua das Acácias, 42, Belo Horizonte
 - 3.0 anos
 - 12.0kg
 - Labrador
```

### Buscando pets

```
===== Busca de Pets =====
Tipo cachorro (Cachorro/Gato):
>> Cachorro

Digite o valor que procura (Nome, raça ou idade):
>> Labrador

1  - Bolt - Cachorro - Macho - Rua das Acácias, 42, Belo Horizonte - 3.0 anos - 12.0kg - Labrador
```

### Deletando um pet

```
===== Excluir Cadastro =====
Tipo cachorro (Cachorro/Gato):
>> Cachorro

Digite o valor que procura (Nome, raça ou idade):
>> Bolt

1  - Bolt - Cachorro - Macho - ...

Escolha o número do pet que deseja deletar:
>> 1

Tem certeza que deseja deletar? (S/N)
>> S

Arquivo de 2024-01-15T14-30-BOLT.txt excluído com sucesso!
```

---

## 🛡️ Validações Implementadas

- **Nome:** somente letras (A-Z), sem números ou caracteres especiais
- **Tipo:** aceita apenas `Cachorro` ou `Gato` (case-insensitive)
- **Sexo:** aceita apenas `Macho` ou `Femea`
- **Idade:** somente números — lança `IdadeInvalidaException` se > 20 anos
- **Peso:** somente números — lança `PesoInvalidoException` se > 60kg ou < 0.5kg
- **Raça:** somente letras, sem números ou caracteres especiais
- **Número do endereço:** opcional — preenche com "Número não informado" se vazio

---

## 🔧 Tecnologias e Conceitos Aplicados

- **Java puro** — sem frameworks externos
- **Orientação a Objetos** — encapsulamento, separação de responsabilidades
- **Java IO** — `BufferedReader`, `BufferedWriter`, `FileReader`, `FileWriter`
- **ENUMs** — `TipoPet` e `SexoPet`
- **Exceções personalizadas** — `IdadeInvalidaException`, `PesoInvalidoException`
- **Regex** — validação de entradas com `String.matches()`
- **Conventional Commits** — histórico de commits padronizado

---

## 📝 Decisões de Projeto

Algumas escolhas foram adaptadas em relação ao enunciado original:

- **Nome do pet sem sobrenome obrigatório** — faz mais sentido para o contexto de um abrigo (pets têm nomes simples como "Noah", "Misty")
- **Formato do arquivo** com data legível (`2024-01-15T14-30-BOLT.txt`) em vez de compacto
---
