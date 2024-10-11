public class Item {
    private final String descricao;
    private final double valorUnitario;
    private double valorTotal;

    public Item(String descricao, double valorUnitario) {
        this.descricao = descricao;
        this.valorUnitario = valorUnitario;
        this.valorTotal = valorUnitario;

    }

    public void setValorTotal(int quantidade){
        this.valorTotal *= quantidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public double getValorTotal() {
        return valorTotal;
    }
}
 // Verificar o item 5.6.1, total do item, com o professor
 
