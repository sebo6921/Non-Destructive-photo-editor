
package cosc202.andie;

import java.awt.*;
import java.awt.event.*;
import java.util.ResourceBundle;
import java.lang.ClassLoader;
import java.nio.file.*;

import javax.swing.*;

import cosc202.andie.ImagePanel.Mode;

/**
 * <p>
 * Toolbar class that creates and manages the application's toolbar.
 * </p>
 * 
 * <p>
 * The toolbar provides quick access to various actions like file operations,
 * editing, viewing, help, and drawing tools.
 * </p>
 * 
 * @author Jessica Fan
 * @version 1.0
 */
public class Toolbar {

        /** A resource bundle to change the language */
        private ResourceBundle bundle;

        /** JFrame that the toolbar is applied to */
        private static JFrame frame;

        /**
         * <p>
         * Creates the main toolbar with various action buttons.
         * </p>
         * 
         * @param bundle      the resource bundle for localization
         * @param drawingArea the drawing area for drawing actions
         * @param imagePanel  the image panel for image related actions
         * @return a JToolBar with the main toolbar buttons
         */
        public static JToolBar createToolbar(ResourceBundle bundle, DrawingArea drawingArea, ImagePanel imagePanel) {
                JToolBar toolBar = new JToolBar();
                toolBar.setPreferredSize(new Dimension(350, 35));

                // Create an instance of Actions classes needed
                FileActions fileActions = new FileActions(bundle);
                EditActions editActions = new EditActions(bundle);
                ViewActions viewActions = new ViewActions(bundle, imagePanel);
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

                // Add a button for crop mode
                ImageIcon cropIcon = new ImageIcon(
                                (Path.of("src/cosc202/andie/toolbarImages/crop.png")).toString());
                Image scaledCropImage = cropIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
                cropIcon = new ImageIcon(scaledCropImage);

                JButton cropButton = new JButton(viewActions.new CropAction(
                                null, cropIcon, bundle.getString("CropDesc"), null));
                toolBar.add(cropButton);

                // Add a button for drawing
                ImageIcon drawingIcon = new ImageIcon(
                                (Path.of("src/cosc202/andie/toolbarImages/drawing.png")).toString());
                Image scaledDrawingImage = drawingIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
                drawingIcon = new ImageIcon(scaledDrawingImage);

                JButton drawingButton = new JButton(drawingIcon);
                drawingButton.setToolTipText(bundle.getString("DrawingToolbar"));
                drawingButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                imagePanel.setMode(Mode.DRAWING);
                                JToolBar drawingToolbar = new JToolBar();
                                Toolbar.createDrawingToolbar(drawingToolbar, bundle, drawingArea);
                                JButton exitButton = new JButton(bundle.getString("DrawingExit"));
                                exitButton.addActionListener(new ActionListener() {
                                        public void actionPerformed(ActionEvent e) {
                                                frame.getContentPane().remove(drawingToolbar);
                                                frame.revalidate();
                                                frame.repaint();
                                                imagePanel.setMode(Mode.SELECTION);
                                        }
                                });
                                drawingToolbar.add(exitButton);
                                frame.getContentPane().add(drawingToolbar, BorderLayout.SOUTH);
                                frame.revalidate();
                                frame.repaint();
                        }
                });
                toolBar.add(drawingButton);

                toolBar.add(Box.createHorizontalGlue());

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

        /**
         * <p>
         * Creates the drawing toolbar with various drawing tools.
         * </p>
         * 
         * @param toolbar     the toolbar to which drawing tools will be added
         * @param bundle      the resource bundle for localization
         * @param drawingArea the drawing area where drawing actions are performed
         * @return a JToolBar with the drawing toolbar buttons
         */
        public static JToolBar createDrawingToolbar(JToolBar toolbar, ResourceBundle bundle, DrawingArea drawingArea) {

                JButton colourMenu = new JButton(bundle.getString("ColourMenu"));
                colourMenu.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                Color selectedColor = JColorChooser.showDialog((Component) e.getSource(),
                                                "Select a Colour", Color.BLACK);
                                if (selectedColor != null) {
                                        drawingArea.setSelectedColour(selectedColor);
                                }
                        }
                });

                JCheckBox fillOrOutline = new JCheckBox(bundle.getString("FillOrOutline"));
                fillOrOutline.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                drawingArea.setFillShape(fillOrOutline.isSelected());
                        }
                });

                ButtonGroup buttonGroup = new ButtonGroup();

                JRadioButton freeDrawButton = new JRadioButton(bundle.getString("FreeDraw"));
                freeDrawButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                drawingArea.setShape("Free");
                        }
                });
                buttonGroup.add(freeDrawButton);

                JRadioButton rectangleButton = new JRadioButton(bundle.getString("Rectangle"));
                rectangleButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                drawingArea.setShape("Rectangle");
                        }
                });
                buttonGroup.add(rectangleButton);

                JRadioButton ellipseButton = new JRadioButton(bundle.getString("Ellipse"));
                ellipseButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                drawingArea.setShape("Ellipse");
                        }
                });
                buttonGroup.add(ellipseButton);

                JRadioButton lineButton = new JRadioButton(bundle.getString("Line"));
                lineButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                drawingArea.setShape("Line");
                        }
                });
                buttonGroup.add(lineButton);

                JButton strokeWidth = new JButton(bundle.getString("StrokeWidth"));
                strokeWidth.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                SpinnerNumberModel strokeModel = new SpinnerNumberModel(1, 1, 10, 1);
                                JSpinner strokeWidthSpinner = new JSpinner(strokeModel);
                                JLabel message1 = new JLabel(bundle.getString("StrokeWidthMSG1"));
                                JLabel message2 = new JLabel(bundle.getString("StrokeWidthMSG2"));
                                JPanel panel = new JPanel();
                                panel.setPreferredSize(new Dimension(300, 70));
                                panel.add(message1);
                                panel.add(message2);
                                panel.add(strokeWidthSpinner);

                                int option = JOptionPane.showOptionDialog(null, panel,
                                                bundle.getString("StrokeWidth"),
                                                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null,
                                                null);

                                if (option == JOptionPane.OK_OPTION) {
                                        drawingArea.setStrokeWidth(strokeModel.getNumber().intValue());
                                }
                        }
                });

                toolbar.add(colourMenu);
                toolbar.add(strokeWidth);
                toolbar.add(fillOrOutline);
                toolbar.add(freeDrawButton);
                toolbar.add(rectangleButton);
                toolbar.add(ellipseButton);
                toolbar.add(lineButton);
                toolbar.add(Box.createHorizontalGlue());

                return toolbar;
        }

        public static void setFrame(JFrame frame) {
                Toolbar.frame = frame;
        }
}