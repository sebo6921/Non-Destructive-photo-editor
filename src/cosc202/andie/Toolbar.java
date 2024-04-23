package cosc202.andie;

import java.awt.event.*;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JToolBar;

public class Toolbar {

    private ResourceBundle bundle;

    public static JToolBar createToolbar(ResourceBundle bundle) { 
        JToolBar toolBar = new JToolBar();
        
        // Create an instance of FileActions
        FileActions fileActions = new FileActions(bundle);

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        toolBar.add(exitButton);
        
        // Create an open button using FileOpenAction
        JButton openButton = new JButton(fileActions.new FileOpenAction(
        bundle.getString("Open"), null, bundle.getString("OpenDesc"), KeyEvent.VK_O)); 
        toolBar.add(openButton);

        

        return toolBar;
    }
}

