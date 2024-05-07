package cosc202.andie;

import java.awt.*;
import java.awt.image.BufferedImage;

import cosc202.andie.ImageOperation;

public abstract class RectangleAreaSelector implements ImageOperation, java.io.Serializable{

    private int startX, endX, startY, endY;

    public void setStartPoint(int x, int y) {
        startX = x;
        endX = startX;
        startY = y;
        endY = startY;
    }

    public void setEndPoint(int x, int y) {
        endX = x;
        endY = y;
    }
    
    public void setStartX(int startX) {
        this.startX = startX;
    }

    public void setEndX(int endX) {
        this.endX = endX;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }

    public void setEndY(int endY) {
        this.endY = endY;
    }

    public int getStartX() {
        return startX;
    }

    public int getEndX() {
        return endX;
    }

    public int getStartY() {
        return startY;
    }

    public int getEndY() {
        return endY;
    }

    public int getMinX() {
        return Math.min(startX, endX);
    }

    public int getMaxX() {
        return Math.max(startX, endX);
    }

    public int getMinY() {
        return Math.min(startY, endY);
    }

    public int getMaxY() {
        return Math.max(startY, endY);
    }

    public int getWidth() {
        return getMaxX() - getMinX();
    }

    public int getHeight() {
        return getMaxY() - getMinY();
    }

    public BufferedImage apply(BufferedImage image) {
        BufferedImage modifiedImage = new BufferedImage(image.getColorModel(), image.copyData(null), image.isAlphaPremultiplied(), null);
        Graphics2D g = modifiedImage.createGraphics();

        g.setStroke(new BasicStroke(1));
        g.setColor(new Color(0, 0, 255, 100));
        g.fill(new Rectangle(getMinX(), getMinY(), getMaxX(), getMaxY()));
        g.dispose();

        return modifiedImage;
    }

}