package cosc202.andie;

import java.awt.image.*;

/**
 * <p>
 * ImageOperation to apply a Gaussian blur filter.
 * </p>
 * 
 * <p>
 * A Gaussian blur filter blurs an image by replacing each pixel by the weighted average 
 * of the pixels in a surrounding neighbourhood, and can be implemented by a convoloution.
 * </p>
 * 
 * @see java.awt.image.ConvolveOp
 * @author Jessica Fan
 * @version 1.0
 */
public class GaussianBlur implements ImageOperation, java.io.Serializable {

    /**
     * The size of filter to apply. A radius of 1 is a 3x3 filter, a radius of 2 a 5x5 filter, and so forth.
     */
    private int radius;

    /**
     * <p>
     * Construct a Gaussian blur filter with the given size.
     * </p>
     * 
     * <p>
     * The size of the filter is the 'radius' of the convolution kernel used.
     * A size of 1 is a 3x3 filter, 2 is 5x5, and so on.
     * Larger filters give a stronger blurring effect.
     * </p>
     * 
     * @param radius The radius of the newly constructed GaussianBlur
     */
    GaussianBlur(int radius){
        this.radius = radius;
    }

    /**
     * <p>
     * Construct a Gaussian blur filter with the default size.
     * </p
     * >
     * <p>
     * By default, a Gaussian blur filter has radius 1.
     * </p>
     * 
     * @see GaussianBlur(int)
     */
    GaussianBlur(){
        this(1);
    }

    /**
     * <p>
     * Apply a Gaussian blur filter to an image.
     * </p>
     * 
     * <p>
     * As with many filters, the Gaussian blur filter is implemented via convolution.
     * The size of the convolution kernel is specified by the {@link radius}.  
     * Larger radii lead to stronger blurring.
     * </p>
     * 
     * @param input The image to apply the Gaussian blur filter to.
     * @return The resulting (blurred)) image.
     */
    public BufferedImage apply (BufferedImage input){
        float[] array = creatingGaussianKernel(radius);
        
        Kernel kernel = new Kernel(2*radius+1, 2*radius+1, array);
        ConvolveOp convOp = new ConvolveOp(kernel);
        BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null), input.isAlphaPremultiplied(), null);
        convOp.filter(input, output);

        return output;
    }

    /**
     * 
     * @param radius The radius of the newly constructed Gaussian blur
     * @return The array that will be used to create the kernel
     */
    private float[] creatingGaussianKernel(int radius){
        int size = 2 * radius + 1;
        float[] array = new float[size * size];
        double sigma = radius / 3.0;
        double frontHalf = 1 / (2 * Math.PI * sigma * sigma);
        double total = 0;

        for (int x = -radius; x <= radius; x++){
            for (int y = -radius; y <= radius; y++){
                int index = (x + radius) * size + (y + radius);
                double exp = -(x * x + y * y)/(2 * sigma * sigma);
                array[index] = (float) frontHalf * (float) Math.exp(exp);
                total += array[index];
            }
        }

        for (int i = 0; i < array.length; i++){
            array[i] /= total;
        }

        return array;
    }

}