package Repository;

import Service.PetService;
import Utils.UtilValidator;
import Service.FormService;

import java.util.concurrent.TimeUnit;

public class Menu {
    private static final PetRepository petRepository = new PetRepository();
    public static void DisplayMenu(){
        System.out.println("""
                1 - Cadastrar um novo pet
                2 - Alterar os dados do pet cadastrado
                3 - Deletar um pet cadastrado
                4 - Listar todos os pets cadastrados
                5 - Listar pets por algum critério (idade, nome, raça)
                6 - Sair""");
        UserChoice();
    }
    public static void UserChoice(){
        UtilValidator businessRules = new UtilValidator();
        int validChoice = businessRules.intScannerValidator();
        switch (validChoice) {
            case 1:
                FormService.PetRegister();
                break;
            case 2:
               PetService.updatePet();
                break;
            case 3:
                PetService.deletePet();
                break;
            case 4:
                petRepository.getAllPets();
                break;
            case 5:
                PetService.getPet();
                break;
            case 6:
                System.out.println("\nAplicação finalizada");
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
