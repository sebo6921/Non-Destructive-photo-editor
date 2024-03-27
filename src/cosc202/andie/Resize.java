package cosc202.andie;

import java.awt.image.*;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

/**
 * <p>
 * ImageOperation to resize images.
 * </p>
 * 
 * <p>
 * This takes the user chosen percentage and applies
 * it to the current image to resize it
 * </p>
 * 
 */
public class Resize implements ImageOperation, java.io.Serializable {

    /**
     * The size of the filter to apply (in percentage).
     */
    private int size;

    // Constructor that takes a size parameter and sets the size attribute
    // accordingly
    Resize(int size) {
        this.size = size;
    }

    // Default constructor that initializes the size attribute with a default value
    // of 100
    Resize() {
        this(100);
    }

    /**
     * <p>
     * Resize image.
     * </p>
     * 
     * <p>
     * Resizes image to a specified percentage of its original size while
     * maintaining better quality using smooth scaling
     * </p>
     * 
     * @param input The image to resize.
     * @return The resulting (resized) image.
     */
    public BufferedImage apply(BufferedImage input) {
        float newWidth = (int) input.getWidth() * (size / 100.0f);
        float newHeight = (int) input.getHeight() * (size / 100.0f);

        BufferedImage output = new BufferedImage((int) newWidth, (int) newHeight, BufferedImage.TYPE_INT_ARGB);

        // Create a Graphics2D object, which allows rendering 2D shapes and images
        Graphics2D g = output.createGraphics();
        // Set rendering hints for better interpolation quality when scaling images
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        // Draw the scaled instance of the input image onto the output image with smooth
        // scaling
        // using Image.SCALE_SMOOTH for better quality
        g.drawImage(input.getScaledInstance((int) newWidth, (int) newHeight, Image.SCALE_SMOOTH), 0, 0, null);
        g.dispose();

        return output;
    }
}
