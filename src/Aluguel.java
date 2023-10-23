import java.util.Date;

public class Aluguel {
    private Date dataInicio;
    private Cliente cliente;
    private Bike bike;
    private int numeroDeDias;
    private int idAluguel;

    private static int hireCount = 1;

    public Aluguel(Date dataInicio, int numeroDeDias, Bike bikeParaAlugar, Cliente cliente) {
        this.dataInicio = dataInicio;
        this.numeroDeDias = numeroDeDias;
        this.cliente = cliente;
        this.bike = bikeParaAlugar;
        this.idAluguel = hireCount++;
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
}
