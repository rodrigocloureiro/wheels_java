package org.example;

import lombok.Getter;

public class Cliente {
    // Configurando os atributos da classe
    @Getter
    private String nome;
    @Getter
    private String cep;
    @Getter
    private int telefone;
    private int idCliente;

    private static int contadorClientes = 1;

    public Cliente(String nome, String cep, int telefone) {
        this.nome = nome;
        this.cep = cep;
        this.telefone = telefone;
    }
}
