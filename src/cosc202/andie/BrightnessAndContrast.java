package cosc202.andie;

import java.awt.image.*;

public class BrightnessAndContrast implements ImageOperation, java.io.Serializable{

    private double brightness;
    private double contrast;

    public BrightnessAndContrast(int brightness, int contrast){
        this.brightness = brightness;
        this.contrast = contrast;
    }

    public BufferedImage apply(BufferedImage input){
        BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null), input.isAlphaPremultiplied(), null);

        for(int y = 0; y < input.getHeight(); y++){
            for(int x = 0; x < input.getWidth(); x++){
                output.setRGB(x, y, getARGB(x, y, input));
            }
        }

        return output;

    } 


    private int getARGB(int x, int y, BufferedImage input){
        int argb = input.getRGB(x, y);

        int a = (argb & 0xFF000000) >> 24;

        int r = (argb & 0x00FF0000) >> 16;

        int g = (argb & 0x0000FF00) >> 8;

        int b = (argb & 0x000000FF);

        int red = calcColour(r);

        int green = calcColour(g);

        int blue = calcColour(b);

        red = truncate(red);
        
        green = truncate(green);
        
        blue = truncate(blue);


        argb = (a << 24) | (red << 16) | (green << 8) | blue;

        return argb;
    }

    private int calcColour(int x){
        return (int) Math.round(((1 + (contrast / 100)) * (x - 127.5)) + (127.5 * (1 + brightness / 100)));
    }

    private int truncate(int x){
        if(x > 255) return 255;
        if(x < 0) return 0;
        return x;
    }

}
