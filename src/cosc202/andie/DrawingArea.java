package cosc202.andie;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class DrawingArea implements ImageOperation {
    private List<Shape> shapes;
    private Shape currentShape;
    private Paint paint;

    public DrawingArea(Paint paint) {
        this.paint = paint;
        shapes = new ArrayList<>();
    }

    public void startDrawing(int x, int y) {
        currentShape = new Rectangle(x, y, 0, 0); // Example: Start with a rectangle
    }

    public void draw(int x, int y) {
        if (currentShape instanceof Rectangle) {
            Rectangle rect = (Rectangle) currentShape;
            rect.setSize(x - rect.x, y - rect.y);
        }
        // Add similar logic for other shapes
    }

    public void endDrawing(int x, int y) {
        if (currentShape != null) {
            shapes.add(currentShape);
            currentShape = null;
        }
    }

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setPaint(paint);
        for (Shape shape : shapes) {
            g2.draw(shape);
        }
        if (currentShape != null) {
            g2.draw(currentShape);
        }
    }

    @Override
    public BufferedImage apply(BufferedImage input) {
        Graphics2D g2 = input.createGraphics();
        g2.setPaint(paint);
        for (Shape shape : shapes) {
            g2.draw(shape);
        }
        if (currentShape != null) {
            g2.draw(currentShape);
        }
        g2.dispose();
        return input;
    }
}
