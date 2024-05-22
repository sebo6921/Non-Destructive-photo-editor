package cosc202.andie;

import java.awt.Color;
import java.awt.image.*;


/**
 * <p>
 * ImageOperation to apply a Block Averaging filter.
 * </p>
 * <p>
 * A block averaging filter pixelates an image by replacing each pixel by the average of the
 * pixels in a surrounding neighbourhood
 * 
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA
 * 4.0</a>
 * </p>
 * 
 * @see java.awt.image.ConvolveOp
 * @author Diego Olivera
 * @version 1.0
 */
public class BlockAveraging implements ImageOperation, java.io.Serializable{
    
    private int blockSize;

    
    /**
     * <p>
     * Construct a BlockAveraging filter with the given size.
     * </p>
     * 
     * </p>
     * 
     * @param blockSize The blockSize of the newly constructed BlockAveraging filter
     */
    public BlockAveraging(int blockSize){
        this.blockSize = blockSize;
    }


    public BufferedImage apply(BufferedImage input){

        BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null), input.isAlphaPremultiplied(), null);

        // Iterate over the input image in blocks of size blockSize
        for(int y = 0; y < input.getHeight(); y += blockSize){
            for(int x = 0; x < input.getWidth(); x += blockSize){
                
                
                int avgRed = 0, avgGreen = 0, avgBlue = 0;
                int pixelCount = 0;
                
                // Iterate over each pixel within the current block
                for(int blockY = y; blockY < y + blockSize && blockY < input.getHeight(); blockY++){
                    for(int blockX = x; blockX < x + blockSize && blockX < input.getWidth(); blockX++){
                        
                        // Get the color of the current pixel
                        Color pixelColor = new Color(input.getRGB(blockX, blockY));

                        // Accumulate color components for averaging
                        avgRed += pixelColor.getRed();
                        avgGreen += pixelColor.getGreen();
                        avgBlue += pixelColor.getBlue();
                        pixelCount++;
                    }
                }

                // Calculate the average color values for the block
                avgRed /= pixelCount;
                avgGreen /= pixelCount;
                avgBlue /= pixelCount;

                // Set the color of all pixels within the current block to the calculated average color
                for(int blockY = y; blockY < y + blockSize && blockY < input.getHeight(); blockY++){
                    for(int blockX = x; blockX < x + blockSize && blockX < input.getWidth(); blockX++){
                        output.setRGB(blockX, blockY, new Color(avgRed, avgGreen, avgBlue).getRGB());
                    }
                }
            }
        }
        return output;
    }
}
