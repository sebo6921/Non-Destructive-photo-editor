package cosc202.andie;

import java.awt.image.BufferedImage;
import java.awt.Color;

public class SobelFilters {

    private static final double[][] SOBEL_HORIZONTAL_KERNEL = {
            { -0.5, 0, 0.5 },
            { -1, 0, 1 },
            { -0.5, 0, 0.5 }
    };

    private static final double[][] SOBEL_VERTICAL_KERNEL = {
            { -0.5, -1, -0.5 },
            { 0, 0, 0 },
            { 0.5, 1, 0.5 }
    };

    public static double[][] getSobelHorizontalKernel() {
        return SOBEL_HORIZONTAL_KERNEL;
    }

    public static double[][] getSobelVerticalKernel() {
        return SOBEL_VERTICAL_KERNEL;
    }

    /**
     * Apply Sobel filters to the given image.
     *
     * @param image The image to which Sobel filters will be applied.
     * @return The resulting image after applying the Sobel filters.
     */
    public BufferedImage applySobel(BufferedImage image) {
        // Apply horizontal Sobel filter
        BufferedImage horizontalFiltered = applyKernel(image, SOBEL_HORIZONTAL_KERNEL);

        // Apply vertical Sobel filter
        BufferedImage verticalFiltered = applyKernel(image, SOBEL_VERTICAL_KERNEL);

        // Combine the results
        return combineImages(horizontalFiltered, verticalFiltered);
    }

    private BufferedImage applyKernel(BufferedImage image, double[][] kernel) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage output = new BufferedImage(width, height, image.getType());

        for (int y = 1; y < height - 1; y++) {
            for (int x = 1; x < width - 1; x++) {
                double sumR = 0, sumG = 0, sumB = 0;

                for (int ky = -1; ky <= 1; ky++) {
                    for (int kx = -1; kx <= 1; kx++) {
                        int pixel = image.getRGB(x + kx, y + ky);
                        Color color = new Color(pixel);
                        double weight = kernel[ky + 1][kx + 1];

                        sumR += weight * color.getRed();
                        sumG += weight * color.getGreen();
                        sumB += weight * color.getBlue();
                    }
                }

                int r = Math.min(Math.max((int) sumR, 0), 255);
                int g = Math.min(Math.max((int) sumG, 0), 255);
                int b = Math.min(Math.max((int) sumB, 0), 255);

                Color newColor = new Color(r, g, b);
                output.setRGB(x, y, newColor.getRGB());
            }
        }

        return output;
    }

    private BufferedImage combineImages(BufferedImage img1, BufferedImage img2) {
        int width = img1.getWidth();
        int height = img1.getHeight();
        BufferedImage output = new BufferedImage(width, height, img1.getType());

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color color1 = new Color(img1.getRGB(x, y));
                Color color2 = new Color(img2.getRGB(x, y));

                int r = Math.min(color1.getRed() + color2.getRed(), 255);
                int g = Math.min(color1.getGreen() + color2.getGreen(), 255);
                int b = Math.min(color1.getBlue() + color2.getBlue(), 255);

                Color newColor = new Color(r, g, b);
                output.setRGB(x, y, newColor.getRGB());
            }
        }

        return output;
    }
}
