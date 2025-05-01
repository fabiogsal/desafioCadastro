package Repository;

import Model.Pet;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PetRepository {
    private static final File fileDir = new File("PetDB");

    public static void SavePetFile(Pet pet){
        boolean mkdir = fileDir.mkdir();

        //Formatação do nome do arquivo .txt
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("hhmm");
        String formattedDate = time.format(formatter) +"T"+time.format(formatterTime);
        String nameFileFormatted = formattedDate +"-"+pet.getName().toUpperCase().replaceAll(" ", "");
        File petFile = new File(fileDir, nameFileFormatted);

        //salva dados no txt
        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(petFile))){
            fileWriter.write("1 - "+pet.getName());
            fileWriter.newLine();
            fileWriter.write("2 - "+pet.getPetType());
            fileWriter.newLine();
            fileWriter.write("3 - "+pet.getPetGender());
            fileWriter.newLine();
            fileWriter.write("4 - "+pet.getAddress().getStreet()+", "+
                    pet.getAddress().getHouseNumber()+", "+
                    pet.getAddress().getCity());
            fileWriter.newLine();
            if (pet.getAge().equals("NÃO INFORMADO")){
                fileWriter.write("5 - "+pet.getAge());
            } else {
                fileWriter.write("5 - " + pet.getAge() + " anos");
            }
            fileWriter.newLine();
            if (pet.getWeight().equals("NÃO INFORMADO")){
                fileWriter.write("6 - "+pet.getWeight());
            }
            else {
                fileWriter.write("6 - " + pet.getWeight() + " kg");
            }
            fileWriter.newLine();
            fileWriter.write("7 - "+pet.getBreed());
            fileWriter.newLine();

        } catch (Exception e){
            throw new RuntimeException("Erro ao salvar pet no banco de dados.");
        }

    }
}
