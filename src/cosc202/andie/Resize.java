package cosc202.andie;

import java.awt.image.*;

public class Resize implements ImageOperation, java.io.Serializable{

    private int size;

    Resize(int size){
        this.size = size;
    }

    Resize(){
        this(100);
    }

    public BufferedImage apply(BufferedImage input){
        
    }
    
}
