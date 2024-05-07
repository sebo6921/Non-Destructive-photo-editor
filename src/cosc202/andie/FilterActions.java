package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.KeyStroke;


//import cosc202.andie.FilterActions.SobelFiltersAction;

import java.awt.*;


/**
 * <p>
 * Actions provided by the Filter menu.
 * </p>
 * 
 * <p>
 * The Filter menu contains actions that update each pixel in an image based on
 * some small local neighbourhood.
 * This includes a mean filter (a simple blur) in the sample code, but more
 * operations will need to be added.
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA
 * 4.0</a>
 * </p>
 * git
 * 
 * @author Steven Mills
 * @version 1.0
 */
public class FilterActions {

    /** A list of actions for the Filter menu. */
    protected ArrayList<Action> actions;

    /** A resource bundle to change the language */
    ResourceBundle bundle;

    /**
     * <p>
     * Create a set of Filter menu actions.
     * </p>
     */
    public FilterActions(ResourceBundle bundle) {
        this.bundle = bundle;
        actions = new ArrayList<Action>();
        actions.add(new MeanFilterAction(bundle.getString("MeanFilter"), null, bundle.getString("MeanFilterDesc"),
                Integer.valueOf(KeyEvent.VK_M)));
        actions.add(new GaussianBlurAction(bundle.getString("GaussianBlur"), null, bundle.getString("GaussianBlurDesc"),
                Integer.valueOf(KeyEvent.VK_G)));
        actions.add(new SoftBlurAction(bundle.getString("SoftBlur"), null, bundle.getString("SoftBlurDesc"),
                Integer.valueOf(KeyEvent.VK_B)));
        actions.add(new SharpenImageAction(bundle.getString("SharpenImage"), null, bundle.getString("SharpenImageDesc"),
                Integer.valueOf(KeyEvent.VK_H)));
        actions.add(new MedianFilterAction(bundle.getString("MedianFilter"), null, bundle.getString("MedianFilterDesc"),
                Integer.valueOf(KeyEvent.VK_D)));
        //actions.add(new EmbossFiltersAction(bundle.getString("EmbossFilters"), null, bundle.getString("EmbossFilterDesc"),
             //Integer.valueOf(KeyEvent.VK_E)));
        // actions.add(new SobelFilterAction(bundle.getString("SobelFilters"), null, bundle.getString("SobelFiltersDesc"),
        //        Integer.valueOf(KeyEvent.VK_S)));
    }

    /**
     * <p>
     * Create a menu contianing the list of Filter actions.
     * </p>
     * 
     * @return The filter menu UI element.
     */
    public JMenu createMenu() {
        JMenu fileMenu = new JMenu(bundle.getString("FilterMenu"));

        for (Action action : actions) {
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
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        MeanFilterAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_M, KeyEvent.CTRL_DOWN_MASK));

        }

        /**
         * <p>
         * Callback for when the convert-to-grey action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the MeanFilterAction is triggered.
         * It prompts the user for a filter radius, then applys an appropriately sized
         * {@link MeanFilter}.
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
            JLabel message1 = new JLabel(bundle.getString("MeanFilterMSG1"));
            JLabel message2 = new JLabel(bundle.getString("MeanFilterMSG2"));
            JPanel panel = new JPanel();
            panel.setPreferredSize(new Dimension(300, 70));
            panel.add(message1);
            panel.add(message2);
            panel.add(radiusSpinner);

            int option = JOptionPane.showOptionDialog(null, panel, bundle.getString("MeanFilterRadius"),
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            // Check the return value from the dialog box.
            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                radius = radiusModel.getNumber().intValue();
                if (radius < 1 || radius > 10) {
                }
            }

            // Create and apply the filter
            target.getImage().apply(new MeanFilter(radius));
            target.repaint();
            target.getParent().revalidate();
        }
    }

    public class GaussianBlurAction extends ImageAction {

        /**  
         *  <p>
         * Action to create a Gaussian Blur filter
         *  </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        GaussianBlurAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_G, KeyEvent.CTRL_DOWN_MASK));

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
            JLabel message1 = new JLabel(bundle.getString("GaussianBlurMSG1"));
            JLabel message2 = new JLabel(bundle.getString("GaussianBlurMSG2"));
            JPanel panel = new JPanel();
            panel.setPreferredSize(new Dimension(300, 70));
            panel.add(message1);
            panel.add(message2);
            panel.add(radiusSpinner);

            int option = JOptionPane.showOptionDialog(null, panel, bundle.getString("GaussianBlurRadius"),
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

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

    public class SoftBlurAction extends ImageAction {
        SoftBlurAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_B, KeyEvent.CTRL_DOWN_MASK));

        }

        /**
         *  <p>
         * Callback for when the soft blur action is triggered.
         *  </p>
         * 
         * <p>
         * This method is called whenever the SoftBlurAction is triggered.
         * It applies a {@link SoftBlur} filter to the image.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            target.getImage().apply(new SoftBlur());
            target.repaint();
            target.getParent().revalidate();
        }
    }

    public class SharpenImageAction extends ImageAction {
        /**
         * Action to sharpen an image.
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        SharpenImageAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_H, KeyEvent.CTRL_DOWN_MASK));

        }

        /**
         *  <p>
         * Callback for when the sharpen image action is triggered.
         *  </p>
         * 
         * <p>
         * This method is called whenever the SharpenImageAction is triggered.
         * It applies a {@link TheSharpen} filter to the image.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            target.getImage().apply(new TheSharpen());
            target.repaint();
            target.getParent().revalidate();
        }
    }

    /**
     * Action to apply a median filter to an image.
     */
    public class MedianFilterAction extends ImageAction {

        /**
         * <p>
         * Create a new median-filter action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        MedianFilterAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_D, KeyEvent.CTRL_DOWN_MASK));

        }

        /**
         * <p>
         * Callback for when the median filter action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the MedianFilterAction is triggered.
         * It prompts the user for a filter radius, then applies an appropriately sized
         * {@link MedianFilter}.
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
            JLabel message1 = new JLabel(bundle.getString("MedianFilterMSG1"));
            JLabel message2 = new JLabel(bundle.getString("MedianFilterMSG2"));
            JPanel panel = new JPanel();
            panel.setPreferredSize(new Dimension(300, 70));
            panel.add(message1);
            panel.add(message2);
            panel.add(radiusSpinner);

            int option = JOptionPane.showOptionDialog(null, panel, bundle.getString("MedianFilterRadius"),
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

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

    // public class SobelFilterAction extends ImageAction {
    //     /**
    //      * Action to apply Sobel filter to an image.
    //      * 
    //      * @param name     The name of the action (ignored if null).
    //      * @param icon     An icon to use to represent the action (ignored if null).
    //      * @param desc     A brief description of the action (ignored if null).
    //      * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
    //      */
    //     SobelFilterAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
    //         super(name, icon, desc, mnemonic);
    //     }
    
    //     /**
    //      * Callback for when the Sobel filter action is triggered.
    //      * 
    //      * This method is called whenever the SobelFilterAction is triggered.
    //      * It applies the Sobel filter to the image.
    //      * 
    //      * @param e The event triggering this callback.
    //      */
    //     public void actionPerformed(ActionEvent e) {
    //         //SobelFilters sobelFilters = new SobelFilters();
    //         //sobelFilters.applySobel(target.getImage());
    //         //target.getImage().apply(new SobelFilters());
    //         target.repaint();
    //         target.getParent().revalidate();

            
    //     }
    // }
}