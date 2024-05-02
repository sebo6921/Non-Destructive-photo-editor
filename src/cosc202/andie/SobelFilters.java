// package cosc202.andie;

// import java.awt.image.BufferedImage;

// public class SobelFilters {
//     private static final double[][] SOBEL_HORIZONTAL_KERNEL = {
//             { -0.5, 0, 0.5 },
//             { -1, 0, 1 },
//             { -0.5, 0, 0.5 }
//     };

//     private static final double[][] SOBEL_VERTICAL_KERNEL = {
//             { -0.5, -1, -0.5 },
//             { 0, 0, 0 },
//             { 0.5, 1, 0.5 }
//     };

//     public static double[][] getSobelHorizontalKernel() {
//         return SOBEL_HORIZONTAL_KERNEL;
//     }

//     public static double[][] getSobelVerticalKernel() {
//         return SOBEL_VERTICAL_KERNEL;
//     }

//     /**
//      * Apply Sobel filters to the given image.
//      *
//      * @param image The image to which Sobel filters will be applied.
//      */
//     public void applySobel(BufferedImage image) {
//         // Apply horizontal Sobel filter
//         BufferedImage horizontalFiltered = applyKernel(image, SOBEL_HORIZONTAL_KERNEL);

//         // Apply vertical Sobel filter
//         BufferedImage verticalFiltered = applyKernel(image, SOBEL_VERTICAL_KERNEL);

//         // Combine the results
//         BufferedImage result = combineImages(horizontalFiltered, verticalFiltered);

//         // Update the original image with the result
//         image.getGraphics().drawImage(result, 0, 0, null);

        
//     }

//     /**
//      * Apply a given kernel to the image.
//      *
//      * @param image  The image to which the kernel will be applied.
//      * @param kernel The kernel matrix to be applied to the image.
//      * @return The filtered image.
//      */
//     private BufferedImage applyKernel(BufferedImage image, double[][] kernel) {
//         int width = image.getWidth();
//         int height = image.getHeight();
//         BufferedImage filteredImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

//         for (int y = 1; y < height - 1; y++) {
//             for (int x = 1; x < width - 1; x++) {
//                 int pixel = applyKernelToPoint(image, x, y, kernel);
//                 filteredImage.setRGB(x, y, pixel);
//             }
//         }

//         return filteredImage;
//     }

//     /**
//      * Apply the given kernel to a single point in the image.
//      *
//      * @param image  The image.
//      * @param x      The x-coordinate of the point.
//      * @param y      The y-coordinate of the point.
//      * @param kernel The kernel matrix.
//      * @return The filtered pixel value.
//      */
//     private int applyKernelToPoint(BufferedImage image, int x, int y, double[][] kernel) {
//         int sumX = 0;
//         int sumY = 0;

//         // Apply the kernel
//         for (int j = -1; j <= 1; j++) {
//             for (int i = -1; i <= 1; i++) {
//                 int pixel = image.getRGB(x + i, y + j);
//                 int grayValue = getGrayValue(pixel);
//                 sumX += grayValue * kernel[j + 1][i + 1];
//                 sumY += grayValue * kernel[i + 1][j + 1];
//             }
//         }

//         // Combine the X and Y gradients
//         int gradient = Math.min(255, Math.max(0, (int) Math.sqrt(sumX * sumX + sumY * sumY)));

//         // Create the new pixel value
//         return (gradient << 16) | (gradient << 8) | gradient;
//     }

//     /**
//      * Combine the results of horizontal and vertical Sobel filters.
//      *
//      * @param horizontalFiltered Image filtered using horizontal Sobel filter.
//      * @param verticalFiltered   Image filtered using vertical Sobel filter.
//      * @return Combined image.
//      */
//     private BufferedImage combineImages(BufferedImage horizontalFiltered, BufferedImage verticalFiltered) {
//         int width = horizontalFiltered.getWidth();
//         int height = horizontalFiltered.getHeight();
//         BufferedImage combinedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

//         for (int y = 0; y < height; y++) {
//             for (int x = 0; x < width; x++) {
//                 int horizontalPixel = horizontalFiltered.getRGB(x, y);
//                 int verticalPixel = verticalFiltered.getRGB(x, y);
//                 int combinedPixel = combinePixels(horizontalPixel, verticalPixel);
//                 combinedImage.setRGB(x, y, combinedPixel);
//             }
//         }

//         return combinedImage;
//     }

//     /**
//      * Combine the results of horizontal and vertical Sobel filters to get the final pixel value.
//      *
//      * @param horizontalPixel Pixel value from horizontal Sobel filter.
//      * @param verticalPixel   Pixel value from vertical Sobel filter.
//      * @return Combined pixel value.
//      */
//     private int combinePixels(int horizontalPixel, int verticalPixel) {
//         int horizontalGray = getGrayValue(horizontalPixel);
//         int verticalGray = getGrayValue(verticalPixel);
//         int combinedGray = (int) Math.sqrt(horizontalGray * horizontalGray + verticalGray * verticalGray);
//         return (combinedGray << 16) | (combinedGray << 8) | combinedGray;
//     }

//     /**
//      * Get the gray value of a pixel.
//      *
//      * @param pixel The pixel value.
//      * @return The gray value.
//      */
//     private int getGrayValue(int pixel) {
//         int red = (pixel >> 16) & 0xFF;
//         int green = (pixel >> 8) & 0xFF;
//         int blue = pixel & 0xFF;
//         return (red + green + blue) / 3;
//     }
// }

