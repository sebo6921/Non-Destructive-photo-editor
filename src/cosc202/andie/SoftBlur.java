package cosc202.andie;

import java.awt.image.*;

/**
 * <p>
 * ImageOperation to apply a soft blur filter.
 * </p>
 * 
 * <p>
 * A soft blur filter blurs an image by replacing each pixel by the average of
 * the
 * pixels in a surrounding neighbourhood, and can be implemented by a
 * convoloution.
 * </p>
 */
public class SoftBlur implements ImageOperation, java.io.Serializable {

    SoftBlur() {
    }

    public BufferedImage apply(BufferedImage input) {
        float[] array = { 0, 1 / 8.0f, 0,
                1 / 8.0f, 1 / 2.0f, 1 / 8, 0f,
                0, 1 / 8.0f, 0 };
        Kernel kernel = new Kernel(3, 3, array);

        ConvolveOp convOp = new ConvolveOp(kernel);

        BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null),
                input.isAlphaPremultiplied(), null);
        convOp.filter(input, output);

        return output;
    }
}
