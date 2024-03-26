package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.nio.file.*;
import java.io.*;

/**
 * <p>
 * Actions provided by the Help menu.
 * </p>
 * 
 * <p>
 * The user will expect to find an ANDIE help guide and the ability to change the language
 * of the ANDIE program.
 * </p>
 * 
 * @author Jessica Fan
 * @version 1.0
 */
public class HelpActions {
    
    /**
     * A list of actions for the Help menu.
     */
    protected ArrayList<Action> actions;

    /**
     * <p>
     * Create a set of Help menu actions.
     * </p>
     */
    public HelpActions() {
        actions = new ArrayList<Action>();
        actions.add(new HelpGuideAction("ANDIE Guide (H)", null, "ANDIE Guide", Integer.valueOf(KeyEvent.VK_H)));
        actions.add(new ChooseLanguageAction("Change Language (L)", null, "Change Language", Integer.valueOf(KeyEvent.VK_L)));

    }

    /**
     * <p>
     * Create a menu containing the list of Help actions.
     * </p>
     * 
     * @return The help menu UI element.
     */
    public JMenu createMenu() {
        JMenu helpMenu = new JMenu("Help");

        for (Action action: actions) {
            helpMenu.add(new JMenuItem(action));
        }

        return helpMenu;
    }

    /**
     * <p>
     * Action to open a guide on how specific buttons on ANDIE work.
     * </p>
     */
    public class HelpGuideAction extends AbstractAction {
        
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
 
 
        /**
         *
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            JDialog helpPanel = new JDialog();
            helpPanel.setTitle("ANDIE Guide");
            helpPanel.setSize(500, 700);
            helpPanel.setLayout(new BorderLayout());
 
            JTextArea textArea = new JTextArea();
            textArea.setEditable(false);
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
            
            textArea.setMargin(new Insets(5, 30, 5, 30));

            Font font = new Font("Times New Roman", Font.PLAIN, 15);
            textArea.setFont(font);

            String helpText = getFileContents("help.txt");
            textArea.setText(helpText);
 
            JScrollPane scrollPane = new JScrollPane(textArea);
            helpPanel.add(scrollPane, BorderLayout.CENTER);
 
            textArea.setCaretPosition(0);
            helpPanel.setLocationRelativeTo(null);
            helpPanel.setVisible(true);  
        }
 
        /** Attempts to open a specified file, read what is in it,  and return the contents a single string 
        * Returns an empty string if the file is not found.
        * @param fileName The name of the file to be read.
        * @return The contents of the file as a single string.
        */
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
     * <p>
     * Action to change the language of the ANDIE application.
     * </p>
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

 

    
