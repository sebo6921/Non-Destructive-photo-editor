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

        BufferedImage output = new BufferedImage(newWidth, newHeight, input.getType());

        Graphics2D g = output.createGraphics();
        AffineTransform transform = new AffineTransform();
        transform.rotate(Math.toRadians(angle), newHeight / 2, newWidth / 2);
        g.drawImage(input, transform, null);
        g.dispose();

        return output;
    }
} 
    
