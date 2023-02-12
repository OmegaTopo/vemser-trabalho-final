import java.util.ArrayList;

public class TesteKleiman{
    //    public ArrayList<Jogo> criarJogos(){
//        ArrayList<Jogo> jogos = new ArrayList<>();
//
//        Jogo grenal = new Jogo("Gauchão","Brasil");
//        grenal.setFinalizado(false);
//        grenal.setTimes(0, "Grêmio");
//        grenal.setTimes(1, "Inter");
//        grenal.setPlacar(0,2);
//        grenal.setPlacar(1,1);
//        System.out.println(grenal.toString());
//        grenal.setFinalizado(true);
//        System.out.println(grenal.toString());
//        jogos.add(grenal);
//
//        Jogo bavi = new Jogo("Libertadores", "Brasil");
//        bavi.setFinalizado(false);
//        bavi.setTimes(0, "Bahia");
//        bavi.setTimes(1, "Vitória");
//        bavi.setPlacar(0, 1);
//        bavi.setPlacar(1, 2);
//        System.out.println(bavi.toString());
//        bavi.setFinalizado(true);
//        System.out.println(bavi.toString());
//        jogos.add(bavi);
//
//        Jogo jogo3 = new Jogo("Libertadores", "Brasil");
//        jogo3.setFinalizado(false);
//        jogo3.setTimes(0, "Flamengo");
//        jogo3.setTimes(1, "Atlético-MG");
//        jogo3.setPlacar(0, 3);
//        jogo3.setPlacar(1, 0);
//        System.out.println(jogo3.toString());
//        jogo3.setFinalizado(true);
//        System.out.println(jogo3.toString());
//        jogos.add(jogo3);
//
//        Jogo jogo4 = new Jogo("Libertadores", "Brasil");
//        jogo4.setFinalizado(false);
//        jogo4.setTimes(0, "Corinthians");
//        jogo4.setTimes(1, "Santos");
//        jogo4.setPlacar(0, 0);
//        jogo4.setPlacar(1, 0);
//        System.out.println(jogo4.toString());
//        jogo4.setFinalizado(true);
//        System.out.println(jogo4.toString());
//        jogos.add(jogo4);
//
//        return jogos;
//    }
    public static void main(String[] args) {

        ArrayList<Apostador> apostadores = new ArrayList<>();

        Apostador moura = new Apostador("Gabriel Schramm", "07","02",1998,"12345678998","moura@mrjavabet.com","senhasupersecreta");
        Apostador miguel = new Apostador("Miguel Krasner", "26","05",1980,"78945612364","miguel@mrjavabet.com","tubarão123");
        Apostador kleiman = new Apostador("Gabriel Kleiman","09","02",1999,"74185296351","kleiman@mrjavabet.com","password");
        apostadores.add(moura);
        apostadores.add(miguel);
        apostadores.add(kleiman);

//        Criação de jogos para testes
        ArrayList<Jogo> jogos = new ArrayList<>();

        if (true) {

            Jogo grenal = new Jogo("Gauchão", "Brasil");
            grenal.setTimes(0, "Grêmio");
            grenal.setTimes(1, "Inter");
            grenal.setPlacar(0, 2);
            grenal.setPlacar(1, 1);
            jogos.add(grenal);

            Jogo jogo2 = new Jogo("Libertadores", "Brasil");
            jogo2.setTimes(0, "Bahia");
            jogo2.setTimes(1, "Vitória");
            jogo2.setPlacar(0, 1);
            jogo2.setPlacar(1, 2);
//        System.out.println(jogo2.toString());
            jogo2.setFinalizado(true);
//        System.out.println(jogo2.toString());
            jogos.add(jogo2);

            Jogo jogo3 = new Jogo("Libertadores", "Brasil");
//        jogo3.setFinalizado(false);
            jogo3.setTimes(0, "Flamengo");
            jogo3.setTimes(1, "Atlético-MG");
            jogo3.setPlacar(0, 3);
            jogo3.setPlacar(1, 0);
//        System.out.println(jogo3.toString());
            jogo3.setFinalizado(true);
//        System.out.println(jogo3.toString());
            jogos.add(jogo3);

            Jogo jogo4 = new Jogo("Libertadores", "Brasil");
//        jogo4.setFinalizado(false);
            jogo4.setTimes(0, "Corinthians");
            jogo4.setTimes(1, "Santos");
            jogo4.setPlacar(0, 0);
            jogo4.setPlacar(1, 0);
//        System.out.println(jogo4.toString());
            jogo4.setFinalizado(true);
//        System.out.println(jogo4.toString());
            jogos.add(jogo4);
        }

        ArrayList<Aposta> apostasK = new ArrayList<>();
        if (true){
            apostasK.add(new Aposta(jogos.get(0), "Grêmio"));
            apostasK.get(0).setPrevisaoPlacar(0, 2);
            apostasK.get(0).setPrevisaoPlacar(1, 1);

            apostasK.add(new Aposta(jogos.get(1), "Vitória"));
            apostasK.get(1).setPrevisaoPlacar(0, 2);
            apostasK.get(1).setPrevisaoPlacar(1, 1);

            apostasK.add(new Aposta(jogos.get(2), "Flamengo"));
            apostasK.get(1).setPrevisaoPlacar(0, 2);
            apostasK.get(1).setPrevisaoPlacar(1, 1);
        }

//      Aposta Kleiman
        jogos.get(0).setFinalizado(true);
        kleiman.realizarAposta(jogos.get(0), "Grêmio");
        kleiman.getApostas().get(0).setPrevisaoPlacar(0, 2);
        kleiman.getApostas().get(0).setPrevisaoPlacar(1, 0);
//        System.out.println(kleiman.getApostas().get(0).getPrevisaoPlacar()[0] + " : " + kleiman.getApostas().get(0).getPrevisaoPlacar()[1]);
        kleiman.getApostas().get(0).distribuirPremio(kleiman);

//        Criar bolao
        Bolao bolaoK = new Bolao(5);

        bolaoK.setApostas(apostasK);
        bolaoK.setApostadores(apostadores);
        for (Apostador apostador : bolaoK.getApostadores()){
            apostador.getBoloes().add(bolaoK);
        }
        bolaoK.distribuirPremio();
//        System.out.println(bolaoK.getResultado()); //OK



        System.out.println(kleiman.toString());
        System.out.println(miguel.toString());

    }
}