package cosc202.andie;

import java.awt.image.*;

public class GaussianBlur implements ImageOperation, java.io.Serializable {

    private int radius;

    GaussianBlur(int radius){
        this.radius = radius;
    }

    GaussianBlur(){
        this(1);
    }

    public BufferedImage apply (BufferedImage input){
        float[] array = creatingGaussianKernel(radius);
        
        Kernel kernel = new Kernel(2*radius+1, 2*radius+1, array);
        ConvolveOp convOp = new ConvolveOp(kernel);
        BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null), input.isAlphaPremultiplied(), null);
        convOp.filter(input, output);

        return output;
    }

    private float[] creatingGaussianKernel(int radius){
        int size = 2 * radius + 1;
        float[] array = new float[size * size];
        double sigma = radius / 3;
        double frontHalf = 1 / (2 * Math.PI * sigma * sigma);
        double total = 0;

        for (int x = -radius; x <= radius; x++){
            for (int y = -radius; y <= radius; y++){
                int index = (x + radius) * size + (y + radius);
                double exp = -(x * x + y * y)/(2 * sigma * sigma);
                array[index] = (float) frontHalf * (float) Math.exp(exp);
                total += array[index];
            }
        }

        for (int i = 0; i < array.length; i++){
            array[i] /= total;
        }

        return array;
    }

}