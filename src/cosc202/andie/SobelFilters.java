package cosc202.andie;

import java.awt.image.BufferedImage;
import java.awt.Color;

public class SobelFilters {

    // apply convolution to a grayscale image with a given kernel
    public static BufferedImage applyConvolution(BufferedImage image, double[][] kernel) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // iterate sover each pixel in the image
        for (int y = 1; y < height - 1; y++) {
            for (int x = 1; x < width - 1; x++) {
                double sumR = 0, sumG = 0, sumB = 0;

                // Apply the convolution kernel to the neighborhood of the pixel
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

                // Set the new color for the pixel in the result image
                Color newColor = new Color((int) sumR, (int) sumG, (int) sumB);
                result.setRGB(x, y, newColor.getRGB());
            }
        }

        return result;
    }

    // Apply horizontal Sobel filter
    public static BufferedImage applySobelHorizontal(BufferedImage image) {
        // Sobel horizontal kernel
        double[][] sobelHorizontalKernel = {
            {-0.5, 0, 0.5},
            {-1, 0, 1},
            {-0.5, 0, 0.5}
        };
        // Apply convolution with the Sobel horizontal kernel
        return applyConvolution(image, sobelHorizontalKernel);
    }

    // Apply vertical Sobel filter
    public static BufferedImage applySobelVertical(BufferedImage image) {
        // Sobel vertical kernel
        double[][] sobelVerticalKernel = {
            {-0.5, -1, -0.5},
            {0, 0, 0},
            {0.5, 1, 0.5}
        };
        // Apply convolution with the Sobel vertical kernel
        return applyConvolution(image, sobelVerticalKernel);
    }
}

