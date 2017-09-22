package src;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/*
*   Pixel
*   One task of object: represent basic pixel
*   
*/

public class Pixel{

    public int red;
    public int green;
    public int blue;
    public int maxColorScheme;

    public Pixel(int maxColorScheme, int red, int green, int blue){
        this.maxColorScheme = maxColorScheme;
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public String toString(){
        String pixelString = red + "\n" + green + "\n" + blue;
        return pixelString;

    }

    public void convertToGray(){
        int averageColor = (red + green + blue)/3;
        red = averageColor;
        green = averageColor;
        blue = averageColor;
    }

	public void invertPixel(){
		red = Math.abs(red - maxColorScheme);
		green = Math.abs(green - maxColorScheme);
		blue = Math.abs(blue - maxColorScheme);
	}



}