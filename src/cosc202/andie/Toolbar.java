package cosc202.andie;

import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JToolBar;

public class Toolbar {
    public static JToolBar createToolbar() {
        JToolBar toolBar = new JToolBar();
        
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        toolBar.add(exitButton);

        JButton openButton = new JButton("Open");
        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        toolBar.add(exitButton);

        return toolBar;
    }
}

