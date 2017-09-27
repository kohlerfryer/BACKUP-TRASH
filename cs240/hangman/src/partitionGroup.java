package src;
import java.util.Scanner;
import java.util.Set;
import java.io.File;
import java.util.TreeSet;
import java.io.FileNotFoundException;
import java.util.HashMap;



public class partitionGroup{
	Set<String> words = new TreeSet<String>();
    int binaryFrequencyPattern;

    public partitionGroup(int binaryFrequencyPattern){
        this.binaryFrequencyPattern = binaryPattern;
    }

    public int getPatternFrequency(){
        return Integer.bitCount(binaryFrequencyPattern);      
    }


}
