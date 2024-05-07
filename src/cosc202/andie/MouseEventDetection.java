package cosc202.andie;

import java.awt.*;
import java.awt.event.*;

import cosc202.andie.RectangleAreaSelector;

public class MouseEventDetection implements MouseListener, MouseMotionListener{

    private static MouseEventDetection med = new MouseEventDetection();

    private static ImagePanel image;

    private static RectangleAreaSelector rasTemp;

    private static RectangleAreaSelector rasFinal;

    private static boolean selecting;

    public static void setImage(ImagePanel input) {
        image = input;
    }
    
    public static void selecting(RectangleAreaSelector start) {
        rasTemp = start;
        rasFinal = rasTemp;
        selecting = true;
    }

    public static void endSelecting() {
        selecting = false;
    }

    public static MouseEventDetection getInstance() {
        return med;
    }
    
    public void mousePressed(MouseEvent e) {
        if (!selecting){
            selecting(rasTemp);
            setStartPoint(e.getPoint());
            image.getImage().apply(rasTemp);
            image.repaint();
            image.getParent().revalidate();
        }
        return;
    }

    public void mouseReleased(MouseEvent e) {
        if (selecting) {
            setEndPoint(e.getPoint());
            image.getImage().apply(rasFinal);
            image.repaint();
            image.getParent().revalidate();
            endSelecting();
        }
        return;
    }

    public void mouseDragged(MouseEvent e) {
        if (selecting) {
            setEndPoint(e.getPoint());
            image.getImage().apply(rasTemp);
            image.repaint();
            image.getParent().revalidate();
        }
    }

    public void mouseClicked(MouseEvent e) {
        if (selecting) {
            endSelecting();
        }
        return;
    }

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}

    public void mouseMoved(MouseEvent e) {}

    private int getX(Point p) {
        return Math.min(Math.max(0, (int)(p.getX() / (image.getZoom() * 0.01))), image.getImage().getWidth() - 1);

    }

    private int getY(Point p) {
        return Math.min(Math.max(0, (int)(p.getY() / (image.getZoom() * 0.01))), image.getImage().getHeight() - 1);
    }

    private void setStartPoint(Point p) {
        rasTemp.setStartPoint(getX(p), getY(p));
        rasFinal.setStartPoint(getX(p), getY(p));
    }

    private void setEndPoint(Point p) {
        rasTemp.setEndPoint(getX(p), getY(p));
        rasFinal.setEndPoint(getX(p), getY(p));
    }
}
