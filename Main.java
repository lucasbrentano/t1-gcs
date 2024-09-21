package src;

public class Main {
    public static void main(String[] args) {
        Departamento d = new Departamento("Principal", 100000.00);
        Funcionario f = new Funcionario("Juca", "0001", d);
        Item i = new Item ("Caneta", 2.00);
        Pedido p = new Pedido(f, 50.00);
        System.out.println(p.toString());

        Empresa empresa = new Empresa();
        empresa.executa();
    }
}
