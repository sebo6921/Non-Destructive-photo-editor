
package cosc202.andie;

import java.awt.*;
import java.awt.event.*;
import java.util.ResourceBundle;
import java.lang.ClassLoader;
import java.nio.file.*;

import javax.swing.*;


public class Toolbar {

        private ResourceBundle bundle;

        public static JToolBar createToolbar(ResourceBundle bundle) {
                JToolBar toolBar = new JToolBar();
                toolBar.setPreferredSize(new Dimension(350, 35));

                // Create an instance of Actions classes needed
                FileActions fileActions = new FileActions(bundle);
                EditActions editActions = new EditActions(bundle);
                ViewActions viewActions = new ViewActions(bundle);
                HelpActions helpActions = new HelpActions(bundle);

                // Add Open to toolbar
                ImageIcon openIcon = new ImageIcon((Path.of("src/cosc202/andie/toolbarImages/open.png")).toString());
                Image scaledOpenImage = openIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
                openIcon = new ImageIcon(scaledOpenImage);

                JButton openButton = new JButton(fileActions.new FileOpenAction(
                                null, openIcon, bundle.getString("OpenDesc"), null));
                toolBar.add(openButton);

                // // Add Save to toolbar
                ImageIcon saveIcon = new ImageIcon((Path.of("src/cosc202/andie/toolbarImages/save.png")).toString());
                Image scaledSaveImage = saveIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
                saveIcon = new ImageIcon(scaledSaveImage);

                JButton saveButton = new JButton(fileActions.new FileSaveAction(
                                null, saveIcon, bundle.getString("SaveDesc"), null));
                toolBar.add(saveButton);

                // // Add SaveAs to toolbar
                ImageIcon saveAsIcon = new ImageIcon((Path.of("src/cosc202/andie/toolbarImages/bee.png")).toString());
                Image scaledSaveAsImage = saveAsIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
                saveAsIcon = new ImageIcon(scaledSaveAsImage);

                JButton saveAsButton = new JButton(fileActions.new FileSaveAsAction(
                                null, saveAsIcon, bundle.getString("SaveAsDesc"), null));
                toolBar.add(saveAsButton);

                // // Add Undo to toolbar
                ImageIcon undoIcon = new ImageIcon((Path.of("src/cosc202/andie/toolbarImages/undo.png")).toString());
                Image scaledUndoImage = undoIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
                undoIcon = new ImageIcon(scaledUndoImage);

                JButton undoButton = new JButton(editActions.new UndoAction(
                                null, undoIcon, bundle.getString("UndoDesc"), null));
                toolBar.add(undoButton);

                // // Add Redo to toolbar
                ImageIcon redoIcon = new ImageIcon((Path.of("src/cosc202/andie/toolbarImages/redo.png")).toString());
                Image scaledRedoImage = redoIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
                redoIcon = new ImageIcon(scaledRedoImage);

                JButton redoButton = new JButton(editActions.new RedoAction(
                                null, redoIcon, bundle.getString("RedoDesc"), null));
                toolBar.add(redoButton);

                // // Add ZoomIn to toolbar
                ImageIcon zoomInIcon = new ImageIcon(
                                (Path.of("src/cosc202/andie/toolbarImages/zoomIn.png")).toString());
                Image scaledZoomInImage = zoomInIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
                zoomInIcon = new ImageIcon(scaledZoomInImage);

                JButton zoomInButton = new JButton(viewActions.new ZoomInAction(
                                null, zoomInIcon, bundle.getString("ZoomInDesc"), null));
                toolBar.add(zoomInButton);

                // // Add ZoomOut to toolbar
                ImageIcon zoomOutIcon = new ImageIcon(
                                (Path.of("src/cosc202/andie/toolbarImages/zoomOut.png")).toString());
                Image scaledZoomOutImage = zoomOutIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
                zoomOutIcon = new ImageIcon(scaledZoomOutImage);

                JButton zoomOutButton = new JButton(viewActions.new ZoomOutAction(
                                null, zoomOutIcon, bundle.getString("ZoomOutDesc"), null));
                toolBar.add(zoomOutButton);

                

                // Add a button for selection mode
                JButton selectionModeButton = new JButton("Selection Mode");
                selectionModeButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                //imagePanel.setCurrentMode(Mode.SELECTION);
                        }
                });
                toolBar.add(selectionModeButton);

                // Add a button for drawing mode
                JButton drawingModeButton = new JButton("Drawing Mode");
                drawingModeButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                //imagePanel.setCurrentMode(Mode.DRAWING);
                        }
                });
                toolBar.add(drawingModeButton);

                // // Add HelpGuide to toolbar
                ImageIcon helpGuideIcon = new ImageIcon(
                                (Path.of("src/cosc202/andie/toolbarImages/help.png")).toString());
                Image scaledHelpGuideImage = helpGuideIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
                helpGuideIcon = new ImageIcon(scaledHelpGuideImage);

                JButton helpGuideButton = new JButton(helpActions.new HelpGuideAction(
                                null, helpGuideIcon, bundle.getString("AndieGuideDesc"), null));
                toolBar.add(helpGuideButton);

                return toolBar;
        }

        public static JToolBar createDrawingToolbar(JToolBar toolbar, ResourceBundle bundle) {
                toolbar.setPreferredSize(new Dimension(350, 35));
                
                JButton exitButton = new JButton(bundle.getString("DrawingExit"));
                exitButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            // Handle exit drawing mode action
                            // You can implement the logic to exit drawing mode here
                        }
                    });

                JButton colourMenu = new JButton(bundle.getString("ColourMenu"));
                colourMenu.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                        JColorChooser.showDialog((Component) e.getSource(), "Select a Colour", Color.BLACK);
                }
                });
                toolbar.add(colourMenu);

                DrawingActions drawingActions = new DrawingActions(bundle);
                JCheckBox fillOrOutline = new JCheckBox(bundle.getString("FillOrOutline"));
                fillOrOutline.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                drawingActions.setFillShape(fillOrOutline.isSelected());
                        }
                });
                toolbar.add(fillOrOutline);


                
                
                return toolbar;
        }
}