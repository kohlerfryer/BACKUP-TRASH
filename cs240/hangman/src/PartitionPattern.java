package src;
import java.util.Scanner;
import java.util.Set;
import java.io.File;
import java.util.TreeSet;
import java.io.FileNotFoundException;


public class PartitionPattern{
    char[] pattern;
    int size;
    final char notFoundDilimeter = '-';

    public PartitionPattern(int size){
        this.size = size;
        pattern = new char[size];
        for(int i = 0; i < size; i++){
              pattern[i] = notFoundDilimeter;
        }
    }

    public boolean incomplete(){
        return toString().contains("-");
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
      while(bitString.length() < size){
         bitString = "0" + bitString;
      }
    //   System.out.println(bitString);
      char[] bitStringArray = bitString.toCharArray();
      for(int i = 0; i < bitStringArray.length; i++){
        if(bitStringArray[i] == '1'){
          pattern[i] = c;
        }
      }
    }
}
