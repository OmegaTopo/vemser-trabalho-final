import java.util.*;
public class Aposta implements Premio {

    private Jogo jogo;
    private String previsaoTime;
    private int[] previsaoPlacar = {0,0};
    private boolean finalizado;
    final static int PONTOS_PLACAR_EXATO = 10;
    final static int PONTOS_TIME = 5;

    public Aposta(Jogo jogo, int previsaoTime1,  int previsaoTime2) {
        this.jogo = jogo;
        this.setPrevisaoPlacar(0, previsaoTime1);
        this.setPrevisaoPlacar(1, previsaoTime2);
        if (previsaoTime1 > previsaoTime2){
            this.previsaoTime = jogo.getTimes()[0];
        } else if (previsaoTime1 < previsaoTime2) {
            this.previsaoTime = jogo.getTimes()[1];
        } else {
            this.previsaoTime = "EMPATE";
        }
        this.finalizado = false;
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
    public boolean distribuirPremio(Apostador apostador) {
        if (this.getJogo().isFinalizado() && !this.getFinalizado()){
            this.setFinalizado(true);
            apostador.setPontos(apostador.getPontos() + this.getResultado());
            return true;
        }
        return false;
    }
    public boolean distribuirPremio(Bolao bolao) {
        if (this.getJogo().isFinalizado()){
            this.setFinalizado(true);
            bolao.setPontos(bolao.getPontos() + this.getResultado());
            return true;
        }
        return false;
    }

    @Override
    public boolean distribuirPremio() {
        return false;
    }

    @Override
    public int getResultado() {
        if (this.getJogo().isFinalizado()){
            String timeVencedor = "EMPATE";
            if ((this.getPrevisaoPlacar()[0] == this.getJogo().getPlacar()[0]) &&
                    (this.getPrevisaoPlacar()[1] == this.getJogo().getPlacar()[1])){
                return PONTOS_PLACAR_EXATO;
            } else if (this.getJogo().getPlacar()[0] > this.getJogo().getPlacar()[1]) {
                timeVencedor = this.getJogo().getTimes()[0];
            } else if (this.getJogo().getPlacar()[0] < this.getJogo().getPlacar()[1]) {
                timeVencedor = this.getJogo().getTimes()[1];
            }
            if (Objects.equals(this.getPrevisaoTime(), timeVencedor)){
                return PONTOS_TIME;
            }
        }
        return 0;
    }

    @Override
    public String toString() {
        return "Previsão: Vencedor " + previsaoTime + " | Placar: " + previsaoPlacar[0] + " X " + previsaoPlacar[1] + "\n";
    }
}
