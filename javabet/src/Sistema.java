import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Sistema {
    private Scanner scanner = new Scanner(System.in);
    private ArrayList<Apostador> apostadores = new ArrayList<>();
    private ArrayList<Administrador> administradores = new ArrayList<>();
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
            System.out.println("\t2. Consulta");
            System.out.println("\t3. Atualizar");
            System.out.println("\t4. Remover");
            System.out.println("\t5. Login");
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
            System.out.println("\t1 - GERAR APOSTADORES TESTE");
            System.out.println("\t2 - GERAR JOGOS TESTE");
            System.out.println("\t3 - GERAR BOLÕES TESTE");
            System.out.println("\t4 - Cadastrar bolão");
            System.out.println("\t5 - Finalizar Jogo");
            System.out.println("\t0 - Sair");
            System.out.print("\tEscolha uma opção: ");
            opcao = scanner.nextLine();
            switch (opcao) {
                case "1":
                    testeGerarApostadores();
                    System.out.println("\n\t----APOSTADORES TESTE GERADOS-----");
                    break;
                case "2":
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
                    break;
                case "2":
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
//    Métodos relacionados aos testes e acessíveis no menu do ADM
    public void testeGerarApostadores() {
        apostadores.add(new Apostador("Gabriel Schramm", "07", "02", 1998, "12345678998", "moura@mrjavabet.com", "schramm"));
        apostadores.add(new Apostador("Miguel Krasner", "26", "05", 1980, "78945612364", "miguel@mrjavabet.com", "krasner"));
        apostadores.add(new Apostador("Gabriel Kleiman", "09", "02", 1999, "74185296351", "kleiman@mrjavabet.com", "kleiman"));
    }
}

