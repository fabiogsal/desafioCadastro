package Repository;

import Model.Pet;
import Utils.UtilValidator;
import java.io.*;
import java.text.Normalizer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PetRepository {
    private static final File fileDir = new File("PetDB");
    private static final UtilValidator validator = new UtilValidator();

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

    public static void getAllPets() {
        File[] files = fileDir.listFiles();
        int fileCont = 1;
        for (File file : files) {
            System.out.println(fileCont+" - "+formatedSearch(file));
            fileCont++;
        }
    }
    public static StringBuilder formatedSearch(File file){
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(file));
            String line;
            StringBuilder result = new StringBuilder();
            while ((line = fileReader.readLine()) != null) {
                line = line.substring(4);
                result.append(line).append(" - ");
            }
            result = new StringBuilder(result.substring(0, result.length() - 3));
            return result;
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }
    public static String returnFileLine(int specificLine, File file){
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(file));
            int cont = 1;
            String line;
            while ((line = fileReader.readLine()) != null) {
                if (cont == specificLine) {
                    return line;
                }
                cont++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "Linha não encontrada";
    }


    public static void findByCriteria(int criteria1, int criteria2){
        File[] files = fileDir.listFiles();
        ArrayList<Integer> specificLine = getIntegers(criteria1, criteria2);
        System.out.print("Buscar: ");
        String regex = validator.BlankStringValidator();
        Pattern pattern = Pattern.compile(Pattern.quote(regex), Pattern.CASE_INSENSITIVE);
        int cont = 1;
        for (File file : files){
            Matcher matcher1 = pattern.matcher(returnFileLine(2, file));
            Matcher matcher2 = pattern.matcher(returnFileLine(specificLine.get(0), file));
            Matcher matcher3 = pattern.matcher(returnFileLine(specificLine.get(1), file));
            if (matcher1.find() || matcher2.find() || matcher3.find()){
                System.out.println(cont+" - "+formatedSearch(file));
                cont++;
            }
        }
        if (cont == 1){
            System.out.println("Não consta no banco de dados");
        }
    }
    public static void findByCriteria(int criteria1){
        File[] files = fileDir.listFiles();
        ArrayList<Integer> specificLine = getIntegers(criteria1);
        System.out.print("Buscar: ");
        String regex = validator.BlankStringValidator();
        Pattern pattern = Pattern.compile(Pattern.quote(regex), Pattern.CASE_INSENSITIVE);
        int cont = 1;
        for (File file : files){
            Matcher matcher1 = pattern.matcher(returnFileLine(2, file));
            Matcher matcher2 = pattern.matcher(returnFileLine(specificLine.getFirst(), file));
            if (matcher1.find() || matcher2.find()){
                System.out.println(cont+" - "+formatedSearch(file));
                cont++;
            }
        }
        if (cont == 1){
            System.out.println("Não consta no banco de dados");
        }
    }

    private static ArrayList<Integer> getIntegers(int criteria1, int criteria2) {
        ArrayList<Integer> specificLine = new ArrayList<>();
        if(criteria1 == 1 || criteria2 == 1)
            specificLine.add(1);
        if(criteria1 == 2 || criteria2 == 2)
            specificLine.add(3);
        if(criteria1 == 3 || criteria2 == 3)
            specificLine.add(5);
        if(criteria1 == 4 || criteria2 == 4)
            specificLine.add(6);
        if(criteria1 == 5 || criteria2 == 5)
            specificLine.add(7);
        if(criteria1 == 6 || criteria2 == 6)
            specificLine.add(4);
        return specificLine;
    }
    private static ArrayList<Integer> getIntegers(int criteria1) {
        ArrayList<Integer> specificLine = new ArrayList<>();
        if(criteria1 == 1)
            specificLine.add(1);
        if(criteria1 == 2)
            specificLine.add(3);
        if(criteria1 == 3)
            specificLine.add(5);
        if(criteria1 == 4)
            specificLine.add(6);
        if(criteria1 == 5)
            specificLine.add(7);
        if(criteria1 == 6)
            specificLine.add(4);
        return specificLine;
    }

}
