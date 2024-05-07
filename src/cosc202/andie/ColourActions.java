package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.KeyStroke;

/**
 * <p>
 * Actions provided by the Colour menu.
 * </p>
 * 
 * <p>
 * The Colour menu contains actions that affect the colour of each pixel
 * directly
 * without reference to the rest of the image.
 * This includes conversion to greyscale in the sample code, but more operations
 * will need to be added.
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
public class ColourActions {

    /** A list of actions for the Colour menu. */
    protected ArrayList<Action> actions;

    /** A resource bundle to change the language */
    ResourceBundle bundle;

    /**
     * <p>
     * Create a set of Colour menu actions.
     * </p>
     */
    public ColourActions(ResourceBundle bundle) {
        this.bundle = bundle;
        actions = new ArrayList<Action>();
        actions.add(new ConvertToGreyAction(bundle.getString("GreyScale"), null, bundle.getString("GreyScaleDesc"),
                Integer.valueOf(KeyEvent.VK_Y)));
        actions.add(new ImageInversionAction(bundle.getString("InvertImage"), null, bundle.getString("InvertImageDesc"),
                Integer.valueOf(KeyEvent.VK_I)));
        actions.add(new ColourChannelCyclingAction(bundle.getString("ColourChannelCycling"), null,
                bundle.getString("ColourChannelCyclingDesc"), Integer.valueOf(KeyEvent.VK_C)));
        actions.add(new BrightnessAndContrastAction(bundle.getString("Brightness&Contrast"), null, 
                bundle.getString("Brightness&ContrastDesc"), Integer.valueOf(KeyEvent.VK_B)));
    }

    /**
     * <p>
     * Create a menu contianing the list of Colour actions.
     * </p>
     * 
     * @return The colour menu UI element.
     */
    public JMenu createMenu() {
        JMenu fileMenu = new JMenu(bundle.getString("ColourMenu"));

        for (Action action : actions) {
            fileMenu.add(new JMenuItem(action));
        }
        return fileMenu;
    }

    /**
     * <p>
     * Action to convert an image to greyscale.
     * </p>
     * 
     * @see ConvertToGrey
     */
    public class ConvertToGreyAction extends ImageAction {

        /**
         * <p>
         * Create a new convert-to-grey action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        ConvertToGreyAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Y, KeyEvent.CTRL_DOWN_MASK));

        }

        /**
         * <p>
         * Callback for when the convert-to-grey action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the ConvertToGreyAction is triggered.
         * It changes the image to greyscale.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            target.getImage().apply(new ConvertToGrey());
            target.repaint();
            target.getParent().revalidate();
        }
    }

    /**
     * <p>
     * Action to invert image RGB values
     * </p>
     * 
     * @see ImageInversion
     */
    public class ImageInversionAction extends ImageAction {

        /**
         * <p>
         * Create a new ImageInversion action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        ImageInversionAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_I, KeyEvent.CTRL_DOWN_MASK));

        }

        /**
         * <p>
         * Callback for when the ImageInversionAction is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the ImageInversionAction is triggered.
         * It inverts the RGB values of the image.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            target.getImage().apply(new ImageInversion());
            target.repaint();
            target.getParent().revalidate();
        }
    }

    /**
     * <p>
     * Action to cycle RGB values
     * </p>
     * 
     * @see ColourChannelCycling
     */
    public class ColourChannelCyclingAction extends ImageAction {

        /**
         * <p>
         * Create a new ColourChannelCycling action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        ColourChannelCyclingAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK));

        }

        /**
         * <p>
         * Callback for when the ImageInversionAction is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the ImageInversionAction is triggered.
         * It inverts the RGB values of the image.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            target.getImage().apply(new ColourChannelCycling());
            target.repaint();
            target.getParent().revalidate();
        }
    }



        /**
         * <p>
         * Action to adjust image brightness and contrast
         * </p>
         * 
         * @see BrightnessAndContrast
         */
        public class BrightnessAndContrastAction extends ImageAction{
        
        
            /**
             * <p>
             * Create a new Brightness and Contrast action.
             * </p>
             * 
             * @param name     The name of the action (ignored if null).
             * @param icon     An icon to use to represent the action (ignored if null).
             * @param desc     A brief description of the action (ignored if null).
             * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
             */
            BrightnessAndContrastAction(String name, ImageIcon icon, String desc, Integer mnemonic){
                super(name, icon, desc, mnemonic);
                putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_B, KeyEvent.CTRL_DOWN_MASK));

            }



            /**
             * <p>
             * Callback for when the BrightnessAndContrastAction is triggered.
             * </p>
             * 
             * <p>
             * This method is called whenever the BrightnessAndContrastAction is triggered.
             * It adjusts the value of the images brightness and colour
             * </p>
             * 
             * @param e The event triggering this callback.
             */
            public void actionPerformed(ActionEvent e){
                
                //Make new slider and adjust settings
                JSlider brightnessSlider = new JSlider(-100, 100);
                brightnessSlider.setMajorTickSpacing(25);
                brightnessSlider.setMinorTickSpacing(5);
                brightnessSlider.setPaintTicks(true);

                
                JSlider contrastSlider = new JSlider(-100, 100);
                contrastSlider.setMajorTickSpacing(25);
                contrastSlider.setMinorTickSpacing(5);
                contrastSlider.setPaintTicks(true);
                System.out.println("Are we here");


                //Make panel and add messages and sliders
                JPanel panel = new JPanel();
                JLabel message1 = new JLabel(bundle.getString("BrightnessMSG"));
                JLabel message2 = new JLabel(bundle.getString("ContrastMSG"));
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                panel.add(message1);
                panel.add(brightnessSlider);
                panel.add(message2);
                panel.add(contrastSlider);

                JOptionPane.showOptionDialog(null, panel, bundle.getString("B&CMSG"),
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
           
    

                target.getImage().apply(new BrightnessAndContrast(brightnessSlider.getValue(), contrastSlider.getValue()));
                target.repaint();
                target.getParent().revalidate();

            }
        }
}