import java.util.*;

public class Administrador extends Usuario {

    private static ArrayList<Jogo> jogos = new ArrayList<>();

    public Administrador(String email, String senha) {
        super(email, senha);
    }

    public static ArrayList<Jogo> getJogos() {
        return jogos;
    }

    public boolean cadastrarJogo() {
        return false;
    }

    public boolean cadastrarBolao() {
        return false;
    }

    public boolean lancarResultadoDeJogo() {
        return false;
    }

    public boolean editarCadastroDeApostador() {
        return false;
    }
}

    // Precisamos montar lista de países para apresentar ao apostador para ele selecionar.
    // Depois, dentro do país, o mesmo procedimento para filtrar o campeonato.
    // e dentro do campeonato, os jogos do mesmo para ele apostar.
//    public static ArrayList<String> mostraPaises() {
//        return jogos.forEach(jogo -> jogo.getPais());
//    }
//}
