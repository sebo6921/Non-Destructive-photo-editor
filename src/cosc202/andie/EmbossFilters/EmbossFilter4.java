package cosc202.andie.EmbossFilters;

import java.awt.image.BufferedImage;
import java.awt.Point;
import java.awt.image.*;
import cosc202.andie.AndieConvolveOp;
import cosc202.andie.ImageOperation;

public class EmbossFilter4 implements ImageOperation, java.io.Serializable {
    private int x1, x2, y1, y2 = -1;

    EmbossFilter4() {
    }

    EmbossFilter4(Point p1, Point p2) {
        this.x1 = (int) p1.getX();
        this.x2 = (int) p2.getX();
        this.y1 = (int) p1.getY();
        this.y2 = (int) p2.getY();
    }

    public BufferedImage apply(BufferedImage input) {
        BufferedImage output = null;
        try {
            float[] array = { 0, 0, 1, 0, 0, 0, -1, 0, 0 };
            Kernel kernel = new Kernel(3, 3, array);
            AndieConvolveOp convOp = new AndieConvolveOp(kernel);
            output = new BufferedImage(input.getColorModel(), input.copyData(null), input.isAlphaPremultiplied(), null);
            if (x1 != -1 && x2 != -1 && y1 != -1 && y2 != -1)
                convOp.filter(input, output, x1, y1, x2, y2);
            else
                convOp.filter(input, output);
        } catch (java.awt.image.RasterFormatException ex) {
            ex.printStackTrace();
        }
        return output;
    }
}

