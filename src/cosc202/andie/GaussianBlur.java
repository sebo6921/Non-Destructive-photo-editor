package cosc202.andie;

import java.awt.image.*;
import java.awt.Point;

/**
 * <p>
 * ImageOperation to apply a Gaussian blur filter.
 * </p>
 * 
 * <p>
 * A Gaussian blur filter blurs an image by replacing each pixel by the weighted
 * average
 * of the pixels in a surrounding neighbourhood, and can be implemented by a
 * convoloution.
 * </p>
 * 
 * @see java.awt.image.ConvolveOp
 * @author Jessica Fan
 * @version 1.0
 */
public class GaussianBlur implements ImageOperation, java.io.Serializable {

    /**
     * The size of the filter to apply. A radius of 1 is 3x3 etc.
     */
    private int radius;

    /**
     * <p>
     * The coordinates of the corners of the selected area. If there is no selected
     * area, these will be equal to -1.
     * </p>
     */
    private int x1, x2, y1, y2 = -1;

    /**
     * <p>
     * Construct a Gaussian Blur filter with the given size
     * </p>
     * 
     * <p>
     * The size of the filter is the 'radius' of the convolution kernel used.
     * A larger radius gives a stronger blurring effect.
     * </p>
     * 
     * @param radius The radius of the newly constructed GaussianBlurFilter
     */
    GaussianBlur(int radius) {
        this.radius = radius;
    }

    /**
     * <p>
     * Construct a Gaussian Blur filter with the given size to apply from p1 to p2.
     * </p>
     * 
     * <p>
     * The size of the filter is the 'radius' of the convolution kernel used.
     * A larger radius gives a stronger blurring effect.
     * </p>
     * 
     * @param radius The radius of the newly constructed GaussianBlurFilter
     * @param p1     The point at the top corner of the selection
     * @param p2     The point at the bottom corner of the selection
     */
    GaussianBlur(int radius, Point p1, Point p2) {
        this.radius = radius;
        this.x1 = (int) p1.getX();
        this.x2 = (int) p2.getX();
        this.y1 = (int) p1.getY();
        this.y2 = (int) p2.getY();
    }

    /**
     * <p>
     * Construct a Gaussian Blur filter with the default size.
     * </p>
     * 
     * <p>
     * By default, a Gaussian Filter has radius 1.
     * </p>
     * 
     * @see GaussianBlurFilter(int)
     */
    GaussianBlur() {
        this(1);
    }

    /**
     * <p>
     * Apply a Gaussian Blur filter to an image.
     * </p>
     * 
     * <p>
     * The Gaussian Blur filter is implemented via convolution.
     * The size of the convolution kernel used is specified by the {@link radius}.
     * A larger radius leads to a stronger blurring effect.
     * </p>
     * 
     * @param input The image to apply the Gaussian Blur filter to.
     * @return The resulting blurred image
     * @throws Exception Raised if an unexpected {@code Exception} occurs.
     */
    public BufferedImage apply(BufferedImage input) {
        BufferedImage output = null;
        try {
            // if radius is 0 then return original image;
            if (radius == 0) {
                return input;
            }
            int size = (2 * radius + 1) * (2 * radius + 1);
            float[] array = new float[size];
            float sigma = ((float) radius) / 3;
            float twoSigmaSq = 2 * sigma * sigma;
            float sum = 0;
            int index = 0;

            for (int y = -radius; y <= radius; y++) {
                for (int x = -radius; x <= radius; x++) {
                    float value = (float) Math.exp(-(x * x + y * y) / twoSigmaSq);
                    value /= twoSigmaSq * Math.PI;
                    array[index] = value;
                    sum += value;
                    index++;
                }
            }

            for (int i = 0; i < array.length; i++) {
                array[i] /= sum;
            }

            Kernel kernel = new Kernel(2 * radius + 1, 2 * radius + 1, array);
            AndieConvolveOp convOp = new AndieConvolveOp(kernel);
            output = new BufferedImage(input.getColorModel(), input.copyData(null), input.isAlphaPremultiplied(), null);

            if (x1 != -1 && x2 != -1 && y1 != -1 && y2 != -1)
                convOp.filter(input, output, x1, y1, x2, y2);
            else
                convOp.filter(input, output);

        } catch (NullPointerException ex) {
            // Handle null pointer exception
            ex.printStackTrace();
        }
        return output;
    }
}
