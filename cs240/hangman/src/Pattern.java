package src;
import java.util.Scanner;
import java.util.Set;
import java.io.File;
import java.util.TreeSet;
import java.io.FileNotFoundException;


public class Pattern{
    char[] pattern;
    final char notFoundDilimeter = '-';

    public Pattern(int size){
        pattern = new char[size];
        for(int i = 0; i < size; i++){
              pattern[i] = notFoundDilimeter;
        }
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < pattern.length; i++){
              sb.append(pattern[i]);
        }
        return sb.toString();
    }

    public void addToPattern(int bit, Character c){
      String bitString = Integer.toBinaryString(bit);
      char[] bitStringArray = bitString.toCharArray();
      for(int i = 0; i < bitStringArray.length -1; i++){
        if(bitStringArray[i] == '1'){
          pattern[i] = c;
        }
      }
    }
}
