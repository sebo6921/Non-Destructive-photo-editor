package cosc202.andie;

import java.util.*;
import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.*;

public class TransformationActions {
    
    /** A list of actions for the Transformations menu. */
    protected ArrayList<Action> actions;

    /**
     * <p>
     * Create a set of Transformation menu actions.
     * </p>
     */
    public TransformationActions(){
        actions = new ArrayList<Action>();
        actions.add(new ResizeAction("Resize", null, "Resize image", Integer.valueOf(KeyEvent.VK_R)));
    }

    /**
     * <p>
     * Create a menu containing the list of Transformation actions.
     * </p>
     * 
     * @return The transformation menu UI element.
     */
    public JMenu createMenu(){
        JMenu transMenu = new JMenu("Transformation");

        JMenu rotateSubMenu = new JMenu("Rotate");

        JMenuItem rotate90Clockwise = new JMenuItem("90 Degrees Clockwise");
        rotate90Clockwise.addActionListener(new Rotate90ClockwiseAction());

        JMenuItem rotate90AntiClockwise = new JMenuItem("90 Degrees Anti-Clockwise");
        rotate90AntiClockwise.addActionListener(new Rotate90AntiClockwiseAction());

        JMenuItem rotate180 = new JMenuItem("Rotate 180 Degrees");
        rotate180.addActionListener(new Rotate180Action());

        rotateSubMenu.add(rotate90Clockwise);
        rotateSubMenu.add(rotate90AntiClockwise);
        rotateSubMenu.add(rotate180);

        JMenu flipSubMenu = new JMenu("Flip");

        JMenuItem flipHorizontally = new JMenuItem("Flip Horizontally");
        flipHorizontally.addActionListener(new FlipHorizontallyAction());

        JMenuItem flipVertically = new JMenuItem("Flip Vertically");
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
            JLabel message1 = new JLabel("Please enter a new size. The size should");
            JLabel message2 = new JLabel("be between 25 and 300 pixels (inclusive).");
            JPanel panel = new JPanel();
            panel.setPreferredSize(new Dimension (300, 70));
            panel.add(message1);
            panel.add(message2);
            panel.add(sizeSpinner);
            
            int option = JOptionPane.showOptionDialog(null, panel, "Enter New Size", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

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
            super("Rotate 90 degrees clockwise", null, "Rotate 90 degrees clockwise", null);
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
            super("Rotate 90 degrees anticlockwise", null, "Rotate 90 degrees anticlockwise", null);
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
            super("Rotate 180 degrees", null, "Rotate 180 degrees", null);
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
            super("Flip Horizontally", null, "Flip Horizontally", null);
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
            super("Flip Vertically", null, "Flip Vertically", null);
        }

        public void actionPerformed(ActionEvent e){

            //Create and apply the change in direction
            target.getImage().apply(new Flip(180));
            target.repaint();
            target.getParent().revalidate();
        }
    }



}
