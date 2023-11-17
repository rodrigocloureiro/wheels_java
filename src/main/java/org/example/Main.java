package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        /*
            Este pequeno programa irá rodar através dos métodos de EmitirBicicletaUI
            chamando cada um por vez, como faria um usuário com um front-end.
        */

        Scanner sc = new Scanner(System.in);

        // Primeiro, UI criada
        EmitirBicicletaUI ui = new EmitirBicicletaUI();

        while (true) {

            System.out.println("Opções de bicicletas para alugar\n");

            for (int i = 0; i < Bike.getListaBicicletas().length; i++) {
                System.out.println(Bike.getListaBicicletas()[i]);
            }

            System.out.print("\nEscolha uma bicicleta pelo número: ");
            int bicicletaEscolhida = sc.nextInt();

            try {
                // Verifica a disponibilodade da bicicleta
                ui.verificarDisponibilidade(bicicletaEscolhida);

                System.out.print("A bicicleta será alugada por quantos dias? ");
                int qtdDias = sc.nextInt();

                try {
                    // 1. Exibindo detalhes da bicicleta escolhida
                    ui.exibirDetalhesBicicleta(bicicletaEscolhida);

                    // 2. Calculando o custo de alugar esta bicicleta por 5 dias
                    System.out.println("Custo: R$ " + ui.calcularCusto(qtdDias));

                    System.out.print("\nProsseguir com o aluguel? 1 - Sim | 2 - Não = ");
                    int opcao = sc.nextInt();
                    sc.nextLine();

                    if (opcao == 1) {
                        System.out.println("===== PREENCHA COM OS DADOS DO CLIENTE =====");
                        System.out.print("\nNome: ");
                        String nome = sc.nextLine();

                        System.out.print("CEP: ");
                        String cep = sc.nextLine();

                        System.out.print("Telefone: ");
                        int telefone = sc.nextInt();

                        // 3. Criando novo cliente
                        ui.criarCliente(nome, cep, telefone);

                        // 4. Calculando o custo total e alugando bicicleta
                        ui.calcularPagamentoTotal();
                        ui.alugar();
                    }
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }

            System.out.println("\n\n\n");
        }
    }
}