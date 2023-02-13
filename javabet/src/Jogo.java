import java.util.*;

public class Jogo {
    private static Scanner scanner = new Scanner(System.in);
    private String campeonato;
    private String pais;
    private String[] times = {"",""};
    private int[] placar = {0,0};
    private boolean finalizado;

    public Jogo(String campeonato, String pais, String time1, String time2) {
        times[0] = time1;
        times[1] = time2;
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

    public void setTimes(int index, String nomeTime) {
        this.times[index] = nomeTime;
    }

    public int[] getPlacar() {
        return placar;
    }

    public void setPlacar(int placar1, int placar2) {
        this.placar[0] = placar1;
        this.placar[1] = placar2;
    }

    public boolean isFinalizado() {
        return finalizado;
    }

    public void setFinalizado(boolean finalizado) {
        this.finalizado = finalizado;
    }

    public static String gerarCampeonato(){
        String campeonato;
        System.out.println("Digite o nome do campeonato: ");
        campeonato = scanner.nextLine();
        return campeonato;
    }

    @Override
    public String toString() {
        return times[0] + " x " + times[1] + " | " + campeonato + " | " + pais;
    }
}