import java.util.Scanner;

public class ApostaCrud {
    Scanner scanner = new Scanner(System.in);
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
}
