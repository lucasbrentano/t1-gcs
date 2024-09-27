public class Funcionario extends Usuario{
    private final Departamento departamento;
    public Funcionario(String nome, Departamento departamento) {
        super(nome);
        this.departamento = departamento;
        id++;
    }

    public Departamento getDepartamento() {
        return departamento;
    }
}
