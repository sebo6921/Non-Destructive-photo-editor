package cosc202.andie;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.*;

import javax.swing.JOptionPane;

public class CropImage implements ImageOperation, java.io.Serializable{

    private int x1, y1, x2, y2;
    
    CropImage(Point p1, Point p2){
        this.x1 = (int) p1.getX();
        this.y1 = (int) p1.getY();
        this.x2 = (int) p2.getX();
        this.y2 = (int) p2.getY();
    }

    public BufferedImage apply(BufferedImage input) {
        BufferedImage newImg = null;
        BufferedImage img = input;

        try {
            int width = x2 - x1;
            int height = y2 - y1;

            if(width < 0 || height < 0){
                throw new IllegalArgumentException("Invalid crop area");
            }

            img = input.getSubimage(x1, y1, width, height);

            newImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            
            Graphics g = newImg.getGraphics();
            g.drawImage(img, 0, 0, null);

        } catch(NullPointerException e){
            JOptionPane.showMessageDialog(null, "No image loaded", "Error", JOptionPane.ERROR_MESSAGE);        
        }

        return newImg;
    }
}