package cosc202.andie;

import java.util.*;
import java.awt.event.*;
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

    private DrawingArea drawingArea;
    
    private ImagePanel imagePanel;

    public DrawingActions(ResourceBundle bundle, DrawingArea drawingArea, ImagePanel imagePanel) {
        this.drawingArea = drawingArea;
        this.bundle = bundle;
        this.imagePanel = imagePanel;
    }

    public JMenu createMenu() {
        JMenu drawingMenu = new JMenu(bundle.getString("DrawingMenu"));

        JMenuItem popUpMenu = new JMenuItem(bundle.getString("DrawingToolbar"));
        popUpMenu.addActionListener(new ActionListener() {
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
}
