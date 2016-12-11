package sample;

import java.io.File;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by Phil on 12/8/2016.
 */
public class BoggleSolver {


    static Set<String> loadHash(String filename){
        Set<String> dictionary = new HashSet<>();
        try {
            File file = new File(filename);
            Scanner input = new Scanner(file);
            while(input.hasNext()){
                dictionary.add(input.next().trim().toUpperCase());
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return dictionary;
    }
}
