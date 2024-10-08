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

import cosc202.andie.EmbossFilters.EmbossFilter1;
import cosc202.andie.EmbossFilters.EmbossFilter2;
import cosc202.andie.EmbossFilters.EmbossFilter3;
import cosc202.andie.EmbossFilters.EmbossFilter4;
import cosc202.andie.EmbossFilters.EmbossFilter5;
import cosc202.andie.EmbossFilters.EmbossFilter6;
import cosc202.andie.EmbossFilters.EmbossFilter7;
import cosc202.andie.EmbossFilters.EmbossFilter8;
import cosc202.andie.SobelFilters.SobelHorizontal;
import cosc202.andie.SobelFilters.SobelVertical;

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
                Integer.valueOf(KeyEvent.VK_E)));
        actions.add(new BlockAveragingAction(bundle.getString("BlockAvg"), null, bundle.getString("BlockAvgDesc"),
                Integer.valueOf(KeyEvent.VK_T)));
        actions.add(new RandomScatteringAction(bundle.getString("RandomScattering"), null, bundle.getString("RandomScatteringDesc"),
                Integer.valueOf(KeyEvent.VK_U)));
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

        JMenu embossFiltersMenu = new JMenu(bundle.getString("EmbossMenu"));

        JMenuItem embossFilter1 = new JMenuItem(bundle.getString("Emboss1"));
        embossFilter1.addActionListener(new Emboss1Action());

        JMenuItem embossFilter2 = new JMenuItem(bundle.getString("Emboss2"));
        embossFilter2.addActionListener(new Emboss2Action());

        JMenuItem embossFilter3 = new JMenuItem(bundle.getString("Emboss3"));
        embossFilter3.addActionListener(new Emboss3Action());

        JMenuItem embossFilter4 = new JMenuItem(bundle.getString("Emboss4"));
        embossFilter4.addActionListener(new Emboss4Action());

        JMenuItem embossFilter5 = new JMenuItem(bundle.getString("Emboss5"));
        embossFilter5.addActionListener(new Emboss5Action());

        JMenuItem embossFilter6 = new JMenuItem(bundle.getString("Emboss6"));
        embossFilter6.addActionListener(new Emboss6Action());

        JMenuItem embossFilter7 = new JMenuItem(bundle.getString("Emboss7"));
        embossFilter7.addActionListener(new Emboss7Action());

        JMenuItem embossFilter8 = new JMenuItem(bundle.getString("Emboss8"));
        embossFilter8.addActionListener(new Emboss8Action());

        embossFiltersMenu.add(embossFilter1);
        embossFiltersMenu.add(embossFilter2);
        embossFiltersMenu.add(embossFilter3);
        embossFiltersMenu.add(embossFilter4);
        embossFiltersMenu.add(embossFilter5);
        embossFiltersMenu.add(embossFilter6);
        embossFiltersMenu.add(embossFilter7);
        embossFiltersMenu.add(embossFilter8);

        fileMenu.add(embossFiltersMenu);

        JMenu sobelFiltersMenu = new JMenu(bundle.getString("SobelMenu"));
        JMenuItem sobelHorizontal = new JMenuItem(bundle.getString("SobelHor"));
        sobelHorizontal.addActionListener(new SobelHorizontalAction());

        JMenuItem sobelVertical = new JMenuItem(bundle.getString("SobelVer"));
        sobelVertical.addActionListener(new SobelVerticalAction());

        sobelFiltersMenu.add(sobelHorizontal);
        sobelFiltersMenu.add(sobelVertical);

        fileMenu.add(sobelFiltersMenu);

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
         * <p>
         * Action to create a Gaussian Blur filter
         * </p>
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
         * <p>
         * Callback for when the soft blur action is triggered.
         * </p>
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
         * <p>
         * Callback for when the sharpen image action is triggered.
         * </p>
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
            putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_E, KeyEvent.CTRL_DOWN_MASK));

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

    /**
     * <p>
     * Action to emboss an image
     * </p>
     */
    public class Emboss1Action extends ImageAction {

        /**
         * <p>
         * Create a new emboss 1 action.
         * </p>
         */
        Emboss1Action() {
            super(bundle.getString("Emboss1"), null, "EmbossFilterDesc", null);
        }

        /**
         *
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            target.getImage().apply(new EmbossFilter1());
            target.repaint();
            target.getParent().revalidate();
        }
    }

    /**
     * <p>
     * Action to emboss an image
     * </p>
     */
    public class Emboss2Action extends ImageAction {

        /**
         * <p>
         * Create a new emboss 2 action.
         * </p>
         */
        Emboss2Action() {
            super(bundle.getString("Emboss2"), null, "EmbossFilterDesc", null);
        }

        /**
         *
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            target.getImage().apply(new EmbossFilter2());
            target.repaint();
            target.getParent().revalidate();
        }
    }

    /**
     * <p>
     * Action to emboss an image
     * </p>
     */
    public class Emboss3Action extends ImageAction {

        /**
         * <p>
         * Create a new emboss 3 action.
         * </p>
         */
        Emboss3Action() {
            super(bundle.getString("Emboss3"), null, "EmbossFilterDesc", null);
        }

        /**
         *
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            target.getImage().apply(new EmbossFilter3());
            target.repaint();
            target.getParent().revalidate();
        }
    }

    /**
     * <p>
     * Action to emboss an image
     * </p>
     */
    public class Emboss4Action extends ImageAction {

        /**
         * <p>
         * Create a new emboss 4 action.
         * </p>
         */
        Emboss4Action() {
            super(bundle.getString("Emboss4"), null, "EmbossFilterDesc", null);
        }

        /**
         *
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            target.getImage().apply(new EmbossFilter4());
            target.repaint();
            target.getParent().revalidate();
        }
    }

    /**
     * <p>
     * Action to emboss an image
     * </p>
     */
    public class Emboss5Action extends ImageAction {

        /**
         * <p>
         * Create a new emboss 5 action.
         * </p>
         */
        Emboss5Action() {
            super(bundle.getString("Emboss5"), null, "EmbossFilterDesc", null);
        }

        /**
         *
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            target.getImage().apply(new EmbossFilter5());
            target.repaint();
            target.getParent().revalidate();
        }
    }

    /**
     * <p>
     * Action to emboss an image
     * </p>
     */
    public class Emboss6Action extends ImageAction {

        /**
         * <p>
         * Create a new emboss 6 action.
         * </p>
         */
        Emboss6Action() {
            super(bundle.getString("Emboss6"), null, "EmbossFilterDesc", null);
        }

        /**
         *
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            target.getImage().apply(new EmbossFilter6());
            target.repaint();
            target.getParent().revalidate();
        }
    }

    /**
     * <p>
     * Action to emboss an image
     * </p>
     */
    public class Emboss7Action extends ImageAction {

        /**
         * <p>
         * Create a new emboss 7 action.
         * </p>
         */
        Emboss7Action() {
            super(bundle.getString("Emboss7"), null, "EmbossFilterDesc", null);
        }

        /**
         *
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            target.getImage().apply(new EmbossFilter7());
            target.repaint();
            target.getParent().revalidate();
        }
    }

    /**
     * <p>
     * Action to emboss an image
     * </p>
     */
    public class Emboss8Action extends ImageAction {

        /**
         * <p>
         * Create a new emboss 8 action.
         * </p>
         */
        Emboss8Action() {
            super(bundle.getString("Emboss8"), null, "EmbossFilterDesc", null);
        }

        /**
         *
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            target.getImage().apply(new EmbossFilter8());
            target.repaint();
            target.getParent().revalidate();
        }
    }

    /**
     * <p>
     * Action to use Sobel Horizontional
     * </p>
     */
    public class SobelHorizontalAction extends ImageAction {
        
        /**
         * <p>
         * Create a sobel horizontal action.
         * </p>
         */
        SobelHorizontalAction() {
            super(bundle.getString("SobelHor"), null, "SobelHor", null);
        }

        /**
         * Callback for when the Sobel filter action is triggered.
         *
         * This method is called whenever the SobelFilterAction is triggered.
         * It applies the Sobel filter to the image.
         *
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) { 
            target.getImage().apply(new SobelHorizontal());
            target.repaint();
            target.getParent().revalidate();

        }
    }

    public class SobelVerticalAction extends ImageAction {
        
        /**
         * <p>
         * Create a sobel vertical action.
         * </p>
         */
        SobelVerticalAction() {
            super(bundle.getString("SobelVer"), null, "SobelVer", null);
        }

        /**
         * Callback for when the Sobel filter action is triggered.
         *
         * This method is called whenever the SobelFilterAction is triggered.
         * It applies the Sobel filter to the image.
         *
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) { 
            target.getImage().apply(new SobelHorizontal());
            target.repaint();
            target.getParent().revalidate();

        }
    }

    /**
     * <p>
     * Action to average pixels into blocks and apply to an image
     * </p>
     */
    public class BlockAveragingAction extends ImageAction{

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
        BlockAveragingAction(String name, ImageIcon icon, String desc, Integer mnemonic){
            super(name, icon, desc, mnemonic);
            putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_T, KeyEvent.CTRL_DOWN_MASK));
        }


        /**
        * Callback for when the BlockAveraging filter action is triggered.
        *
        * This method is called whenever the BlockAveragingAction is triggered.
        * It applies the BlockAveraging filter to the image.
        *
        * @param e The event triggering this callback.
        */
        public void actionPerformed(ActionEvent e){

            int blockSize = 1;

            // Pop-up dialog box to ask for the radius value.
            SpinnerNumberModel blockAvgModel = new SpinnerNumberModel(1, 1, 20, 1);
            JSpinner blockSizeSpinner = new JSpinner(blockAvgModel);
            JLabel message1 = new JLabel(bundle.getString("BlockAvgMSG1"));
            JLabel message2 = new JLabel(bundle.getString("BlockAvgMSG2"));
            JPanel panel = new JPanel();
            panel.setPreferredSize(new Dimension(300, 70));
            panel.add(message1);
            panel.add(message2);
            panel.add(blockSizeSpinner);

            int option = JOptionPane.showOptionDialog(null, panel, bundle.getString("BlockAvgSize"),
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            // Check the return value from the dialog box.
            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                blockSize = blockAvgModel.getNumber().intValue();
            }

            // Create and apply the filter
            target.getImage().apply(new BlockAveraging(blockSize));
            target.repaint();
            target.getParent().revalidate();
        }
    }


    /**
     * <p>
     * Action to scatter pixels within an image within a given radius
     * </p>
     */
    public class RandomScatteringAction extends ImageAction{
        
        /**
        * <p>
         * Create a new RandomScattering action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        RandomScatteringAction(String name, ImageIcon icon, String desc, Integer mnemonic){
            super(name, icon, desc, mnemonic);
            putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_U, KeyEvent.CTRL_DOWN_MASK));
        }
        
        
        /**
        * Callback for when the RandomScattering filter action is triggered.
        *
        * This method is called whenever the RandomScatteringAction is triggered.
        * It applies the RandomScattering filter to the image.
        *
        * @param e The event triggering this callback.
        */
        public void actionPerformed(ActionEvent e){
            int radius = 1;

            // Pop-up dialog box to ask for the radius value.
            SpinnerNumberModel radiusModel = new SpinnerNumberModel(1, 1, 20, 1);
            JSpinner radiusSpinner = new JSpinner(radiusModel);
            JLabel message1 = new JLabel(bundle.getString("RandomScatteringMSG1"));
            JLabel message2 = new JLabel(bundle.getString("RandomScatteringMSG2"));
            JPanel panel = new JPanel();
            panel.setPreferredSize(new Dimension(300, 70));
            panel.add(message1);
            panel.add(message2);
            panel.add(radiusSpinner);

            int option = JOptionPane.showOptionDialog(null, panel, bundle.getString("RandomScatteringRadius"),
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
            
            // Check the return value from the dialog box.
            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                radius = radiusModel.getNumber().intValue();
            }

            // Create and apply the filter
            target.getImage().apply(new RandomScattering(radius));
            target.repaint();
            target.getParent().revalidate();
        }
    }
}