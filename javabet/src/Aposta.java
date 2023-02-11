public class Aposta implements Premio {

    private Jogo jogo;
    private String previsaoTime;
    private int previsaoPlacar[];
    private boolean finalizado;

    public Aposta(Jogo jogo, String previsaoTime, int previsaoPlacar[], boolean finalizado) {
        this.jogo = jogo;
        this.previsaoTime = previsaoTime;
        this.previsaoPlacar = previsaoPlacar;
        this.finalizado = false;
    }

    public Aposta() {
    }

    public Jogo getJogo() {
        return jogo;
    }

    public void setJogo(Jogo jogo) {
        this.jogo = jogo;
    }

    public String getPrevisaoTime() {
        return previsaoTime;
    }

    public void setPrevisaoTime(String previsaoTime) {
        this.previsaoTime = previsaoTime;
    }

    public int[] getPrevisaoPlacar() {
        return previsaoPlacar;
    }

    public void setPrevisaoPlacar(int previsaoPlacar[]) {
        this.previsaoPlacar = previsaoPlacar;
    }

    public boolean isFinalizado() {
        return finalizado;
    }

    public void setFinalizado(boolean finalizado) {
        this.finalizado = finalizado;
    }

    @Override
    public boolean distribuirPremio() {
        return false;
    }
}
