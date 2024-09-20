import java.util.ArrayList;
import java.util.List;

public class Departamento {
    private final String nome;
    private final List<Funcionario> funcionarios;
    private final List<Pedido> pedidos;

    public Departamento(String nome){
        this.funcionarios = new ArrayList<>();
        this.pedidos = new ArrayList<>();
        this.nome = nome;
    }
}
