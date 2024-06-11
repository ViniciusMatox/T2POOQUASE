package app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import Dados.*;

public class ACMEMidia {
    private Scanner sc;
    private BufferedReader streamEntrada;

    private Midiateca midiateca;
    
    public ACMEMidia() {
        try {
            streamEntrada = new BufferedReader(new FileReader("entrada.txt"));
            sc = new Scanner(streamEntrada);
            PrintStream streamSaida = new PrintStream(new File("saida.txt"), Charset.defaultCharset());
            System.setOut(streamSaida);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Locale.setDefault(Locale.ENGLISH);
        sc.useLocale(Locale.ENGLISH);

        midiateca = new Midiateca();
    }
    public void executa() {
        cadastraVideos();
        cadastraMusicas();
        lerDadosMidiaCodigo();
        lerDadosMidiaCategoria();
        lerDadosMidiaQualidade();
        mostrarMusicaMaiorDuracao();
        removerMidia();
        somatorioLocacoes();
    }
    private void cadastraVideos() {
        String linha;
        try {
            while ((linha = streamEntrada.readLine()) != null) {
                if (linha.equals("-1")) break;
    
                Scanner scanner = new Scanner(linha).useDelimiter(";");
                int codigo = scanner.nextInt();
                String titulo = scanner.next();
                int ano = scanner.nextInt();
                Categoria categoria = Categoria.valueOf(scanner.next().toUpperCase());
                int qualidade = scanner.nextInt();
    
                Video video = new Video(scanner.nextInt(), scanner.nextLine(), scanner.nextInt(), Categoria.valueOf(scanner.nextLine()), scanner.nextInt());
    
                if (midiateca.consultaPorCodigo(scanner.nextInt()) != null) {
                    System.out.println(String.format("1:Erro-video com codigo repetido: %d", codigo));
                } else {
                    midiateca.adicionaMidia(video);
                    System.out.println(String.format("1:%d,%s,%d,%s,%d", codigo, titulo, ano, categoria, qualidade));
                }
                
                scanner.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void cadastraMusicas() {
        String linha;
        try {
            while ((linha = streamEntrada.readLine()) != null) {
                if (linha.equals("-1")) break;

                Scanner scanner = new Scanner(linha).useDelimiter(";");
                int codigo = scanner.nextInt();
                String titulo = scanner.next();
                int ano = scanner.nextInt();
                Categoria categoria = Categoria.valueOf(scanner.next().toUpperCase());
                double duracao = scanner.nextDouble();

                Musica musica = new Musica(codigo, titulo, ano, categoria, duracao);

                if (midiateca.consultaPorCodigo(codigo) != null) {
                    System.out.println(String.format("2:Erro-musica com codigo repetido: %d", codigo));
                } else {
                    midiateca.adicionaMidia(musica);
                    System.out.println(String.format("2:%d,%s,%d,%s,%.2f", codigo, titulo, ano, categoria, duracao));
                }

                scanner.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void lerDadosMidiaCodigo() {
        int codigo = sc.nextInt();
        Midia midia = midiateca.consultaPorCodigo(codigo);
        if (midia != null) {
            System.out.println(String.format("3:%d,%s,%d,%s,%.2f", midia.getCodigo(), midia.getTitulo(), midia.getAno(), midia.getCategoria(), midia.calculaAcao()));
        } else {
            System.out.println("3:Codigo inexistente.");
        }
    }
    private void lerDadosMidiaCategoria() {
        String categoriaStr = sc.next();
        Categoria categoria = Categoria.valueOf(categoriaStr.toUpperCase());
        List<Midia> midiasPorCategoria = midiateca.consultaPorCategoria(categoria);
        if (midiasPorCategoria.isEmpty()) {
            System.out.println("4:Nenhuma midia encontrada.");
        } else {
            for (Midia midia : midiasPorCategoria) {
                System.out.println(String.format("4:%d,%s,%d,%s,%.2f", midia.getCodigo(), midia.getTitulo(), midia.getAno(), midia.getCategoria(), midia.calculaAcao()));
            }
        }
    }
    private void lerDadosMidiaQualidade() {
        int qualidadeConsulta = sc.nextInt();
        boolean encontrou = false;
        for (Midia midia : midiateca.consultaTodasMidias()) {
            if (midia instanceof Video) {
                Video video = (Video) midia;
                if (video.getQualidade() == qualidadeConsulta) {
                    System.out.println(String.format("5:%d,%s,%d,%s,%.2f", video.getCodigo(), video.getTitulo(), video.getAno(), video.getCategoria(), video.calculaLocacao()));
                    encontrou = true;
                }
            }
        }
        if (!encontrou) {
            System.out.println("5:Qualidade inexistente.");
        }
    }

    private void mostrarMusicaMaiorDuracao() {
        Musica maiorDuracao = null;
        for (Midia midia : midiateca.consultaTodasMidias()) {
            if (midia instanceof Musica) {
                Musica musica = (Musica) midia;
                if (maiorDuracao == null || musica.getDuracao() > maiorDuracao.getDuracao()) {
                    maiorDuracao = musica;
                }
            }
        }
        if (maiorDuracao == null) {
            System.out.println("6:Nenhuma m�sica encontrada.");
        } else {
            System.out.println(String.format("6:%s,%.2f", maiorDuracao.getTitulo(), maiorDuracao.getDuracao()));
        }
    }

    private void mostrarMusicaMaiorDuracao() {
    Musica maiorDuracao = null;
    for (Midia midia : midiateca.consultaTodasMidias()) {
        if (midia instanceof Musica) {
            Musica musica = (Musica) midia;
            if (maiorDuracao == null || musica.getDuracao() > maiorDuracao.getDuracao()) {
                maiorDuracao = musica;
            }
        }
    }
    if (maiorDuracao == null) {
        System.out.println("6:Nenhuma m�sica encontrada.");
    } else {
        System.out.println(String.format("6:%s,%.2f", maiorDuracao.getTitulo(), maiorDuracao.getDuracao()));
    }
}
private void removerMidia() {
    int codigo = sc.nextInt();
    Midia midia = midiateca.consultaPorCodigo(codigo);
    if (midia == null) {
        System.out.println("7:Codigo inexistente.");
    } else {
        System.out.println(String.format("7:%d,%s,%d,%s,%.2f", midia.getCodigo(), midia.getTitulo(), midia.getAno(), midia.getCategoria(), midia.calculaAcao()));
        midiateca.removeMidia(codigo);
    }
}

private void somatorioLocacoes() {
    double somatorio = 0.0;
    List<Midia> todasMidias = midiateca.consultaTodasMidias();
    
    if (todasMidias.isEmpty()) {
        System.out.println("8:Nenhuma midia encontrada.");
    } else {
        for (Midia midia : todasMidias) {
            somatorio += midia.calculaLocacao();
        }
        System.out.println(String.format("8:%.2f", somatorio));
    }
}
    
    
}
