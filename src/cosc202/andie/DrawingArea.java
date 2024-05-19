package cosc202.andie;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class DrawingArea implements ImageOperation, java.io.Serializable {
    /** Current fill shape option (or default) */
    private boolean fillShape = false;

    /** Current selected colour (or default) */
    private Color selectedColour = Color.BLACK;

    /** Current selected shape (or default) */
    private String currentShape = null;

    private int startX, startY, endX, endY;
    private List<Point> points = new ArrayList<>();
    
    private boolean isDrawing = false;

    public DrawingArea() {}

    public void startDrawing(int x, int y) {
        startX = x;
        startY = y;
        endX = x;
        endY = y;
        isDrawing = true;
        if ("Free".equals(currentShape)) {
            points.clear();
            points.add(new Point(x, y));
        }
    }

    public void stopDrawing() {
        isDrawing = false;
        currentShape = null;
    }

    public void draw(int x, int y) {
        endX = x;
        endY = y;
        if ("Free".equals(currentShape) && isDrawing) {
            points.add(new Point(x, y));
        }
    }

    public void completeDrawing(int x, int y) {
        endX = x;
        endY = y;
        isDrawing = false;
        if ("Free".equals(currentShape)) {
            points.add(new Point(x, y));
        }
    }

    public void endDrawing(int x, int y) {
        if (currentShape != null) {
            currentShape = null;
        }
    }

    public void paint(Graphics g) {
        if (!isDrawing) {
            return;
        }

        Graphics2D g2d = (Graphics2D) g;
        shapes(g2d);
    }

    @Override
    public BufferedImage apply(BufferedImage input) {
        BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null), input.isAlphaPremultiplied(), null);
        
        Graphics2D g2d = output.createGraphics();
        shapes(g2d);

        g2d.dispose();
        return output;
    }

    public void shapes(Graphics g2d){
        g2d.setColor(selectedColour);

        int width = Math.abs(endX - startX);
        int height = Math.abs(endY - startY);
        int topLeftX = Math.min(startX, endX);
        int topLeftY = Math.min(startY, endY);

        switch (currentShape) {
            case "Rectangle":
                if (fillShape) {
                    g2d.fillRect(topLeftX, topLeftY, width, height);
                } else {
                    g2d.drawRect(topLeftX, topLeftY, width, height);
                }
                break;
            case "Ellipse":
                if (fillShape) {
                    g2d.fillOval(topLeftX, topLeftY, width, height);
                } else {
                    g2d.drawOval(topLeftX, topLeftY, width, height);
                }
                break;
            case "Line":
                g2d.drawLine(startX, startY, endX, endY);
                break;
            case "Free":
                if (points.size() > 1) {
                    Point prev = points.get(0);
                    for (int i = 1; i < points.size(); i++) {
                        Point current = points.get(i);
                        g2d.drawLine(prev.x, prev.y, current.x, current.y);
                        prev = current;
                    }
                }
                break;
        }
    }

    public void setFillShape(boolean ifFill) {
        fillShape = ifFill;
    }

    public void setSelectedColour(Color color) {
        selectedColour = color;
    }

    public void setShape(String shape) {
        currentShape = shape;
    }
}
