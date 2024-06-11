package Dados;

public class Musica extends Midia {
    private int duracao;

    public Musica(int codigo, String titulo, int ano, Categoria categoria, int duracao) {
        super(codigo, titulo, ano, categoria);
        this.duracao = duracao;
    }

    public int getDuracao() {
        return duracao;
    }

    @Override
    public double calculaLocacao() {
        double valorPorMinuto;
        switch (getCategoria()) {
            case ACA:
                valorPorMinuto = 0.90;
                break;
            case DRA:
                valorPorMinuto = 0.70;
                break;
            case FIC:
                valorPorMinuto = 0.50;
                break;
            case ROM:
                valorPorMinuto = 0.30;
                break;
            default:
                valorPorMinuto = 0.0;
        }
        return duracao * valorPorMinuto;
    }

    @Override
    public String toString() {
        return super.toString() + "," + duracao + "," + calculaLocacao();
    }
}
