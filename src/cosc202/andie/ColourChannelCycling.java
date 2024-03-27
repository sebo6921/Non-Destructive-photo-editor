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
 * @version 1.1
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
    // public BufferedImage apply(BufferedImage input) {
    //     int r, g, b; // the pixel colour
    //     for (int y = 0; y < input.getHeight(); y++) {
    //         for (int x = 0; x < input.getWidth(); x++) {
    //             int argb = input.getRGB(x, y);
                
    //             int a = (argb >> 24) & 0xFF;
    //             r = (argb >> 16) & 0xFF;
    //             g = (argb >> 8) & 0xFF;
    //             b = argb & 0xFF;
                    
    //             int temp = r;
    //             r = g;
    //             g = b;
    //             b = temp;    
    
    //             argb = (a << 24) | (r << 16) | (g << 8) | b;
    //             input.setRGB(x, y, argb);
                
    //         }
    //     }

    //     return input;
    // }

    private static int currentVariationIndex = 0;

    public BufferedImage apply(BufferedImage input) {
        int r, g, b; // the pixel colour
        
        // Define variations of swapping RGB channels
        int[][] variations = {
            {0, 2, 1},  // RBG
            {1, 0, 2},  // GRB
            {1, 2, 0},  // GBR
            {2, 0, 1},  // BRG
            {2, 1, 0},   // BGR
            {0, 1, 2},  // RGB
        };
        
        // Determine the current variation based on the stored index
        int[] currentVariation = variations[currentVariationIndex];

        for (int y = 0; y < input.getHeight(); y++) {
            for (int x = 0; x < input.getWidth(); x++) {
                int argb = input.getRGB(x, y);
                
                int a = (argb >> 24) & 0xFF;
                r = (argb >> 16) & 0xFF;
                g = (argb >> 8) & 0xFF;
                b = argb & 0xFF;
                    
                // Swap the RGB channels based on the current variation
                int tempR = r;
                int tempG = g;
                int tempB = b;
                r = tempR;
                g = tempG;
                b = tempB;

                if (currentVariationIndex == 0){
                    b = tempG;
                    g = tempB;
                } else if (currentVariationIndex == 1){
                    r = tempG;
                    g = tempR;
                } else if (currentVariationIndex == 2){
                    r = tempG;
                    g = tempB;
                    b = tempR;
                } else if (currentVariationIndex == 3){
                    r = tempB;
                    g = tempR;
                    b = tempG;
                } else if (currentVariationIndex == 4){
                    r = tempB;
                    g = tempR;
                    b = tempG;
                } else if (currentVariationIndex == 5){
                    r = tempR;
                    g = tempG;
                    b = tempB;
                }

                // if (currentVariationIndex == 1 || currentVariationIndex == 3 || currentVariationIndex == 5) {
                //     r = tempB;
                //     b = tempR;
                // } else if (currentVariationIndex == 2 || currentVariationIndex == 4) {
                //     r = tempG;
                //     g = tempR;
                // }
                
                argb = (a << 24) | (r << 16) | (g << 8) | b;
                input.setRGB(x, y, argb);
            }
        }
        
        currentVariationIndex = (currentVariationIndex + 1) % 6; // Update to the next variation index

        return input;
    }

    
}