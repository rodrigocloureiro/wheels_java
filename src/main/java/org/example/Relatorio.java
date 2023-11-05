package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

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
