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
         Departamento d = new Departamento(nome, 50000);
         this.addFuncionarioADepartamento(d);
         this.departamentos.add(d);
       }
    }

    private void addFuncionarioADepartamento(Departamento d){
      //Adicionar n funcionarios em cada departamento (15 min.)
      Random geraNumeros = new Random();
      String[] nomesPossiveis = {
          "Ana", "Carlos", "Fernanda", "João", "Mariana", "Ricardo", "Sofia", "Lucas", "Bruno", "Juliana",
          "Gabriel", "Laura", "Mateus", "Isabela", "Pedro", "Clara", "Felipe", "Renata", "Vinícius", "Tatiane",
          "Roberto", "Amanda", "Thiago", "Camila", "Júlio", "Rafael", "Bianca", "André", "Lúcia", "Gustavo",
          "Julio", "Luciana", "Érica", "Murilo", "Natália", "Alberto", "Priscila", "Leonardo", "Nathalia", "Cíntia"
      };

      int countNames = nomesPossiveis.length;
      for(int i = 0 ; i < 3 ; i++) {
        int indexRandom = geraNumeros.nextInt(countNames);
        String nomeFuncionario = nomesPossiveis[indexRandom];
        Funcionario f = new Funcionario(nomeFuncionario, d);
        // d.addFuncionario(f);
      }
  }


    public void executa(){
      this.initDepartamentosIniciais();
    }

}
//Fazer com que seja possível definir o usuário ativo.
//Pedido pode ser excluido somente pelo usuário que o criou (somente quando o status estiver aberto)
