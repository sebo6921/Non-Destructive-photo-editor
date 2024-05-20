package cosc202.andie;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * ImageOperation to use mouseListener objects onto the image.
 * </p>
 * 
 * <p>
 * This takes the mouseListener object and uses the x and y coordinates
 * from the mouse to perform an action
 * </p>
 * 
 * @author Jessica Fan
 * @version 1.0
 */
public class DrawingArea implements ImageOperation, java.io.Serializable {
    /** Current fill shape option (or default) */
    protected boolean fillShape = false;

    /** Current selected colour (or default) */
    protected Color selectedColour = Color.BLACK;

    /** Current selected shape (or default) */
    protected String currentShape = null;

    /** Current selected stroke width (or default) */
    protected int strokeWidth = 1;

    /** Coordinates for when the mouse is pressed and when it is released */
    protected int startX, startY, endX, endY;

    /** List of points used in Freehand Drawing */
    protected List<Point> points = new ArrayList<>();

    /** If the mouse is selected or not */
    private boolean isDrawing = false;

    /**
     * <p>
     * Construct a new DrawingArea.
     * </p>
     */
    public DrawingArea() {
    }

    /**
     * <p>
     * Activated when the mouse is pressed
     * </p>
     * 
     * @param x x coordinate of the mouse location
     * @param y y coordinate of the mouse location
     */
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

    /**
     * <p>
     * Activated when the mouse is released
     * </p>
     * 
     * @param x x coordinate of the mouse location
     * @param y y coordinate of the mouse location
     */
    public void stopDrawing() {
        isDrawing = false;
        currentShape = null;
    }

    /**
     * <p>
     * Draws the current shape onto the image
     * </p>
     * 
     * @param x x coordinate of the mouse location
     * @param y y coordinate of the mouse location
     */
    public void draw(int x, int y) {
        endX = x;
        endY = y;
        if ("Free".equals(currentShape) && isDrawing) {
            points.add(new Point(x, y));
        }
    }

    /**
     * <p>
     * Activated when the mouse is released
     * </p>
     * 
     * @param x x coordinate of the mouse location
     * @param y y coordinate of the mouse location
     */
    public void completeDrawing(int x, int y) {
        endX = x;
        endY = y;
        isDrawing = false;
        if ("Free".equals(currentShape)) {
            points.add(new Point(x, y));
        }
    }

    /**
     * <p>
     * Activated when the mouse is released
     * </p>
     * 
     * @param x x coordinate of the mouse location
     * @param y y coordinate of the mouse location
     */
    public void endDrawing(int x, int y) {
        if (currentShape != null) {
            currentShape = null;
        }
    }

    /**
     * <p>
     * Paints the current object onto the frame
     * </p>
     * 
     * @param g graphics object
     */
    public void paint(Graphics g) {
        if (!isDrawing) {
            return;
        }

        Graphics2D g2d = (Graphics2D) g;
        shapes(g2d);
    }

    /**
     * <p>
     * Apply graphic to the given image
     * </p>
     * 
     * <p>
     * This takes the input coordinates from the mouse and paints
     * the current object to match how the mouse is pressed and released
     * </p>
     * 
     * @param input the image having the graphic applied to it
     * @return the image that has the graphic applied to it
     */
    @Override
    public BufferedImage apply(BufferedImage input) {
        BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null),
                input.isAlphaPremultiplied(), null);

        Graphics2D g2d = output.createGraphics();
        shapes(g2d);

        g2d.dispose();
        return output;
    }

    /**
     * <p>
     * Method that chooses the right shape/object to draw
     * </p>
     * 
     * @param g2d graphics object
     */
    public void shapes(Graphics2D g2d) {
        if (currentShape == null) {
            return;
        }

        g2d.setColor(selectedColour);
        g2d.setStroke(new BasicStroke(strokeWidth));

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

    /**
     * <p>
     * Sets whether the shape is filled or not
     * </p>
     * 
     * @param ifFill boolean indicating whether the shape should be filled or not
     */
    public void setFillShape(boolean ifFill) {
        fillShape = ifFill;
    }

    /**
     * <p>
     * Sets the colour of the shape/object
     * </p>
     * 
     * @param color Color object indicating the colour
     */
    public void setSelectedColour(Color color) {
        selectedColour = color;
    }

    /**
     * <p>
     * Sets the shape/object
     * </p>
     * 
     * @param shape the shape/object that will be used
     */
    public void setShape(String shape) {
        currentShape = shape;
    }

    /**
     * <p>
     * Sets the stroke width of the shape/object
     * </p>
     * 
     * @param stroke the size of the stroke width
     */
    public void setStrokeWidth(int strokeWidth) {
        this.strokeWidth = strokeWidth;
    }
}
