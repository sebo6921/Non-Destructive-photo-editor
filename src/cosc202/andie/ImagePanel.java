package cosc202.andie;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.swing.*;

import cosc202.andie.DrawingShapes.EllipseOperation;
import cosc202.andie.DrawingShapes.FreehandOperation;
import cosc202.andie.DrawingShapes.LineOperation;
import cosc202.andie.DrawingShapes.RectangleOperation;

/**
 * <p>
 * UI display element for {@link EditableImage}s.
 * </p>
 * 
 * <p>
 * This class extends {@link JPanel} to allow for rendering of an image, as well
 * as zooming
 * in and out.
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA
 * 4.0</a>
 * </p>
 * 
 * @author Steven Mills
 * @version 1.0
 */
public class ImagePanel extends JPanel implements MouseListener, MouseMotionListener {

    /** Mode to change the mouse actions based on the selected Mode */
    public enum Mode {
        SELECTION, DRAWING, CROPPING
    }

    private Mode mode = Mode.SELECTION;
    private int cropStartX, cropStartY, cropEndX, cropEndY;
    private int drawStartX, drawStartY, drawEndX, drawEndY;
    private Image backgroundImage;

    /**
     * The image to display in the ImagePanel.
     */
    private EditableImage image;

    /**
     * <p>
     * The zoom-level of the current view.
     * A scale of 1.0 represents actual size; 0.5 is zoomed out to half size; 1.5 is
     * zoomed in to one-and-a-half size; and so forth.
     * </p>
     * 
     * <p>
     * Note that the scale is internally represented as a multiplier, but externally
     * as a percentage.
     * </p>
     */
    private double scale;

    private DrawingArea drawingArea;

    /**
     * <p>
     * Create a new ImagePanel.
     * </p>
     * 
     * <p>
     * Newly created ImagePanels have a default zoom level of 100%
     * </p>
     */
    public ImagePanel(DrawingArea drawingArea) {
        this.drawingArea = drawingArea;
        image = new EditableImage();
        scale = 1.0;

        addMouseListener(this);
        addMouseMotionListener(this);
    }

    /**
     * <p>
     * Get the currently displayed image
     * </p>
     *
     * @return the image currently displayed.
     */
    public EditableImage getImage() {
        return image;
    }

    /**
     * <p>
     * Get the current zoom level as a percentage.
     * </p>
     * 
     * <p>
     * The percentage zoom is used for the external interface, where 100% is the
     * original size, 50% is half-size, etc.
     * </p>
     * 
     * @return The current zoom level as a percentage.
     */
    public double getZoom() {
        return 100 * scale;
    }

    /**
     * <p>
     * Set the current zoom level as a percentage.
     * </p>
     * 
     * <p>
     * The percentage zoom is used for the external interface, where 100% is the
     * original size, 50% is half-size, etc.
     * The zoom level is restricted to the range [50, 200].
     * </p>
     * 
     * @param zoomPercent The new zoom level as a percentage.
     */
    public void setZoom(double zoomPercent) {
        if (zoomPercent < 50) {
            zoomPercent = 50;
        }
        if (zoomPercent > 200) {
            zoomPercent = 200;
        }
        scale = zoomPercent / 100;
    }

    /**
     * <p>
     * Gets the preferred size of this component for UI layout.
     * </p>
     * 
     * <p>
     * The preferred size is the size of the image (scaled by zoom level), or a
     * default size if no image is present.
     * </p>
     * 
     * @return The preferred size of this component.
     */
    @Override
    public Dimension getPreferredSize() {
        if (image.hasImage()) {
            return new Dimension((int) Math.round(image.getCurrentImage().getWidth() * scale),
                    (int) Math.round(image.getCurrentImage().getHeight() * scale));
        } else {
            return new Dimension(450, 450);
        }
    }

    /**
     * <p>
     * Sets the background image
     * </p>
     * 
     * @param image The image to be set as background
     */
    public void setBackgroundImage(Image image) {
        this.backgroundImage = image;
        revalidate();
        repaint();
    }

    /**
     * <p>
     * Sets the background colour
     * </p>
     * 
     * @param image The colour to be set as background
     */
    public void setBackgroundColor(Color color) {
        int width = getWidth();
        int height = getHeight();
        BufferedImage colorImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = colorImage.createGraphics();
        g2.setColor(color);
        g2.fillRect(0, 0, width, height);
        g2.dispose();
        setBackgroundImage(colorImage);
    }

