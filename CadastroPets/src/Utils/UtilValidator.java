package Utils;



import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UtilValidator {
    private static final Scanner scanner = new Scanner(System.in);
    private final String NAO_INFORMADO = "NÃO INFORMADO";
    //Valida nome(Apenas letras de A-Z e obrigatoriamente exije um sobrenome)
    public String nameValidator() {
        while (true) {
            String name = scanner.nextLine();
            String regex = "^[A-Za-zÀ-ÿ]+(?: [A-Za-zÀ-ÿ]+)+$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(name);
            if(name.isBlank()){
                return NAO_INFORMADO;
            }
            if (matcher.find()) {
                return name;
            } else {
                System.out.println("Nome deve conter apenas caracteres de A a Z e deve conter um sobrenome.");
            }
        }
    }
    //Tratamento de erro caso numero não seja um inteiro
    public int intScannerValidator() {
        String choice;
        int validChoice;
         while (true) {
             System.out.print("Insira a opção desejada: ");
             choice = scanner.nextLine();
             try {
                 validChoice = Integer.parseInt(choice);
                 return validChoice;
             } catch (Exception e) {
                 System.out.println("Opção deve ser um numero inteiro. Tente novamente: ");
             }
         }
    }
    public int oneOrTwoValidator(){
        String choice;
        int validChoice;
        while (true){
            choice = scanner.nextLine();
            try {
                validChoice = Integer.parseInt(choice);
                if(validChoice == 1 || validChoice == 2) {
                    return validChoice;
                }
                else{
                    System.out.println("O valor deve ser 1 ou 2 apenas");
                }
            } catch (Exception e) {
                System.out.println("Opção deve ser um numero inteiro. Tente novamente: ");
            }
        }
    }
    public String onlyLettersValidator(){
        while (true) {
            String letters = scanner.nextLine();
            String regex = "^[A-Za-zÀ-ÿ\\s]+$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(letters);
            if (matcher.find()) {
                return letters;
            } else {
                System.out.println("Resposta deve conter apenas caracteres de A a Z.");
            }
        }
    }

        public String petAgeValidator() {
            String petAge;
            while (true){
                petAge = scanner.nextLine();
                if (petAge.isBlank()){
                    return NAO_INFORMADO;
                }
                try {
                    double validPetAge = Double.parseDouble(petAge.replace(",", "."));
                    if(validPetAge > 20){
                        throw new IllegalArgumentException("Idade invalida");
                    }
                    if (validPetAge < 1){
                        System.out.println("Digite idade em meses: ");
                        int mountValue = intScannerValidator();
                        double monthValueToDouble = (double) mountValue /12;
                        petAge = String.valueOf(monthValueToDouble);
                    }
                    break;
                }
                catch (Exception e){
                    System.out.println("Resposta de conter apenas números");
                }
            }
            return petAge;
        }
    public String petWeightValidator() {
        String petWeight;
        System.out.println("Informe o peso em kg: ");
        while (true){
            petWeight = scanner.nextLine();
            if (petWeight.isBlank()){
                return NAO_INFORMADO;
            }
            try {
                double validPetWeight = Double.parseDouble(petWeight.replace(",", "."));
                if(validPetWeight < 0.5 || validPetWeight >  60){
                    throw new IllegalArgumentException("Valor invalido, peso deve estar entre 0.5kg e 60kg");
                }
                break;
            }
            catch (Exception e){
                System.out.println("Resposta de conter apenas números");
            }
        }
        return petWeight;
    }

    public String stringNullableValidator(){
        String value = scanner.nextLine();
        if (value.isBlank()){
            return NAO_INFORMADO;
        }
        return value;
    }

    public String stringNotNullableValidator(){
        String value;
        while (true) {
            value = scanner.nextLine();
            if (!value.isBlank()) {
                break;
            }
            System.out.println("Valor desse campo não pode ser vazio.");
        }
        return value;
    }
    public int intOrNullableValidator(){
        String choice;
        int validChoice;
        while (true) {
            System.out.print("Insira a opção desejada: ");
            choice = scanner.nextLine();
            if (choice.isBlank()){
                choice = "0";
            }
            try {
                validChoice = Integer.parseInt(choice);
                return validChoice;
            } catch (Exception e) {
                System.out.println("Opção deve ser um numero inteiro ou uma resposta vazia. Tente novamente: ");
            }
        }
    }
    public String BlankStringValidator(){
        String value;
        while (true){
            value = scanner.nextLine();
            if (!value.isBlank()){
                break;
            }
            System.out.println("Invalido. Resposta deve conter ao menos uma letra");
        }
        return value;
    }
    public boolean YesOrNot() {
        while (true) {
            String choice = onlyLettersValidator();
            choice = choice.toUpperCase().replace("Ã", "A");
            if (choice.equals("SIM")) {
                return true;
            }
            if (choice.equals("NAO")) {
                return false;
            }
            System.out.println("Resposta deve ser ''Sim'' ou '''Não''.");
        }
    }
}
