package sample;

import java.io.File;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by Phil on 12/8/2016.
 */
public class BoggleSolver {
    static Set<String> boggleDictionary = new HashSet<>();

    static Set<String> loadHash(){
        try {
            File file = new File("C:/Users/Phil/IdeaProjects/buzzwordfx/words/boggleDictionary.txt");
            Scanner input = new Scanner(file);
            while(input.hasNext()){
                boggleDictionary.add(input.next().trim().toUpperCase());
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return boggleDictionary;
    }


}
