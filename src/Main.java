public class Main {
    public static void main(String[] args) {

        /*
            This little program will run through the methods on EmitirBicicletaUI
            calling each in turn, like a user with a front end would do.
        */

        // Primeiro, UI criada
        EmitirBicicletaUI ui = new EmitirBicicletaUI();

        // 1. Exibindo detalhes da bicicleta escolhida
        ui.exibirDetalhesBicicleta(100);

        // 2. Calculando o custo de alugar esta bicicleta por 5 dias
        ui.calcularCusto(5);

        // 3. Criando novo cliente
        ui.criarCliente("Les Hargreaves", "PW2 6TR", 1462501339);

        // 4. Calculando o custo total
        ui.calcularPagamentoTotal();
    }
}