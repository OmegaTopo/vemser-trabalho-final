public class Jogo {
    private String campeonato;
    private String pais;
    private String[] times = new String[2];
    private int[] placar = new int[2];
    private boolean finalizado;

    public Jogo(String campeonato, String pais, boolean finalizado) {
        this.setCampeonato(campeonato);
        this.setPais(pais);
        this.setFinalizado(finalizado);
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

    public void setTimes(String[] times) {
        this.times = times;
    }

    public int[] getPlacar() {
        return placar;
    }

    public void setPlacar(int[] placar) {
        this.placar = placar;
    }

    public boolean isFinalizado() {
        return finalizado;
    }

    public void setFinalizado(boolean finalizado) {
        this.finalizado = finalizado;
    }
}
