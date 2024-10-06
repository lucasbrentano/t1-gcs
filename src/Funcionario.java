import java.util.ArrayList;
import java.util.List;

public class Funcionario extends Usuario{
    private final Departamento departamento;
    public Funcionario(String nome, Departamento departamento) {
        super(nome);
        this.departamento = departamento;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void getPedidos() {
        List<Pedido> pedidos = getDepartamento().getPedidos();
        List<Pedido> aprovados = new ArrayList<>();
        List<Pedido> reprovados = new ArrayList<>();

        for (Pedido p : pedidos) {
            if (p.getStatus() == Status.APROVADO) {
                aprovados.add(p);
            } else if (p.getStatus() == Status.REPROVADO) {
                reprovados.add(p);
            }
        }

        System.out.println("Aprovados:");
        System.out.println(aprovados);
        System.out.println("Aprovados (%):" + aprovados.size() / pedidos.size());

        System.out.println("Reprovados:");
        System.out.println(reprovados);
        System.out.println("Reprovados: " + reprovados.size() / pedidos.size() + "%");
    }

}
