package src;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;

/*
*   ImageGreyScaleMachine
*   One task of object: make images black and white
*   
*/

public class PPMImage{

   private Scanner scanner;
   private File inputFile;
   private String PPMType;
   private int width;
   private int height;
   private int maxColorScheme;
   private Pixel[][] pixelGrid;

    public PPMImage(String inputFileName){
        try {
            inputFile = new File(inputFileName);
            //ignore comments
            scanner = new Scanner(inputFile).useDelimiter("((#[^\\n]*\\n)|(\\s+))+");

            PPMType = scanner.next();
            width = scanner.nextInt();
            height = scanner.nextInt();
            maxColorScheme = scanner.nextInt();
            //to start grid counting at 0 : width-1...etc
            pixelGrid = new Pixel[height][width];
            //UtilityBelt.print(pixelGrid[0]);
            fillUpPixelGrid();
            
        }
        catch(FileNotFoundException|NoSuchElementException exception){
            //UtilityBelt.print("Input file "+ inputFileName +"  not found");
            System.out.println(exception);
        }
    }
    

    private void fillUpPixelGrid(){
        int row = 0;
        int column = 0;
        try {
            for(int i = 0; i < height; i++){
                for(int j = 0; j < width; j++){
                    int red = scanner.nextInt();
                    int blue = scanner.nextInt();
                    int green = scanner.nextInt();
                    pixelGrid[i][j] = new Pixel(maxColorScheme, red, blue, green);
                }
            }
            // while(scanner.hasNextInt()){

            //     if(column == width -1){
            //         column = 0;
            //         row+=1;
            //     }

            //     int red = scanner.nextInt();
            //     int blue = scanner.nextInt();
            //     int green = scanner.nextInt();
            //     pixelGrid[row][column] = new Pixel(red, blue, green);

            //     column++;
            // }
        }
        catch(ArrayIndexOutOfBoundsException exception){
            //handleTheExceptionSomehow(exception);
            UtilityBelt.print("out of bounds at row: " + row + "and column: " + column + "with width: " + width);
        }

    }

    //TODO
    // private void editPixelMap(Function<Void> closure)){
    //     closure(pixelGrid);
    // }

    //TODO
    //helper method for iterating with closure :)

    public void convertToGrayScale(){
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                pixelGrid[i][j].convertToGray();
            }
        }
    }

    public void invertColors(){
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                pixelGrid[i][j].invertPixel();
            }
        }
    }

    public void embossColors(){
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){

                //todo fix this
                if(i-1 < 0 || j-1< 0)continue;
                
                //todo clean is ugly
                Pixel pixelIteration = pixelGrid[i][j];
                Pixel upperLeftPixelIteeration = pixelGrid[i-1][j-1];
                int maxDifference = pixelIteration.red - upperLeftPixelIteeration.red;
                maxDifference = maxDifference < pixelIteration.green - upperLeftPixelIteeration.green ? pixelIteration.green - upperLeftPixelIteeration.green : maxDifference;
                maxDifference = maxDifference < pixelIteration.blue - upperLeftPixelIteeration.blue ? pixelIteration.blue - upperLeftPixelIteeration.blue : maxDifference;
                maxDifference += 128;
                maxDifference = maxDifference < 0 ? 0 : maxDifference;
                maxDifference = maxDifference > 255 ? 255 : maxDifference;
                
                pixelIteration.red = maxDifference;
                pixelIteration.green = maxDifference;
                pixelIteration.blue = maxDifference;

            }
        }
    }

    public String toString(){
		StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("P3\n");
		stringBuilder.append(width);
		stringBuilder.append(" ");
		stringBuilder.append(height);
		stringBuilder.append("\n"+maxColorScheme+"\n");
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                stringBuilder.append(pixelGrid[i][j].toString());
                stringBuilder.append("\n");
            }
        }
        return stringBuilder.toString();
    }



}