package Dados;

import java.util.ArrayList;
import java.util.List;

public class Midiateca implements Iterador<Midia> {
    private List<Midia> midias;
    private int posicao;

    public Midiateca() {
        this.midias = new ArrayList<>();
        this.posicao = 0;
    }

    public void adicionarMidia(Midia midia) {
        midias.add(midia);
    }

    public Midia buscarMidiaPorCodigo(int codigo) {
        for (Midia midia : midias) {
            if (midia.getCodigo() = codigo) {
                return midia;
            }
        }
        return null;
    }

    public List<Midia> buscarMidiasPorCategoria(Categoria categoria) {
        List<Midia> resultado = new ArrayList<>();
        for (Midia midia : midias) {
            if (midia.getCategoria().equals(categoria)) {
                resultado.add(midia);
            }
        }
        return resultado;
    }

    public List<Video> buscarVideosPorQualidade(String qualidade) {
        List<Video> resultado = new ArrayList<>();
        for (Midia midia : midias) {
            if (midia instanceof Video) {
                Video video = (Video) midia;
                if (video.getQualidade().equals(qualidade)) {
                    resultado.add(video);
                }
            }
        }
        return resultado;
    }

    public Musica buscarMusicaMaiorDuracao() {
        Musica maiorDuracao = null;
        for (Midia midia : midias) {
            if (midia instanceof Musica) {
                Musica musica = (Musica) midia;
                if (maiorDuracao == null || musica.getDuracao() > maiorDuracao.getDuracao()) {
                    maiorDuracao = musica;
                }
            }
        }
        return maiorDuracao;
    }

    public void removerMidia(Midia midia) {
        midias.remove(midia);
    }

    public double calcularSomatorioLocacoes() {
        double total = 0;
        for (Midia midia : midias) {
            total += midia.calculaLocacao();
        }
        return total;
    }

    @Override
    public void reset() {
        posicao = 0;
    }

    @Override
    public boolean hasNext() {
        return posicao < midias.size();
    }

    @Override
    public Midia next() {
        if (hasNext()) {
            return midias.get(posicao++);
        }
        return null;
    }
}
