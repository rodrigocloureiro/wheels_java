package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;

public class Relatorio {
    private Cliente cliente;
    private Bike[] listaBicicletas;
    private Bike bike;
    private Aluguel aluguel;
    private Path dir;

    public Relatorio(Cliente cliente, Bike bike, Aluguel aluguel) {
        this.cliente = cliente;
        this.bike = bike;
        this.aluguel = aluguel;
    }

    // Informações que serão escritas no relatório
    private String gerarLinha() {
        return String.format("%s - %s (CEP: %s, Tel.: %s) alugou a bicicleta '%s' por %d dias no total de £%d\n",
                aluguel.getDataFormatada(),
                cliente.getNome(),
                cliente.getCep(),
                cliente.getTelefone(),
                aluguel.getBike().getModelo(),
                aluguel.getNumeroDeDias(),
                bike.calcularCusto(aluguel.getNumeroDeDias()));
    }

    private void atualizarNumeroPedidos() throws IOException {
        Path file = Paths.get("relatorio/relatorio.txt");
        int numeroDePedidos = Integer.parseInt(Files.readAllLines(file).get(0).split(":")[1].trim());
        Files.write(file,
                String.format("Pedidos: %d\n", numeroDePedidos + 1).getBytes(),
                StandardOpenOption.WRITE);
    }

    // Cria o diretório do relatório
    private void criarDiretorio() throws IOException {
        dir = Paths.get("relatorio");
        if (!Files.exists(dir)) Files.createDirectory(dir);
    }

    // Cria o arquivo do relatório no formado txt
    private Path criarArquivo() throws IOException {
        Path file = dir.resolve(Paths.get("relatorio.txt"));
        if (!Files.exists(file)) Files.createFile(file);
        return file;
    }

    // Escreve no relatório
    private void escreverLinha(Path file) throws IOException {
        atualizarNumeroPedidos();
        Files.write(file, gerarLinha().getBytes(), StandardOpenOption.APPEND);
    }

    // Cria o relatório
    public void gerarRelatorio() {
        try {
            criarDiretorio();
            escreverLinha(criarArquivo());
        } catch (IOException ex) {
            System.out.println(ex + " - Erro ao lidar com arquivo.");
        }
    }
}
