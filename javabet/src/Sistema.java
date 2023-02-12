import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Sistema {
    private Scanner scanner = new Scanner(System.in);
    private ArrayList<Apostador> apostadores = new ArrayList<>();
    private ArrayList<Administrador> administradores = new ArrayList<>();
    private ArrayList<Jogo> jogos = new ArrayList<>();
    private ArrayList<Bolao> boloes = new ArrayList<>();
    private Usuario usuarioAtivo;

//  Funções relacionadas ao Apostador
    public void cadastroDeApostador() {
        try {
            System.out.println("\n\tCADASTRO DE APOSTADOR\n---------------------");
//        E-mail
            System.out.print("\tE-mail: ");
            String email = scanner.nextLine();
//        Senha
            System.out.print("\tSenha: ");
            String senha = scanner.nextLine();
//        Nome completo
            System.out.print("\tNome completo: ");
            String nome = scanner.nextLine();
//        CPF
            System.out.print("\tCPF: ");
            String cpf = scanner.nextLine();
//        Ano nascimento
            System.out.print("\tAno de nascimento (ex: 1990): ");
            int ano = scanner.nextInt();
            scanner.nextLine();
//        Mês nascimento
            System.out.print("\tMês de nascimento (ex: 05): ");
            String mes = scanner.nextLine();
//        Dia nascimento
            System.out.print("\tDia de nascimento (ex: 05): ");
            String dia = scanner.nextLine();
//        Fim do cadastro
            apostadores.add(new Apostador(nome, dia, mes, ano, cpf, email, senha));
            System.out.println("\n----CADASTRO REALIZADO COM SUCESSO----\n");
        } catch (Exception e) {
            System.out.println("\n----ERRO AO REALIZAR O CADASTRO----\n");
        }
    }

    public void listarApostadores() {
        System.out.println("\n\t\tLISTA DE APOSTADORES\n\t\t---------------------");
        for (int i = 0; i < apostadores.size(); i++) {
            System.out.println("\t\t\t"+(i + 1) + ". " + apostadores.get(i).getNome());
        }
    }
    public void atualizarApostador() {
        System.out.println("\n\tATUALIZAÇÃO DE APOSTADOR\n\t------------------------");
        System.out.print("\tDigite o CPF do apostador: ");
        String cpf = scanner.nextLine();
        boolean encontrado = false;
        for (Apostador apostador : apostadores) {
            if (apostador.getCpf().equals(cpf)) {
                encontrado = true;
                System.out.println("\tO que deseja alterar?");
                System.out.println("\t1 - Nome completo");
                System.out.println("\t2 - Data de nascimento");
                System.out.println("\t3 - E-mail");
                System.out.println("\t4 - Senha");
                System.out.print("\tDigite a opção desejada: ");
                String opcao = scanner.nextLine();
                switch (opcao) {
                    case "1":
                        System.out.print("\tDigite o novo nome completo: ");
                        apostador.setNome(scanner.nextLine());
                        break;
                    case "2":
                        System.out.print("\tNovo ano de nascimento: ");
                        int ano = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("\tNovo mês de nascimento: ");
                        String mes = scanner.nextLine();
                        System.out.print("\tNovo dia de nascimento: ");
                        String dia = scanner.nextLine();
                        Date dataNascimento = new Date(ano-1900, Integer.parseInt(mes) - 1, Integer.parseInt(dia));
                        apostador.setDataNascimento(dataNascimento);
                        break;
                    case "3":
                        System.out.print("\tDigite o novo e-mail: ");
                        apostador.setEmail(scanner.nextLine());
                        break;
                    case "4":
                        System.out.print("\tDigite a nova senha: ");
                        apostador.setSenha(scanner.nextLine());
                        break;
                }
                break;
            }
        }
        if (!encontrado) {
            System.out.println("\n\t----APOSTADOR NÃO ENCONTRADO----");
        }
    }

    public void removerApostador() {
        System.out.println("\n\tREMOÇÃO DE APOSTADOR\n---------------------");
        listarApostadores();
        System.out.print("\tSelecione o número do apostador que deseja remover: ");
        String selecao = scanner.nextLine();
        try {
            apostadores.remove(Integer.parseInt(selecao) - 1);
            System.out.println("\tApostador removido com sucesso.");
        } catch (Exception e) {
            System.out.println("\n\t----APOSTADOR NÃO ENCONTRADO----");
        }

    }
    public void apostadorRealizarAposta(){
        if (!(usuarioAtivo instanceof Apostador)) {
            System.out.println("\n\t----USUÁRIO INVÁLIDO PARA ESTE MÉTODO----");
            return;
        }
        System.out.println("\n\tRealizar Aposta\n--------------------");
        listarJogos();
        System.out.print("\t\tEscolha o jogo para apostar:");
        String opcao = scanner.nextLine();
        int index = 0; // Este padrão é apenas para não dar erro adiante, mas ao tentar transformar a opção em int isso será trocado.
        try {
            index = Integer.parseInt(opcao);
        } catch (Exception e){
            System.out.println("\n\t----JOGO NÃO ENCONTRADO----");
            return;
        }
        Jogo jogo = jogos.get(index - 1);
        System.out.println("\tVocê escolheu o jogo: " + jogo.getTimes()[0] + " x " + jogo.getTimes()[1] + " | " + jogo.getCampeonato() + " | " + jogo.getPais());
        System.out.println("\t\t1. " + jogo.getTimes()[0]);
        System.out.println("\t\t2. " + jogo.getTimes()[1]);
        System.out.print("\tEscolha o time para apostar:");
        opcao = scanner.nextLine();
        String escolhaTime = "";
        if (opcao.equals("1")) {
            escolhaTime = jogo.getTimes()[0];
        } else if (opcao.equals("2")) {
            escolhaTime = jogo.getTimes()[1];
        }
        System.out.println("\tVocê escolheu o time " + escolhaTime + " para apostar");
        System.out.println("\tDigite a previsão de placar para " + jogo.getTimes()[0] + ": ");
//        Adicionar tratamento de erro
        int previsaoTime1 = scanner.nextInt();
        scanner.nextLine();
        System.out.println("\tDigite a previsão de placar para " + jogo.getTimes()[1] + ": ");
//        Adicionar tratamento de erro
        int previsaoTime2 = scanner.nextInt();
        scanner.nextLine();
        Aposta novaAposta = new Aposta(jogo, escolhaTime);
        novaAposta.setPrevisaoPlacar(0, previsaoTime1);
        novaAposta.setPrevisaoPlacar(1, previsaoTime2);
        apostadores.get(apostadores.indexOf(usuarioAtivo)).getApostas().add(novaAposta);
        //REALIZAR PRINT DA APOSTA FINAL
        System.out.println("\n\t----APOSTA REALIZADA COM SUCESSO----");
    }

//    Métodos relacionados ao Administrador
    public void cadastroDeAdministrador() {
        try {
            System.out.println("\n\tCADASTRO DE ADMINISTRADOR\n---------------------");
//        E-mail
            System.out.print("\tE-mail: ");
            String email = scanner.nextLine();
//        Senha
            System.out.print("\tSenha: ");
            String senha = scanner.nextLine();
//        Fim do cadastro
            administradores.add(new Administrador(email, senha));
        } catch (Exception e) {
            System.out.println("\n----ERRO AO REALIZAR O CADASTRO----\n");
        }
    }
//    Métodos relacionados aos Jogos
    public void listarJogos() {
    System.out.println("\n\t\tLISTA DE JOGOS\n\t\t---------------------");
    Jogo jogo;
    for (int i = 0; i < jogos.size(); i++) {
        jogo = jogos.get(i);
//            Isso tem que ir para o Jogo.toString()
        System.out.println("\t\t\t"+(i + 1) + ". " + jogo.getTimes()[0] + " x " + jogo.getTimes()[1] + " | " + jogo.getCampeonato() + " | " + jogo.getPais());
    }
}

    //    Métodos gerais de funcionamento do sistema
    public boolean verificarLogin(String email, String senha) {
        for (Apostador apostador : apostadores) {
            if (apostador.getEmail().equals(email) && apostador.getSenha().equals(senha)) {
                usuarioAtivo = apostador;
                return true;
            }
        }
        for (Administrador administrador : administradores) {
            if (administrador.getEmail().equals(email) && administrador.getSenha().equals(senha)) {
                usuarioAtivo = administrador;
                return true;
            }
        }
        return false;
    }

    public void menuPrincipal() {
        String opcao = "";
        while (!opcao.equals("0")) {
            System.out.println("\n\tMenu Principal\n--------------------");
            System.out.println("\t1. Cadastro de Apostador");
            System.out.println("\t2. Consulta de Apostadores");
            System.out.println("\t3. Atualizar");
            System.out.println("\t4. Remover");
            System.out.println("\t5. Login");
            System.out.println("\t6. Consulta de Jogos");
            System.out.println("\t0. Sair");
            System.out.print("\tDigite a opção desejada: ");
            opcao = scanner.nextLine();
            switch (opcao) {
                case "CADM":
                    cadastroDeAdministrador();
                    break;
                case "1":
                    cadastroDeApostador();
                    break;
                case "2":
                    listarApostadores();
                    break;
                case "3":
                    atualizarApostador();
                    break;
                case "4":
                    removerApostador();
                    break;
                case "5":
                    menuLogin();
                    break;
                case "6":
                    listarJogos();
                    break;
                case "0":
                    System.out.println("\tSaindo do sistema...");
                    break;
                default:
                    System.out.println("\tOpção inválida!");
                    menuPrincipal();
                    break;
            }
        }
    }

    private void menuLogin() {
        System.out.println("\n\tLOGIN\n\t----------");
        System.out.print("\tE-mail: ");
        String email = scanner.nextLine();
        System.out.print("\tSenha: ");
        String senha = scanner.nextLine();

        if (verificarLogin(email, senha)) {
            if (usuarioAtivo instanceof Apostador) {
                menuApostador();
            } else if (usuarioAtivo instanceof Administrador) {
                menuAdministrador();
            } else {
                System.out.println("\n\t----E-MAIL OU SENHA INVÁLIDOS----");
                menuPrincipal();
            }
        } else {
            System.out.println("\n\t----USUÁRIO NÃO ENCONTRADO----");
        }
    }
    public void menuAdministrador(){
        System.out.println("\n\tBEM VINDO " + usuarioAtivo.getEmail().toUpperCase() + "!\n\t-------------------");
        String opcao = "";
        while (!opcao.equals("0")) {
            System.out.println("\n\tMENU ADMINISTRADOR\n---------------------");
            System.out.println("\t1 - CRUD Apostador");
            System.out.println("\t2 - CRUD Administrador");
            System.out.println("\t3 - CRUD Aposta");
            System.out.println("\t4 - CRUD Bolão");
            System.out.println("\t5 - CRUD Jogo");
            System.out.println("\t6 - GERAR APOSTADORES TESTE");
            System.out.println("\t7 - GERAR JOGOS TESTE");
            System.out.println("\t8 - GERAR BOLÃO TESTE");
            System.out.println("\t0 - Sair");
            System.out.print("\tEscolha uma opção: ");
            opcao = scanner.nextLine();
            switch (opcao) {
                case "1":
                    crudApostador();
                    break;
                case "2":
                    crudAdministrador();
                    break;
                case "3":
                    crudAposta();
                    break;
                case "4":
                    crudBolao();
                    break;
                case "5":
                    crudJogo();
                    break;
                case "6":
                    testeGerarApostadores();
                    System.out.println("\n\t----APOSTADORES TESTE GERADOS----");
                    break;
                case "7":
                    testeGerarJogos();
                    System.out.println("\n\t----JOGOS TESTE GERADOS----");
                    break;
                case "8":
                    testeGerarBolao();
                    System.out.println("\n\t----BOLÃO TESTE GERADOS----");
                    break;
                case "0":
                    break;
                default:
                    System.out.println("\n\tOpção inválida");
                    break;
            }
        }
    }
    public void menuApostador() {
        System.out.println("\n\tBEM VINDO " + usuarioAtivo.getEmail().toUpperCase() + "!\n-------------------");
        String opcao = "";
        while (!opcao.equals("0")) {
            System.out.println("\n\tMENU APOSTADOR\n---------------------");
            System.out.println("\t1 - Realizar uma aposta");
            System.out.println("\t2 - Comprar cota de bolão");
            System.out.println("\t3 - Verificar resultado de apostas");
            System.out.println("\t4 - Verificar resultado de bolões");
            System.out.println("\t0 - Sair");
            System.out.print("\tEscolha uma opção: ");
            opcao = scanner.nextLine();
            switch (opcao) {
                case "1":
                    System.out.println("\nRealizar uma aposta");
                    apostadorRealizarAposta();
                    break;
                case "2":
                    //Listar bolões
                    System.out.println("\nComprar cota de bolão");
                    break;
                case "3":
                    System.out.println("\nVerificar resultado de apostas");
                    break;
                case "4":
                    System.out.println("\nVerificar resultado de bolões");
                    break;
                case "0":
                    System.out.println("\nSaindo...");
                    break;
                default:
                    System.out.println("\nOpção inválida");
                    break;
            }
        }
    }
//    CRUD MENU
    public void crudApostador(){
        System.out.println("\n\t----CRUD APOSTADOR----");
        String opcao = "";
        while (!opcao.equals("0")) {
            System.out.println("\t1 - Cadastrar Apostador");
            System.out.println("\t2 - Listar Apostadores");
            System.out.println("\t3 - Atualizar Apostador");
            System.out.println("\t4 - Deletar Apostador");
            System.out.println("\t0 - Sair");
            System.out.print("\tEscolha uma opção: ");
            opcao = scanner.nextLine();
            switch (opcao) {
                case "1":
                    cadastroDeApostador();
                    break;
                case "2":
                    listarApostadores();
                    break;
                case "3":
                    break;
                case "4":
                    break;
                default:
                    break;
            }
        }
    }
    public void crudAposta(){
        System.out.println("\n\t----CRUD APOSTA----");
        String opcao = "";
        while (!opcao.equals("0")) {
            System.out.println("\t1 - Cadastrar Aposta");
            System.out.println("\t2 - Listar Apostas");
            System.out.println("\t3 - Atualizar Aposta");
            System.out.println("\t4 - Deletar Aposta");
            System.out.println("\t0 - Sair");
            System.out.print("\tEscolha uma opção: ");
            opcao = scanner.nextLine();
            switch (opcao) {
                case "1":
                    break;
                case "2":
                    break;
                case "3":
                    break;
                case "4":
                    break;
                default:
                    break;
            }
        }
    }
    public void crudAdministrador() {
        System.out.println("\n\t----CRUD ADMINISTRADOR----");
        String opcao = "";
        while (!opcao.equals("0")) {
            System.out.println("\t1 - Cadastrar Administrador");
            System.out.println("\t2 - Listar Administradores");
            System.out.println("\t3 - Atualizar Administrador");
            System.out.println("\t4 - Deletar Administrador");
            System.out.println("\t0 - Sair");
            System.out.print("\tEscolha uma opção: ");
            opcao = scanner.nextLine();
            switch (opcao) {
                case "1":
                    break;
                case "2":
                    break;
                case "3":
                    break;
                case "4":
                    break;
                default:
                    break;
            }
        }
    }
    public void crudBolao(){
        System.out.println("\n\t----CRUD BOLAO----");
        String opcao = "";
        while (!opcao.equals("0")) {
            System.out.println("\t1 - Cadastrar Bolão");
            System.out.println("\t2 - Listar Bolões");
            System.out.println("\t3 - Atualizar Bolão");
            System.out.println("\t4 - Deletar Bolão");
            System.out.println("\t0 - Sair");
            System.out.print("\tEscolha uma opção: ");
            opcao = scanner.nextLine();
            switch (opcao) {
                case "1":
                    break;
                case "2":
                    break;
                case "3":
                    break;
                case "4":
                    break;
                default:
                    break;
            }
        }
    }
    public void crudJogo(){
        System.out.println("\n\t----CRUD JOGO----");
        String opcao = "";
        while (!opcao.equals("0")) {
            System.out.println("\t1 - Cadastrar Jogo");
            System.out.println("\t2 - Listar Jogos");
            System.out.println("\t3 - Atualizar Jogo");
            System.out.println("\t4 - Deletar Jogo");
            System.out.println("\t0 - Sair");
            System.out.print("\tEscolha uma opção: ");
            opcao = scanner.nextLine();
            switch (opcao) {
                case "1":
                    break;
                case "2":
                    listarJogos();
                    break;
                case "3":
                    break;
                case "4":
                    break;
                default:
                    break;
            }
        }
    }
    //    Métodos relacionados aos testes e acessíveis no menu do ADM
    public void testeInicializar(){
        this.administradores.add(new Administrador("adm@adm.com", "adm"));
        this.testeGerarApostadores();
        this.testeGerarJogos();
        this.testeGerarBolao();
    }
    public void testeGerarApostadores() {
        apostadores.add(new Apostador("Gabriel Schramm", "07", "02", 1998, "12345678998", "moura@mrjavabet.com", "schramm"));
        apostadores.add(new Apostador("Miguel Krasner", "26", "05", 1980, "78945612364", "miguel@mrjavabet.com", "krasner"));
        apostadores.add(new Apostador("Gabriel Kleiman", "09", "02", 1999, "74185296351", "kleiman@mrjavabet.com", "kleiman"));
    }
    public void testeGerarJogos(){
        Jogo jogo1 = new Jogo("Gauchão", "Brasil");
        jogo1.setTimes(0, "Grêmio");
        jogo1.setTimes(1, "Inter");
        jogo1.setPlacar(0, 2);
        jogo1.setPlacar(1, 1);
        jogos.add(jogo1);

        Jogo jogo2 = new Jogo("Libertadores", "Brasil");
        jogo2.setTimes(0, "Bahia");
        jogo2.setTimes(1, "Vitória");
        jogo2.setPlacar(0, 1);
        jogo2.setPlacar(1, 2);
        jogos.add(jogo2);

        Jogo jogo3 = new Jogo("Libertadores", "Brasil");
        jogo3.setTimes(0, "Flamengo");
        jogo3.setTimes(1, "Atlético-MG");
        jogo3.setPlacar(0, 3);
        jogo3.setPlacar(1, 0);
        jogos.add(jogo3);

        Jogo jogo4 = new Jogo("Libertadores", "Brasil");
        jogo4.setTimes(0, "Corinthians");
        jogo4.setTimes(1, "Santos");
        jogo4.setPlacar(0, 0);
        jogo4.setPlacar(1, 0);
        jogos.add(jogo4);
    }
    public void testeGerarBolao(){
        ArrayList<Aposta> apostas = new ArrayList<>();
        apostas.add(new Aposta(jogos.get(0), "Grêmio"));
        apostas.get(0).setPrevisaoPlacar(0, 2);
        apostas.get(0).setPrevisaoPlacar(1, 1);

        apostas.add(new Aposta(jogos.get(1), "Vitória"));
        apostas.get(1).setPrevisaoPlacar(0, 2);
        apostas.get(1).setPrevisaoPlacar(1, 1);

        apostas.add(new Aposta(jogos.get(2), "Flamengo"));
        apostas.get(1).setPrevisaoPlacar(0, 2);
        apostas.get(1).setPrevisaoPlacar(1, 1);

        boloes.add(new Bolao(5));

        boloes.get(boloes.size() - 1).setApostas(apostas);
        boloes.get(boloes.size() - 1).setApostadores(apostadores);
        for (Apostador apostador : boloes.get(boloes.size() - 1).getApostadores()){
            apostador.getBoloes().add(boloes.get(boloes.size() - 1));
        }
    }
}

