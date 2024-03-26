package cosc202.andie;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.*;

public class Rotate implements ImageOperation, java.io.Serializable{

    private int angle;
    
    public Rotate(){
        this.angle = 1;
    }

    public Rotate(int angle){
        this.angle = angle;
    }
    

    public BufferedImage apply(BufferedImage input){
        int newWidth = input.getWidth();
        int newHeight = input.getHeight();
        
        if(angle == 90){
            return clockwise(input);
        }
        
        if(angle == -90){
            return antiClockwise(input);
        }

       
        BufferedImage output = new BufferedImage(newWidth, newHeight, input.getType());

        Graphics2D g = output.createGraphics();
        AffineTransform transform = new AffineTransform();

        transform.rotate(Math.toRadians(angle), newWidth / 2, newHeight / 2);
        g.drawImage(input, transform, null);
        g.dispose();

        return output;
    }

    public BufferedImage clockwise(BufferedImage input){
        int newWidth = input.getWidth();
        int newHeight = input.getHeight();
        
        
        
        BufferedImage transposed = new BufferedImage(newHeight, newWidth, input.getType());
        for(int y = 0; y < newWidth; y++){
            for(int x = 0; x < newHeight; x++){
                transposed.setRGB(x, y, input.getRGB(y, x));
         }
        }


        BufferedImage flipped = new BufferedImage(newHeight, newWidth, input.getType());
        for(int y = 0; y < newWidth; y++){
            for(int x = 0; x < newHeight; x++){
                flipped.setRGB(x, y, transposed.getRGB(newHeight - 1 - x, y));
            }
        }



        return flipped;
    }


    public BufferedImage antiClockwise(BufferedImage input){
        int newWidth = input.getWidth();
        int newHeight = input.getHeight();


        BufferedImage flipped = new BufferedImage(newWidth, newHeight, input.getType());
        for(int y = 0; y < newHeight; y++){
            for(int x = 0; x < newWidth; x++){
                flipped.setRGB(x, y, input.getRGB(newWidth - 1 - x, y));
            }
        }
        
        BufferedImage transposed = new BufferedImage(newHeight, newWidth, input.getType());
        for(int y = 0; y < newWidth; y++){
            for(int x = 0; x < newHeight; x++){
                transposed.setRGB(x, y, flipped.getRGB(y, x));
            }
        }    
    



        return transposed;
    }

} 
    
