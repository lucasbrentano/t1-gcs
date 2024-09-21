public class Pedido {
    private final Funcionario funcionario;
    private final Departamento departamento;
    private final String dataAbertura;
    private final String dataFechamento;
    private Status status;
    private final double valorTotal;

    public Pedido(
            Funcionario funcionario,
            String dataAbertura,
            String dataFechamento,
            double valorTotal
    ) {
        this.funcionario = funcionario;
        this.departamento = new Departamento(dataFechamento, valorTotal);
        this.dataAbertura = dataAbertura;
        this.dataFechamento = dataFechamento;
        this.valorTotal = valorTotal;
        this.status = Status.ABERTO;
    }

}
// Arrumar o construtor para setar o departamento
// Verificar se o valor total do pedido ultrapassa o limite definido