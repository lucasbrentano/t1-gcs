import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Empresa {
    private final List<Departamento> departamentos;
    private final List<Item> estoque;
    private final List<Usuario> usuarios;

    public Empresa(){
        this.departamentos = new ArrayList<>();
        this.estoque = new ArrayList<>();
        this.usuarios = new ArrayList<>();
    }

    private void initDepartamentosIniciais() {
      String []nomesDepartametos = {"Financeiro","Logistico","RH","Projetos","Producao"};
       for(String nome : nomesDepartametos){
         Departamento d = new Departamento(nome, 1000000);
         this.departamentos.add(d);
         //TODO: add funcionarios ao departamento
       }
    }


    public void executa(){

    }

    
}
//Fazer com que seja possível definir o usuário ativo.
//Pedido pode ser excluido somente pelo usuário que o criou (somente quando o status estiver aberto)
