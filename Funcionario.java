package src;

public class Funcionario extends Usuario{
    private Departamento departamento;

    public Funcionario(String nome, String id, Departamento departamento) {
        super(nome, id);
        this.departamento = departamento;
    }

    public Departamento getDepartamento() {
        return departamento;
    }
}
