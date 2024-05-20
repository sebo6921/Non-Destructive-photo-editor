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

    private static JFrame frame;
    protected ArrayList<Action> actions;
    private DrawingArea drawingArea;

    private ImagePanel imagePanel;

    public DrawingActions(ResourceBundle bundle, DrawingArea drawingArea, ImagePanel imagePanel) {
        this.drawingArea = drawingArea;
        this.bundle = bundle;
        this.imagePanel = imagePanel;
        actions = new ArrayList<Action>();
        actions.add(new DrawingShapeActions(bundle.getString("DrawingToolbar"), null, bundle.getString("DrawingDesc"),
                Integer.valueOf(KeyEvent.VK_D)));

    }

    public JMenu createMenu() {
        JMenu drawingMenu = new JMenu(bundle.getString("DrawingMenu"));

        for (Action action : actions) {
            drawingMenu.add(new JMenuItem(action));
        }

        return drawingMenu;
    }

    public static void setFrame(JFrame frame) {
        DrawingActions.frame = frame;
    }

    public class DrawingShapeActions extends ImageAction {
        DrawingShapeActions(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_1, KeyEvent.CTRL_DOWN_MASK));
        }

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
