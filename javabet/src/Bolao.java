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
    public boolean distribuirPremio(Apostador apostador) {
//        if (!this.isFinalizado()){
            int pontoPorCota = this.getPontos()/this.getCotas();
            this.setFinalizado(true);
            apostador.setPontos(apostador.getPontos() + pontoPorCota);
            return true;
//        }

//        if (!this.getApostas().isEmpty()){
//            int soma = this.getApostas().stream()
//                    .filter(aposta -> aposta.getFinalizado())
//                    .mapToInt(Aposta::getPrevisaoPlacar()[0])
//                    .sum();
//        }
//        return false;
    }

    @Override
    public boolean distribuirPremio(Bolao bolao) {
        return false;
    }

    @Override
    public boolean distribuirPremio() {
//        Este variação do distribuirPremio tem como função percorrer toda a lista de apostas para solicitar o premio para o bolão
        ArrayList<Boolean> distribuicaoOK = new ArrayList<>();
        for (Aposta aposta : this.getApostas()){
            distribuicaoOK.add(aposta.distribuirPremio(this));
        }
//        System.out.println(!distribuicaoOK.contains(false));
        if (!distribuicaoOK.contains(false)){//Contem falso
            for (Apostador apostador : this.getApostadores()){
//                Nesse ponto é invocada a distribuição de pontos para cada apostador do bolão
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
