package cosc202.andie;

import java.util.*;
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
        actions.add(new RotateAction("Rotate", null, "Rotate Image", Integer.valueOf(KeyEvent.VK_O)));

    }

    /**
     * <p>
     * Create a menu contianing the list of Transformation actions.
     * </p>
     * 
     * @return The transformation menu UI element.
     */
    public JMenu createMenu(){
        JMenu transMenu = new JMenu("Transformation");

        for(Action action: actions){
            transMenu.add(new JMenuItem(action));
        }

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
            int option = JOptionPane.showOptionDialog(null, sizeSpinner, "Enter new size", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
            
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


    public class RotateAction extends ImageAction{

        RotateAction(String name, ImageIcon icon, String desc, Integer mnemonic){
            super(name, icon, desc, mnemonic);
        }


        public void actionPerformed(ActionEvent e){

            int angle = 0;

            SpinnerNumberModel angleModel = new SpinnerNumberModel(0, -180, 180, 90);
            JSpinner angleSpinner = new JSpinner(angleModel);
            int option = JOptionPane.showOptionDialog(null, angleSpinner, "Enter Rotation Angle (degrees)", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null );

            if(option == JOptionPane.CANCEL_OPTION){
                return;
            }else if(option == JOptionPane.OK_OPTION){
                angle = (int) angleModel.getNumber().intValue();
            }
            

            target.getImage().apply(new Rotate(angle));
            target.repaint();
            target.getParent().revalidate();

        }

    }


}
