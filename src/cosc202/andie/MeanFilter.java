package cosc202.andie;

import java.awt.image.*;
import java.util.*;

/**
 * <p>
 * ImageOperation to apply a Mean (simple blur) filter.
 * </p>
 * 
 * <p>
 * A Mean filter blurs an image by replacing each pixel by the average of the
 * pixels in a surrounding neighbourhood, and can be implemented by a
 * convoloution.
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA
 * 4.0</a>
 * </p>
 * 
 * @see java.awt.image.ConvolveOp
 * @author Steven Mills
 * @version 1.0
 */
public class MeanFilter implements ImageOperation, java.io.Serializable {

    /**
     * The size of filter to apply. A radius of 1 is a 3x3 filter, a radius of 2 a
     * 5x5 filter, and so forth.
     */
    private int radius;

    /**
     * <p>
     * Construct a Mean filter with the given size.
     * </p>
     * 
     * <p>
     * The size of the filter is the 'radius' of the convolution kernel used.
     * A size of 1 is a 3x3 filter, 2 is 5x5, and so on.
     * Larger filters give a stronger blurring effect.
     * </p>
     * 
     * @param radius The radius of the newly constructed MeanFilter
     */
    MeanFilter(int radius) {
        this.radius = radius;
    }

    /**
     * <p>
     * Construct a Mean filter with the default size.
     * </p
     * >
     * <p>
     * By default, a Mean filter has radius 1.
     * </p>
     * 
     * @see MeanFilter(int)
     */
    MeanFilter() {
        this(1);
    }

    /**
     * <p>
     * Apply a Mean filter to an image.
     * </p>
     * 
     * <p>
     * As with many filters, the Mean filter is implemented via convolution.
     * The size of the convolution kernel is specified by the {@link radius}.
     * Larger radii lead to stronger blurring.
     * </p>
     * 
     * @param input The image to apply the Mean filter to.
     * @return The resulting (blurred)) image.
     */
    public BufferedImage apply(BufferedImage input) {
        int width = input.getWidth();
        int height = input.getHeight();

        // Pad the input image
        BufferedImage paddedImage = padImage(input, radius);

        // Create an output image with the same dimensions as the input image
        BufferedImage output = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // Apply the Mean filter
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // Calculate the average pixel value within the filter window
                int[] pixel = calculateMean(paddedImage, x + radius, y + radius, radius);
                // Set the pixel value in the output image
                output.setRGB(x, y, new Color(pixel[0], pixel[1], pixel[2]).getRGB());
            }
        }

        return output;
    }

    private BufferedImage padImage(BufferedImage input, int radius) {
        int padSize = 2 * radius;
        int width = input.getWidth();
        int height = input.getHeight();
        
        // Create a new image with padded borders
        BufferedImage paddedImage = new BufferedImage(width + padSize, height + padSize, BufferedImage.TYPE_INT_RGB);
        
        // Copy the input image into the padded image, leaving the border pixels with zero values
        Graphics2D g2d = paddedImage.createGraphics();
        g2d.drawImage(input, radius, radius, null);
        g2d.dispose();

        return paddedImage;
    }

    private int[] calculateMean(BufferedImage image, int x, int y, int radius) {
        int[] sum = {0, 0, 0};
        int count = 0;

        // Iterate over the filter window
        for (int i = y - radius; i <= y + radius; i++) {
            for (int j = x - radius; j <= x + radius; j++) {
                // Get pixel value at (j, i)
                Color color = new Color(image.getRGB(j, i));
                sum[0] += color.getRed();
                sum[1] += color.getGreen();
                sum[2] += color.getBlue();
                count++;
            }
        }

        // Calculate the mean of pixel values
        int[] mean = {
            sum[0] / count,
            sum[1] / count,
            sum[2] / count
        };

        return mean;
    }
}
