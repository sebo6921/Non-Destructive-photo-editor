package cosc202.andie;

import java.awt.Graphics;
import java.awt.image.*;

import javax.swing.JOptionPane;

public class CropImage implements ImageOperation, java.io.Serializable{

    private int x1, y1, x2, y2;

    CropImage(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public BufferedImage apply(BufferedImage input) {
        BufferedImage output = null;

        try {
            int width = Math.abs(x2 - x1); 
            int height = Math.abs(y2 - y1); 

            if(width < 0 || height < 0){
                throw new IllegalArgumentException("Invalid crop area");
            }

            output = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            
            Graphics g = output.getGraphics();
            g.drawImage(input.getSubimage(x1, y1, width, height), 0, 0, null);
            g.dispose();

        } catch(NullPointerException e){
            JOptionPane.showMessageDialog(null, "No image loaded", "Error", JOptionPane.ERROR_MESSAGE);        
        }

        return output;
    }
}