
package src;

/*
*   EmbossImageMachines
*   One task of object: make images black and white
*   
*/

// To convert an image to grayscale, each pixel’s color value is changed to the average of the pixel’s red, green, and blue value. For example:
// Original pixel color values:
// Red: 25 Green: 230 Blue: 122
// Grayscale conversion: (25 + 230 + 122) / 3 = 125 (using integer division) Red: 125 Green: 125 Blue: 125


public class EmbossImageMachine extends ImageMachine {

    public GrayScaleImageMachine(String[] args){
        super(args);
        //UtilityBelt.print("HELLLLLLOOOOOO THERE from grayscale");

    }

    //TODO pass in closure to handle processing here 
    public void processImage(){
        image.embossColors();
    }

}