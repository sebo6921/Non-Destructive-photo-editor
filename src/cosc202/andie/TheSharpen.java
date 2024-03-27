package cosc202.andie;

import java.awt.image.*;
import java.util.*;

/**
 * <p>
 * ImageOperation to apply sharpening effect to images.
 * </p>
 * 
 * <p>
 * This class provides a method to sharpen an input image using a specified
 * kernel.
 * </p>
 */
public class TheSharpen implements ImageOperation, java.io.Serializable {

    /**
     * Constructs a new TheSharpen instance.
     */
    TheSharpen() {

    }

    /**
     * Applies the sharpening effect to the input image.
     * 
     * @param input The input image to be sharpened.
     * @return The sharpened image.
     */
    public BufferedImage apply(BufferedImage input) {
        float[] array = { 0, -1 / 2.0f, 0,
                -1 / 2.0f, 3, -1 / 2.0f,
                0, -1 / 2.0f, 0 };
        Kernel kernel = new Kernel(3, 3, array);

        ConvolveOp convOp = new ConvolveOp(kernel);

        BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null),
                input.isAlphaPremultiplied(), null);
        convOp.filter(input, output);

        return output;
    }
}
