package cosc202.andie;

import java.awt.image.*;

public class BrightnessAndContrast implements ImageOperation, java.io.Serializable{

    private double brightness;
    private double contrast;


    
    /**
     * Constructor to create a BrightnessAndContrast object with specified brightness and contrast.
     * @param brightness The brightness adjustment value (percentage).
     * @param contrast The contrast adjustment value (percentage).
     */
    public BrightnessAndContrast(int brightness, int contrast){
        this.brightness = brightness;
        this.contrast = contrast;
    }

    
    /**
     * Applies the brightness and contrast adjustments to the input image.
     * @param input The input BufferedImage to which the adjustments are applied.
     * @return The adjusted BufferedImage.
     */
    public BufferedImage apply(BufferedImage input){
        BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null), input.isAlphaPremultiplied(), null);

        for(int y = 0; y < input.getHeight(); y++){
            for(int x = 0; x < input.getWidth(); x++){
                
                // Set the adjusted pixel value in the output image
                output.setRGB(x, y, getARGB(x, y, input));
            }
        }

        return output;

    } 


    /**
     * Calculates the adjusted ARGB value of a pixel based on brightness and contrast.
     * @param x The x-coordinate of the pixel.
     * @param y The y-coordinate of the pixel.
     * @param input The input BufferedImage.
     * @return The adjusted ARGB value of the pixel.
     */
    private int getARGB(int x, int y, BufferedImage input){
        int argb = input.getRGB(x, y);

        // Extract the alpha, red, green, and blue components from the ARGB value
        int a = (argb & 0xFF000000) >> 24;

        int r = (argb & 0x00FF0000) >> 16;

        int g = (argb & 0x0000FF00) >> 8;

        int b = (argb & 0x000000FF);

        int red = calcColour(r);

        int green = calcColour(g);

        int blue = calcColour(b);

        // Truncate the values to ensure they are within the valid range [0, 255]
        red = truncate(red);
        
        green = truncate(green);
        
        blue = truncate(blue);


        argb = (a << 24) | (red << 16) | (green << 8) | blue;

        return argb;
    }

    
    /**
     * Calculates the adjusted color value based on brightness and contrast.
     * @param x The original color value.
     * @return The adjusted color value.
     */
    private int calcColour(int x){
        return (int) Math.round(((1 + (contrast / 100)) * (x - 127.5)) + (127.5 * (1 + brightness / 100)));
    }
    

    /**
     * Truncates the color value to ensure it falls within the valid range [0, 255].
     * @param x The color value to be truncated.
     * @return The truncated color value.
     */
    private int truncate(int x){
        if(x > 255) return 255;
        if(x < 0) return 0;
        return x;
    }

}
