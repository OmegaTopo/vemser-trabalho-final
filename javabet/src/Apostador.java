import java.util.*;

public class Apostador extends Usuario implements Imprimir, Comparable<Apostador> {

    private int pontos;
    private ArrayList<Aposta> apostas;
    private ArrayList<Bolao> boloes;
    private String nome;
    private Date dataNascimento;
    private String cpf;
    final static double TROCA_PONTOS_POR_REAL = 10;

    public Apostador(String nome, String dia, String mes, int ano, String cpf, String email, String senha) {
        super(email, senha);
        this.nome = nome;
        this.pontos = 0;
        this.apostas = new ArrayList<>();
        this.boloes = new ArrayList<>();
        this.dataNascimento = new Date(ano - 1900, Integer.parseInt(mes) - 1, Integer.parseInt(dia));
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        String bol, apo;
        if (boloes.size() > 0) { bol = boloes.toString(); }
        else { bol = "Nenhum bolão ainda"; }
        if (apostas.size() > 0) { apo = apostas.toString(); }
        else { apo = "Nenhuma aposta ainda"; }
        return "Nome: " + nome + " | Pontos: " + pontos + "\n" +
                "Apostas:" + "\n" + apo + "\n" +
                "Boloes:" + "\n" + bol;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public ArrayList<Aposta> getApostas() {
        return apostas;
    }

    public ArrayList<Bolao> getBoloes() {
        return boloes;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public void imprimir() {
        System.out.println(this.toString());
    }

    public boolean comprarCotaBolao(Bolao bolao) {
        boloes.add(bolao);
        bolao.getApostadores().add(this);
        return true;
    }

    public double trocarPremio(int pontosTroca) {
        if ((pontosTroca <= getPontos()) && (pontosTroca > 0)) {
            double valor = (double) pontosTroca / TROCA_PONTOS_POR_REAL;
            setPontos(getPontos() - pontosTroca);
            return valor;
        } else {
            return 0;
        }
    }

    public boolean comprarPontos(int valor) {
        setPontos(getPontos() + (valor * (int) TROCA_PONTOS_POR_REAL));
        return true;
    }

    @Override
    public int compareTo(Apostador o) {
        if (this.getPontos() > o.getPontos()) {
            return 1;
        } else if (this.getPontos() < o.getPontos()) {
            return -1;
        }
        return 0;
    }
}
