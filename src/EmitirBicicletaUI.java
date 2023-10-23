import java.util.Date;

public class EmitirBicicletaUI {

    // Configurando os atributos da classe
    private Bike bikeEscolhida;
    private Cliente cliente;
    private Pagamento pagamento;
    private Aluguel aluguel;
    private int numeroDeDias;

    public void exibirDetalhesBicicleta(int bikeNum) {
        // Encontra a bicicleta pelo número
        bikeEscolhida = Bike.procurarBikePeloNumero(bikeNum);
        if (bikeEscolhida != null) {
            // Exibe os detalhes da bicicleta encontrada
            bikeEscolhida.exibirDetalhes();
        }
    }

    public void calcularCusto(int numDays) {
        // Atribui o número de dias recebido via parâmetro ao atributo numeroDeDias
        numeroDeDias = numDays;
        // Calcula o custo da bicicleta de acordo com o número de dias
        bikeEscolhida.calcularCusto(numDays);
    }

    public void criarCliente(String nome, String cep, int telefone) {
        // Cria um cliente e associado aluguel e pagamento
        cliente = new Cliente(nome, cep, telefone);
        pagamento = new Pagamento(cliente);
        aluguel = new Aluguel(new Date(), numeroDeDias, bikeEscolhida, cliente);
    }

    public void calcularPagamentoTotal() {
        // Obtém o pagamento total do alguel
        pagamento.calcularPagamentoTotal(aluguel);
    }
}
