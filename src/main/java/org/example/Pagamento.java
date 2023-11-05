package org.example;

public class Pagamento {
    // Configurando os atributos da classe
    private Cliente cliente;
    private int idPagamento;

    private static int contadorPagamento = 1;

    public Pagamento(Cliente cliente) {
        // Atribui valor aos atributos da classe
        this.cliente = cliente;
        this.idPagamento = contadorPagamento++;
    }

    public void calcularPagamentoTotal(Aluguel aluguel) {
        // Chama o método privado emitirRecibo()
        emitirRecibo(aluguel);
    }

    private void emitirRecibo(Aluguel aluguel) {
        // Exibe todos os detalhes relevantes
        String cliente = aluguel.getCliente().getNome();
        String cep = aluguel.getCliente().getCep();
        int telefone = aluguel.getCliente().getTelefone();

        System.out.println("\nImprimindo recibo.....\n");
        System.out.printf("Cliente: %s\nCEP: %s\nContato: %d\n", cliente, cep, telefone);
        System.out.println("Bicicleta alugada: Nº" + aluguel.getBike().getNumeroBicicleta() + " - " + aluguel.getBike().getModelo());
        System.out.println("Dias alugados: " + aluguel.getNumeroDeDias());
        System.out.println("Valor total: £" + aluguel.getBike().calcularCusto(aluguel.getNumeroDeDias()));
    }
}
