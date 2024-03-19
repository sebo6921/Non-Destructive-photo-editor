package cosc202.andie;

import java.awt.image.*;
import java.util.*;

public class MedianFilter implements ImageOperation, java.io.Serializable  {
    /**
     * The size of filter to apply. A radius of 1 is a 3x3 filter, a radius of 2 a
     * 5x5 filter, and so forth.
     */
    private int radius;

    /**
     * constructor, with a default radius of 1
     */
    public MedianFilter() {
        this.radius = 1;
    }

    /**
     * constructor that sets the radius based on user input
     * 
     * @param radius - the int value that the user wants to set as the radius
     */
    public MedianFilter(int radius) {
        this.radius = radius;
    }

    public BufferedImage apply(BufferedImage input) {
        int width = input.getWidth(); // retrieves the width of the BufferedImage parameter
        int height = input.getHeight(); // retrieves the height of the BufferedImage parameter

        BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null), input.isAlphaPremultiplied(), null);

        

        


        return output;




}

}
