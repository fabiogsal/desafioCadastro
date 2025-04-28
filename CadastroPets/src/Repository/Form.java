package Repository;

import java.io.*;

public class Form {

    private static final File filesDir = new File("Files");
    private static final File form = new File(filesDir, "formulario.txt");

    public static void CreateForm(){

         boolean IsFileDirCreated = filesDir.mkdir();

         try {
             boolean isFormCreated = form.createNewFile();
             if (isFormCreated){
                 BufferedWriter formWriter = new BufferedWriter(new FileWriter(form, true));
                 formWriter.write("1 - Qual o nome e sobrenome do pet?");
                 formWriter.newLine();
                 formWriter.write("2 - Qual o tipo do pet (Cachorro/Gato)?");
                 formWriter.newLine();
                 formWriter.write("3 - Qual o sexo do animal?");
                 formWriter.newLine();
                 formWriter.write("4 - Qual endereço e bairro que ele foi encontrado?");
                 formWriter.newLine();
                 formWriter.write("5 - Qual a idade aproximada do pet?");
                 formWriter.newLine();
                 formWriter.write("6 - Qual o peso aproximado do pet?");
                 formWriter.newLine();
                 formWriter.write("7 - Qual a raça do pet?");
                 formWriter.newLine();
                 formWriter.flush();
             }
         } catch (IOException e) {
             throw new RuntimeException(e);
         }
    }
     public static void FormReader(){
         try {
             BufferedReader formReader = new BufferedReader(new FileReader(form));
             String linha;
             while ((linha = formReader.readLine()) != null){
                 System.out.println(linha);
             }
         } catch (IOException e) {
             throw new RuntimeException(e);
         }
    }
}
