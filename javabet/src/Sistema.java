import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Sistema {
    private Scanner scanner = new Scanner(System.in);
    private ArrayList<Apostador> apostadores = new ArrayList<>();
    private AdministradorCrud administradorCrud = new AdministradorCrud();
    private ArrayList<Jogo> jogos = new ArrayList<>();
    private ArrayList<Bolao> boloes = new ArrayList<>();
    private Usuario usuarioAtivo;
    private Apostador apostadorAtivo; // add
    private Ranking ranking = new Ranking(jogos, apostadores);

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
            System.out.println("\t\t\t" + (i + 1) + ". " + apostadores.get(i).getNome());
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
                        Date dataNascimento = new Date(ano - 1900, Integer.parseInt(mes) - 1, Integer.parseInt(dia));
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

    public void apostadorRealizarAposta() {
        if (!(usuarioAtivo instanceof Apostador)) {
            System.out.println("\n\t----USUÁRIO INVÁLIDO PARA ESTE MÉTODO----");
            return;
        }
        System.out.println("\n\tRealizar Aposta\n--------------------");
        listarJogos();
        System.out.print("\t\tEscolha o jogo para apostar:");
        String opcao = scanner.nextLine();
        int index = 0;
        try {
            index = Integer.parseInt(opcao);
        } catch (Exception e) {
            System.out.println("\n\t----JOGO NÃO ENCONTRADO----");
            return;
        }
        Jogo jogo = jogos.get(index - 1);
        System.out.println("\tVocê escolheu o jogo: " + jogo.getTimes()[0] + " x " + jogo.getTimes()[1] + " | " + jogo.getCampeonato() + " | " + jogo.getPais());
        System.out.print("\tDigite a previsão de placar para " + jogo.getTimes()[0] + ": ");
//        Adicionar tratamento de erro
        int previsaoTime1 = scanner.nextInt();
        scanner.nextLine();
        System.out.print("\tDigite a previsão de placar para " + jogo.getTimes()[1] + ": ");
//        Adicionar tratamento de erro
        int previsaoTime2 = scanner.nextInt();
        scanner.nextLine();
        Aposta novaAposta = new Aposta(jogo, previsaoTime1, previsaoTime2);
        novaAposta.setPrevisaoPlacar(0, previsaoTime1);
        novaAposta.setPrevisaoPlacar(1, previsaoTime2);
        apostadores.get(apostadores.indexOf(usuarioAtivo)).getApostas().add(novaAposta);
        apostadores.get(apostadores.indexOf(usuarioAtivo)).setPontos(apostadores.get(apostadores.indexOf(usuarioAtivo)).getPontos()-2);
        //REALIZAR PRINT DA APOSTA FINAL
        System.out.println("\n\t----APOSTA REALIZADA COM SUCESSO----");
        //Invocar um método para descontar os pontos do apostador
    }

    //    Métodos gerais de funcionamento do sistema
    public boolean verificarLogin(String email, String senha) {
        for (Apostador apostador : apostadores) {
            if (apostador.getEmail().equals(email) && apostador.getSenha().equals(senha)) {
                usuarioAtivo = apostador;
                return true;
            }
        }
        for (Administrador administrador : administradorCrud.getAdministradores()) {
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
            System.out.println("\t1. Login");
            System.out.println("\t2. Cadastro de Apostador");
            System.out.println("\t3. Ranking de Apostadores");
            System.out.println("\t0. Sair");
            System.out.print("\tDigite a opção desejada: ");
            opcao = scanner.nextLine();
            switch (opcao) {
                case "CADM":
                    administradorCrud.cadastroDeAdm();
                    break;
                case "1":
                    menuLogin();
                    break;
                case "2":
                    cadastroDeApostador();
                    break;
                case "3":
                    mostrarRanking();
                    break;
                case "0":
                    System.out.println("\tSaindo do sistema...");
                    break;
                default:
                    System.out.println("\tOpção inválida!");
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
                apostadorAtivo = apostadores.get(apostadores.indexOf(usuarioAtivo)); //add
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

    public void menuAdministrador() {
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
                    administradorCrud.crudAdministrador();
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
//                    testeGerarJogos();
                    System.out.println("\n\t----JOGOS TESTE GERADOS----");
                    break;
                case "8":
//                    testeGerarBolao();
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
            System.out.println("\t5 - Consultar meus dados");
            System.out.println("\t6 - Depositar");
            System.out.println("\t7 - Trocar por prêmio");
            System.out.println("\t0 - Sair");
            System.out.print("\tEscolha uma opção: ");
            opcao = scanner.nextLine();
            switch (opcao) {
                case "1":
                    System.out.println("\nRealizar uma aposta");
                    apostadorRealizarAposta();
                    break;
                case "2":
                    System.out.println("\nComprar cota de bolão");
                    apostadorComprarCotaBolao();
                    // FAZER
                    break;
                case "3":
                    System.out.println("\nVerificar resultado de apostas");
                    apostadorVerificarApostas();
                    // FAZER
                    break;
                case "4":
                    System.out.println("\nVerificar resultado de bolões");
                    // FAZER
                    break;
                case "5":
                    System.out.println(usuarioAtivo.toString());
                    break;
                case "6":
                    depositar();
                    break;
                case "7":
                    trocaPremio();
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

    private void apostadorVerificarApostas() {
        for (Aposta aposta: apostadorAtivo.getApostas()) {
            Jogo jogo = aposta.getJogo();
            System.out.println(aposta.toString());
            if (jogo.isFinalizado()){
                System.out.println(jogo.toString() + " | Placar final: " + jogo.getPlacar()[0] + " x " + jogo.getPlacar()[1]);
                System.out.println("Pontos ganhos: " + aposta.getResultado());
            } else {
                System.out.println(jogo.toString());
                System.out.println("Jogo ainda não finalizado.");
            }
        }
    }

    //add
    private void apostadorComprarCotaBolao() {
        listarBolao();
        System.out.print("\t\tEscolha o bolão para apostar:");
        int opcao = scanner.nextInt();
        scanner.nextLine();
        Bolao bolao = boloes.get(opcao - 1);
        if (bolao.getCotas() > bolao.getApostadores().size()){
            apostadorAtivo.comprarCotaBolao(bolao);
            apostadorAtivo.setPontos(apostadorAtivo.getPontos() - 4);
            System.out.println("\t\t----COTA DE BOLÃO COMPRADA COM SUCESSO----\n");
        } else {
            System.out.println("\t\t\n----O bolão selecionado não tem cotas disponíveis----\n");
        }
    }

    //    CRUD MENU
    public void crudApostador() {
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
                    atualizarApostador();
                    break;
                case "4":
                    listarApostadores();
                    removerApostador();
                    break;
                default:
                    break;
            }
        }
    }

    public void crudAposta() {
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

    public void crudBolao() {
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
                    criarBolao();
                    break;
                case "2":
                    listarBolao();
                    break;
                case "3":
                    atualizarBolao();
                    break;
                case "4":
                    removerBolao();
                    break;
                default:
                    break;
            }
        }
    }

    public void crudJogo() {
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
                    gerarJogo();
                    break;
                case "2":
                    listarJogos();
                    break;
                case "3":
                    atualizarJogo();
                    break;
                case "4":
                    removerJogo();
                    break;
                default:
                    break;
            }
        }
    }

    //    Métodos relacionados aos Jogos
    public void listarJogos() {
        System.out.println("\n\t\tLISTA DE JOGOS\n\t\t---------------------");
        Jogo jogo;
        for (int i = 0; i < jogos.size(); i++) {
            jogo = jogos.get(i);
//            System.out.println("\t\t\t" + (i + 1) + ". " + jogo.getTimes()[0] + " x " + jogo.getTimes()[1] + " | " + jogo.getCampeonato() + " | " + jogo.getPais());
            System.out.println("\t\t\t" + (i + 1) + ". " + jogo.toString());
        }
    }
    public void listarBolao() {
        System.out.println("\n\t\tLISTA DE Bolão\n\t\t---------------------");
        Bolao bolao;
        for (int i = 0; i < boloes.size(); i++) {
            bolao = boloes.get(i);
            System.out.println("\t\t" + (i + 1) + ". Bolão:");
            Aposta aposta;
            for (int j = 0; j < bolao.getApostas().size(); j++){
                aposta = bolao.getApostas().get(j);
                System.out.println("\t\t\t" + (j + 1) + ". " + aposta.getJogo().toString());
                System.out.println("\t\t\t\t" + aposta.toString()); // confiogurar toString;
            }
//            System.out.println("\t\t\t" + (i + 1) + ". " + bolao.getTimes()[0] + " x " + bolao.getTimes()[1] + " | " + jogo.getCampeonato() + " | " + jogo.getPais());
        }

        //Listar cada Bolao dentro dos bolões
        //Listar cada Aposta dentro das apostas do bolão
        //Listar cada Jogo dentro da Aposta
    }

    public void criarBolao() {
        if (!(usuarioAtivo instanceof Administrador)) {
            System.out.println("\n\t----USUÁRIO INVÁLIDO PARA ESTE MÉTODO----");
            return;
        }
        System.out.println("\n\tRealizar Bolão\n--------------------");
        Bolao bolao = new Bolao (3);
        for(int i =0; i<3; i++){
            listarJogos();
            System.out.print("\t\tEscolha o jogo para apostar:");
            String opcao = scanner.nextLine();
            int index = 0; // Este padrão é apenas para não dar erro adiante, mas ao tentar transformar a opção em int isso será trocado.
            try {
                index = Integer.parseInt(opcao);
            } catch (Exception e) {
                System.out.println("\n\t----JOGO NÃO ENCONTRADO----");
                return;
            }
            Jogo jogo = jogos.get(index - 1);
            System.out.println("\tVocê escolheu o jogo: " + jogo.getTimes()[0] + " x " + jogo.getTimes()[1] + " | " + jogo.getCampeonato() + " | " + jogo.getPais());
            System.out.println("\t\t1. " + jogo.getTimes()[0]);
            System.out.println("\t\t2. " + jogo.getTimes()[1]);
            System.out.println("\tDigite a previsão de placar para " + jogo.getTimes()[0] + ": ");
            int previsaoTime1 = scanner.nextInt();
            scanner.nextLine();
            System.out.println("\tDigite a previsão de placar para " + jogo.getTimes()[1] + ": ");
            int previsaoTime2 = scanner.nextInt();
            scanner.nextLine();
            Aposta novaAposta = new Aposta(jogo, previsaoTime1, previsaoTime2);
            bolao.getApostas().add(novaAposta);
        }
        System.out.println("\n\t----BOLÃO CRIADO COM SUCESSO----");
        boloes.add(bolao);

    }

    public void atualizarBolao(){
        System.out.println("\n\tATUALIZAÇÃO DE BOLÃO\n\t------------------------");
        listarBolao();
        System.out.print("\tDigite o número do bolão que deseja alterar: ");
        String indice = scanner.nextLine();
        try {
            boloes.remove(Integer.parseInt(indice) - 1);
        } catch (Exception e) {
            System.out.println("\n\t----BOLÃO NÃO ENCONTRADO----");
        }
        Bolao bolao = new Bolao (3);
        for(int i =0; i<3; i++) {
            listarJogos();
            System.out.print("\t\tEscolha o jogo para apostar no bolão:");
            String opcao = scanner.nextLine();
            int index = 0;
            try {
                index = Integer.parseInt(opcao);
            } catch (Exception e) {
                System.out.println("\n\t----JOGO NÃO ENCONTRADO----");
                return;
            }
            Jogo jogo = jogos.get(index - 1);
            System.out.println("\tVocê escolheu o jogo: " + jogo.getTimes()[0] + " x " + jogo.getTimes()[1] + " | " + jogo.getCampeonato() + " | " + jogo.getPais());
            System.out.println("\t\t1. " + jogo.getTimes()[0]);
            System.out.println("\t\t2. " + jogo.getTimes()[1]);
            System.out.println("\tDigite a previsão de placar para " + jogo.getTimes()[0] + ": ");
            int previsaoTime1 = scanner.nextInt();
            scanner.nextLine();
            System.out.println("\tDigite a previsão de placar para " + jogo.getTimes()[1] + ": ");
            int previsaoTime2 = scanner.nextInt();
            scanner.nextLine();
            Aposta novaAposta = new Aposta(jogo, previsaoTime1, previsaoTime2);
            bolao.getApostas().add(novaAposta);
        }
        System.out.println("\n\t ------BOLÃO ATUALIZADO COM SUCESSO!----- ");
        boloes.add(bolao);
    }

    public void removerBolao() {
        listarBolao();
        System.out.print("\tSelecione o número do bolão que deseja remover: ");
        String selecao = scanner.nextLine();
        try {
            boloes.remove(Integer.parseInt(selecao) - 1);
            System.out.println("\tBolão removido com sucesso.");
        } catch (Exception e) {
            System.out.println("\n\t----BOLÃO NÃO ENCONTRADO----");
        }
    }

    public void gerarJogo() {
        System.out.println(" Digite o campeonato do jogo: ");
        String campeonato = scanner.nextLine();
        System.out.println(" Digite o país do jogo: ");
        String pais = scanner.nextLine();
        System.out.println(" Digite o primeiro time: ");
        String time1 = scanner.nextLine();
        System.out.println(" Digite o segundo time: ");
        String time2 = scanner.nextLine();
        jogos.add(new Jogo(campeonato,pais,time1,time2) );
    }

    public void atualizarJogo() {
        System.out.println("\n\tATUALIZAÇÃO DE JOGO\n\t------------------------");
        listarJogos();
        System.out.print("\tDigite o número do jogo: ");
        String indexStr = scanner.nextLine();
        try {
            int index = Integer.parseInt(indexStr);
            if (index >= 0 && index <= jogos.size()) {
                Jogo jogoSelecionado = jogos.get(index - 1);
                System.out.println("\t\t1 - Campeonato");
                System.out.println("\t\t2 - País");
                System.out.println("\t\t3 - Time 1");
                System.out.println("\t\t4 - Time 2");
                System.out.println("\t\t5 - " + ((jogoSelecionado.isFinalizado())?("Reabrir jogo"):("Finalizar jogo")));
                System.out.print("\t\tDigite o número do que deseja alterar: ");
                int escolha = scanner.nextInt();
                scanner.nextLine();
                switch (escolha) {
                    case 1:
                        System.out.print("\t\tDigite o novo campeonato: ");
                        jogoSelecionado.setCampeonato(scanner.nextLine());
                        break;
                    case 2:
                        System.out.print("\t\tDigite o novo país: ");
                        jogoSelecionado.setPais(scanner.nextLine());
                        break;
                    case 3:
                        System.out.print("\t\tDigite o novo time 1: ");
                        jogoSelecionado.setTimes(0, scanner.nextLine());
                        break;
                    case 4:
                        System.out.print("\t\tDigite o novo time 2: ");
                        jogoSelecionado.setTimes(1, scanner.nextLine());
                        break;
                    case 5:
                        if (jogoSelecionado.isFinalizado()){
                            jogoSelecionado.setFinalizado(false);
                            System.out.println("\t\t\tJogo reaberto para apostas");
                            break;
                        } else {
                            //Solicitar o novo placar
                            int placar1, placar2;
                            System.out.print("\t\tDigite o número de gols do time " + jogoSelecionado.getTimes()[0] + ": ");
                            placar1 = scanner.nextInt();
                            System.out.print("\t\tDigite o número de gols do time " + jogoSelecionado.getTimes()[1] + ": ");
                            placar2 = scanner.nextInt();
                            jogoSelecionado.setPlacar(placar1, placar2);
                            jogoSelecionado.setFinalizado(true);
                            for (Apostador apostador : apostadores){
                                for (Aposta aposta : apostador.getApostas()){
                                    if (aposta.getJogo().equals(jogoSelecionado)){
                                        aposta.distribuirPremio(apostador);
                                    }
                                }
                                for (Bolao bolao : boloes) {
                                    for (Aposta aposta : bolao.getApostas()) {
                                        if (aposta.getJogo().equals(jogoSelecionado)) {
                                            aposta.distribuirPremio(bolao);
                                        }
                                    }
                                }
                            }
                            System.out.println("\t\t\tPrêmios distribuidos");
                        }
                        System.out.println("\n\t" + jogoSelecionado.toString() + " | Placar final: " + jogoSelecionado.getPlacar()[0] + " x " + jogoSelecionado.getPlacar()[1] + "\n");
                        break;
                    default:
                        System.out.println("\n\tOpção inválida.\n");
                        break;
                }
            } else {
                System.out.println("Jogo não encontrado.");
            }
        } catch (Exception e) {
            System.out.println("\n\t----JOGO NÃO ENCONTRADO----");
        }

//            for (Apostador apostador : apostadores) {
//                if (apostador.getCpf().equals(cpf)) {
//                    encontrado = true;
//                    System.out.println("\tO que deseja alterar?");
//                    System.out.println("\t1 - Nome completo");
//                    System.out.println("\t2 - Data de nascimento");
//                    System.out.println("\t3 - E-mail");
//                    System.out.println("\t4 - Senha");
//                    System.out.print("\tDigite a opção desejada: ");
//                    String opcao = scanner.nextLine();
//                    switch (opcao) {
//                        case "1":
//                            System.out.print("\tDigite o novo nome completo: ");
//                            apostador.setNome(scanner.nextLine());
//                            break;
//                        case "2":
//                            System.out.print("\tNovo ano de nascimento: ");
//                            int ano = scanner.nextInt();
//                            scanner.nextLine();
//                            System.out.print("\tNovo mês de nascimento: ");
//                            String mes = scanner.nextLine();
//                            System.out.print("\tNovo dia de nascimento: ");
//                            String dia = scanner.nextLine();
//                            Date dataNascimento = new Date(ano - 1900, Integer.parseInt(mes) - 1, Integer.parseInt(dia));
//                            apostador.setDataNascimento(dataNascimento);
//                            break;
//                        case "3":
//                            System.out.print("\tDigite o novo e-mail: ");
//                            apostador.setEmail(scanner.nextLine());
//                            break;
//                        case "4":
//                            System.out.print("\tDigite a nova senha: ");
//                            apostador.setSenha(scanner.nextLine());
//                            break;
//                    }
//                    break;
//                }
//            }

//        System.out.println("\n\tATUALIZAÇÃO DE JOGO\n\t------------------------");
//        listarJogos();
//        System.out.print("\tDigite o número do jogo que deseja alterar: ");
//        int index = scanner.nextInt();
//        scanner.nextLine();
//        index--;
//        System.out.println(" Digite o campeonato do jogo: ");
//        String campeonato = scanner.nextLine();
//        jogos.get(index).setCampeonato(campeonato);
//        System.out.println(" Digite o país do jogo: ");
//        String pais = scanner.nextLine();
//        jogos.get(index).setPais(pais);
//        System.out.println(" Digite o primeiro time: ");
//        String time1 = scanner.nextLine();
//        jogos.get(index).setTimes(0, time1);
//        System.out.println(" Digite o segundo time: ");
//        String time2 = scanner.nextLine();
//        jogos.get(index).setTimes(1, time2);
    }

    public void removerJogo() {
        System.out.println("\n\tREMOÇÃO DE JOGO\n---------------------");
        listarJogos();
        System.out.print("\tSelecione o número do jogo que deseja remover: ");
        String selecao = scanner.nextLine();
        try {
            jogos.remove(Integer.parseInt(selecao) - 1);
            System.out.println("\tJogo removido com sucesso.");
        } catch (Exception e) {
            System.out.println("\n\t----JOGO NÃO ENCONTRADO----");
        }
    }

    public void mostrarRanking(){
        int i=1;
        for(Apostador apostador : ranking.getPontuacaoGeral()){
            System.out.println("\t\t"+i+" "+apostador.getNome() +" -- \t"+ apostador.getPontos()+" Pontos ");
            i++;
        }
    }

    public void depositar(){
        Apostador apostador = (Apostador) usuarioAtivo;
        System.out.println(" Digite o valor que deseja depositar: ");
        int valor = scanner.nextInt();
        scanner.nextLine();
        apostador.comprarPontos(valor);
        System.out.println("\n\tDEPOSITO REALIZADO COM SUCESSO!");
    }

    public void trocaPremio(){
        Apostador apostador = (Apostador) usuarioAtivo;
        System.out.println("Você tem " + apostador.getPontos() + " pontos");
        System.out.println(" Digite o número de pontos que deseja trocar: ");
        int valor = scanner.nextInt();
        scanner.nextLine();
        System.out.println("\n\tTROCA REALIZADA COM SUCESSO!");
        double resgatado = apostador.trocarPremio(valor);
        System.out.println("Você resgatou: " + resgatado+" reais");
        System.out.println("O seu saldo de pontos é: " + apostador.getPontos());
    }
    //    Métodos relacionados aos testes e acessíveis no menu do ADM
    public void testeInicializar() {
        this.administradorCrud.getAdministradores().add(new Administrador("adm@adm.com", "adm"));
        this.administradorCrud.getAdministradores().add(new Administrador("", ""));
        this.testeGerarApostadores();
        this.testeGerarJogos();
        this.testeGerarBolao();
    }
    public void testeGerarApostadores() {
        apostadores.add(new Apostador("Gabriel Schramm", "07", "02", 1998, "12345678998", "moura@mrjavabet.com", "schramm"));
        apostadores.add(new Apostador("Miguel Krasner", "26", "05", 1980, "78945612364", "miguel@mrjavabet.com", "krasner"));
        apostadores.add(new Apostador("Gabriel Kleiman", "09", "02", 1999, "74185296351", "kleiman@mrjavabet.com", "kleiman"));
        apostadores.add(new Apostador("Vazio", "09", "02", 1999, "74185296351", " ", " "));
        apostadores.get(0).setPontos(200);
        apostadores.get(1).setPontos(250);
        apostadores.get(2).setPontos(300);
        apostadores.get(3).setPontos(400);
    }
    public void testeGerarJogos() {
        Jogo jogo1 = new Jogo("Gauchão", "Brasil", "Grêmio", "Inter");
        jogo1.setPlacar(0, 2);
        jogo1.setPlacar(1, 1);
        jogos.add(jogo1);

        Jogo jogo2 = new Jogo("Libertadores", "Brasil", "Bahia", "Vitória");
        jogo2.setPlacar(0, 1);
        jogo2.setPlacar(1, 2);
        jogos.add(jogo2);

        Jogo jogo3 = new Jogo("Libertadores", "Brasil", "Flamengo", "Atlético-MG");
        jogo3.setPlacar(0, 3);
        jogo3.setPlacar(1, 0);
        jogos.add(jogo3);

        Jogo jogo4 = new Jogo("Libertadores", "Brasil", "Corinthians", "Santos");
        jogo4.setPlacar(0, 0);
        jogo4.setPlacar(1, 0);
        jogos.add(jogo4);
    }
    public void testeGerarBolao() {
        ArrayList<Aposta> apostas = new ArrayList<>();
        apostas.add(new Aposta(jogos.get(0), 2,3));

        apostas.add(new Aposta(jogos.get(1), 2,1));

        apostas.add(new Aposta(jogos.get(2), 2,2));

        boloes.add(new Bolao(5));

        boloes.get(boloes.size() - 1).setApostas(apostas);
        boloes.get(boloes.size() - 1).setApostadores(apostadores);
        for (Apostador apostador : boloes.get(boloes.size() - 1).getApostadores()) {
            apostador.getBoloes().add(boloes.get(boloes.size() - 1));
        }
    }
}