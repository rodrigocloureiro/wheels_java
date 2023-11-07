package org.example;

import com.itextpdf.text.DocumentException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmitirBicicletaUI {

    // Configurando os atributos da classe
    private Bike bikeEscolhida;
    private Cliente cliente;
    private Pagamento pagamento;
    private Aluguel aluguel;
    private int numeroDeDias;

    public void exibirDetalhesBicicleta(int numeroBicicleta) throws Exception {
        // Exibe os detalhes da bicicleta encontrada
        procurarBike(numeroBicicleta).exibirDetalhes();
    }

    public void verificarDisponibilidade(int numeroBicicleta) throws Exception {
        int quantidade = procurarBike(numeroBicicleta).getQuantidade();
        if (quantidade == 0) throw new Exception("Bicicleta indisponível (fora de estoque)");
    }

    private Bike procurarBike(int numeroBicicleta) throws Exception {
        // Encontra a bicicleta pelo número
        bikeEscolhida = Bike.procurarBikePeloNumero(numeroBicicleta);
        if (bikeEscolhida != null) return bikeEscolhida;
        else {
            // Se não encontrarmos a bicicleta, avisamos o usuário e não devolvemos nada
            throw new Exception("\nBicicleta de número '" + numeroBicicleta + "' não encontrada");
        }
    }

    public void alugar() throws DocumentException, IOException {
        bikeEscolhida.alugarBicicleta(bikeEscolhida);
        Relatorio relatorio = new Relatorio(cliente, bikeEscolhida, aluguel);
        relatorio.gerarRelatorio();
        atualizarEstoque();
        ReciboPDF pdf = new ReciboPDF(cliente, bikeEscolhida, aluguel);
        pdf.gerarPDF();
    }

    private void atualizarEstoque() throws IOException {
        Path file = Paths.get("estoque/estoque.csv");
        List<String> novasLinhas = new ArrayList<>();

        for (var bike : Bike.getListaBicicletas()) {
            String novaLinha = gerarLinha(bike.getDeposito(),
                    bike.getTaxa(),
                    bike.getNumeroBicicleta(),
                    bike.getQuantidade(),
                    bike.getModelo()
            );

            novasLinhas.add(novaLinha);
        }

        Files.write(file, novasLinhas, StandardOpenOption.TRUNCATE_EXISTING);
    }

    private String gerarLinha(int deposito, int taxa, int numeroBicicleta, int quantidade, String modelo) {
        return String.format("%d;%d;%d;%d;%s",
                deposito,
                taxa,
                numeroBicicleta,
                quantidade,
                modelo);
    }

    public int calcularCusto(int numDays) {
        // Atribui o número de dias recebido via parâmetro ao atributo numeroDeDias
        numeroDeDias = numDays;
        // Calcula o custo da bicicleta de acordo com o número de dias
        return bikeEscolhida.calcularCusto(numDays);
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
