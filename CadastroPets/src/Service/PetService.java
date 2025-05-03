package Service;

import Repository.PetRepository;
import Utils.UtilValidator;


public class PetService {
    private static final UtilValidator validator = new UtilValidator();
    public static void getPet(){
        System.out.println("""
             escolha um ou dois critérios para buscar um pet
             1 - Nome ou sobrenome
             2 - Sexo
             3 - Idade
             4 - Peso
             5 - Raça
             6 - Endereço""");
        System.out.println("Critério 1: ");
        int choice1, choice2;
        while (true) {
            choice1 = validator.intScannerValidator();
            if (choice1 > 0 && choice1 < 7){
                break;
            }
            System.out.println("Resposta invalida. Deve selecionar apenas opções de 1 a 6.");
        }
        System.out.println("Critério 2(opcional): ");
        while (true) {
            choice2 = validator.intOrNullableValidator();
            if (choice2 == choice1){
                System.out.println("Resposta invalida. a segunda resposta não pode ser igual a primeira.");
            }
            else if (choice2 == 0){
                break;
            }
            else if (choice2 > 0 && choice2 < 7){
                break;
            }
            System.out.println("Resposta invalida. O campo deve ser vazio ou números de 0 a 6.");
        }
        if (choice2 != 0)
            PetRepository.findByCriteria(choice1, choice2);
        else
            PetRepository.findByCriteria(choice1);
    }
}
