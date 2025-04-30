package Utils;


import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UtilValidator {
    Scanner scanner = new Scanner(System.in);

    //Valida nome(Apenas letras de a-z e obrigatoriamente exije um sobrenome)
    public String nameValidator() {
        while (true) {
            String name = scanner.nextLine();
            String regex = "^[A-Za-z]+(?: [A-Za-z]+)+$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(name);
            if (matcher.find()) {
                return name;
            } else {
                System.out.println("Nome deve conter apenas caracteres de A a Z e deve conter um sobrenome.");
            }
        }
    }
    //Tratamento de erro caso numero não seja um inteiro
    public int IntScannerValidator() {
        String choice;
        int validChoice;
         while (true) {
             System.out.println("Insira a opção desejada: ");
             choice = scanner.nextLine();
             try {
                 validChoice = Integer.parseInt(choice);
                 return validChoice;
             } catch (Exception e) {
                 System.out.println("Opção deve ser um numero inteiro. Tente novamente: ");
             }
         }
    }
    public int OneOrTwoValidator(){
        String choice;
        int validChoice;
        while (true){
            System.out.println("Insira a opção desejada: ");
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
    public String OnlyLettersValidator(){
        while (true) {
            String letters = scanner.nextLine();
            String regex = "^[A-Za-z]+$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(letters);
            if (matcher.find()) {
                return letters;
            } else {
                System.out.println("Resposta deve conter apenas caracteres de A a Z.");
            }
        }
    }
    //Converte String em double aceitando tanto o ponto quanto a virgula.
    public double StringToDoubleValidator(){
        String valueString;
        double valueDouble;
        while (true) {
            valueString = scanner.nextLine();
            try {
                valueDouble = Double.parseDouble(valueString.replace(",", "."));
                break;
            } catch (Exception e) {
                System.out.println("Deve conter apenas números");
            }
        }
        return valueDouble;
    }
}
