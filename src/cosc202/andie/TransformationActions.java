package cosc202.andie;

import java.util.*;
import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.*;

public class TransformationActions {
    
    /** A list of actions for the Transformations menu. */
    protected ArrayList<Action> actions;

    ResourceBundle bundle;

    /**
     * <p>
     * Create a set of Transformation menu actions.
     * </p>
     */
    public TransformationActions(ResourceBundle bundle){
        this.bundle = bundle;
        actions = new ArrayList<Action>();
        actions.add(new ResizeAction(bundle.getString("Resize"), null, bundle.getString("ResizeDesc"), Integer.valueOf(KeyEvent.VK_R)));
    }

    /**
     * <p>
     * Create a menu containing the list of Transformation actions.
     * </p>
     * 
     * @return The transformation menu UI element.
     */
    public JMenu createMenu(){
        JMenu transMenu = new JMenu(bundle.getString("TransformationMenu"));

        JMenu rotateSubMenu = new JMenu(bundle.getString("RotateMenu"));

        JMenuItem rotate90Clockwise = new JMenuItem(bundle.getString("90DegClockwiseMenu"));
        rotate90Clockwise.addActionListener(new Rotate90ClockwiseAction());

        JMenuItem rotate90AntiClockwise = new JMenuItem(bundle.getString("90DegAntiClockwiseMenu"));
        rotate90AntiClockwise.addActionListener(new Rotate90AntiClockwiseAction());

        JMenuItem rotate180 = new JMenuItem(bundle.getString("Rotate180DegMenu"));
        rotate180.addActionListener(new Rotate180Action());

        rotateSubMenu.add(rotate90Clockwise);
        rotateSubMenu.add(rotate90AntiClockwise);
        rotateSubMenu.add(rotate180);

        JMenu flipSubMenu = new JMenu(bundle.getString("FlipMenu"));

        JMenuItem flipHorizontally = new JMenuItem(bundle.getString("FlipHorMenu"));
        flipHorizontally.addActionListener(new FlipHorizontallyAction());

        JMenuItem flipVertically = new JMenuItem(bundle.getString("FlipVerMenu"));
        flipVertically.addActionListener(new FlipVerticallyAction());

        flipSubMenu.add(flipHorizontally);
        flipSubMenu.add(flipVertically);

        for(Action action: actions){
            transMenu.add(new JMenuItem(action));
        }

        transMenu.add(rotateSubMenu);

        transMenu.add(flipSubMenu);

        return transMenu;
    }

    /**
     * <p>
     * Action to resize an image.
     * </p>
     * 
     * @see Resize
     */
    public class ResizeAction extends ImageAction{
        
        /**
         * <p>
         * Create a new resize action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        ResizeAction(String name, ImageIcon icon, String desc, Integer mnemonic){
            super(name, icon, desc, mnemonic);
        }

        public void actionPerformed(ActionEvent e){
            
            // Determine the size - ask the user.
            int size = 100;

            // Pop-up dialog box to ask for the size value.
            SpinnerNumberModel sizeModel = new SpinnerNumberModel(100, 25, 300, 5);
            JSpinner sizeSpinner = new JSpinner(sizeModel);
            JLabel message1 = new JLabel(bundle.getString("TransformationActionsMsg"));
            JLabel message2 = new JLabel(bundle.getString("TransformationActionsMsg2"));
            JPanel panel = new JPanel();
            panel.setPreferredSize(new Dimension (300, 70));
            panel.add(message1);
            panel.add(message2);
            panel.add(sizeSpinner);
            
            int option = JOptionPane.showOptionDialog(null, panel, bundle.getString("Size"), JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            // Check the return value from the dialog box
            if(option == JOptionPane.CANCEL_OPTION){
                return;
            } else if(option == JOptionPane.OK_OPTION){
                size = sizeModel.getNumber().intValue();
            }


            // Create and apply the size change
            target.getImage().apply(new Resize(size));
            target.repaint();
            target.getParent().revalidate();
        }
    }



    /**
     * <p>
     * Action to rotate an image 90 degreees clockwise.
     * </p>
     * 
     * @see Rotate
     */
    public class Rotate90ClockwiseAction extends ImageAction{
        /**
         * <p>
         * Create a new rotate clockwise action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        Rotate90ClockwiseAction(){
            super(bundle.getString("RotateNintyDegreesName"), null, bundle.getString("RotateNintyDegreesDesc"), null);
        }


        public void actionPerformed(ActionEvent e){
            
            //Create and apply the change in rotation
            target.getImage().apply(new Rotate(90));
            target.repaint();
            target.getParent().revalidate();

        }

    }



    /**
     * <p>
     * Action to rotate an image 90 degreees anti-clockwise.
     * </p>
     * 
     * @see Rotate
     */
    public class Rotate90AntiClockwiseAction extends ImageAction{
        /**
         * <p>
         * Create a new rotate anti-clockwise action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        Rotate90AntiClockwiseAction(){
            super(bundle.getString("RotateNintyDegreesAntiName"), null, bundle.getString("RotateNintyDegreesAntiDesc"), null);
        }


        public void actionPerformed(ActionEvent e){

            //Create and apply the change in rotation
            target.getImage().apply(new Rotate(-90));
            target.repaint();
            target.getParent().revalidate();

        }

    }

    /**
     * <p>
     * Action to rotate an image 180 degreees.
     * </p>
     * 
     * @see Rotate
     */
    public class Rotate180Action extends ImageAction{
        /**
         * <p>
         * Create a new rotate 180 degrees action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        Rotate180Action(){
            super(bundle.getString("Rotate180"), null, bundle.getString("RotateDesc180"), null);
        }


        public void actionPerformed(ActionEvent e){

            //Create and apply the change in rotation
            target.getImage().apply(new Rotate(180));
            target.repaint();
            target.getParent().revalidate();

        }

    }


    /**
     * <p>
     * Action to flip image horizontally.
     * </p>
     * 
     * @see Flip
     */
    public class FlipHorizontallyAction extends ImageAction{
        /**
         * <p>
         * Create a new flip horizontally action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        FlipHorizontallyAction(){
            super(bundle.getString("FlipHorizontally"), null, bundle.getString("FlipHorizontallyDesc"), null);
        }

        public void actionPerformed(ActionEvent e){

            //Create and apply the change in direction
            target.getImage().apply(new Flip(90));
            target.repaint();
            target.getParent().revalidate();
        }
    }


    /**
     * <p>
     * Action to flip image vertically.
     * </p>
     * 
     * @see Flip
     */
    public class FlipVerticallyAction extends ImageAction{
        /**
         * <p>
         * Create a new flip vertically action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        FlipVerticallyAction(){
            super(bundle.getString("FlipVertically"), null, bundle.getString("FlipVerticallyDesc"), null);
        }

        public void actionPerformed(ActionEvent e){

            //Create and apply the change in direction
            target.getImage().apply(new Flip(180));
            target.repaint();
            target.getParent().revalidate();
        }
    }



}