    /**
     * <p>
     * (Re)draw the component in the GUI.
     * </p>
     * 
     * @param g The Graphics component to draw the image on.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, this);
        }
        if (image.hasImage()) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.scale(scale, scale);
            g2.drawImage(image.getCurrentImage(), null, 0, 0);
            g2.dispose();
        }

        drawingArea.paint(g);

    }

    /**
     * <p>
     * Handles mouse press events.
     * Depending on the current mode, this method initiates different actions:
     * - In SELECTION mode, it starts drawing a selection rectangle.
     * - In DRAWING mode, it starts drawing a shape.
     * - In CROPPING mode, it starts drawing a cropping rectangle.
     * </p>
     * 
     * @param e the mouse event
     */
    @Override
    public void mousePressed(MouseEvent e) {
        if (mode == Mode.SELECTION) {
            drawingArea.setFillShape(true);
            drawingArea.setShape("Rectangle");
            drawingArea.setSelectedColour(new Color(69, 69, 69, 69));
            drawingArea.startDrawing(e.getX(), e.getY());
        } else if (mode == Mode.DRAWING) {
            drawingArea.startDrawing(e.getX(), e.getY());
            drawStartX = e.getX();
            drawStartY = e.getY();
        } else if (mode == Mode.CROPPING) {
            cropStartX = e.getX();
            cropStartY = e.getY();
            drawingArea.startDrawing(e.getX(), e.getY());
            drawingArea.setShape("Rectangle");
            drawingArea.setFillShape(true);
            drawingArea.setSelectedColour(new Color(255, 255, 255, 200));
        }
        setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));

    }

    /**
     * <p>
     * Handles mouse release events.
     * Depending on the current mode, this method completes different actions:
     * - In SELECTION mode, it completes the selection rectangle.
     * - In DRAWING mode, it completes the shape drawing and applies the
     * corresponding operation.
     * - In CROPPING mode, it completes the cropping rectangle and applies the crop
     * operation.
     * </p>
     * 
     * @param e the mouse event
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        if (mode == Mode.SELECTION) {
            drawingArea.setFillShape(false);
            drawingArea.setShape(null);
            drawingArea.setSelectedColour(Color.BLACK);
            drawingArea.completeDrawing(e.getX(), e.getY());
        } else if (mode == Mode.DRAWING) {
            drawEndX = e.getX();
            drawEndY = e.getY();
            drawingArea.completeDrawing(e.getX(), e.getY());
            if (drawingArea.currentShape == "Rectangle") {
                getImage().apply(new RectangleOperation(drawStartX, drawStartY, drawEndX, drawEndY,
                        drawingArea.selectedColour, drawingArea.fillShape, drawingArea.strokeWidth));
            } else if (drawingArea.currentShape == "Line") {
                getImage().apply(new LineOperation(drawStartX, drawStartY, drawEndX, drawEndY,
                        drawingArea.selectedColour, drawingArea.strokeWidth));
            } else if (drawingArea.currentShape == "Ellipse") {
                getImage().apply(new EllipseOperation(drawStartX, drawStartY, drawEndX, drawEndY,
                        drawingArea.selectedColour, drawingArea.fillShape, drawingArea.strokeWidth));
            } else if (drawingArea.currentShape == "Free") {
                getImage().apply(
                        new FreehandOperation(drawingArea.points, drawingArea.selectedColour, drawingArea.strokeWidth));
            }
        } else if (mode == Mode.CROPPING) {
            cropEndX = e.getX();
            cropEndY = e.getY();
            getImage().apply(new CropImage(cropStartX, cropStartY, cropEndX, cropEndY));
            drawingArea.completeDrawing(e.getX(), e.getY());
            drawingArea.setShape(null);
            drawingArea.setFillShape(false);
            drawingArea.setSelectedColour(Color.BLACK);
            mode = Mode.SELECTION;
        }
        setCursor(Cursor.getDefaultCursor());
        repaint();
        revalidate();
    }

    /**
     * <p>
     * Handles mouse drag events.
     * Draws on the drawing area based on the current mouse position.
     * </p>
     * 
     * @param e the mouse event
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        drawingArea.draw(e.getX(), e.getY());
        repaint();
    }

    /**
     * <p>
     * Handles mouse click events.
     * This method is currently not used.
     * </p>
     * 
     * @param e the mouse event
     */
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    /**
     * <p>
     * Handles mouse enter events.
     * This method is currently not used.
     * </p>
     * 
     * @param e the mouse event
     */
    @Override
    public void mouseEntered(MouseEvent e) {
    }

    /**
     * <p>
     * Handles mouse exit events.
     * This method is currently not used.
     * </p>
     * 
     * @param e the mouse event
     */
    @Override
    public void mouseExited(MouseEvent e) {
    }

    /**
     * <p>
     * Handles mouse move events.
     * This method is currently not used.
     * </p>
     * 
     * @param e the mouse event
     */
    @Override
    public void mouseMoved(MouseEvent e) {
    }

    /**
     * <p>
     * Sets the current mode of operation.
     * This method updates the mode to the specified value.
     * </p>
     * 
     * @param mode the new mode of operation
     */
    public void setMode(Mode mode) {
        this.mode = mode;
    }
}