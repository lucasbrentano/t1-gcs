import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Empresa {
    private final List<Departamento> departamentos;
    private final List<Item> estoque;
    private final List<Usuario> usuarios;
    private final Scanner scanner;
    private Departamento departamento;
    private Usuario usuarioAtivo;

    public Empresa() {
        this.departamentos = new ArrayList<>();
        this.estoque = new ArrayList<>();
        this.usuarios = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    private void initDepartamentosIniciais() {
        String[] nomesDepartametos = {"Financeiro", "Logistico", "RH", "Projetos", "Producao"};

        for (String nome : nomesDepartametos) {
            Departamento d = new Departamento(nome, 50000);
            this.departamentos.add(d);
        }
    }

    private void addAdministradorDepertamento() {
        String[] nomeAdministradores = {"Brian", "Bob", "Milton", "Maicon", "Rubens"};

        for (int i = 0; i < departamentos.size(); i++) {
            Departamento d = departamentos.get(i);
            Administrador administrador = new Administrador(nomeAdministradores[i], d);
            d.cadastraFuncionario(administrador);
            usuarios.add(administrador);
        }
    }

    private void addFuncionariosADepartamentos() {
        //Adicionar n funcionarios em cada departamento (15 min.)
        Random geraNumeros = new Random();
        String[] nomesPossiveis = {
                "Ana", "Carlos", "Fernanda", "João", "Mariana", "Ricardo", "Sofia", "Lucas", "Bruno", "Juliana",
                "Gabriel", "Laura", "Mateus", "Isabela", "Pedro", "Clara", "Felipe", "Renata", "Vinícius", "Tatiane",
                "Roberto", "Amanda", "Thiago", "Camila", "Júlio", "Rafael", "Bianca", "André", "Lúcia", "Gustavo",
                "Julio", "Luciana", "Érica", "Murilo", "Natália", "Alberto", "Priscila", "Leonardo", "Nathalia", "Cíntia"
        };

        int countNames = nomesPossiveis.length;

        for (Departamento d : departamentos) {
            for (int i = 0; i < 2; i++) {
                int indexRandom = geraNumeros.nextInt(countNames);
                String nomeFuncionario = nomesPossiveis[indexRandom];
                Funcionario f = new Funcionario(nomeFuncionario, d);
                d.cadastraFuncionario(f);
                usuarios.add(f);

            }
        }
    }

    private void inicializaPedidos() {
        for (Usuario usuario : usuarios) {
            Pedido pedido = new Pedido((Funcionario) usuario);

            Item item = new Item("Teste", new Random().nextDouble(10.50));

            pedido.addItem(item, new Random().nextInt(8));

            ((Funcionario) usuario).getDepartamento().getPedidos().add(pedido);
        }
    }


    private void initDados() {
        initDepartamentosIniciais();
        addAdministradorDepertamento();
        addFuncionariosADepartamentos();
        inicializaPedidos();
    }

    public void executa() {
        initDados();
        int opcao;
        boolean trocaDeUsuario = false;

        while (!trocaDeUsuario) {
            trocaDeUsuario = trocaUsuario();
        }

        while (true) {
            if (usuarioAtivo instanceof Administrador) {
                menuAdministrador();
                opcao = scanner.nextInt();
                scanner.nextLine();
                switch (opcao) {
                    case 1:
                        trocaUsuario();
                        break;
                    case 2:
                        registrarPedido();
                        break;
                    case 3:
                        excluiPedido();
                        break;
                    case 4:
                        avaliaPedido();
                        break;
                    case 5:
                        listarPedidos();
                        break;
                    case 6:
                        buscaPedidosPorFuncionario();
                        break;
                    case 7:
                        buscaPedidosPorDescricao();
                        break;
                    case 8:
                        mostraEstatisticas();
                        break;
                    default:
                        break;
                }
            } else {
                menuFuncionario();
                opcao = scanner.nextInt();
                scanner.nextLine();
                switch (opcao) {
                    case 1:
                        trocaUsuario();
                        break;
                    case 2:
                        registrarPedido();
                        break;
                    case 3:
                        excluiPedido();
                        break;
                    default:
                        break;
                }
            }
        }
    }


    public void menuAdministrador() {
        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║                     MENU PRINCIPAL                   ║");
        System.out.println("╠══════════════════════════════════════════════════════╣");
        System.out.println("║ 1 - Trocar Usuário                                   ║");
        System.out.println("║ 2 - Registrar Pedido                                 ║");
        System.out.println("║ 3 - Exclui Pedido                                    ║");
        System.out.println("║ 4 - Avalia Pedido                                    ║");
        System.out.println("║ 6 - Busca Pedidos Por Funcionario                    ║");
        System.out.println("║ 7 - Busca Pedidos Por Descricao                      ║");
        System.out.println("║ 8 - Mostra Estatisticas                              ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
        System.out.print("Escolha uma opção: ");
    }

    public void menuFuncionario() {
        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║                     MENU PRINCIPAL                   ║");
        System.out.println("╠══════════════════════════════════════════════════════╣");
        System.out.println("║ 1 - Trocar Usuário                                   ║");
        System.out.println("║ 2 - Registrar Pedido                                 ║");
        System.out.println("║ 3 - Exclui Pedido                                    ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
        System.out.print("Escolha uma opção: ");
    }

    private boolean trocaUsuario() {
        System.out.println("Digite o Codigo do usuario");
        int codigoUsuario = scanner.nextInt();

        for (Usuario u : usuarios) {
            if (u.getId() == codigoUsuario) {
                usuarioAtivo = u;
                System.out.println("╔══════════════════════════════════════════════════════╗");
                System.out.println("        BEM VINDO " + this.usuarioAtivo.getNome() + " :)");
                System.out.println("╚══════════════════════════════════════════════════════╝");
                return true;
            }
        }

        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║                     FALHA AO LOGAR                   ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");

        return false;

    }

    private void excluiPedido() {
        Funcionario funcionario = (Funcionario) usuarioAtivo;
        List<Pedido> pedidos = funcionario.getDepartamento().getPedidos();
        Pedido pedidoExcluido;
        int id = 0;
        while (true) {
            System.out.println("Digite o ID do pedido que você quer excluir");
            id = scanner.nextInt();
            if (id < 0) {
                System.out.println("Número digitado invalido!");

            } else {
                break;
            }

        }

        for (Pedido p : pedidos) {
            if (p.getId() == id && p.getStatus().equals(Status.ABERTO) && p.getFuncionario().equals(funcionario)) {
                pedidoExcluido = p;
                funcionario.getDepartamento().getPedidos().remove(pedidoExcluido);
                System.out.println("Pedido Excluído com sucesso!");
                return;
            } else {
                System.out.println("Pedido não encontrado ou não pode ser excluído!");
                return;
            }
        }
    }

    private void avaliaPedido() {
        System.out.println("Informe a ID do pedido: ");
        int codigoPedido = scanner.nextInt();
        scanner.nextLine();
        for (Departamento d : departamentos) {
            if (((Funcionario) usuarioAtivo).getDepartamento().equals(d)) {
                boolean pedidoEncontrado = false;
                for (Pedido p : d.getPedidos()) {
                    if (p.getId() == codigoPedido && p.getStatus().equals(Status.ABERTO)) {
                        System.out.println("Pedido " + p.getId() + " encontrado. \n" +
                                p.toString() +
                                "\nSelecione uma opção: ");
                        System.out.println("1 - Aprovar pedido.");
                        System.out.println("2 - Reprovar pedido.");
                        System.out.println("3 - Sair");
                        int opcao = scanner.nextInt();
                        scanner.nextLine();
                        switch (opcao) {
                            case 1:
                                p.setStatus(Status.APROVADO);
                                System.out.println("Pedido aprovado com sucesso!");
                                break;
                            case 2:
                                p.setStatus(Status.REPROVADO);
                                System.out.println("Pedido reprovado com sucesso!");
                                break;
                            case 3:
                                return;
                            default:
                                break;
                        }
                        pedidoEncontrado = true;
                        break;
                    } else if (p.getId() == codigoPedido && !p.getStatus().equals(Status.ABERTO)) {
                        System.out.println("Pedido " + p.getId() + " encerrado.");
                        pedidoEncontrado = true;
                        break;
                    }
                }
                if (!pedidoEncontrado) {
                    System.out.println("Pedido não encontrado!");
                }
            }
        }
    }

    private void buscaPedidosPorFuncionario() {
        System.out.println("Digite o codigo do usuario");
        int codigoUsuario = scanner.nextInt();
        scanner.nextLine();
        Funcionario usuario = null;
        for (Usuario u : usuarios) {
            if (u.getId() == codigoUsuario) {
                usuario = (Funcionario) u;
                break;
            }
        }

        if (usuario == null) {
            System.out.println("Usuario não encontrado");
            return;
        }

        List<Pedido> pedidosDepartamento = usuario.getDepartamento().getPedidos();

        List<Pedido> pedidoUsuario = ((Administrador) usuarioAtivo).buscarPedidosPorFuncionario(pedidosDepartamento, usuario);

        for (Pedido p : pedidoUsuario) {
            System.out.println("╔══════════════════════════════════════════════════════╗");
            System.out.println("║                      PEDIDO                          ║");
            System.out.println("╚══════════════════════════════════════════════════════╝");

            System.out.println(p);
        }
    }

    private void buscaPedidosPorDescricao() {

        Administrador funcionario = (Administrador) usuarioAtivo;
        List<Pedido> pedidos = funcionario.getDepartamento().getPedidos();

        System.out.println("Insira o item que deseja buscar:");
        String descricaoItem = scanner.nextLine();

        List<Pedido> pedidosPorItem = funcionario.buscarPedidosPorItem(pedidos, descricaoItem);

        for (Pedido p : pedidosPorItem) {
            System.out.println("╔══════════════════════════════════════════════════════╗");
            System.out.println("║                      PEDIDO                          ║");
            System.out.println("╚══════════════════════════════════════════════════════╝");

            System.out.println(p);
        }
    }

    public void listarPedidos() {
        String dataDeInicio;
        String dataFinal;
        departamento = ((Administrador) usuarioAtivo).getDepartamento();
        List<Pedido> pedidos = departamento.getPedidos();

        System.out.println("Digite a data inicial:(Formato : dia/mes/ano ) ");
        dataDeInicio = scanner.nextLine();
        System.out.println("Digite a data final: (Formato : dia/mes/ano )");
        dataFinal = scanner.nextLine();

        //Definindo a formatação da data
        DateTimeFormatter formatacao = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        //Convertendo de String para LocalDate
        LocalDate dataIni = LocalDate.parse(dataDeInicio, formatacao);
        LocalDate dataFim = LocalDate.parse(dataFinal, formatacao);

        for (Pedido p : Administrador.listarPedidosEntreDatas(pedidos, dataIni, dataFim)) {
            System.out.println(p);
        }
    }

    private void mostraEstatisticas() {

        Map<Status, List<Pedido>> pedidos = ((Administrador) usuarioAtivo).getPedidos();
        List<Pedido> aprovados = pedidos.get(Status.APROVADO);
        List<Pedido> reprovados = pedidos.get(Status.APROVADO);
        List<Pedido> total = pedidos.get(Status.ABERTO);
        List<Pedido> pedidosDoMes = ((Administrador) usuarioAtivo).getPedidosDoMes();
        List<String> valorTotalCadaItem = ((Administrador) usuarioAtivo).getValorCadaItem();

        double valorTotal = 0;

        for (Pedido p : pedidosDoMes) {
            valorTotal += p.getValorTotal();
        }

        double valorMedio = valorTotal / pedidosDoMes.size();

        Pedido pedidoMaisCaro = ((Administrador) usuarioAtivo).getPedidoMaisCaro();

        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║                     ESTATISTICAS                     ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");

        System.out.println("Pedidos aprovados:");
        System.out.println(aprovados);
        System.out.println("Aprovados (%):" + aprovados.size() / total.size() + "%");

        System.out.println("Pedidos reprovados:");
        System.out.println(reprovados);
        System.out.println("Reprovados: " + reprovados.size() / total.size() + "%");

        if (pedidoMaisCaro == null) {
            System.out.println("Não há pedido");
        } else {
            System.out.println("Pedido de maior valor:" + pedidoMaisCaro);
        }

        System.out.println("Pedidos dos ultimos 30 dias: ");
        System.out.println(pedidosDoMes);
        System.out.println("Valor medio dos pedidos: " + valorMedio);

        System.out.println("Valor total de cada tipo de item: ");
        System.out.println(valorTotalCadaItem);

    }

    private void registrarPedido() {
        Funcionario funcionario = (Funcionario) usuarioAtivo;
        Pedido pedido = new Pedido(funcionario);

        while (!"-1".equals(scanner.nextLine())) {
            System.out.println("Insira um item no pedido");
            System.out.println("Descricao do item (nome):");
            String descricao = scanner.nextLine();
            System.out.println("Valor unitario");
            double valorUnitario = scanner.nextDouble();
            scanner.nextLine();
            System.out.println("Quantidade deste item");
            int quantidade = scanner.nextInt();
            scanner.nextLine();

            Item item = new Item(descricao, valorUnitario);

            pedido.addItem(item, quantidade);

            System.out.println("Deseja continuar? Se sim, digite qualquer coisa. Se nao, digite -1");
        }


        funcionario.getDepartamento().getPedidos().add(pedido);
    }

}

//Fazer com que seja possível definir o usuário ativo.
//Pedido pode ser excluido somente pelo usuário que o criou (somente quando o status estiver aberto)
