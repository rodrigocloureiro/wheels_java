package org.example;

import lombok.Getter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Aluguel {
    @Getter
    private Date dataInicio;
    @Getter
    private Cliente cliente;
    @Getter
    private Bike bike;
    @Getter
    private int numeroDeDias;
    @Getter
    private int idAluguel;

    private static int hireCount = 0;

    public Aluguel(Date dataInicio, int numeroDeDias, Bike bikeParaAlugar, Cliente cliente) {
        try {
            Path file = Paths.get("relatorio/relatorio.txt");
            int numeroDePedidos = Integer.parseInt(Files.readAllLines(file).get(0).split(":")[1].trim());
            this.dataInicio = dataInicio;
            this.numeroDeDias = numeroDeDias;
            this.cliente = cliente;
            this.bike = bikeParaAlugar;
            this.idAluguel = numeroDePedidos + 1;
        } catch (IOException ex) {
            System.out.println(ex.getMessage() + " - Erro ao lidar com o arquivo!");
        }
    }

    public String getDataFormatada() {
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss EEEE", Locale.getDefault());
        return formatador.format(this.dataInicio).toUpperCase();
    }
}
