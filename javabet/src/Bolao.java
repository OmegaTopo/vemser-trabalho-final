import java.util.*;

public class Bolao implements Premio {
    private ArrayList<Aposta> apostas;
    private ArrayList<Apostador> apostadores;
    private boolean finalizado;
    private int cotas;
    private int pontos = 0;

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public Bolao(int cotas) {
        this.setCotas(cotas);
        this.setApostadores(new ArrayList<>());
        this.setApostas(new ArrayList<>());
        this.setFinalizado(false);
    }

    public Bolao() {
    }

    public boolean cadastrarAposta(Aposta aposta) {
        try {
            this.apostas.add(aposta);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean cadastrarApostador(Apostador apostador) {
        try {
            this.apostadores.add(apostador);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public ArrayList<Aposta> getApostas() {
        return this.apostas;
    }

    public void setApostas(ArrayList<Aposta> apostas) {
        this.apostas = apostas;
    }

    public ArrayList<Apostador> getApostadores() {
        return this.apostadores;
    }

    public void setApostadores(ArrayList<Apostador> apostadores) {
        this.apostadores = apostadores;
    }

    public boolean isFinalizado() {
        return this.finalizado;
    }

    public void setFinalizado(boolean finalizado) {
        this.finalizado = finalizado;
    }

    public int getCotas() {
        return this.cotas;
    }

    public void setCotas(int cotas) {
        this.cotas = cotas;
    }

    @Override
    public String toString() {
        return "Bolao | Apostas:\n" + apostas +
                "\nFinalizado? " + finalizado +
                '\n';
    }
    @Override
    public boolean distribuirPremio(Bolao bolao) {
        return false;
    }
    @Override
    public boolean distribuirPremio(Apostador apostador) {
        int pontoPorCota = this.getPontos()/this.getCotas();
        this.setFinalizado(true);
        apostador.setPontos(apostador.getPontos() + pontoPorCota);
        return true;
    }
    @Override
    public boolean distribuirPremio() {
        ArrayList<Boolean> distribuicaoOK = new ArrayList<>();
        for (Aposta aposta : this.getApostas()){
            distribuicaoOK.add(aposta.distribuirPremio(this));
        }
        if (!distribuicaoOK.contains(false)){//Contem falso
            for (Apostador apostador : this.getApostadores()){
                this.distribuirPremio(apostador);
            }
        }
        return true;
    }
    @Override
    public int getResultado() {
        return this.getApostas().stream()
                .mapToInt(Aposta::getResultado)
                .sum();
    }
}
