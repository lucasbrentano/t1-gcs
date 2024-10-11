public class Item {
    private final String descricao;
    private final double valorUnitario;
    private double valorTotal;
    private static int count = 0;
    private final int id;

    public Item(String descricao, double valorUnitario) {
        this.id = count++;
        this.descricao = descricao;
        this.valorUnitario = valorUnitario;
        this.valorTotal = valorUnitario;

    }

    public int getId() {
        return id;
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
 
