package Service;

import Model.Address;
import Repository.PetRepository;
import Utils.UtilValidator;

import java.io.File;
import java.util.ArrayList;

import static Repository.PetRepository.formatedSearch;


public class PetService {
    private static final UtilValidator validator = new UtilValidator();
    private static final File fileDir = new File("PetDB");
    private static final PetRepository petRepository = new PetRepository();
    public static ArrayList<String> getPet(){
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
        ArrayList<String> petList;
        if (choice2 != 0)
            petList =petRepository.findByCriteria(choice1, choice2);
        else
            petList = petRepository.findByCriteria(choice1);
        for (String pet : petList){
            System.out.println(pet);
        }
        return petList;
    }
    public static void updatePet() {
        int choice;
        ArrayList<String> petList;

        while (true) {
            do {
                petList = PetService.getPet();
            } while (petList.isEmpty());
            choice = validator.intScannerValidator();
            if (choice <= petList.size()) {
                break;
            }
            System.out.println("Permitido apenas valores de 0 à " + petList.size());
        }
        String selectedPet = petList.get(choice - 1);
        System.out.println("Pet selecionado ");
        System.out.println(selectedPet);
        System.out.println("Você tem certeza que deseja altera-lo? [SIM/NÃO]: ");
        boolean check = validator.YesOrNot();
        selectedPet = selectedPet.substring(4);
        if (check) {
            File[] files = fileDir.listFiles();
            assert files != null;
            for (File file : files) {
                String formatedFile = String.valueOf(formatedSearch(file));
                if (formatedFile.equals(selectedPet)) {
                    petRepository.readFile(file);
                    System.out.println("Digite qual atributo deseja alterar: ");
                    int numLine= validator.intScannerValidator();
                    if (!(numLine == 2 || numLine == 3 || numLine > 7)){
                        System.out.println(PetRepository.returnFileLine(numLine, file));
                        System.out.print("Alterar por: ");
                        String updatedLine;
                        if (numLine == 1){
                            updatedLine = validator.nameValidator();
                            petRepository.updatePet(file, numLine, updatedLine);
                        } else if (numLine == 4) {
                            Address address = new Address();
                            System.out.print("Cidade: ");
                            address.setCity(validator.onlyLettersValidator());
                            System.out.print("Rua: ");
                            address.setStreet(validator.stringNotNullableValidator());
                            System.out.print("Número da residência: ");
                            address.setHouseNumber(validator.stringOrNullableValidator());
                            updatedLine = address.getStreet()+", "+address.getHouseNumber()+", "+address.getCity();
                            petRepository.updatePet(file, numLine, updatedLine);
                        } else if (numLine == 5) {
                            updatedLine = validator.petAgeValidator();
                            petRepository.updatePet(file, numLine, updatedLine);
                        }else if (numLine == 6) {
                            updatedLine = validator.petWeightValidator();
                            petRepository.updatePet(file, numLine, updatedLine);
                        }else if (numLine == 7) {
                            updatedLine = validator.stringOrNullableValidator();
                            petRepository.updatePet(file, numLine, updatedLine);
                        }
                    } else {
                        System.out.println("Opção selecionada não existe ou não pode ser alterada.");
                    }


                }
            }

        }
    }
    public static void deletePet() {
        int choice;
        ArrayList<String> petList;

        while (true) {
            do {
                petList = PetService.getPet();
            } while (petList.isEmpty());
            choice = validator.intScannerValidator();
            if (choice <= petList.size()) {
                break;
            }
            System.out.println("Permitido apenas valores de 0 à " + petList.size());
        }
        String selectedPet = petList.get(choice-1);
        System.out.println("Pet selecionado ");
        System.out.println(selectedPet);
        System.out.println("Você tem certeza que deseja deleta-lo? [SIM/NÃO]: ");
        boolean check = validator.YesOrNot();
        selectedPet = selectedPet.substring(4);

        if (check) {
            petRepository.deletePet(selectedPet);
        }
    }
}
