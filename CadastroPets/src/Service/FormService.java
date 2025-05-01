package Service;

import Model.Address;
import Model.Pet;
import Repository.Form;
import Repository.PetRepository;
import Utils.UtilValidator;

import java.util.Scanner;


public class FormService {
    private static final UtilValidator Validator = new UtilValidator();
    public static void PetRegister(){
       Pet pet = new Pet();
       Address address = new Address();
       Scanner scanner = new Scanner(System.in);
       Form.CreateForm();
       //Nome sobrenome
        Form.FormReaderLine(1);
        pet.setName(Validator.nameValidator());
        //tipo (gato / chachorro)
        Form.FormReaderLine(2);
        System.out.println("Opções: 1 - Cachorro ||| 2 - Gato");
        int choice = Validator.OneOrTwoValidator();
        pet.DefinePetType(choice);
        //sexo do animal
        Form.FormReaderLine(3);
        System.out.println("Opções: 1 - Macho ||| 2 - Fêmea");
        choice = Validator.OneOrTwoValidator();
        pet.DefinePetGender(choice);
        //endereço
        Form.FormReaderLine(4);
        System.out.println("Cidade: ");
        address.setCity(Validator.OnlyLettersValidator());
        System.out.println("Rua: ");
        address.setStreet(scanner.nextLine());
        System.out.println("Número da residência: ");
        address.setHouseNumber(scanner.nextLine());
        //idade
        Form.FormReaderLine(5);
        double petAge = Validator.StringToDoubleValidator();
        if (petAge < 1){
            System.out.println("Digite a idade em meses: ");
            int petAgemonths = Validator.IntScannerValidator();
            petAge = (double) petAgemonths / 12;
            System.out.println(petAge);

        }
        if (petAge > 20){
            throw new IllegalArgumentException("Valor execede a idade permitida");
        }
        pet.setAge(petAge);
        //peso
        Form.FormReaderLine(6);
        double petWeight = Validator.StringToDoubleValidator();
        if (petWeight > 60 || petWeight < 0.5) {
            throw new IllegalArgumentException("Valor não permitido");
        }
        pet.setWeight(petWeight);
        //raça
        Form.FormReaderLine(7);
        pet.setBreed(Validator.OnlyLettersValidator());
        //salva informações no banco de dados
        PetRepository.SavePetFile(pet);



    }
}
