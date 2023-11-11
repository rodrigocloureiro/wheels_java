package org.example;

import lombok.Getter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

public class Bike {

    // Criando a lista de bicicletas
    @Getter
    private static Bike[] listaBicicletas = new Bike[6];
    // Configurando os atributos da classe
    @Getter
    private int deposito;
    @Getter
    private int taxa;
    @Getter
    private int numeroBicicleta;
    @Getter
    private int quantidade;
    @Getter
    private String modelo;

    /*
        Este bloco é executado quando a classe é carregada e configura nossa loja de bicicletas.
        Ele preenche os atributos depósito, taxa e número da bicicleta a partir do arquivo estoque.csv.
    */
    static {
        Path programDir = Paths.get(System.getProperty("user.dir"), "/estoque");
        Path file = programDir.resolve(Paths.get("estoque.csv"));
        try {
            List<String> linhas = Files.readAllLines(file);

            for (int i = 0; i < linhas.size(); i++) {
                String[] parte = linhas.get(i).split(";");
                Bike b = new Bike(Integer.parseInt(parte[0]),
                        Integer.parseInt(parte[1]),
                        Integer.parseInt(parte[2]),
                        Integer.parseInt(parte[3]),
                        parte[4]);
                listaBicicletas[i] = b;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Bike(int deposito, int taxa, int numeroBicicleta, int quantidade, String modelo) {
        // Atribuindo valores aos atributos
        this.deposito = deposito;
        this.taxa = taxa;
        this.numeroBicicleta = numeroBicicleta;
        this.quantidade = quantidade;
        this.modelo = modelo;
    }

    public static Bike procurarBikePeloNumero(int numeroBicicleta) {
        int numeroDeBicicletas = listaBicicletas.length;

        // Iterando sobre a lista de bicicletas
        for (int i = 0; i < numeroDeBicicletas; i++) {
            // Se encontrarmos a bike com o número correto...
            if (listaBicicletas[i].getNumeroBicicleta() == numeroBicicleta) {
                // diga ao usuário que encontramos
                System.out.println("\nBicicleta de número '" + numeroBicicleta + "' encontrada\n");
                // e retorne-a para a IU
                return listaBicicletas[i];
            }
        }

        return null;
    }

    public boolean alugarBicicleta(Bike bike) {
        if (bike.quantidade > 0) {
            bike.quantidade -= 1;
            return true;
        }

        return false;
    }

    public void exibirDetalhes() {
        // Exibindo todos os detalhes
        System.out.println("Detalhes da bicicleta de número '" + numeroBicicleta + "':");
        System.out.println("MODELO: " + modelo);
        System.out.println("DEPÓSITO: " + deposito);
        System.out.println("TAXA: " + taxa + "\n");
    }

    public int calcularCusto(int numeroDeDias) {
        // Calculando o custo
        return deposito + (taxa * numeroDeDias);
    }

    @Override
    public String toString() {
        return String.format("Número: %d - Taxa: %d - Depósito: %d - Quantidade: %d - Modelo: %s", numeroBicicleta, taxa, deposito, quantidade, modelo);
    }
}
