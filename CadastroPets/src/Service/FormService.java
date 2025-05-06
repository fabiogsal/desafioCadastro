package Service;

import Model.Address;
import Model.Pet;
import Repository.FormRepository;
import Repository.PetRepository;
import Utils.UtilValidator;



public class FormService {
    private static final UtilValidator validator = new UtilValidator();
    public static void PetRegister(){
       Pet pet = new Pet();
       PetRepository petRepository = new PetRepository();
       Address address = new Address();
       FormRepository.CreateForm();

       //Nome sobrenome
        FormRepository.FormReaderLine(1);
        pet.setName(validator.nameValidator());

        //tipo (gato / chachorro)
        FormRepository.FormReaderLine(2);
        System.out.println("Opções: 1 - Cachorro ||| 2 - Gato");
        int choice = validator.oneOrTwoValidator();
        pet.DefinePetType(choice);

        //sexo do animal
        FormRepository.FormReaderLine(3);
        System.out.println("Opções: 1 - Macho ||| 2 - Fêmea");
        choice = validator.oneOrTwoValidator();
        pet.DefinePetGender(choice);

        //endereço
        FormRepository.FormReaderLine(4);
        System.out.print("Cidade: ");
        address.setCity(validator.onlyLettersValidator());
        System.out.print("Rua: ");
        address.setStreet(validator.stringNotNullableValidator());
        System.out.print("Número da residência: ");
        address.setHouseNumber(validator.stringOrNullableValidator());
        pet.setAddress(address);
        //idade
        FormRepository.FormReaderLine(5);
        pet.setAge(validator.petAgeValidator());

        //peso
        FormRepository.FormReaderLine(6);
        pet.setWeight(validator.petWeightValidator());

        //raça
        FormRepository.FormReaderLine(7);
        pet.setBreed(validator.stringOrNullableValidator());

        //salva informações no banco de dados
        petRepository.SavePetFile(pet);
    }
}
