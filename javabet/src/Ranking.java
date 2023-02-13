import java.util.*;

public class Ranking implements Imprimir{

    private List<Apostador> apostadores;
    private List<Jogo> jogos;

    public Ranking(List<Apostador> apostadores) {
        this.apostadores = apostadores;
    }
    public Ranking(){}

    public ArrayList<Apostador> getPontuacaoGeral(){
        List<Apostador> listaGeral = apostadores.stream()
            .sorted(Comparator.comparing(Apostador::getPontos))
            .toList();
        System.out.println(listaGeral);
        return getPontuacaoGeral();
    }

//    public ArrayList<Jogo> getPontuacaoCampeonato() {
//        for (Jogo jogo : jogos) {
//            Jogo listaCampeonato = new Jogo();
//            System.out.println(listaCampeonato);
//        }
//        return getPontuacaoCampeonato();
//    }
//
//    public int getPontuacaoPais(){
//        getPontuacaoGeral();
//        List<Jogo> listaPais = jogos.stream()
//            .sorted(Comparator.comparing(Jogo::getPais));
//            .toList();
//        System.out.println(listaCampeonato);
//        return 0;
//    }

    @Override
    public void imprimir() {
        System.out.println("");
    }
}
