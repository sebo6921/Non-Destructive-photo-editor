package cosc202.andie.DrawingShapes;

import java.util.*;
import java.util.List;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.*;
import javax.swing.*;

import cosc202.andie.ImageOperation;

public class RectangleOperation implements ImageOperation, java.io.Serializable {
    private int x1, y1, x2, y2;
    private Color color;
    private boolean fill;
    private int strokeWidth;

    public RectangleOperation(int x1, int y1, int x2, int y2, Color color, boolean fill, int strokeWidth) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = color;
        this.fill = fill;
        this.strokeWidth = strokeWidth;
    }

    @Override
    public BufferedImage apply(BufferedImage input) {
        BufferedImage output = new BufferedImage(input.getWidth(), input.getHeight(), input.getType());
        Graphics2D g2d = output.createGraphics();
        g2d.setStroke(new BasicStroke(strokeWidth));

        int width = Math.abs(x2 - x1); 
        int height = Math.abs(y2 - y1); 

        g2d.drawImage(input, 0, 0, null);
        g2d.setColor(color);
        if (fill) {
            g2d.fillRect(x1, y1, width, height);
        } else {
            g2d.drawRect(x1, y1, width, height);
        }
        g2d.dispose();
        return output;
    }
}