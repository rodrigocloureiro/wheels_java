public class Cliente {
    // Configurando os atributos da classe
    private String nome;
    private String cep;
    private int telefone ;
    private int idCliente;

    private static int contadorClientes = 1;

    public Cliente(String nome, String cep, int telefone) {
        this.nome = nome;
        this.cep = cep;
        this.telefone = telefone;
    }

    public int getNumeroCliente() {
        return idCliente;
    }

    public String getNome() {
        return nome;
    }

    public String getCep() {
        return cep;
    }
}
