package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

/**
 * <p>
 * Actions provided by the Filter menu.
 * </p>
 * 
 * <p>
 * The Filter menu contains actions that update each pixel in an image based on
 * some small local neighbourhood. 
 * This includes a mean filter (a simple blur) in the sample code, but more operations will need to be added.
 * </p>
 * 
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * git 
 * @author Steven Mills
 * @version 1.0
 */
public class FilterActions {
    
    /** A list of actions for the Filter menu. */
    protected ArrayList<Action> actions;

    /**
     * <p>
     * Create a set of Filter menu actions.
     * </p>
     */
    public FilterActions() {
        actions = new ArrayList<Action>();
        actions.add(new MeanFilterAction("Mean Filter (M)", null, "Apply a mean filter", Integer.valueOf(KeyEvent.VK_M)));
        actions.add(new GaussianBlurAction("Gaussian Blur (G)", null, "Apply a gaussian blur", Integer.valueOf(KeyEvent.VK_G)));
        actions.add(new SoftBlurAction("Soft Blur (B)", null, "Apply a soft blur", Integer.valueOf(KeyEvent.VK_B)));
        actions.add(new SharpenImageAction("Sharpen Image (H)", null, "Sharpen the Image", Integer.valueOf(KeyEvent.VK_H)));
        actions.add(new MedianFilterAction("Median Filter (D)", null, "Apply a median filter", Integer.valueOf(KeyEvent.VK_D)));


    }

    /**
     * <p>
     * Create a menu contianing the list of Filter actions.
     * </p>
     * 
     * @return The filter menu UI element.
     */
    public JMenu createMenu() {
        JMenu fileMenu = new JMenu("Filter");

        for(Action action: actions) {
            fileMenu.add(new JMenuItem(action));
        }

        return fileMenu;
    }

    /**
     * <p>
     * Action to blur an image with a mean filter.
     * </p>
     * 
     * @see MeanFilter
     */
    public class MeanFilterAction extends ImageAction {

        /**
         * <p>
         * Create a new mean-filter action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        MeanFilterAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the convert-to-grey action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the MeanFilterAction is triggered.
         * It prompts the user for a filter radius, then applys an appropriately sized {@link MeanFilter}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            // Determine the radius - ask the user.
            int radius = 1;

            // Pop-up dialog box to ask for the radius value.
            SpinnerNumberModel radiusModel = new SpinnerNumberModel(1, 1, 10, 1);
            JSpinner radiusSpinner = new JSpinner(radiusModel);
            JLabel message1 = new JLabel("Enter a filter radius");
            JLabel message2 = new JLabel("between 1 and 10 (inclusive)");
            JPanel panel = new JPanel();
            panel.setPreferredSize(new Dimension (190, 30));
            panel.add(message1);
            panel.add(message2);
            panel.add(radiusSpinner);
            
            int option = JOptionPane.showOptionDialog(null, panel, "Enter Filter Radius", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            // Check the return value from the dialog box.
            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                radius = radiusModel.getNumber().intValue();
                if (radius < 1 || radius > 10){
                }
            }

            // Create and apply the filter
            target.getImage().apply(new MeanFilter(radius));
            target.repaint();
            target.getParent().revalidate();
        }

    }

    public class GaussianBlurAction extends ImageAction {

        /* 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        GaussianBlurAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {

            // Determine the radius - ask the user.
            int radius = 1;

            // Pop-up dialog box to ask for the radius value.
            SpinnerNumberModel radiusModel = new SpinnerNumberModel(1, 1, 10, 1);
            JSpinner radiusSpinner = new JSpinner(radiusModel);
            JLabel message1 = new JLabel("Enter a filter radius");
            JLabel message2 = new JLabel("between 1 and 10 (inclusive)");
            JPanel panel = new JPanel();
            panel.setPreferredSize(new Dimension (190, 30));
            panel.add(message1);
            panel.add(message2);
            panel.add(radiusSpinner);
            
            int option = JOptionPane.showOptionDialog(null, panel, "Enter Filter Radius", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            // Check the return value from the dialog box.
            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                radius = radiusModel.getNumber().intValue();
            }

            // Create and apply the filter
            target.getImage().apply(new GaussianBlur(radius));
            target.repaint();
            target.getParent().revalidate();
        }

    }


    public class SoftBlurAction extends ImageAction{
        SoftBlurAction(String name, ImageIcon icon, String desc, Integer mnemonic){
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e){
            target.getImage().apply(new SoftBlur());
            target.repaint();
            target.getParent().revalidate();
        }
    }

    public class SharpenImageAction extends ImageAction{
        SharpenImageAction(String name, ImageIcon icon, String desc, Integer mnemonic){
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e){
            target.getImage().apply(new TheSharpen());
            target.repaint();
            target.getParent().revalidate();
        }
    }

    public class MedianFilterAction extends ImageAction {

        /**
         * <p>
         * Create a new median-filter action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        MedianFilterAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the median filter action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the MedianFilterAction is triggered.
         * It prompts the user for a filter radius, then applies an appropriately sized {@link MedianFilter}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {

            // Determine the radius - ask the user.
            int radius = 1;

            // Pop-up dialog box to ask for the radius value.
            SpinnerNumberModel radiusModel = new SpinnerNumberModel(1, 1, 10, 1);
            JSpinner radiusSpinner = new JSpinner(radiusModel);
            JLabel message1 = new JLabel("Enter a filter radius");
            JLabel message2 = new JLabel("between 1 and 10 (inclusive)");
            JPanel panel = new JPanel();
            panel.setPreferredSize(new Dimension (190, 30));
            panel.add(message1);
            panel.add(message2);
            panel.add(radiusSpinner);
            
            int option = JOptionPane.showOptionDialog(null, panel, "Enter Filter Radius", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            // Check the return value from the dialog box.
            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                radius = radiusModel.getNumber().intValue();
            }

            // Create and apply the filter
            target.getImage().apply(new MedianFilter(radius));
            target.repaint();
            target.getParent().revalidate();
        }

    }

}
