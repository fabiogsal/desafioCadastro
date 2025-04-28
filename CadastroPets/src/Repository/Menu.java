package Repository;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Menu {
    public static void DisplayMenu(){
        System.out.println("1 - Cadastrar um novo pet\n" +
                "2 - Alterar os dados do pet cadastrado\n" +
                "3 - Deletar um pet cadastrado\n" +
                "4 - Listar todos os pets cadastrados\n" +
                "5 - Listar pets por algum critério (idade, nome, raça)\n" +
                "6 - Sair");
        UserChoice();
    }
    public static void UserChoice(){
        Scanner scanner = new Scanner(System.in);
        String choice;
        int validChoice;
        while(true){
            System.out.println("Insira a opção desejada: ");
            choice = scanner.nextLine();
            try {
                validChoice = Integer.parseInt(choice);
                break;
            } catch (Exception e){
                System.out.println("Opção deve ser um numero inteiro. Tente novamente");
            }
        }
        switch (validChoice) {
            case 1:
                Form.CreateForm();
                Form.FormReader();
                break;
            case 2:
                //Todo: alterar cadastro pet
                break;
            case 3:
                //Todo: deletar pet
                break;
            case 4:
                //Todo: listar pet cadastrado
                break;
            case 5:
                //Todo: Listar pet por criterio
            case 6:
                System.out.println("Aplicação finalizada");
                break;
            default:
                System.out.println("Opção invalida. Por favor tente novamente.");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                DisplayMenu();

        }
    }
}
