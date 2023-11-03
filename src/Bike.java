import java.util.Random;

public class Bike {

    // Criando a lista de bicicletas
    private static Bike[] listaBicicletas = new Bike[5];
    // Configurando os atributos da classe
    private int deposito;
    private int taxa;
    private int numeroBicicleta;
    private int quantidade;
    private String modelo;

    /*
        Este bloco é executado quando a classe é carregada e configura nossa loja de bicicletas.
        Ele preenche arbitrariamente os atributos: depósito, taxa e número da bicicleta.
    */
    static {
        String[] modelosBicicletas = {"Houston Foxer Hammer", "Caloi Urban E-vibe Urbam", "Track & Bikes Serena", "Caloi Lazer Andes", "BMX Colli Bikes Cross Extreme", "Caloi Strada Racing"};
        int j = 0;
        Random random = new Random();

        for (int i = 0; i < 5; i++) {
            Bike b = new Bike(random.nextInt(20) + 10, random.nextInt(20) + 10, (j + 100), random.nextInt(3) + 1, modelosBicicletas[i]);
            listaBicicletas[j] = b;
            j++;
        }
    }

    public Bike(int deposito, int taxa, int numeroBicicleta, int quantidade, String modelo) {
        // Atribuindo valores aos atributos
        this.deposito = deposito;
        this.taxa = taxa;
        this.numeroBicicleta = numeroBicicleta;
        this.quantidade = quantidade;
        this.modelo = modelo;
    }

    public static Bike[] getListaBicicletas() {
        return listaBicicletas;
    }

    public int getDeposito() {
        return deposito;
    }

    public int getTaxa() {
        return taxa;
    }

    public int getNumeroBicicleta() {
        return numeroBicicleta;
    }

    public String getModelo() {
        return modelo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public static Bike procurarBikePeloNumero(int numeroBicicleta) {
        int numeroDeBicicletas = listaBicicletas.length;

        // Iterando sobre a lista de bicicletas
        for (int i = 0; i < numeroDeBicicletas; i++) {
            // Se encontrarmos a bike com o número correto...
            if (listaBicicletas[i].getNumeroBicicleta() == numeroBicicleta) {
                // diga ao usuário que encontramos
                System.out.println("\nBicicleta de número '" + numeroBicicleta + "' encontrada\n");
                // e retorne-a para a IU
                return listaBicicletas[i];
            }
        }

        return null;
    }

    public boolean alugarBicicleta(Bike bike) {
        if (bike.quantidade > 0) {
            bike.quantidade -= 1;
            return true;
        }

        return false;
    }

    public void exibirDetalhes() {
        // Exibindo todos os detalhes
        System.out.println("Detalhes da bicicleta de número '" + numeroBicicleta + "':");
        System.out.println("MODELO: " + modelo);
        System.out.println("DEPÓSITO: " + deposito);
        System.out.println("TAXA: " + taxa + "\n");
    }

    public int calcularCusto(int numeroDeDias) {
        // Calculando o custo
        return deposito + (taxa * numeroDeDias);
    }

    @Override
    public String toString() {
        return String.format("Número: %d - Taxa: %d - Depósito: %d - Quantidade: %d - Modelo: %s", numeroBicicleta, taxa, deposito, quantidade, modelo);
    }
}
