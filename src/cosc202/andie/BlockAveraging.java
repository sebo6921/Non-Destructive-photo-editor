package cosc202.andie;

import java.awt.Color;
import java.awt.image.*;

public class BlockAveraging implements ImageOperation, java.io.Serializable{
    
    private int blockSize;

    
    public BlockAveraging(int blockSize){
        this.blockSize = blockSize;
    }


    public BufferedImage apply(BufferedImage input){

        BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null), input.isAlphaPremultiplied(), null);

        for(int y = 0; y < input.getHeight(); y += blockSize){
            for(int x = 0; x < input.getWidth(); x += blockSize){
                
                //calculate average colour for block
                int avgRed = 0, avgGreen = 0, avgBlue = 0;

                int pixelCount = 0;

                for(int blockY = y; blockY < y + blockSize && blockY < input.getHeight(); blockY++){
                    for(int blockX = x; blockX < x + blockSize && blockX < input.getWidth(); blockX++){
                        Color pixelColor = new Color(input.getRGB(blockX, blockY));
                        avgRed += pixelColor.getRed();
                        avgGreen += pixelColor.getGreen();
                        avgBlue += pixelColor.getBlue();
                        pixelCount++;
                    }
                }
                avgRed /= pixelCount;
                avgGreen /= pixelCount;
                avgBlue /= pixelCount;

                //replace pixels with average color

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
