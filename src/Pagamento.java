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
        System.out.println("Imprimindo recibo para '" + cliente + "'.....");
        System.out.println("No CEP: " + cep + "\n");

        System.out.println("Alugando bicicleta de número '" + aluguel.getBike().getNumeroBicicleta() + "' por " + aluguel.getNumeroDeDias() + " dias\n");

        aluguel.getBike().calcularCusto(aluguel.getNumeroDeDias());
    }
}
