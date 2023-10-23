import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        /*
            Este pequeno programa irá rodar através dos métodos de EmitirBicicletaUI
            chamando cada um por vez, como faria um usuário com um front-end.
        */

        Scanner sc = new Scanner(System.in);

        System.out.println("Opções de bicicletas para alugar\n");

        for (int i = 0; i < Bike.getListaBicicletas().length; i++) {
            System.out.println(Bike.getListaBicicletas()[i]);
        }

        System.out.print("\nEscolha uma bicicleta pelo número: ");
        int bicicletaEscolhida = sc.nextInt();

        System.out.print("A bicicleta será alugada por quantos dias? ");
        int qtdDias = sc.nextInt();

        // Primeiro, UI criada
        EmitirBicicletaUI ui = new EmitirBicicletaUI();

        try {
            // 1. Exibindo detalhes da bicicleta escolhida
            ui.exibirDetalhesBicicleta(bicicletaEscolhida);

            // 2. Calculando o custo de alugar esta bicicleta por 5 dias
            System.out.println("O custo seria £" + ui.calcularCusto(qtdDias));

            System.out.print("\nDeseja prosseguir com o aluguel? 1 - Sim | 2 - Não = ");
            int opcao = sc.nextInt();
            sc.nextLine();

            if (opcao == 1) {
                System.out.print("\nQual seu nome? ");
                String nome = sc.nextLine();

                System.out.print("Qual seu CEP? ");
                String cep = sc.nextLine();

                System.out.print("Qual seu telefone? ");
                int telefone = sc.nextInt();

                // 3. Criando novo cliente
                ui.criarCliente(nome, cep, telefone);

                // 4. Calculando o custo total
                ui.calcularPagamentoTotal();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}