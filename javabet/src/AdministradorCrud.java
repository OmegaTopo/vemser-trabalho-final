import java.util.ArrayList;
import java.util.Scanner;

public class AdministradorCrud {
    private ArrayList<Administrador> administradores = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    public ArrayList<Administrador> getAdministradores() {
        return administradores;
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
                    cadastroDeAdm();
                    break;
                case "2":
                    listarAdm();
                    break;
                case "3":
                    atualizarAdm();
                    break;
                case "4":
                    removerAdm();
                    break;
                default:
                    break;
            }
        }
    }

    //    Métodos relacionados ao Administrador
    public void cadastroDeAdm() {
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

    public void listarAdm() {
        System.out.println("\n\t\tLISTA DE ADMINISTRADORES\n\t\t---------------------");
        Administrador administrador;
        for (int i = 0; i < administradores.size(); i++) {
            administrador = administradores.get(i);
            System.out.println("\t\t\t" + (i + 1) + ". " +
                    administradores.get(i).getEmail());
        }
    }

    public void atualizarAdm() {
        System.out.println("\n\tATUALIZAÇÃO DE ADMINISTRADOR\n\t------------------------");
        System.out.print("\tDigite o email do administrador: ");
        String email = scanner.nextLine();
        boolean encontrado = false;
        for (Administrador administrador : administradores) {
            if (administrador.getEmail().equals(email)) {
                encontrado = true;
                System.out.println("\tO que deseja alterar?");
                System.out.println("\t1 - E-mail");
                System.out.println("\t2 - Senha");
                System.out.print("\tDigite a opção desejada: ");
                String opcao = scanner.nextLine();
                switch (opcao) {
                    case "1":
                        System.out.print("\tDigite o novo e-mail: ");
                        administrador.setEmail(scanner.nextLine());
                        break;
                    case "2":
                        System.out.print("\tDigite a nova senha: ");
                        administrador.setSenha(scanner.nextLine());
                        break;
                }
                break;
            }
        }
        if (!encontrado) {
            System.out.println("\n\t----APOSTADOR NÃO ENCONTRADO----");
        }
    }

    public void removerAdm() {
        System.out.println("\n\tREMOÇÃO DE ADMINISTRADOR\n---------------------");
        System.out.println("\n\tATUALIZAÇÃO DE ADMINISTRADOR\n\t------------------------");
        System.out.print("\tDigite o email do administrador: ");
        String email = scanner.nextLine();
        boolean encontrado = false;
        for (Administrador administrador : administradores) {
            if (administrador.getEmail().equals(email)) {
                encontrado = true;
                administradores.remove(administrador);
                break;
            }
        }
        if (!encontrado) {
            System.out.println("\n\t----APOSTADOR NÃO ENCONTRADO----");
        }
    }
}
