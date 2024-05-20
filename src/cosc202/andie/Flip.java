package cosc202.andie;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.*;

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
 * @author Diego Olivera
 * @version 1.0
 * 
 */
public class Flip implements ImageOperation, java.io.Serializable {

    private int f;

    /**
     * Constructs a new Flip instance with a default flip angle of 1 degree.
     */
    public Flip() {
        this.f = 1;
    }

    /**
     * Constructs a new Flip instance with the specified flip angle.
     * 
     * @param f The angle at which to flip the image (90 or 180 degrees).
     */
    public Flip(int f) {
        this.f = f;
    }

    /**
     * Applies the flip operation to the input image.
     * 
     * @param input The input image to be flipped.
     * @return The flipped image.
     */
    public BufferedImage apply(BufferedImage input) {
        int newWidth = input.getWidth();
        int newHeight = input.getHeight();

        if (f == 90) {
            return flipHorizontally(input);
        }

        if (f == 180) {
            return flipVertically(input);
        }

        BufferedImage output = new BufferedImage(newWidth, newHeight, input.getType());

        Graphics2D g = output.createGraphics();
        AffineTransform transform = new AffineTransform();

        transform.rotate(Math.toRadians(f), newWidth / 2, newHeight / 2);
        g.drawImage(input, transform, null);
        g.dispose();

        return output;
    }

    /**
     * Flips the input image horizontally.
     * 
     * @param input The input image to be flipped.
     * @return The horizontally flipped image.
     */
    public BufferedImage flipHorizontally(BufferedImage input) {
        int newWidth = input.getWidth();
        int newHeight = input.getHeight();

        BufferedImage flipped = new BufferedImage(newWidth, newHeight, input.getType());
        for (int y = 0; y < newHeight; y++) {
            for (int x = 0; x < newWidth; x++) {
                flipped.setRGB(x, y, input.getRGB(newWidth - 1 - x, y));
            }
        }

        return flipped;
    }

    /**
     * Flips the input image vertically.
     * 
     * @param input The input image to be flipped.
     * @return The vertically flipped image.
     */
    public BufferedImage flipVertically(BufferedImage input) {
        int newWidth = input.getWidth();
        int newHeight = input.getHeight();

        BufferedImage flipped = new BufferedImage(newWidth, newHeight, input.getType());
        for (int y = 0; y < newHeight; y++) {
            for (int x = 0; x < newWidth; x++) {
                flipped.setRGB(x, y, input.getRGB(x, newHeight - 1 - y));
            }
        }

        return flipped;
    }
}
