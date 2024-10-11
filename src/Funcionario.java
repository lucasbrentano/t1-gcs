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
}
