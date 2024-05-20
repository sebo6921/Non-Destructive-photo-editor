package cosc202.andie;

import java.util.*;
import java.util.List;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.*;
import javax.swing.*;

import cosc202.andie.ImagePanel.Mode;

/**
 * <p>
 * Actions provided by the Drawing menu.
 * </p>
 *
 * <p>
 * The user will expect to find a button that allows them to start
 * drawing on an image.
 * </p>
 *
 * @author Jessica Fan
 * @version 1.0
 */
public class DrawingActions {

    /** A resource bundle to change the language */
    ResourceBundle bundle;

    /** Frame the drawing actions and applied to */
    private static JFrame frame;

    /** A list of actions for the Drawing menu. */
    protected ArrayList<Action> actions;

    /** Class with methods to display the drawing actions */
    private DrawingArea drawingArea;

    /** ImagePanel the drawing actions are applied to */
    private ImagePanel imagePanel;

    /**
     * <p>
     * Create a set of Drawing menu actions.
     * </p>
     */
    public DrawingActions(ResourceBundle bundle, DrawingArea drawingArea, ImagePanel imagePanel) {
        this.drawingArea = drawingArea;
        this.bundle = bundle;
        this.imagePanel = imagePanel;
        actions = new ArrayList<Action>();
        actions.add(new DrawingShapeActions(bundle.getString("DrawingToolbar"), null, bundle.getString("DrawingDesc"),
                Integer.valueOf(KeyEvent.VK_D)));

    }

    /**
     * <p>
     * Create a menu contianing the list of Drawing actions.
     * </p>
     * 
     * @return The drawing menu UI element.
     */
    public JMenu createMenu() {
        JMenu drawingMenu = new JMenu(bundle.getString("DrawingMenu"));

        for (Action action : actions) {
            drawingMenu.add(new JMenuItem(action));
        }

        return drawingMenu;
    }

    /**
     * Set frame the drawing actions are applied to
     * 
     * @param frame Frame the actions are applied to
     */
    public static void setFrame(JFrame frame) {
        DrawingActions.frame = frame;
    }

    /**
     * <p>
     * Action to add a toolbar to allow the use to start drawing
     * </p>
     * 
     * @see Toolbar
     */
    public class DrawingShapeActions extends ImageAction {

        /**
         * <p>
         * Create a new drawing shape action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        DrawingShapeActions(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_1, KeyEvent.CTRL_DOWN_MASK));
        }

        /**
         * <p>
         * Callback for when the drawing shape is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the drawing shape action is triggered.
         * It adds a toolbar to the frame that allows the user to draw onto the image.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            imagePanel.setMode(Mode.DRAWING);
            JToolBar drawingToolbar = new JToolBar();
            Toolbar.createDrawingToolbar(drawingToolbar, bundle, drawingArea);
            JButton exitButton = new JButton(bundle.getString("DrawingExit"));
            exitButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    drawingArea.stopDrawing();
                    frame.getContentPane().remove(drawingToolbar);
                    frame.revalidate();
                    frame.repaint();
                    imagePanel.setMode(Mode.SELECTION);
                }
            });
            drawingToolbar.add(exitButton);
            frame.getContentPane().add(drawingToolbar, BorderLayout.SOUTH);
            frame.repaint();
            frame.revalidate();
        }
    }
}
