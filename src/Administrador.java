import java.sql.Array;
import java.util.*;
import java.time.LocalDate;

public class Administrador extends Funcionario {

    public Administrador(String nome, Departamento departamento) {
        super(nome, departamento);
    }

    public Map<Status, List<Pedido>> getPedidos() {
        List<Pedido> pedidos = super.getDepartamento().getPedidos();
        List<Pedido> aprovados = new ArrayList<>();
        List<Pedido> reprovados = new ArrayList<>();
        Map<Status, List<Pedido>> result = new HashMap<>();

        for (Pedido p : pedidos) {
            if (p.getStatus() == Status.APROVADO) {
                aprovados.add(p);
            } else if (p.getStatus() == Status.REPROVADO) {
                reprovados.add(p);
            }
        }

        result.put(Status.APROVADO, aprovados);
        result.put(Status.REPROVADO, reprovados);
        result.put(Status.ABERTO, pedidos);

        return result;
    }

    public static List<Pedido> listarPedidosEntreDatas(List<Pedido> listaPedidos, LocalDate dataInicio, LocalDate dataFim) {
        List<Pedido> pedidosFiltrados = new ArrayList<>();
        for (Pedido pedido : listaPedidos) {
            if (!pedido.getDataAbertura().isBefore(dataInicio) && !pedido.getDataAbertura().isAfter(dataFim)) {
                pedidosFiltrados.add(pedido);
            }
        }
        return pedidosFiltrados;
    }

    public static List<Pedido> buscarPedidosPorFuncionario(List<Pedido> listaPedidos, Funcionario funcionario) {
        List<Pedido> pedidosFiltrados = new ArrayList<>();
        for (Pedido pedido : listaPedidos) {
            if (pedido.getFuncionario().getNome().equalsIgnoreCase(funcionario.getNome())) {
                pedidosFiltrados.add(pedido);
            }
        }
        return pedidosFiltrados;
    }

    public static List<Pedido> buscarPedidosPorItem(List<Pedido> listaPedidos, String descricaoItem) {
        List<Pedido> pedidosFiltrados = new ArrayList<>();
        for (Pedido pedido : listaPedidos) {
            for (Item item : pedido.getItens()) {
                if (item.getDescricao().equalsIgnoreCase(descricaoItem)) {
                    pedidosFiltrados.add(pedido);
                    break;
                }
            }
        }
        return pedidosFiltrados;
    }

    public static String visualizarPedido(Pedido pedido) {
        return pedido.toString();
    }

    public List<Pedido> getPedidosDoMes() {
        List<Pedido> pedidosDoMes = new ArrayList<>();

        for (Pedido p : getDepartamento().getPedidos()) {
            if (p.getDataAbertura().isAfter(LocalDate.now().minusMonths(1))) {
                pedidosDoMes.add(p);
            }
        }

        return pedidosDoMes;
    }

    public List<String> getValorCadaItem() {

        List<Pedido> pedidosDoMes = getPedidosDoMes();
        Set<Item> itens = new HashSet<>();
        List<String> valorCadaItem = new ArrayList<>();

        for (Pedido p : pedidosDoMes) {
            itens.addAll(p.getItens());
        }

        for (Item item : itens) {
            //System.out.println("Nome:" + item.getDescricao() + " ID:" + item.getId() + " Valor Unitario:" + item.getValorUnitario());
            double valor = 0.0;

            for (Pedido pedido : pedidosDoMes) {

                for (Item i : pedido.getItens()) {
                    if (item.getId() == i.getId()) {
                        valor += i.getValorUnitario();
                    }
                }

                String itemValue = "Item: %s , Item ID: %d, Valor: %.2f ";
                String itemValueFormat = String.format(itemValue, item.getDescricao(), item.getId(), valor);

                if (!valorCadaItem.contains(itemValueFormat) && valor != 0.0) {
                    valorCadaItem.add(itemValueFormat);
                }
            }
        }


        return valorCadaItem;
    }

    public Pedido getPedidoMaisCaro() {
        List<Pedido> pedidos = super.getDepartamento().getPedidos();
        Pedido pedidoMaisCaro = pedidos.getFirst();

        for (Pedido p : pedidos) {
            if (p.isAberto() && p.getValorTotal() > pedidoMaisCaro.getValorTotal()) {
                pedidoMaisCaro = p;
            }
        }

        if (pedidoMaisCaro.isAberto()) {
            return pedidoMaisCaro;
        } else {
            return null;
        }
    }
}
