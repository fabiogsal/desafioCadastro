package Service;

import Model.Address;
import Model.Pet;
import Repository.Form;
import Utils.UtilValidator;

import java.util.Scanner;


public class FormService {
    private static final UtilValidator Validator = new UtilValidator();
    public static void PetRegister(){
       Pet pet = new Pet();
       Address address = new Address();
       Form form = new Form();
       Scanner scanner = new Scanner(System.in);
       form.CreateForm();
       //Nome sobrenome
        // form.FormReaderNextLine();
        pet.setName(Validator.nameValidator());
        //tipo (gato / chachorro)
        form.FormReaderNextLine();
        System.out.println("1 - Cachorro ||| 2 - Gato");
        int choice = Validator.OneOrTwoValidator();
        pet.DefinePetType(choice);
        //sexo do animal
        form.FormReaderNextLine();
        choice = Validator.OneOrTwoValidator();
        pet.DefinePetGender(choice);
        //endereço
        form.FormReaderNextLine();
        System.out.println("Digite o nome de sua cidade: ");
        address.setCity(Validator.OnlyLettersValidator());
        System.out.println("Digite o nome de sua rua: ");
        address.setStreet(scanner.nextLine());
        System.out.println("Digite o número de sua residência: ");
        address.setHouseNumber(scanner.nextLine());
        //idade
        form.FormReaderNextLine();
        double petAge = Validator.StringToDoubleValidator();
        if (petAge < 20){
            throw new IllegalArgumentException("Valor execede a idade permitida");
        }
        pet.setAge(petAge);
        //peso
        form.FormReaderNextLine();
        double petWeight = Validator.StringToDoubleValidator();
        if (petWeight > 60 || petWeight < 0.5) {
            throw new IllegalArgumentException("Valor não permitido");
        }
        pet.setWeight(petWeight);
        //raça
        form.FormReaderNextLine();
        pet.setBreed(Validator.OnlyLettersValidator());



    }
}
