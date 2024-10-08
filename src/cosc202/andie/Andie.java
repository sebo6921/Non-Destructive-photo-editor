package cosc202.andie;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.imageio.*;
import java.util.*;
import java.util.prefs.Preferences;

/**
 * <p>
 * Main class for A Non-Destructive Image Editor (ANDIE).
 * </p>
 * 
 * <p>
 * This class is the entry point for the program.
 * It creates a Graphical User Interface (GUI) that provides access to various
 * image editing and processing operations.
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
public class Andie {

    /** A resource bundle to change the language */
    private static ResourceBundle bundle;

    /**
     * <p>
     * Launches the main GUI for the ANDIE program.
     * </p>
     * 
     * <p>
     * This method sets up an interface consisting of an active image (an
     * {@code ImagePanel})
     * and various menus which can be used to trigger operations to load, save,
     * edit, etc.
     * These operations are implemented {@link ImageOperation}s and triggerd via
     * {@code ImageAction}s grouped by their general purpose into menus.
     * </p>
     * 
     * @see ImagePanel
     * @see ImageAction
     * @see ImageOperation
     * @see FileActions
     * @see EditActions
     * @see ViewActions
     * @see FilterActions
     * @see ColourActions
     * @see HelpActions
     * 
     * @throws Exception if something goes wrong.
     */
    private static void createAndShowGUI() throws Exception {
        // Language Bundle
        bundle = ResourceBundle.getBundle("cosc202.andie.MessageBundle");

        // Set up the main GUI frame
        JFrame frame = new JFrame("ANDIE");
        DrawingArea drawingArea = new DrawingArea();
        ImagePanel imagePanel = new ImagePanel(drawingArea);
        JToolBar toolBar = Toolbar.createToolbar(bundle, drawingArea, imagePanel);
        toolBar.setBackground(Color.WHITE);

        Image image = ImageIO.read(Andie.class.getClassLoader().getResource("icon.png"));
        frame.setIconImage(image);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // The main content area is an ImagePanel
        ImageAction.setTarget(imagePanel);
        frame.add(imagePanel, BorderLayout.CENTER);

        // Add in menus for various types of action the user may perform.
        JMenuBar menuBar = new JMenuBar();

        // File menus are pretty standard, so things that usually go in File menus go
        // here.
        FileActions fileActions = new FileActions(bundle);
        menuBar.add(fileActions.createMenu());

        // Likewise Edit menus are very common, so should be clear what might go here.
        EditActions editActions = new EditActions(bundle);
        menuBar.add(editActions.createMenu());

        // View actions control how the image is displayed, but do not alter its actual
        // content
        ViewActions viewActions = new ViewActions(bundle, imagePanel);
        menuBar.add(viewActions.createMenu());

        // Filters apply a per-pixel operation to the image, generally based on a local
        // window
        FilterActions filterActions = new FilterActions(bundle);
        menuBar.add(filterActions.createMenu());

        // Actions that affect the representation of colour in the image
        ColourActions colourActions = new ColourActions(bundle);
        menuBar.add(colourActions.createMenu());

        // Transformations take care of things such as flipping, rtating and resizing
        // the image
        TransformationActions transformationActions = new TransformationActions(bundle);
        menuBar.add(transformationActions.createMenu());

        // Drawing Menu and Toolbar that can create Rectangles, Ellipses, Lines etc
        DrawingActions drawingActions = new DrawingActions(bundle, drawingArea, imagePanel);
        DrawingActions.setFrame(frame);
        Toolbar.setFrame(frame);
        menuBar.add(drawingActions.createMenu());

        menuBar.add(Box.createHorizontalGlue());

        // Actions that help with usability
        HelpActions helpActions = new HelpActions(bundle);
        JMenu helpMenu = helpActions.createMenu();
        JMenuItem colourButton = new JMenuItem(bundle.getString("ToolbarColour"));
        colourButton.setPreferredSize(new Dimension(50, 20)); // Set preferred size
        colourButton.setMargin(new Insets(5, 5, 5, 5));
        colourButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color selectedColour = JColorChooser.showDialog(frame, bundle.getString("ToolbarColour"),
                        imagePanel.getBackground());
                if (selectedColour != null) {
                    toolBar.setBackground(selectedColour);
                }
            }
        });
        helpMenu.add(colourButton);
        menuBar.add(helpMenu);

        frame.setJMenuBar(menuBar);
        frame.add(toolBar, BorderLayout.NORTH); // Adding the toolbar to the top of the frame
        frame.pack();
        frame.setSize(800, 700);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    /**
     * <p>
     * Main entry point to the ANDIE program.
     * </p>
     * 
     * <p>
     * Creates and launches the main GUI in a separate thread.
     * As a result, this is essentially a wrapper around {@code createAndShowGUI()}.
     * </p>
     * 
     * @param args Command line arguments, not currently used
     * @throws Exception If something goes awry
     * @see #createAndShowGUI()
     */
    public static void main(String[] args) throws Exception {

        Preferences prefs = Preferences.userNodeForPackage(Andie.class);

        Locale.setDefault(new Locale(prefs.get("language", "en"),
                prefs.get("country", "NZ")));

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    createAndShowGUI();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    System.out.println("Error opening ANDIE");
                    System.exit(1);
                }
            }
        });
    }
}
