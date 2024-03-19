package cosc202.andie;
import java.awt.image.*;
import java.util.*;

public class TheSharpen implements ImageOperation,java.io.Serializable {

    TheSharpen(){

    }

    public BufferedImage apply (BufferedImage input){
        float[] array = { 0, -1/2.0f, 0,
                     -1/2.0f, 3, -1/2.0f,
                          0, -1/2.0f, 0 };
        Kernel kernel = new Kernel(3, 3, array);

        ConvolveOp convOp = new ConvolveOp(kernel);

        BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null), input.isAlphaPremultiplied(), null);
        convOp.filter(input, output);
        
        return output;
    }
}
