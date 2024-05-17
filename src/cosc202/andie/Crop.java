// package cosc202.andie;

// import java.awt.Graphics;
// import java.awt.Point;
// import java.awt.image.*;

// public class Crop implements ImageOperation, java.io.Serializable{

//     private int x1, y1, x2, y2;
    
//     CropImage(Point p1, Point p2){
//         this.x1 = p1.getX();
//         this.y1 = p1.getY();
//         this.x2 = p2.getX();
//         this.y2 = p2.getY();
//     }

//     public BufferedImage apply(BufferedImage input) {
//         BufferedImage newImg;
//         BufferedImgage img = input;

//         try{
//             int width = x2 - x1;
//             int height = y2 - y1;

//             if(width < 0 || height < 0){
//                 throw new IllegalArgumentException("Invalid crop area");
//                 return input;
//             }

//             img = input.getSubimage(x1, y1, width, height);

//             newImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            
//             Graphics g = newImg.getGraphics();
//             g.drawImage(img, 0, 0, null);

//         }catch(NullPointerException e){
//             UserMessage.display("No image loaded");
//         }

//         return newImg;
//     }
// }