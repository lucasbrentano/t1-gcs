public abstract class Usuario {
    private String nome;
    private static int count = 0;
    private int id;

    public Usuario(String nome){
        this.nome = nome;
        count ++;
        this.id = count;
    }

    public String getNome() {
        return nome;
    }

    public int getId(){
        return id;
    }

}