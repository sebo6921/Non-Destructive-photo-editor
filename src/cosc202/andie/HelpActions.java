package cosc202.andie;


import java.util.*;
import java.awt.event.*;
import java.awt.BorderLayout;
import javax.swing.*;


import java.nio.file.*;
import java.io.*;


public class HelpActions {
    
    /**
     * A list of actions for the View menu.
     */
    protected ArrayList<Action> actions;

    /**
     * <p>
     * Create a set of View menu actions.
     * </p>
     */
    public HelpActions() {
        actions = new ArrayList<Action>();
        actions.add(new HelpGuideAction("ANDIE Guide (H)", null, "ANDIE Guide", Integer.valueOf(KeyEvent.VK_H)));
        actions.add(new ChooseLanguageAction("Change Language (L)", null, "Change Language", Integer.valueOf(KeyEvent.VK_L)));

    }

    /**
     * <p>
     * Create a menu containing the list of View actions.
     * </p>
     * 
     * @return The view menu UI element.
     */
    public JMenu createMenu() {
        JMenu helpMenu = new JMenu("Help");

        for (Action action: actions) {
            helpMenu.add(new JMenuItem(action));
        }

        return helpMenu;
    }

    public class HelpGuideAction extends AbstractAction {
        JButton helpOkButton;
        /**
         *
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        HelpGuideAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon);
            if (desc != null) {
               putValue(SHORT_DESCRIPTION, desc);
            }
            if (mnemonic != null) {
                putValue(MNEMONIC_KEY, mnemonic);
            }
        }
 
 
    //     /**
    //      *
    //      * @param e The event triggering this callback.
    //      */
        public void actionPerformed(ActionEvent e) {
            JDialog helpPanel = new JDialog();
            helpPanel.setTitle("Help Guide");
            helpPanel.setSize(400, 300);
            helpPanel.setLayout(new BorderLayout());
 
 
            JTextArea textArea = new JTextArea();
            // textArea.setEditable(false);
            // textArea.setLineWrap(true);
            // textArea.setWrapStyleWord(true);
 
 
            String helpText = getFileContents("help.txt");
            textArea.setText(helpText);
 
 
            // // Add the JTextArea to the dialog inside a JScrollPane (for scrolling)
            // JScrollPane scrollPane = new JScrollPane(textArea);
            // helpPanel.add(scrollPane, BorderLayout.CENTER);
 
 
            // Display the dialog
            helpPanel.setLocationRelativeTo(null); // Center on screen
            helpPanel.setVisible(true);
           
        }
 
 
        public static String getFileContents(String fileName){
            String fileContents = "";
            try {
                fileContents = Files.readString(Path.of(fileName));
            } catch (IOException e) {
               System.out.println("File not found.");
            }
            return fileContents;
        }
 
 
    } 
 
 
    /**
     *
     */
    public class ChooseLanguageAction extends AbstractAction {
 
 
        /**
         *
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        ChooseLanguageAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon);
            if (desc != null) {
                putValue(SHORT_DESCRIPTION, desc);
            }
            if (mnemonic != null) {
                putValue(MNEMONIC_KEY, mnemonic);
            }
        }
 
 
        /**
         *
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
           
        }
 
 
    }

}

 

    
