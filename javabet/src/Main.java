public class Main {
    public static void main(String[] args) {

        Apostador moura = new Apostador("Gabriel Schramm", 7,2,1998,"12345678998","moura@mrjavabet.com","senhasupersecreta");
        moura.imprimir();

        Apostador miguel = new Apostador("Miguel Krasner", 26,5,1980,"78945612364","miguel@mrjavabet.com","tubarão123");
        miguel.imprimir();

        Apostador kleiman = new Apostador("Gabriel Kleiman",9,2,1999,"74185296351","kleiman@mrjavabet.com","password");
        kleiman.imprimir();

        Jogo grenal = new Jogo();
        grenal.getCampeonato(){

        }

        Aposta apostaMoura = new Aposta(grenal,"Grêmio",2,false);


    }
}