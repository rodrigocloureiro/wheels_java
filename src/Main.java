public class Main {
    public static void main(String[] args) {

        /*
            Este pequeno programa irá rodar através dos métodos de EmitirBicicletaUI
            chamando cada um por vez, como faria um usuário com um front-end.
        */

        // Primeiro, UI criada
        EmitirBicicletaUI ui = new EmitirBicicletaUI();

        // 1. Exibindo detalhes da bicicleta escolhida
        ui.exibirDetalhesBicicleta(100);

        // 2. Calculando o custo de alugar esta bicicleta por 5 dias
        ui.calcularCusto(5);

        // 3. Criando novo cliente
        ui.criarCliente("Marcos", "22250-010", 99125003);

        // 4. Calculando o custo total
        ui.calcularPagamentoTotal();
    }
}