import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Pedido {
    private final Funcionario funcionario;
    private final Departamento departamento;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.FRENCH);
    private final LocalDate dataAbertura;
    private LocalDate dataFechamento;
    private Status status;
    private final double valorTotal;

    public Pedido (Funcionario funcionario, double valorTotal) {
        this.funcionario = funcionario;
        this.departamento = funcionario.getDepartamento();
        this.dataAbertura = LocalDate.now();
        this.valorTotal = valorTotal;
        if (valorTotal > departamento.getLimite()) {
            this.dataFechamento = LocalDate.now();
            this.status = Status.REPROVADO;
        } else {
            this.status = Status.ABERTO;
        }
    }

    public Funcionario getFuncionario() {
        return this.funcionario;
    }

    public Departamento getDepartamento() {
        return this.departamento;
    }

    public LocalDate getDataAbertura() {
        return this.dataAbertura;
    }

    public LocalDate getDataFechamento() {
        return this.dataFechamento;
    }

    public Status getStatus() {
        return this.status;
    }

    public double getValorTotal() {
        return this.valorTotal;
    }

    public void setDataFechamento(final LocalDate dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    public void setStatus(final Status status) {
        this.status = status;
    }

    public String toString() {
        String dataAberturaString = dataAbertura.format(formatter);
        String dados = "Funcionario: " + getFuncionario().getNome() + "\n"
                + "Departamento: " + departamento.getNome() + "\n"
                + "Data de Abertura: " + dataAberturaString + "\n"
                + "Status: " + status + "\n"
                + "Valor Total: " + valorTotal + "\n";
        if (dataFechamento == null) {
            dados += "Data de Fechamento: em Aberto" + "\n";
        } else {
            String dataFechamentoString = dataFechamento.format(formatter);
            dados += "Data de Fechamento: " + dataFechamentoString + "\n";
        }
        return dados;
    }
}

// Arrumar o construtor para setar o departamento
// Verificar se o valor total do pedido ultrapassa o limite definido