package src;

public abstract class Usuario {
    private String nome;
    private String id;

    protected Usuario(final String nome, final String id) {
        this.nome = nome;
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public String getId() {
        return this.id;
    }
}

// adicionar os tipos (Funcionario ou Administrador)
// Criar Método para ID
// Criar Método abstrato para registro de novos pedidos (Recebe como parâmetros uma lista de item)