package cosc202.andie;

import java.awt.image.*;

/**
 * <p>
 * ImageOperation to cycle the colour channels of the image.
 * </p>
 * testing this is working
 * <p>
 * This takes the current values of the rgb colour channels and
 * rotates them between each other.
 * </p>
 * 
 * @author James Maher
 * @version 1.1
 */
public class ColourChannelCycling implements ImageOperation, java.io.Serializable {

    /** Int variable that keeps track of the variation index */
    private static int currentVariationIndex = 0;

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
     * @param input  the image having colour channel cycling applied to it
     * @param return the colour channel cycled image
     */
    public BufferedImage apply(BufferedImage input) {
        for (int y = 0; y < input.getHeight(); y++) {
            for (int x = 0; x < input.getWidth(); x++) {
                int argb = input.getRGB(x, y);

                int a = (argb >> 24) & 0xFF;
                int r = (argb >> 16) & 0xFF;
                int g = (argb >> 8) & 0xFF;
                int b = argb & 0xFF;

                int tempR = r;
                int tempG = g;
                int tempB = b;

                if (currentVariationIndex == 0) {
                    r = tempR;
                    g = tempB;
                    b = tempG;
                } else if (currentVariationIndex == 1) {
                    r = tempG;
                    g = tempR;
                    b = tempB;
                } else if (currentVariationIndex == 2) {
                    r = tempG;
                    g = tempB;
                    b = tempR;
                } else if (currentVariationIndex == 3) {
                    r = tempB;
                    g = tempR;
                    b = tempG;
                } else if (currentVariationIndex == 4) {
                    r = tempB;
                    g = tempG;
                    b = tempR;
                } else if (currentVariationIndex == 5) {
                    r = tempR;
                    g = tempG;
                    b = tempB;
                }

                argb = (a << 24) | (r << 16) | (g << 8) | b;
                input.setRGB(x, y, argb);
            }
        }
        currentVariationIndex = (currentVariationIndex + 1) % 6; // Update to the next variation index

        return input;
    }
}