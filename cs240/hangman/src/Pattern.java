package src;
import java.util.Scanner;
import java.util.Set;
import java.io.File;
import java.util.TreeSet;
import java.io.FileNotFoundException;


public class Pattern{
    Array<Character> pattern;
    final Character notFoundDilimeter = "-";

    public Pattern(int size){
        pattern = new Array<Character>(size);
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < pattern.length; i++){
            if(pattern[i] == null) 
                sb.append(notFoundDilimeter);
            else
                sb.append(pattern[i]);
        }
        return sb.toString();
    }

    // public findBestPattern(Set<String> dictionary, Character c){
    //     //make sure to clear dictionary and re search it before calling this each time
    //     for(String word : dictionary){
    //     }
    // }

    private void addToPattern(String bitString, Character c){
        pattern[index] = c;
    }
}