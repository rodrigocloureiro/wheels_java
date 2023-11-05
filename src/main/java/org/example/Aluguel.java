package org.example;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Aluguel {
    private Date dataInicio;
    private Cliente cliente;
    private Bike bike;
    private int numeroDeDias;
    private int idAluguel;

    private static int hireCount = 0;

    public Aluguel(Date dataInicio, int numeroDeDias, Bike bikeParaAlugar, Cliente cliente) {
        this.dataInicio = dataInicio;
        this.numeroDeDias = numeroDeDias;
        this.cliente = cliente;
        this.bike = bikeParaAlugar;
        this.idAluguel = hireCount + 1;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Bike getBike() {
        return bike;
    }

    public int getNumeroDeDias() {
        return numeroDeDias;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public String getDataFormatada() {
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss EEEE", Locale.getDefault());
        return formatador.format(this.dataInicio).toUpperCase();
    }

    public int getIdAluguel() {
        return idAluguel;
    }
}
