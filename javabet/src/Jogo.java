import java.util.*;

public class Jogo {
    private String campeonato;
    private String pais;
    private String[] times = {"",""};
    private int[] placar = {0,0};
    private boolean finalizado;

    public Jogo(String campeonato, String pais) {
        this.campeonato = campeonato;
        this.pais = pais;
        this.finalizado = false;
    }

    public Jogo() {
    }

    public String getCampeonato() {
        return campeonato;
    }

    public void setCampeonato(String campeonato) {
        this.campeonato = campeonato;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String[] getTimes() {
        return times;
    }

    public void setTimes(int time, String nomeTime) {
        getTimes()[time] = nomeTime;
    }

    public int[] getPlacar() {
        return placar;
    }

    public void setPlacar(int index, int placar) {
        getPlacar()[index] = placar;
    }

    public boolean isFinalizado() {
        return finalizado;
    }

    public void setFinalizado(boolean finalizado) {
        this.finalizado = finalizado;
    }

    @Override
    public String toString() {
        return "Jogo{" + "campeonato=" + campeonato + ", pais=" + pais + ", times=" + Arrays.toString(times) + ", placar=" + Arrays.toString(placar) + ", finalizado=" + finalizado + '}';
    }
}
