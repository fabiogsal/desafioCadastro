package Repository;

import Model.Address;
import Model.Pet;
import Model.PetGender;
import Model.PetType;
import Service.PetService;
import Utils.UtilValidator;
import java.io.*;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PetRepository {
    private static final File fileDir = new File("PetDB");
    private static final UtilValidator validator = new UtilValidator();

    public void SavePetFile(Pet pet) {

        boolean mkdir = fileDir.mkdir();

        //Formatação do nome do arquivo .txt
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("hhmm");
        String formattedDate = time.format(formatter) + "T" + time.format(formatterTime);
        String nameFileFormatted = formattedDate + "-" + pet.getName().toUpperCase().replaceAll(" ", "");
        File petFile = new File(fileDir, nameFileFormatted);

        //salva dados no txt
        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(petFile))) {
            fileWriter.write("1 - " + pet.getName());
            fileWriter.newLine();
            fileWriter.write("2 - " + pet.getPetType());
            fileWriter.newLine();
            fileWriter.write("3 - " + pet.getPetGender());
            fileWriter.newLine();
            fileWriter.write("4 - " + pet.getAddress().getStreet() + ", " +
                    pet.getAddress().getHouseNumber() + ", " +
                    pet.getAddress().getCity());
            fileWriter.newLine();
            if (pet.getAge().equals("NÃO INFORMADO")) {
                fileWriter.write("5 - " + pet.getAge());
            } else {
                fileWriter.write("5 - " + pet.getAge() + " anos");
            }
            fileWriter.newLine();
            if (pet.getWeight().equals("NÃO INFORMADO")) {
                fileWriter.write("6 - " + pet.getWeight());
            } else {
                fileWriter.write("6 - " + pet.getWeight() + " kg");
            }
            fileWriter.newLine();
            fileWriter.write("7 - " + pet.getBreed());
            fileWriter.newLine();
            fileWriter.flush();
            fileWriter.close();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar pet no banco de dados.");
        }
    }

    public void getAllPets() {
        File[] files = fileDir.listFiles();
        int fileCont = 1;
        for (File file : files) {
            System.out.println(fileCont + " - " + formatedSearch(file));
            fileCont++;
        }
    }
    public void readFile(File file){
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String linha;
            while ((linha = bufferedReader.readLine()) != null){
                System.out.println(linha);
            }
            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static StringBuilder formatedSearch(File file) {
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(file));
            String line;
            StringBuilder result = new StringBuilder();
            while ((line = fileReader.readLine()) != null) {
                line = line.substring(4);
                result.append(line).append(" - ");
            }
            result = new StringBuilder(result.substring(0, result.length() - 3));
            fileReader.close();
            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String returnFileLine(int specificLine, File file) {
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(file));
            int cont = 1;
            String line;
            while ((line = fileReader.readLine()) != null) {
                if (cont == specificLine) {
                    fileReader.close();
                    return line;
                }
                cont++;
            }
            fileReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return "Linha não encontrada";
    }


    public ArrayList<String> findByCriteria(int criteria1, int criteria2) {
        File[] files = fileDir.listFiles();
        ArrayList<String> petList = new ArrayList<>();
        ArrayList<Integer> specificLine = getIntegers(criteria1, criteria2);
        System.out.print("Buscar: ");
        String regex = validator.BlankStringValidator();
        Pattern pattern = Pattern.compile(Pattern.quote(regex), Pattern.CASE_INSENSITIVE);
        int cont = 1;
        for (File file : files) {
            Matcher matcher1 = pattern.matcher(returnFileLine(2, file));
            Matcher matcher2 = pattern.matcher(returnFileLine(specificLine.get(0), file));
            Matcher matcher3 = pattern.matcher(returnFileLine(specificLine.get(1), file));
            if (matcher1.find() || matcher2.find() || matcher3.find()) {
                String formated = cont + " - " + formatedSearch(file);
                petList.add(formated);
                cont++;
            }
        }
        if (petList.isEmpty()) {
            System.out.println("Não foi possível encontrar no banco de dados");
        }
        return petList;
    }

    public ArrayList<String> findByCriteria(int criteria1) {
        File[] files = fileDir.listFiles();
        ArrayList<String> petList = new ArrayList<>();
        ArrayList<Integer> specificLine = getIntegers(criteria1);
        System.out.print("Buscar: ");
        String regex = validator.BlankStringValidator();
        Pattern pattern = Pattern.compile(Pattern.quote(regex), Pattern.CASE_INSENSITIVE);
        int cont = 1;
        for (File file : files) {
            Matcher matcher1 = pattern.matcher(returnFileLine(2, file));
            Matcher matcher2 = pattern.matcher(returnFileLine(specificLine.getFirst(), file));
            if (matcher1.find() || matcher2.find()) {

                String formatedString = cont + " - " + formatedSearch(file);
                petList.add(formatedString);
                cont++;
            }
        }
        if (petList.isEmpty()) {
            System.out.println("Não foi possível encontrar no banco de dados");
        }
        return petList;
    }

    public void deletePet(String selectedPet) {
        File[] files = fileDir.listFiles();
        assert files != null;
        for (File file : files) {
            String formatedFile = String.valueOf(formatedSearch(file));
            if (formatedFile.equals(selectedPet)) {
                boolean delete = file.delete();
                if (delete){

                    System.out.println("Arquivo deletado com sucesso");
                }
                else{
                    System.out.println("Não foi possível deletar arquivo " );
                }

            }
        }
    }
    public void updatePet(File file, int numLine, String updatedLine){
        String petName = returnFileLine(1, file).split(" - ")[1];
        String petType = returnFileLine(2, file).split(" - ")[1];
        String petGender = returnFileLine(3, file).split(" - ")[1];
        String petAddress = returnFileLine(4, file).split(" - ")[1];
        String petAge = returnFileLine(5, file).split(" - ")[1].split(" anos")[0];
        String petWeight = returnFileLine(6, file).split(" - ")[1].split(" kg")[0];
        String petBreed = returnFileLine(7, file).split(" - ")[1];

        if (numLine == 1){
            String newName = file.getName().split("-")[0]+"-"+updatedLine.toUpperCase().replaceAll(" ", "");
            File newFileName = new File(newName);
            boolean isFileRenamed = file.renameTo(newFileName);
            if (isFileRenamed){
                petName = updatedLine;
            } else {
                System.out.println("Erro ao renomear arquivo");
            }
        } else if (numLine == 4) {
            petAddress = updatedLine;
        } else if (numLine == 5) {
            petAge = updatedLine;
        } else if (numLine == 6) {
            petWeight = updatedLine;
        } else if (numLine == 7) {
            petBreed = updatedLine;
        } else {
            System.out.println("Opção invalida. Tente novamente");
        }
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            bufferedWriter.write("1 - "+petName);
            bufferedWriter.newLine();
            bufferedWriter.write("2 - "+petType);
            bufferedWriter.newLine();
            bufferedWriter.write("3 - "+petGender);
            bufferedWriter.newLine();
            bufferedWriter.write("4 - "+petAddress);
            bufferedWriter.newLine();
            bufferedWriter.write("5 - "+petAge + " anos");
            bufferedWriter.newLine();
            bufferedWriter.write("6 - "+petWeight + " kg");
            bufferedWriter.newLine();
            bufferedWriter.write("7 - "+petBreed);
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
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
