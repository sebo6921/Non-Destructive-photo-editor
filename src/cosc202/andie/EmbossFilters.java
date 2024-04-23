package cosc202.andie;

import java.awt.image.BufferedImage;
import java.awt.Color;

/**
 * A utility class for applying emboss filters to images.
 */
public class EmbossFilters {

    /**
     * Applies convolution to a grayscale image with a given kernel.
     *
     * @param image  The input grayscale image.
     * @param kernel The convolution kernel.
     * @return The result of applying convolution.
     */
    public static BufferedImage applyConvolution(BufferedImage image, double[][] kernel) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // iterates over each pixel in the image
        for (int y = 1; y < height - 1; y++) {
            for (int x = 1; x < width - 1; x++) {
                double sumR = 0, sumG = 0, sumB = 0;

                // applies the convolution kernel to the neighborhood of the pixel
                for (int j = -1; j <= 1; j++) {
                    for (int i = -1; i <= 1; i++) {
                        Color c = new Color(image.getRGB(x + i, y + j));
                        double kernelVal = kernel[j + 1][i + 1];
                        sumR += c.getRed() * kernelVal;
                        sumG += c.getGreen() * kernelVal;
                        sumB += c.getBlue() * kernelVal;
                    }
                }

                // clamp the color values to [0, 255]
                sumR = Math.min(Math.max(sumR, 0), 255);
                sumG = Math.min(Math.max(sumG, 0), 255);
                sumB = Math.min(Math.max(sumB, 0), 255);

                // set the new color for the pixel in the result image
                Color newColor = new Color((int) sumR, (int) sumG, (int) sumB);
                result.setRGB(x, y, newColor.getRGB());
            }
        }

        return result;
    }

    /**
     * Applies a set of emboss filters to an input image.
     *
     * @param image The input image.
     * @return An array of images, each processed with a different emboss filter.
     * 
     */
    public static BufferedImage[] applyEmbossFilters(BufferedImage image) {
        // Define the emboss kernels
        double[][][] embossKernels = {
            {{0, 0, 0}, {1, 0, -1}, {0, 0, 0}},
            {{1, 0, -1}, {0, 0, 0}, {-1, 0, 1}},
            {{0, 0, 0}, {1, 0, 0}, {0, 0, -1}},
            {{0, 0, 0}, {0, 0, -1}, {0, 1, 0}},
            {{0, 0, 0}, {-1, 0, 0}, {0, 1, 0}},
            {{0, 0, 0}, {0, 1, 0}, {0, 0, -1}},
            {{0, 0, 0}, {0, 1, 0}, {-1, 0, 0}},
            {{0, 0, 0}, {-1, 0, 0}, {0, 0, 1}}
        };

        BufferedImage[] embossResults = new BufferedImage[embossKernels.length];
        // applying each emboss filter to the input image
        for (int i = 0; i < embossKernels.length; i++) {
            embossResults[i] = applyConvolution(image, embossKernels[i]);
        }
        return embossResults;
    }

}
s