package cosc202.andie;

import java.awt.image.*;

/**
 * <p>
 * ImageOperation to invert the colours of the image.
 * </p>
 * 
 * <p>
 * This takes the current values of the rgb parameters and
 * changes them to be (255 - current rgb value) for each
 * parameter individually.
 * </p>
 * 
 * @author James Maher
 * @version 0.1
 */
public class ImageInversion implements ImageOperation, java.io.Serializable{

    /**
     * <p>
     * Create a new ImageInversion operation
     * </p
     */
    ImageInversion() {
    
    }
    
    /**
     * <p>
     * Apply the inversion to the image.
     * </p>
     * 
     * <p>
     * The inversion process iterates over the image and
     * converts the current rgb values for each pixel to
     * (255 - current rgb value).
     * </p>
     * 
     * @param input the image being inverted
     * @param return the inverted image
     */
    public BufferedImage apply(BufferedImage input) {
        for (int y = 0; y < input.getHeight(); y++) {
            for (int x = 0; x < input.getWidth(); x++) {
                int argb = input.getRGB(x, y);
                int a = (argb & 0xFF000000) >> 24;
                int r = (argb & 0x00FF0000) >> 16;
                int g = (argb & 0x0000FF00) >> 8;
                int b = (argb & 0x000000FF);

                r = 255 - r;
                g = 255 - g;
                b = 255 - b;


                argb = (a << 24) | (r << 16) | (g << 8) | b;
                input.setRGB(x, y, argb);
            }
        }

        return input;
    }
}
