package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

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

    private static JFrame frame;

    public DrawingActions(ResourceBundle bundle) {
        this.bundle = bundle;
    }

    public JMenu createMenu() {
        JMenu drawingMenu = new JMenu(bundle.getString("DrawingMenu"));

        JMenuItem popUpMenu = new JMenuItem(bundle.getString("DrawingToolbar"));
        popUpMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JToolBar drawingToolbar = new JToolBar();
                Toolbar.createDrawingToolbar(drawingToolbar, bundle);
                JButton exitButton = new JButton(bundle.getString("DrawingExit"));
                exitButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        // Remove the drawing toolbar from the frame's content pane
                        frame.getContentPane().remove(drawingToolbar);

                        // Repaint the frame
                        frame.revalidate();
                        frame.repaint();
                    }
                });
                drawingToolbar.add(exitButton);
                frame.getContentPane().add(drawingToolbar, BorderLayout.SOUTH);
                frame.revalidate();
                frame.repaint();
            }
        });
        drawingMenu.add(popUpMenu);
        return drawingMenu;
    }

    public static void setFrame(JFrame frame) {
        DrawingActions.frame = frame;
    }

    public void setFillShape(boolean ifFill) {
        fillShape = ifFill;
    }

    public void setSelectedColour(Color color) {
        selectedColour = color;
    }

    public void setShape(String shape) {
        selectedShape = shape;
    }
}
