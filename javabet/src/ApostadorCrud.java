import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class ApostadorCrud {
    private ArrayList<Apostador> apostadores = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

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
                    removerApostador();
                    break;
                default:
                    break;
            }
        }
    }

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

}
