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

<<<<<<< HEAD
    public Aposta (){

=======
    public Aposta() {
>>>>>>> a5f873a7515cb3c978121a828ec691e3d26c7bf1
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

    public boolean getFinalizado() {
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
