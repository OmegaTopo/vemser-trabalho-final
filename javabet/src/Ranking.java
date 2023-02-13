import java.util.*;
import java.util.stream.Collectors;

public class Ranking implements Imprimir{

    private List<Apostador> apostadores;
    private List<Jogo> jogos;

    public Ranking(List<Jogo> jogos, List<Apostador> apostadores) {
        this.apostadores = apostadores;
        this.jogos = jogos;
    }

    public ArrayList<Apostador> getPontuacaoGeral(){
        return apostadores.stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toCollection(ArrayList::new));
    }


//    public ArrayList<Apostador> getPontuacaoGeral(){
//        for (Apostador apostador : apostadores) {
//            ArrayList<Apostador> ranking = new ArrayList<>();
//
//            System.out.println(apostador);
//        }
//        return getPontuacaoGeral();
//    }

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
        getPontuacaoGeral();
        System.out.println(getPontuacaoGeral());
    }
}
