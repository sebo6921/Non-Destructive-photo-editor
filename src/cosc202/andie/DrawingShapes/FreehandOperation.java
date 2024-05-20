package cosc202.andie.DrawingShapes;

import java.util.*;
import java.util.List;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.*;
import javax.swing.*;

import cosc202.andie.ImageOperation;

public class FreehandOperation implements ImageOperation {
    private List<Point> points;
    private Color color;
    private int strokeWidth;

    public FreehandOperation(List<Point> points, Color color, int strokeWidth) {
        this.points = points;
        this.color = color;
        this.strokeWidth = strokeWidth;
    }

    @Override
    public BufferedImage apply(BufferedImage input) {
        BufferedImage output = new BufferedImage(input.getWidth(), input.getHeight(), input.getType());
        Graphics2D g2d = output.createGraphics();
        g2d.drawImage(input, 0, 0, null);
        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(strokeWidth));
        if (points.size() > 1) {
            Point prev = points.get(0);
            for (int i = 1; i < points.size(); i++) {
                Point current = points.get(i);
                g2d.drawLine(prev.x, prev.y, current.x, current.y);
                prev = current;
            }
        }
        g2d.dispose();
        return output;
    }
}