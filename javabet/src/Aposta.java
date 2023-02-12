import java.util.*;
public class Aposta implements Premio {

    private Jogo jogo;
    private String previsaoTime;
//    Talvez o ideal seja previsaoPlacar inicial como {-1, -1}, assim ficará claro que não foram feitos palpites no placarExato
    private int[] previsaoPlacar = {0,0};
    private boolean finalizado;
    final static int PONTOS_PLACAR_EXATO = 10;
    final static int PONTOS_TIME = 5;

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
    public boolean distribuirPremio(Apostador apostador) {
//      Caso o jogo tenha sido finalizado e a aposta não: define o fim da aposta, recebe o resultado e incrementa na pontuação do apostador
        if (this.getJogo().isFinalizado() && !this.getFinalizado()){
            this.setFinalizado(true);
            apostador.setPontos(apostador.getPontos() + this.getResultado());
            return true;
        }
        return false;
    }
    public boolean distribuirPremio(Bolao bolao) {
//      Caso o jogo tenha sido finalizado e a aposta não: define o fim da aposta, recebe o resultado e incrementa na pontuação do bolãor
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
//        O retorno automático de uma aposta em jogo em andamento é ZERO
        if (this.getJogo().isFinalizado()){
            String timeVencedor = "EMPATE";
//            Primeiro é verificado se o palpite de placar bate com o do Jogo
//            System.out.println(this.getPrevisaoPlacar()[0] + ":" + this.getPrevisaoPlacar()[1] + " " + this.getJogo().getPlacar() + " " + (this.getPrevisaoPlacar() == this.getJogo().getPlacar()));
//            if (this.getPrevisaoPlacar() == this.getJogo().getPlacar()){     //Não funciona pois compara os endereções de memória
            if ((this.getPrevisaoPlacar()[0] == this.getJogo().getPlacar()[0]) && (this.getPrevisaoPlacar()[1] == this.getJogo().getPlacar()[1])){
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
        return "Aposta{" + "jogo=" + jogo + ", previsaoTime=" + previsaoTime + ", previsaoPlacar=" + Arrays.toString(previsaoPlacar) + ", finalizado=" + finalizado + '}';
    }
}
