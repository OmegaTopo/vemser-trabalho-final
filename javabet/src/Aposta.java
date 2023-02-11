import java.util.*;
public class Aposta implements Premio {

    private Jogo jogo;
    private String previsaoTime;
    private int[] previsaoPlacar = {0,0};
    private boolean finalizado;

    public Aposta(Jogo jogo, String previsaoTime) {
        this.jogo = jogo;
        this.previsaoTime = previsaoTime;
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

    public void setPrevisaoPlacar(int index, int previsaoPlacar) {
        getPrevisaoPlacar()[index] = previsaoPlacar;
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

    @Override
    public String toString() {
        return "Aposta{" + "jogo=" + jogo + ", previsaoTime=" + previsaoTime + ", previsaoPlacar=" + Arrays.toString(previsaoPlacar) + ", finalizado=" + finalizado + '}';
    }
}
