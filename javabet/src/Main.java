//public class Main {
//    public static void main(String[] args) {
//
//        Apostador moura = new Apostador("Gabriel Schramm", "07","02",1998,"12345678998","moura@mrjavabet.com","senhasupersecreta");
//        moura.imprimir();
//
//        Apostador miguel = new Apostador("Miguel Krasner", "26","05",1980,"78945612364","miguel@mrjavabet.com","tubarão123");
//        miguel.imprimir();
//
//        Apostador kleiman = new Apostador("Gabriel Kleiman","09","02",1999,"74185296351","kleiman@mrjavabet.com","password");
//        kleiman.imprimir();
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
//
//        Aposta apostaMoura = new Aposta(grenal,"Grêmio");
//        apostaMoura.setPrevisaoPlacar(0,2);
//        apostaMoura.setPrevisaoPlacar(1,1);
//        apostaMoura.setPrevisaoTime("Grêmio");
//        System.out.println(apostaMoura.toString());
//
//        Bolao bolaoMoura = new Bolao(10);
//
////        bolaoMoura.setApostadores(moura, miguel, kleiman);
////        bolaoMoura.cadastrarAposta(apostaMoura);
//
//    }
//
//}