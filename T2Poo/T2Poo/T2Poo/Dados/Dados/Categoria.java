package Dados;

public enum Categoria {
    ACA("Ação"),
    DRA("Drama"),
    FIC("Ficção"),
    ROM("Romance");

    private String nome;

    private Categoria(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
