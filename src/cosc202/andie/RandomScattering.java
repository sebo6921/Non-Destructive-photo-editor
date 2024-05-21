package cosc202.andie;

import java.awt.image.BufferedImage;

/**
 * <p>
 * ImageOperation to apply a Random Scattering filter.
 * </p>
 * <p>
 * A Random Scattering filter scatters the pixels of an image
 * by randomly offsetting them within a given range.
 * 
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA
 * 4.0</a>
 * </p>
 * 
 * @see java.awt.image.ConvolveOp
 * @author Diego Olivera
 * @version 1.0
 */
public class RandomScattering implements ImageOperation, java.io.Serializable {

    private int scatterAmount;

    /**
     * <p>
     * Construct a RandomScattering filter with the given size.
     * </p>
     * 
     * </p>
     * 
     * @param radius The radius of the newly constructed RandomScattering filter
     */
    public RandomScattering(int scatterAmount) {
        this.scatterAmount = scatterAmount;
    }

    public BufferedImage apply(BufferedImage input) {
        BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null),
                input.isAlphaPremultiplied(), null);

        // Iterate over each pixel in the input image
        for (int y = 0; y < input.getHeight(); y++) {
            for (int x = 0; x < input.getWidth(); x++) {

                // Calculate new coordinates by randomly offsetting within the scatterAmount
                int newX = x + (int) (Math.random() * scatterAmount * 2 - scatterAmount);
                int newY = y + (int) (Math.random() * scatterAmount * 2 - scatterAmount);

                // Check if the new coordinates are within the bounds of the input image
                if (newX >= 0 && newX < input.getWidth() && newY >= 0 && newY < input.getHeight()) {

                    // Set the color of the pixel at the new coordinates in the output image
                    // to be the same as the color of the corresponding pixel in the input image
                    output.setRGB(newX, newY, input.getRGB(x, y));
                }
            }
        }
        return output;
    }

}
