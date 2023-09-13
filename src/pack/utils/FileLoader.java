package pack.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * This class load file
 * and make file to string
 */
public class FileLoader {

    /**
     * read file and make
     * it to string builder
     * @param path
     * @return builder
     */
    public static String loadFileAsString(String path){
        StringBuilder builder = new StringBuilder();

        try (FileReader fr = new FileReader(path); BufferedReader br = new BufferedReader(fr)) {
            String line;
            while((line = br.readLine()) != null)
                builder.append(line).append("\n");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return builder.toString();
    }

    public static int parseInt(String number){
        try{
            return Integer.parseInt(number);
        }catch(NumberFormatException e){
            e.printStackTrace();
            return 0;
        }
    }

}