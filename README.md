# Tabela de Símbolos
> TRABALHO 1 - LPOO 2026

## Integrantes
- Paola Campos da Silva
- João Pedro Huppes Arenales

## Status das Atividades
### Atividade A1 (Hierarquia de Símbolos e Escopos)
> COMPLETAMENTE CONCLUÍDA

Foram implementadas todas as classes da hierarquia de símbolos (`ClassSymbol`, `Constructor`, `Method`, `Attribute`, `Variable` e `Parameter`) com suporte a tipos e modificadores restritos. A hierarquia de escopos (`GlobalScope`, `ClassScope`, `MethodScope` e `BlockScope`) também foi estruturada seguindo rigorosamente as regras de parentesco.

### Atividade A2 (Operações da Tabela de Símbolos)
> COMPLETAMENTE CONCLUÍDA

A classe `SymbolTable` foi desenvolvida com sucesso. Ela gerencia o escopo corrente dinamicamente, barra a criação de símbolos duplicados no mesmo escopo (lançando `DuplicateSymbolException`) e realiza perfeitamente a busca simples (subindo a árvore de escopos) e a busca qualificada (com validação de delimitador ".").

### Atividade A3 (Aplicação de Teste)
> COMPLETAMENTE CONCLUÍDA

Criamos uma bateria de testes separada em 3 cenários robustos (`Test1`, `Test2` e `Test3`), executados em sequência pela classe `Main`. A aplicação simula as chamadas do parser de forma idêntica à especificação, testando desde a declaração básica de variáveis até aninhamento profundo de blocos, herança, resolução de sobrecarga de métodos e falhas semânticas (Fail-safes).

## Vídeo de Apresentação
Link para o vídeo explicativo: [https://www.youtube.com/watch?v=Kx46ZV0yZso](https://www.youtube.com/watch?v=Kx46ZV0yZso)

## Tipos de commit

| Tipo       | Descrição                                                                 |
|------------|---------------------------------------------------------------------------|
| `feat`     | Adição de nova funcionalidade                                             |
| `fix`      | Correção de bug                                                           |
| `docs`     | Mudanças apenas na documentação                                           |
| `style`    | Alterações de formatação, como identação, sem alterar lógica              |
| `refactor` | Refatorações (alteração de código que não corrige nem adiciona funcionalidade) |
| `test`     | Adição ou alteração de testes                                             |
| `chore`    | Tarefas de manutenção (ex: build, configs, atualização de dependências)   |
| `add`      | Adição de novos arquivos, recursos (assets) ou textos estáticos           |
