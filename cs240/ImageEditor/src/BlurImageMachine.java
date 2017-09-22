package src;


/*
*   BlurImageMachine
*   One task of object: blur images
*   
*/

public class BlurImageMachine extends ImageMachine{

   private String blurLength;

   //this machine needs another argument, blurlength,
   //so the superconstructor must be overidden
   public BlurImageMachine(String[] args){
        super(args);
        try {
            blurLength = args[4];
        }
        catch(ArrayIndexOutOfBoundsException exception) {
            handleTheExceptionSomehow(exception);
        }
   }

}