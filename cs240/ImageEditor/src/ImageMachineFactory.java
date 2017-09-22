package src;

/*
*   ImageMachineFactory
*   One task of class: build and return reference to image editing machines
*/

public class ImageMachineFactory {

   public static ImageMachine constructMachine(String[] args) {
        String machineType = "";
        ImageMachine machine = null;
           //java ImageEditor inputFileName outputFileName {grayscale|invert|emboss|motionblur blurLength}

        try {
            machineType = args[2];
        }
        catch(ArrayIndexOutOfBoundsException exception) {
            //handleTheExceptionSomehow(exception);
            UtilityBelt.print("Invalid parameterss");
        }

        switch (machineType.toLowerCase()) {
            case "grayscale":
                machine =  new GrayScaleImageMachine(args);
                break;
            case "invert":
                machine =  new InvertImageMachine(args);
                break;
            case "emboss":
                machine =  new EmbossImageMachine(args);
                break;
            case "motionblur":
                //return motionblur machine
                break;
            default: 
                //throw ArrayIndexOutOfBoundsException exception
                System.out.print("Invalid machine type");
                break;
        }

        return machine;
   }



}