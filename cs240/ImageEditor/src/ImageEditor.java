package src;

/*
*   ImageEditor
*   One task of object: Main class that manages the overhead for image editing process
*   
*/

public class ImageEditor {

   public static void main(String[] args) {
      ImageMachine machine = ImageMachineFactory.constructMachine(args);
      machine.processImage();
      machine.saveImage();
      UtilityBelt.print("");
      //machine.processImage();
      //machine.exportImage();
   }

}