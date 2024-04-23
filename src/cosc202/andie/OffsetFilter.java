package cosc202.andie;

import java.awt.image.*;

public class OffsetFilter {

    private int offset;

    public OffsetFilter(){
        this.offset = 127;
    }

    public OffsetFilter(int offset){
        this.offset = offset;
    }

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