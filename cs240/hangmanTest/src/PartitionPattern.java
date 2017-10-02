package src;
import java.util.*;
import java.io.*;

public class PartitionPattern{
    char[] wordRepresentation;

    PartitionPattern(int size){
      wordRepresentation = new char[size];
      for(int i = 0; i < size-1; i ++){
          wordRepresentation[i] = '-';
      }
    }

    public boolean patternIncomplete(){
       return toString().contains("-");
    }

    public String toString(){
      StringBuilder sb = new StringBuilder();
      for(char c : wordRepresentation){
          sb.append(c);
      }
      return sb.toString();
    }

    public void addPattern(int binaryIndex, char guess){
        String binaryString = Integer.toBinaryString(binaryIndex);
        while(binaryString.length() != wordRepresentation.length){
            binaryString = "0" + binaryString;
        }
        // System.out.println(binaryString);
        char[] binaryStringChar = binaryString.toCharArray();
        int index = 0;
        for(char c : binaryStringChar){
          if(c == '1') wordRepresentation[index] = guess;
          index++;
        }
    }
}
