package src;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.IOException;

public class PPMFileGenerator {

    //TODO handle failures	
	public static boolean writeFile(String contents, String filePath){
        try{

            PrintWriter printWriter = new PrintWriter(new FileWriter(filePath));
            printWriter.write(contents);
            printWriter.close();

        }catch(IOException exception){
            System.out.println(exception);
            return false;
        }
        return true;
	}
}