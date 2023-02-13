import java.util.*;
import java.util.stream.Collectors;

public class Ranking implements Imprimir {

    private List<Apostador> apostadores;
    private List<Jogo> jogos;

    public Ranking(List<Jogo> jogos, List<Apostador> apostadores) {
        this.apostadores = apostadores;
        this.jogos = jogos;
    }

    public ArrayList<Apostador> getPontuacaoGeral() {
        return apostadores.stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public void imprimir() {
        int i = 1;
        for (Apostador apostador : this.getPontuacaoGeral()) {
            System.out.println("\t\t" + i + " " + apostador.getNome() + " -- \t" + apostador.getPontos() + " Pontos ");
            i++;
        }
    }
}
