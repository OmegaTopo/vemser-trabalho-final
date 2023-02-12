import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Sistema {
    private Scanner scanner = new Scanner(System.in);
    private ArrayList<Apostador> apostadores = new ArrayList<>();
    private Usuario usuarioAtivo;

    public void cadastroDeApostador() {
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
    }

    public void listarApostadores() {
        System.out.println("\n\tLISTA DE APOSTADORES\n---------------------");
        for (int i = 0; i < apostadores.size(); i++) {
            System.out.println((i + 1) + ". " + apostadores.get(i).getNome());
        }
    }

    public void atualizarApostador() {
        System.out.println("\n\tATUALIZAÇÃO DE APOSTADOR\n------------------------");
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
                int opcao = scanner.nextInt();
                scanner.nextLine();
                switch (opcao) {
                    case 1:
                        System.out.print("\tDigite o novo nome completo: ");
                        apostador.setNome(scanner.nextLine());
                        break;
                    case 2:
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
                    case 3:
                        System.out.print("\tDigite o novo e-mail: ");
                        apostador.setEmail(scanner.nextLine());
                        break;
                    case 4:
                        System.out.print("\tDigite a nova senha: ");
                        apostador.setSenha(scanner.nextLine());
                        break;
                }
                break;
            }
        }
        if (!encontrado) {
            System.out.println("\tApostador não encontrado.");
        }
    }

    public void removerApostador() {
        System.out.println("\n\tREMOÇÃO DE APOSTADOR\n---------------------");
        listarApostadores();
        System.out.print("\tSelecione o número do apostador que deseja remover: ");
        int selecao = scanner.nextInt();
        scanner.nextLine();
        apostadores.remove(selecao - 1);
        System.out.println("\tApostador removido com sucesso.");
    }

    //    public void menu() {
//        int opcao = 0;
//        do {
//            System.out.println("\n\t MENU");
//            System.out.println("1 - Cadastro");
//            System.out.println("2 - Consulta");
//            System.out.println("3 - Atualizar");
//            System.out.println("4 - Remover");
//            System.out.println("0 - Sair");
//            System.out.print("\nEscolha uma opção: ");
//            opcao = scanner.nextInt();
//            scanner.nextLine();
//
//            switch (opcao) {
//                case 1:
//                    cadastroDeApostador();
//                    break;
//                case 2:
//                    consultaApostador();
//                    break;
//                case 3:
//                    atualizarApostador();
//                    break;
//                case 4:
//                    removerApostador();
//                    break;
//                case 0:
//                    System.out.println("\nSaindo do sistema...");
//                    break;
//                default:
//                    System.out.println("\nOpção inválida, escolha uma opção válida.");
//            }
//        } while (opcao != 0);
//    }]
    public boolean verificarLogin(String email, String senha) {
        for (Apostador apostador : apostadores) {
            if (apostador.getEmail().equals(email) && apostador.getSenha().equals(senha)) {
                usuarioAtivo = apostador;
                return true;
            }
        }
        return false;
    }

    public void menuPrincipal() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n\tMenu Principal\n--------------------");
            System.out.println("\t1. Cadastro");
            System.out.println("\t2. Consulta");
            System.out.println("\t3. Atualizar");
            System.out.println("\t4. Remover");
            System.out.println("\t5. Login");
            System.out.println("\t0. Sair");
            System.out.print("\tDigite a opção desejada: ");
            opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                case 1:
                    cadastroDeApostador();
                    break;
                case 2:
//                consultaApostador();
                    break;
                case 3:
                    atualizarApostador();
                    break;
                case 4:
                    removerApostador();
                    break;
                case 5:
                    menuLogin();
                    break;
                case 0:
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
        System.out.println("\n\tLOGIN\n----------");
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
                System.out.println("\n\tUsuário ou senha inválidos");
                menuPrincipal();
            }
        }
    }

    public void menuAdministrador(){

    }

    public void menuApostador() {
        System.out.println("\n\tBEM VINDO " + usuarioAtivo.getEmail().toUpperCase() + "!\n-------------------");
        int opcao = 0;
        while (opcao != 4) {
            System.out.println("\n\tMENU APOSTADOR\n---------------------");
            System.out.println("\t1 - Realizar uma aposta");
            System.out.println("\t2 - Comprar cota de bolão");
            System.out.println("\t3 - Verificar resultado de apostas");
            System.out.println("\t4 - Verificar resultado de bolões");
            System.out.println("\t0 - Sair");
            System.out.print("\tEscolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                    case 1:
                        System.out.println("\nRealizar uma aposta");
                        break;
                    case 2:
                        System.out.println("\nComprar cota de bolão");
                        break;
                    case 3:
                        System.out.println("\nVerificar resultado de apostas");
                        break;
                    case 4:
                        System.out.println("\nVerificar resultado de bolões");
                        break;
                    case 0:
                        System.out.println("\nSaindo...");
                        break;
                    default:
                        System.out.println("\nOpção inválida");
                        break;
                }
        }
    }

}

