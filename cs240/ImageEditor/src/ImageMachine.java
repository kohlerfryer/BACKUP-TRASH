package src;

/*
*   ImageMachineFactory
*   One task of object: provide basic functionality to inherit from to edit images
*   Reads in data source from stream
*/

public abstract class ImageMachine {
   private String inputFilePath;
   private String outputFilePath;
   private String machineType;
   protected PPMImage image;

   public ImageMachine(String[] args){
        try {
            inputFilePath = args[0];
            outputFilePath = args[1];
            machineType = args[2];
        }
        catch(ArrayIndexOutOfBoundsException exception){
            //handleTheExceptionSomehow(exception);
            UtilityBelt.print("Invalid number of arguments provided");
        }

        image = new PPMImage(inputFilePath);

   }

   public void saveImage(){
       PPMFileGenerator.writeFile(image.toString(), outputFilePath);
   }

   public abstract void processImage();

//    public void processImage(){

//    }


}