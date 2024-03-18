package cosc202.andie;

import java.awt.image.*;
import java.util.*;

public class MedianFilter implements ImageOperation {
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
}
