import java.util.ArrayList;
import java.util.List;

public class Departamento {
    private final String nome;
    private final List<Funcionario> funcionarios;
    private final List<Pedido> pedidos;
    private final double limite; // Valor limitante do pedido

    public Departamento(String nome, double limite){
        this.funcionarios = new ArrayList<>();
        this.pedidos = new ArrayList<>();
        this.nome = nome;
        this.limite = limite;
    }
}
