public class Administrador extends Usuario{
    public Administrador(String email, String senha){
        super(email, senha);
    }

    public boolean cadastrarJogo(){
        return false;
    }
    public boolean cadastrarBolao(){
        return false;
    }
    public boolean lancarResultadoDeJogo(){
        return false;
    }
    public boolean editarCadastroDeApostador(){
        return false;
    }
}
