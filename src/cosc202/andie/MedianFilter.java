package cosc202.andie;

import java.awt.image.*;
import java.util.*;

public class MedianFilter implements ImageOperation, java.io.Serializable {
    /**
     * The size of filter to apply. A radius of 1 is a 3x3 filter, a radius of 2 a
     * 5x5 filter, and so forth.
     */
    private int radius;

    /**
     * constructor, with a default radius of 1
     */
    public MedianFilter() {
        this.radius = 1;
    }

    /**
     * constructor that sets the radius based on user input
     * 
     * @param radius - the int value that the user wants to set as the radius
     */
    public MedianFilter(int radius) {
        this.radius = radius;
    }
    /**
     * Apply the median filter to the input image.
     * 
     * @param input The input image to filter.
     * @return The filtered output image.
     */
    public BufferedImage apply(BufferedImage input) {
        int width = input.getWidth();
        int height = input.getHeight();
        BufferedImage output = new BufferedImage(width, height, input.getType());

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int argb = input.getRGB(x, y);
                // By shifting the bits appropriately, you can isolate each color channel so that 
                //you can manipulate or extract it independently.
                int a = (argb >> 24) & 0xFF; // Alpha channel, shifts to the right by 24 bits
                //int r = (argb >> 16) & 0xFF; // Red channel, shifts to the right by 16 bits
                //int g = (argb >> 8) & 0xFF; // Green channel, shifts tot the right by 8 bits
                //int b = argb & 0xFF; // Blue channel, no need to shift this one

                // applying the median filter to each color channel separately
                int redMedian = getMedian(input, x, y, 16);
                int greenMedian = getMedian(input, x, y, 8);
                int blueMedian = getMedian(input, x, y, 0);

                // adding together the color channels into a single ARGB value
                int addedArgb = (a << 24) | (redMedian << 16) | (greenMedian << 8) | blueMedian;

                
                output.setRGB(x, y, addedArgb);
            }
        }

        return output;
    }
     /**
     * Finds the median value for a given pixel location and color channel shift.
     *
     * @param input The input image.
     * @param x The x-coordinate of the pixel.
     * @param y The y-coordinate of the pixel.
     * @param shift The bit shift value for the color channel.
     * @return The median value for the specified color channel.
     */
    private int getMedian(BufferedImage input, int x, int y, int shift) {
        int[] filterSize = new int[(2 * radius + 1) * (2 * radius + 1)]; // To store pixel values in the neighborhood
        int index = 0;
        for (int j = y - radius; j <= y + radius; j++) {
            for (int i = x - radius; i <= x + radius; i++) {
                if (i >= 0 && i < input.getWidth() && j >= 0 && j < input.getHeight()) {
                    filterSize[index++] = (input.getRGB(i, j) >> shift) & 0xFF;
                }
            }
        }

        // Use Quickselect algorithm to find the median
        int medianIndex = filterSize.length / 2;
        int low = 0;
        int high = filterSize.length - 1;
        while (low < high) {
            int pivotIndex = partition(filterSize, low, high);
            if (pivotIndex < medianIndex)
                low = pivotIndex + 1;
            else if (pivotIndex > medianIndex)
                high = pivotIndex - 1;
            else
                break;
        }

        return filterSize[medianIndex];
    }

    /**
     * Partitions the array around a pivot element for Quickselect algorithm.
     *
     * @param arr The array to partition.
     * @param low The lower index of the array.
     * @param high The higher index of the array.
     * @return The index of the pivot element after partitioning.
     */
    private int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }
}