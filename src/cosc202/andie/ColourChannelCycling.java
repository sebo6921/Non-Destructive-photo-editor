package cosc202.andie;

import java.awt.image.*;

/**
 * <p>
 * ImageOperation to cycle the colour channels of the image.
 * </p>
 * 
 * <p>
 * This takes the current values of the rgb colour channels and
 * rotates them between each other.
 * </p>
 * 
 * @author James Maher
 * @version 1.0
 */
public class ColourChannelCycling implements ImageOperation, java.io.Serializable{

    /**
     * <p>
     * Create a new ColourChannelCycling operation
     * </p
     */
    ColourChannelCycling() {
    
    }
    
    /**
     * <p>
     * Apply the colour channel cycling to the image.
     * </p>
     * 
     * <p>
     * The colour channel cycling process iterates over the image and
     * converts the current rgb values for each pixel to
     * the corresponding rgb value for the current cycle.
     * </p>
     * 
     * @param input the image having colour channel cycling applied to it
     * @param return the colour channel cycled image
     */
    public BufferedImage apply(BufferedImage input) {
        for (int y = 0; y < input.getHeight(); y++) {
            for (int x = 0; x < input.getWidth(); x++) {
                int argb = input.getRGB(x, y);
                int a = (argb & 0xFF000000) >> 24;
                int r = (argb & 0x00FF0000) >> 16;
                int g = (argb & 0x0000FF00) >> 8;
                int b = (argb & 0x000000FF);

                int temp = 0;
                
                if(temp == 0){
                    temp = r;
                    r = b;
                    b = g;
                    g = temp;
                }else{
                    break;
                }

                argb = (a << 24) | (r << 16) | (g << 8) | b;
                input.setRGB(x, y, argb);
            }
        }

        return input;
    }
}