public class Pedido {
    private final Funcionario funcionario;
    private final Departamento departamento;
    private final String dataAbertura;
    private final String dataFechamento;
    private final Status status;
    private final double valorTotal;

    public Pedido(
            Funcionario funcionario,
            Departamento departamento,
            String dataAbertura,
            String dataFechamento,
            double valorTotal
    ) {
        this.funcionario = funcionario;
        this.departamento = departamento;
        this.dataAbertura = dataAbertura;
        this.dataFechamento = dataFechamento;
        this.valorTotal = valorTotal;
        this.status = Status.ABERTO;
    }

}
