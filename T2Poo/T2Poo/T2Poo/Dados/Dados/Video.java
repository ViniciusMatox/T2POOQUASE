package Dados;

public class Video extends Midia {
    private int qualidade;

    public Video(int codigo, String titulo, int ano, Categoria categoria, int qualidade) {
        super(codigo, titulo, ano, categoria);
        this.qualidade = qualidade;
    }

    public int getQualidade() {
        return qualidade;
    }

    @Override
    public double calculaLocacao() {
        int ano = getAno();
        if (ano == 2024) {
            return 20.00;
        } else if (ano >= 2000 && ano <= 2023) {
            return 15.00;
        } else {
            return 10.00;
        }
    }

    @Override
    public String toString() {
        return super.toString() + "," + qualidade + "," + calculaLocacao();
    }
}
