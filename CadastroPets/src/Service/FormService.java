package Service;

import Model.Address;
import Model.Pet;
import Repository.Form;
import Repository.PetRepository;
import Utils.UtilValidator;



public class FormService {
    private static final UtilValidator validator = new UtilValidator();
    public static void PetRegister(){
       Pet pet = new Pet();
       Address address = new Address();
       Form.CreateForm();

       //Nome sobrenome
        Form.FormReaderLine(1);
        pet.setName(validator.nameValidator());

        //tipo (gato / chachorro)
        Form.FormReaderLine(2);
        System.out.println("Opções: 1 - Cachorro ||| 2 - Gato");
        int choice = validator.oneOrTwoValidator();
        pet.DefinePetType(choice);

        //sexo do animal
        Form.FormReaderLine(3);
        System.out.println("Opções: 1 - Macho ||| 2 - Fêmea");
        choice = validator.oneOrTwoValidator();
        pet.DefinePetGender(choice);

        //endereço
        Form.FormReaderLine(4);
        System.out.print("Cidade: ");
        address.setCity(validator.onlyLettersValidator());
        System.out.print("Rua: ");
        address.setStreet(validator.stringNotNullableValidator());
        System.out.print("Número da residência: ");
        address.setHouseNumber(validator.stringNullableValidator());
        pet.setAddress(address);
        //idade
        Form.FormReaderLine(5);
        pet.setAge(validator.petAgeValidator());

        //peso
        Form.FormReaderLine(6);
        pet.setWeight(validator.petWeightValidator());

        //raça
        Form.FormReaderLine(7);
        pet.setBreed(validator.stringNullableValidator());

        //salva informações no banco de dados
        PetRepository.SavePetFile(pet);



    }
}
