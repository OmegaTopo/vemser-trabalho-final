import java.util.ArrayList;
import java.util.Date;

public class Apostador extends Usuario implements Imprimir{

    private int pontos;
    private ArrayList<Aposta> apostas;
    private ArrayList<Bolao> boloes;
    private String nome;
    private Date dataNascimento;
    private String cpf;

    public Apostador(String nome, int dia, int mes, int ano, String cpf, String email, String senha) {
        super(email, senha);
        this.nome = nome;
        this.dataNascimento = new Date(ano, mes, dia);
        this.cpf = cpf;
        this.pontos = 0;
        this.apostas = new ArrayList<>();;
        this.boloes = new ArrayList<>();;
    }

    @Override
    public String toString() {
        return "Apostador " +
                "Nome: " + nome + "\n" +
                "Data de nascimento: " + dataNascimento + "\n" +
                "CPF: " + cpf + "\n" +
                "Pontos: " + pontos + "\n" +
                "Apostas:" + "\n" + apostas + "\n" +
                "Boloes:" + "\n" + boloes;
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
}
