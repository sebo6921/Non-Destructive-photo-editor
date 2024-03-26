package cosc202.andie;

import java.awt.image.*;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

/**
 * <p>
 * ImageOperation to resize images.
 * </p>
 * 
 * <p>
 * This takes the user chosen percentage and applies 
 * it to the current image to resize it
 * </p>
 * 
 */
public class Resize implements ImageOperation, java.io.Serializable{

    private int size;

    Resize(int size){
        this.size = size;
    }

    Resize(){
        this(100);
    }



    /**
     * <p>
     * Resize image.
     * </p>
     * 
     * <p>
     * Resizes image to a specified percentage of its original size while maintaining better quality using smooth scaling
     * </p>
     * 
     * @param input The image to resize.
     * @return The resulting (resized) image.
     */
    public BufferedImage apply(BufferedImage input){
        float newWidth = (int) input.getWidth() * (size / 100.0f);
        float newHeight = (int) input.getHeight() * (size / 100.0f);

        BufferedImage output = new BufferedImage((int)newWidth, (int)newHeight, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g = output.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(input.getScaledInstance((int)newWidth, (int)newHeight, Image.SCALE_SMOOTH), 0, 0, null);
        g.dispose();
        
        return output;

       }
    
}
