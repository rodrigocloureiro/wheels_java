public class Bike {

    // Criando a lista de bicicletas
    private static Bike[] listaBicicletas = new Bike[5];
    // Configurando os atributos da classe
    private int deposito = 0;
    private int taxa = 0;
    private int numeroBicicleta = 0;

    /*
        Este bloco é executado quando a classe é carregada e configura nossa loja de bicicletas.
        Ele preenche arbitrariamente os atributos: depósito, taxa e número da bicicleta.
    */
    static {
        int j = 0;
        for (int i = 10; i < 15; i++) {
            Bike b = new Bike(i, i, (j + 100));
            listaBicicletas[j] = b;
            j++;
        }
    }

    public Bike(int deposito, int taxa, int numeroBicicleta) {
        // Atribuindo valores aos atributos
        this.deposito = deposito;
        this.taxa = taxa;
        this.numeroBicicleta = numeroBicicleta;
    }

    public static Bike[] getListaBicicletas() { return listaBicicletas; }

    public int getDeposito() {
        return deposito;
    }

    public int getTaxa() {
        return taxa;
    }

    public int getNumeroBicicleta() {
        return numeroBicicleta;
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

    public void exibirDetalhes() {
        // Exibindo todos os detalhes
        System.out.println("Detalhes da bicicleta de número '" + numeroBicicleta + "':");
        System.out.println("DEPÓSITO: " + deposito);
        System.out.println("TAXA: " + taxa + "\n");
    }

    public int calcularCusto(int numeroDeDias) {
        // Calculando o custo
        return deposito + (taxa * numeroDeDias);
    }

    @Override
    public String toString() {
        return String.format("Número: %d : Taxa: %d - Depósito: %d", numeroBicicleta, taxa, deposito);
    }
}
