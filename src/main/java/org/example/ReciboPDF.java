package org.example;

import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfWriter;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class ReciboPDF {
    private Cliente cliente;
    private Bike bike;
    private Aluguel aluguel;
    private Path dir;
    private Path caminhoArquivo;
    private Document documento;
    private Font fonte;

    public ReciboPDF(Cliente cliente, Bike bike, Aluguel aluguel) {
        this.cliente = cliente;
        this.bike = bike;
        this.aluguel = aluguel;
        this.documento = new Document();
        this.fonte = FontFactory.getFont(FontFactory.COURIER, 12, BaseColor.BLACK);
    }

    // Gera o pdf
    public void gerarPDF() {
        try {
            criarDiretorio();
            PdfWriter.getInstance(this.documento, new FileOutputStream(String.valueOf(criarArquivo())));
            this.documento.open();

            criarParagrafo("Recibo - Wheels ®",
                    new Font(Font.FontFamily.COURIER, 14, Font.BOLD, BaseColor.BLACK),
                    Element.ALIGN_CENTER);

            criarParagrafo(criarDivisor(), Element.ALIGN_LEFT);
            criarParagrafo("Detalhes Pedido", Element.ALIGN_CENTER);
            criarParagrafo("Pedido: No " + aluguel.getIdAluguel(), Element.ALIGN_LEFT);
            criarParagrafo("Data/Hora: " + aluguel.getDataFormatada(), Element.ALIGN_LEFT);
            criarParagrafo(criarDivisor(), Element.ALIGN_LEFT);

            criarParagrafo("Dados Cliente", Element.ALIGN_CENTER);

            criarParagrafo("Cliente: " + cliente.getNome(), Element.ALIGN_LEFT);
            criarParagrafo("CEP: " + cliente.getCep(), Element.ALIGN_LEFT);
            criarParagrafo("Telefone: " + cliente.getTelefone(), Element.ALIGN_LEFT);
            criarParagrafo(criarDivisor(), Element.ALIGN_LEFT);

            criarParagrafo("Dados Bicicleta", Element.ALIGN_CENTER);
            criarParagrafo("Bicicleta: " + bike.getModelo(), Element.ALIGN_LEFT);
            criarParagrafo("Número: " + bike.getNumeroBicicleta(), Element.ALIGN_LEFT);
            criarParagrafo(criarDivisor(), Element.ALIGN_LEFT);

            criarParagrafo("Detalhes Pagamento", Element.ALIGN_CENTER);
            criarParagrafo("Dias alugados: " + aluguel.getNumeroDeDias(), Element.ALIGN_LEFT);
            criarParagrafo("Depósito: R$ " + bike.getDeposito(), Element.ALIGN_LEFT);
            criarParagrafo("Taxa: R$ " + bike.getTaxa(), Element.ALIGN_LEFT);

            criarParagrafo(criarDivisor(), Element.ALIGN_LEFT);
            criarParagrafo("Total: R$ " + bike.calcularCusto(aluguel.getNumeroDeDias()),
                    new Font(Font.FontFamily.COURIER, 14, Font.UNDERLINE, BaseColor.RED),
                    Element.ALIGN_RIGHT);

            this.documento.close();

            // Abre o arquivo após finalizar sua criação
            Desktop.getDesktop().open(new File(this.caminhoArquivo.toString()));
        } catch (IOException ex) {
            System.out.println(ex.getMessage() + " - Erro ao lidar com arquivo/diretório!");
        } catch (Exception ex) {
            System.out.println(ex.getMessage() + " - Erro com documento PDF!");
        }
    }

    // Cria o diretório do arquivo pdf
    private void criarDiretorio() throws IOException {
        this.dir = Paths.get("recibos");
        if (!Files.exists(this.dir)) Files.createDirectory(this.dir);
    }

    // Cria o arquivo pdf
    private Path criarArquivo() throws IOException {
        Path arquivo = this.dir.resolve(Paths.get("Recibo_" + cliente.getNome() + ".pdf"));
        if (!Files.exists(arquivo)) Files.createFile(arquivo);
        this.caminhoArquivo = arquivo;
        return arquivo;
    }

    // Cria um parágrafo com a fonte padrão do pdf (definida nos atributos)
    private void criarParagrafo(String conteudo, int alinhamento) {
        Paragraph paragrafo = new Paragraph(conteudo, this.fonte);
        paragrafo.setAlignment(alinhamento);
        adicionarParagrafo(paragrafo);
    }

    // Cria um parágrafo com fonte personalizada
    private void criarParagrafo(String conteudo, Font fonte, int alinhamento) {
        Paragraph paragrafo = new Paragraph(conteudo, fonte);
        paragrafo.setAlignment(alinhamento);
        adicionarParagrafo(paragrafo);
    }

    // Adiciona o conteúdo no pdf
    private void adicionarParagrafo(Paragraph paragrafo) {
        try {
            this.documento.add(paragrafo);
        } catch (DocumentException ex) {
            System.out.println(ex.getMessage() + " - Erro com documento PDF!");
        }
    }

    // Cria o divisor dos itens
    private String criarDivisor() {
        return "------------------------------------------------------------------------";
    }
}
