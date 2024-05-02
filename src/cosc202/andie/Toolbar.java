
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
        toolBar.setPreferredSize(new Dimension(200, 35));

        // Create an instance of Actions classes needed
        FileActions fileActions = new FileActions(bundle);
        EditActions editActions = new EditActions(bundle);
        ViewActions viewActions = new ViewActions(bundle);
        HelpActions helpActions = new HelpActions(bundle);

        // Add Exit to toolbar (DELETE LATER)
        ImageIcon exitIcon = new ImageIcon((Path.of("src/cosc202/andie/toolbarImages/bee.png")).toString());
        Image scaledExitImage = exitIcon.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
        exitIcon = new ImageIcon(scaledExitImage);

        JButton exitButton = new JButton(fileActions.new FileExitAction(
                "Exit", exitIcon, bundle.getString("ExitDesc"), null));
        toolBar.add(exitButton);

        // Add Open to toolbar
        ImageIcon openIcon = new ImageIcon((Path.of("src/cosc202/andie/toolbarImages/open.png")).toString());
        Image scaledOpenImage = openIcon.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
        openIcon = new ImageIcon(scaledOpenImage);

        JButton openButton = new JButton(fileActions.new FileOpenAction(
                "Open", openIcon, bundle.getString("OpenDesc"), null));
        toolBar.add(openButton);

        // // Add Save to toolbar
        ImageIcon saveIcon = new ImageIcon((Path.of("src/cosc202/andie/toolbarImages/save.png")).toString());
        Image scaledSaveImage = saveIcon.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
        saveIcon = new ImageIcon(scaledSaveImage);

        JButton saveButton = new JButton(fileActions.new FileSaveAction(
                "Save", saveIcon, bundle.getString("SaveDesc"), null));
        toolBar.add(saveButton);

        // // Add SaveAs to toolbar
        ImageIcon saveAsIcon = new ImageIcon((Path.of("src/cosc202/andie/toolbarImages/bee.png")).toString());
        Image scaledSaveAsImage = saveAsIcon.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
        saveAsIcon = new ImageIcon(scaledSaveAsImage);

        JButton saveAsButton = new JButton(fileActions.new FileSaveAsAction(
                "Save As", saveAsIcon, bundle.getString("SaveAsDesc"), null));
        toolBar.add(saveAsButton);

        // // Add Undo to toolbar
        ImageIcon undoIcon = new ImageIcon((Path.of("src/cosc202/andie/toolbarImages/undo.png")).toString());
        Image scaledUndoImage = undoIcon.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
        undoIcon = new ImageIcon(scaledUndoImage);

        JButton undoButton = new JButton(editActions.new UndoAction(
                "Undo", undoIcon, bundle.getString("UndoDesc"), null));
        toolBar.add(undoButton);

        // // Add Redo to toolbar
        ImageIcon redoIcon = new ImageIcon((Path.of("src/cosc202/andie/toolbarImages/redo.png")).toString());
        Image scaledRedoImage = redoIcon.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
        redoIcon = new ImageIcon(scaledRedoImage);

        JButton redoButton = new JButton(editActions.new RedoAction(
                "Redo", redoIcon, bundle.getString("RedoDesc"), null));
        toolBar.add(redoButton);

        // // Add ZoomIn to toolbar
        ImageIcon zoomInIcon = new ImageIcon((Path.of("src/cosc202/andie/toolbarImages/zoomIn.png")).toString());
        Image scaledZoomInImage = redoIcon.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
        zoomInIcon = new ImageIcon(scaledZoomInImage);

        JButton zoomInButton = new JButton(viewActions.new ZoomInAction(
                "Zoom In", zoomInIcon, bundle.getString("ZoomInDesc"), null));
        toolBar.add(zoomInButton);

        // // Add ZoomOut to toolbar
        ImageIcon zoomOutIcon = new ImageIcon((Path.of("src/cosc202/andie/toolbarImages/zoonOut.png")).toString());
        Image scaledZoomOutImage = redoIcon.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
        zoomOutIcon = new ImageIcon(scaledZoomOutImage);

        JButton zoomOutButton = new JButton(viewActions.new ZoomOutAction(
                "Zoom Out", zoomOutIcon, bundle.getString("ZoomOutDesc"), null));
        toolBar.add(zoomOutButton);

        // // Add HelpGuide to toolbar
        ImageIcon helpGuideIcon = new ImageIcon((Path.of("src/cosc202/andie/toolbarImages/bee.png")).toString());
        Image scaledHelpGuideImage = redoIcon.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
        helpGuideIcon = new ImageIcon(scaledHelpGuideImage);

        JButton helpGuideButton = new JButton(helpActions.new HelpGuideAction(
                "Andie Guide", helpGuideIcon, bundle.getString("AndieGuideDesc"), null));
        toolBar.add(helpGuideButton);

        return toolBar;
    }
}