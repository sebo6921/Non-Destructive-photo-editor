package cosc202.andie.SobelFilters;


import java.awt.image.*;

import cosc202.andie.AndieConvolveOp;
import cosc202.andie.ImageOperation;

import java.awt.Point;

/**
 * <p>
 * ImageOperation to apply horizontal Sobel effect to images.
 * </p>
 * 
 * <p>
 * This class provides a method to apply the horizontal Sobel filter to an input image using a specified kernel.
 * </p>
 */
public class SobelHorizontal implements ImageOperation, java.io.Serializable {
    /**
     * <p>
     * The coordinates of the corners of the selected area. If there is no selected
     * area, these will be equal to -1.
     * </p>
     */
    private int x1, x2, y1, y2 = -1;

    /**
     * <p>
     * Construct a new SobelHorizontal instance
     * </p>
     */
    SobelHorizontal() {
    }

    /**
     * <p>
     * Construct a new SobelHorizontal filter with given points
     * </p>
     */
    SobelHorizontal(Point p1, Point p2) {
        this.x1 = (int) p1.getX();
        this.x2 = (int) p2.getX();
        this.y1 = (int) p1.getY();
        this.y2 = (int) p2.getY();
    }

    /**
     * <p>
     * Apply a horizontal Sobel filter to an image
     * </p>
     * 
     * <p>
     * The Sobel filter is implemented via convolution. There is no size
     * to the filter as it is applied to the whole image.
     * </p>
     * 
     * @param input The image to apply the Sobel filter to.
     * @return The resulting image.
     * @throws Exception Raised if an unexpected {@code Exception} occurs.
     */
    public BufferedImage apply(BufferedImage input) {
        BufferedImage output = null;
        try {
            float[] array = { -0.5f, 0f, 0.5f, -1f, 0f, 1f, -0.5f, 0f, 0.5f };

            Kernel kernel = new Kernel(3, 3, array);
            AndieConvolveOp convOp = new AndieConvolveOp(kernel);
            output = new BufferedImage(input.getColorModel(), input.copyData(null), input.isAlphaPremultiplied(), null);
            if (x1 != -1 && x2 != -1 && y1 != -1 && y2 != -1)
                convOp.filter(input, output, x1, y1, x2, y2);
            else
                convOp.filter(input, output);
        } catch (java.awt.image.RasterFormatException ex) {
            // Handle raster format exception
            ex.printStackTrace();
        }
        return output;
    }
}
