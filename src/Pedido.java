import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Pedido {
    private final int id;
    private final Funcionario funcionario;
    private final Departamento departamento;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.FRENCH);
    private final LocalDate dataAbertura;
    private LocalDate dataFechamento;
    private Status status;
    private double valorTotal;
    private final List<Item> itens;
    private static int count= 0;

    public Pedido (Funcionario funcionario) {
        this.id = count++;
        this.funcionario = funcionario;
        this.departamento = funcionario.getDepartamento();
        this.dataAbertura = LocalDate.now();
        this.valorTotal = 0;
        this.itens = new ArrayList<>();
        if (valorTotal > departamento.getLimite()) {
            this.dataFechamento = LocalDate.now();
            this.status = Status.REPROVADO;
        } else {
            this.status = Status.ABERTO;
        }
    }

    public void addItem(Item item, int quantidade){
        item.setValorTotal(quantidade);
        this.valorTotal += item.getValorTotal();
        for (int i=0; i < quantidade; i++){
            itens.add(item);
        }
    }

    public int getId() {
        return id;
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

    public List<Item> getItens(){
        return itens;
    }

    public String toString() {
        String dataAberturaString = dataAbertura.format(formatter);
        String dados = "ID: " + this.getId() + "\n"
                + "Funcionario: " + getFuncionario().getNome() + "\n"
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