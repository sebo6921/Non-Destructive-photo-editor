// package cosc202.andie;

// import java.awt.*;
// import java.awt.Color;
// import java.awt.Graphics2D;
// import java.awt.image.BufferedImage;

// //import cosc202.andie.ImagePanel;

// public class AreaSelection extends Selection{

//     public AreaSelection() {};

//     public BufferedImage apply(BufferedImage input) {
//         BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null), input.isAlphaPremultiplied(), null);
//         Graphics2D g2d = output.createGraphics();
//         g2d.setStroke(new BasicStroke(1));
//         g2d.setColor(new Color(69, 69, 69, 69));
//         g2d.fill(new Rectangle(getMinX(), getMinY(), getMaxX() - getMinX(), getMaxY() - getMinY()));
//         g2d.dispose();

//         return output;
//     }
 
// }
