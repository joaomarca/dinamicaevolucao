# Relatório de Arquitetura de Software

## 1. Redução da Complexidade Ciclomática com Strategy

A aplicação do padrão de projeto **Strategy** reduz a complexidade ciclomática da classe `ProcessadorDeVendas`, separando as regras de cálculo de frete em classes independentes: `FreteVipStrategy`, `FreteSudesteStrategy` e `FreteNacionalStrategy`.

Na estrutura original, as regras de frete diferentes eram verificadas por meio de condicionais dentro da classe principal, aumentando o número de caminhos de execução. Com o Strategy, cada estratégia implementa a sua própria regra de cálculo, enquanto o `ProcessadorDeVendas` apenas delega o cálculo à estratégia recebida, simplificando o fluxo de processamento e facilitando a manutenção e testes.

## 2. Princípios OCP e DIP

**Open/Closed Principle (OCP):** A estrutura respeita o princípio OCP, porque a classe `ProcessadorDeVendas` não precisa ser modificada quando uma nova regra de frete vai ser adicionada. Para implementar, por exemplo, `FreteInternacionalStrategy`, basta criarmos uma nova classe que implemente a interface `EstrategiaFrete` e configurar sua utilização no ponto adequado da aplicação. Dessa forma, o sistema permite a extensão de funcionalidades sem modificar o código existente do processador.

**Dependency Inversion Principle (DIP):** O princípio DIP é respeitado, porque `ProcessadorDeVendas` depende da abstração `EstrategiaFrete`, e não de implementações concretas. A estratégia é injetada por meio do construtor, permitindo que implementações diferentes sejam utilizadas sem acoplar o processador a uma classe específica.

## Conclusão

O padrão Strategy tornou o código modular, flexível e mais fácil de manter, reduzindo a complexidade do processador e acrescentando à aplicação os princípios OCP e DIP. A inclusão de regras novas de frete passa a exigir menos alterações no código, facilitando a evolução do sistema.
