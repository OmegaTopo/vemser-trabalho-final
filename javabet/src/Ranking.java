import java.util.*;

public class :Ranking implements Imprimir{


    private List<Apostador> apostadores = new ArrayList<>();

    public Ranking(List<Apostador> apostadores) {
        this.apostadores = apostadores;
    }

    public Ranking(){}

    public int getPontuacaoGeral(){
        return 0;
    }

    public int getPontuacaoCampeonato(){
        return 0;
    }

    public int getPontuacaoPais(){
        return 0;
    }

    @Override
    public void imprimir() {
    }
}
