import java.util.ArrayList;

public class Bolao implements Premio {
    private ArrayList<Aposta> apostas;
    private ArrayList<Apostador> apostadores;
    private boolean finalizado;
    private int cotas;

    public Bolao(boolean finalizado, int cotas) {
        this.setFinalizado(finalizado);
        this.setCotas(cotas);
        this.setApostadores(new ArrayList<>());
        this.setApostas(new ArrayList<>());
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
    public boolean distribuirPremio() {
        return false;
    }
}
