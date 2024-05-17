package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.*;
import javax.swing.*;
import cosc202.andie.ImageOperation;

/**
 * <p>
 * Actions provided by the Help menu.
 * </p>
 *
 * <p>
 * The user will expect to find an ANDIE help guide and the ability to change
 * the language
 * of the ANDIE program.
 * </p>
 *
 * @author Jessica Fan
 * @version 1.0
 */
public class DrawingActions {

    /** A resource bundle to change the language */
    ResourceBundle bundle;

    /** Current fill shape option (or default) */
    private boolean fillShape = false;

    /** Current selected colour (or default) */
    private Color selectedColour = Color.BLACK;

    /** Current selected shape (or default) */
    private String selectedShape = "Rectangle";

    // Drawing operation points
    private int startX;
    private int startY;
    private int endX;
    private int endY;

    /**
     * <p>
     * Create a set of Help menu actions.
     * </p>
     */
    public DrawingActions(ResourceBundle bundle) {
        this.bundle = bundle;
    }

    public DrawingActions() {
    }

    /**
     * <p>
     * Create a menu containing the list of Help actions.
     * </p>
     *
     * @return The help menu UI element.
     */
    public JMenu createMenu() {
        JMenu drawingMenu = new JMenu(bundle.getString("DrawingMenu"));

        JMenuItem colourMenu = new JMenuItem(bundle.getString("ColourMenu"));
        colourMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JColorChooser.showDialog((Component) e.getSource(), "Select a Colour", Color.BLACK);
            }
        });

        JMenu shapeSubMenu = new JMenu(bundle.getString("ShapeMenu"));

        JCheckBox fillOrOutline = new JCheckBox(bundle.getString("FillOrOutline"), false);
        fillOrOutline.addActionListener(e -> fillShape = fillOrOutline.isSelected());
        fillOrOutline.setFont(new Font("Arial", Font.PLAIN, 14));

        ImagePanel imagePanel = new ImagePanel();

        JMenuItem rectangle = new JMenuItem(bundle.getString("Rectangle"));
        rectangle.addActionListener(e -> {
            selectedShape = "Rectangle";
            //imagePanel.setCurrentMode(Mode.DRAWING);
        });

        JMenuItem ellipse = new JMenuItem(bundle.getString("Ellipse"));
        ellipse.addActionListener(e -> {
            selectedShape = "Ellipse";
            //imagePanel.setCurrentMode(Mode.DRAWING);
        });
        JMenuItem line = new JMenuItem(bundle.getString("Line"));
        line.addActionListener(e -> {
            selectedShape = "Line";
            //imagePanel.setCurrentMode(Mode.DRAWING);
        });

        shapeSubMenu.add(rectangle);
        shapeSubMenu.add(ellipse);
        shapeSubMenu.add(line);
        shapeSubMenu.add(fillOrOutline);
        drawingMenu.add(colourMenu);
        drawingMenu.add(shapeSubMenu);

        return drawingMenu;
    }

    // public BufferedImage apply(BufferedImage input) {
    //     BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null), input.isAlphaPremultiplied(), null);
    //     Graphics2D g2d = output.createGraphics();
    //     g2d.drawImage(input, 0, 0, null);
    //     g2d.setStroke(new BasicStroke(1));
    //     g2d.setColor(selectedColour);

    //     if (fillShape) {
    //         g2d.setPaint(selectedColour);
    //     } else {
    //         g2d.setPaint(null);
    //     }

    //     switch (selectedShape) {
    //         case "Rectangle":
    //             int width = Math.abs(endX - startX);
    //             int height = Math.abs(endY - startY);
    //             int x = Math.min(startX, endX);
    //             int y = Math.min(startY, endY);
    //             g2d.drawRect(x, y, width, height);
    //             if (fillShape) {
    //                 g2d.fillRect(x, y, width, height);
    //             }
    //             break;
    //         case "Ellipse":
    //             width = Math.abs(endX - startX);
    //             height = Math.abs(endY - startY);
    //             x = Math.min(startX, endX);
    //             y = Math.min(startY, endY);
    //             g2d.drawOval(x, y, width, height);
    //             if (fillShape) {
    //                 g2d.fillOval(x, y, width, height);
    //             }
    //             break;
    //         case "Line":
    //             g2d.drawLine(getMinX(), getMinY(), getMaxX(), getMaxY());
    //             break;
    //     }

    //     return output;
    // }

    // public void setStartPoint(int x, int y) {
    //     startX = x;
    //     startY = y;
    // }

    // public void setEndPoint(int x, int y) {
    //     endX = x;
    //     endY = y;
    // }

}
