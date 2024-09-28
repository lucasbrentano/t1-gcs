import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

public class Administrador extends Funcionario {

    public Administrador(String nome, Departamento departamento) {
        super(nome, departamento);
        id++;
    }

    public void getPedidos() {
        List<Pedido> pedidos = super.getDepartamento().getPedidos();
        List<Pedido> aprovados = new ArrayList<>();
        List<Pedido> reprovados = new ArrayList<>();

        for (Pedido p : pedidos) {
            if (p.getStatus() == Status.APROVADO) {
                aprovados.add(p);
            } else if (p.getStatus() == Status.REPROVADO) {
                reprovados.add(p);
            }
        }

        System.out.println("Aprovados:" );
        System.out.println(aprovados);
        System.out.println("Aprovados (%):" + aprovados.size()/pedidos.size());

        System.out.println("Reprovados:");
        System.out.println(reprovados);
        System.out.println("Reprovados: " + reprovados.size()/pedidos.size() + "%");
    }

    public void getPedidosDoMes(){
        List<Pedido> result = new ArrayList<>();
        double valorMedio = 0;

        for (Pedido p : getDepartamento().getPedidos()){
            if ( p.getDataAbertura().isBefore(LocalDate.now().minusDays(30))){
                result.add(p);
            }
        }

        for (Pedido p : result){
            valorMedio += p.getValorTotal();
        }

        System.out.println("Pedidos dos ultimos 30 dias: " );
        System.out.println(result);
        System.out.println("Valor medio dos pedidos: " + valorMedio/result.size() );

    }
}

//Valor total de cada tipo de item nos últimos 30 dias.
//        Detalhes do pedido de aquisição de maior valor ainda aberto.