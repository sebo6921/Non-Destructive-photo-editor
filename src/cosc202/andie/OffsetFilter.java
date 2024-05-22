package cosc202.andie;

import java.awt.image.*;

/**
 * <p>
 * Operation to offset the filter to apply positive and negative numbers
 * </p>
 * 
 * <p>
 * This takes a filter applied to it and applies an offset convolution so the filters
 * can offset to apply positive and negative numbers as a result of a convolution
 * </p>
 * 
 * @author Jessica Fan
 * @version 1.0
 */
public class OffsetFilter {

    private int offset;

    /**
     * <p>
     * Construct OffsetFilter with default offset
     * </p>
     */
    public OffsetFilter(){
        this.offset = 127;
    }

    /**
     * <p>
     * Construct OffsetFilter with a given offset value
     * </p>
     * 
     * @param offset The given {@code offset}.
     */
    public OffsetFilter(int offset){
        this.offset = offset;
    }
 /**
     * <p>
     * Apply a Offset to the filter.
     * </p>
     * 
     * <p>
     * The OffsetFilter is implemented to be able to apply both positive
     * and negative values to a filter 
     * </p>
     * 
     * @param image The image to apply the Median filter to.
     * @return The resulting image.
     */
    public BufferedImage apply(BufferedImage image){
        int width = image.getWidth();
        int height = image.getHeight();

        // Create a new buffered image to store the result
        BufferedImage resultImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
        
        WritableRaster inputRaster = image.getRaster();
        WritableRaster outputRaster = resultImage.getRaster();
        
        // Apply the offset and clip values to the range 0-255
        int[] pixels = new int[width];
        for (int y = 0; y < height; y++) {
            inputRaster.getPixels(0, y, width, 1, pixels);
            for (int x = 0; x < width; x++) {
                pixels[x] = Math.min(Math.max(pixels[x] + offset, 0), 255);
            }
            outputRaster.setPixels(0, y, width, 1, pixels);
        }
        
        return resultImage;
    }
}