package Buzzword;

import java.io.File;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by Phil on 12/8/2016.
 */
class BoggleSolver {

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

    private static boolean findWord(String word, char[][] board) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board.length; col++) {
                if (findWord(word, row, col, board)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean findWord(String word, int row, int col, char[][] board){
        if(word.equals("")) return true;
        else if (row < 0 || row >= board.length || col < 0 || col >= board.length || board[row][col] != word.charAt(0)) return false;
        else {
            char save = board[row][col];
            board[row][col] = '*';
            String rest = word.substring(1, word.length());
            boolean result =    findWord(rest, row-1, col-1, board) ||
                                findWord(rest, row-1,   col, board) ||
                                findWord(rest, row-1, col+1, board) ||
                                findWord(rest,   row, col-1, board) ||
                                findWord(rest,   row, col+1, board) ||
                                findWord(rest, row+1, col-1, board) ||
                                findWord(rest, row+1,   col, board) ||
                                findWord(rest, row+1, col+1, board);
            board[row][col] = save;
            return result;
        }
    }

    static Set<String> solve(char[][] board, Set<String> dictionary){
        Set<String> toReturn = new HashSet<>();
        for(String word : dictionary){
            if (findWord(word, board)) {
                System.out.println(word);
                toReturn.add(word);
            }
        }
        return toReturn;
    }

    static int getMaxScore(Set<String> wordList){
        int maxScore = 0;
        for (String word : wordList){
            maxScore += wordScore(word.length());
        }
        return maxScore;
    }

    private static int wordScore(int wordLength){
        if (wordLength<=2) return 0;
        switch(wordLength){
            case 3:
            case 4:
                return 1;
            case 5:
                return 2;
            case 6:
                return 3;
            case 7:
                return 5;
            default:
                return 11;
        }
    }

}
