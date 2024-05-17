// package cosc202.andie;

// public abstract class Selection implements ImageOperation, java.io.Serializable {

//     private int startX, endX, startY, endY;

//     public void setPoints(double x, double y) {
//         setPoints((int) x, (int) y);
//     }
    
//     public void setStartX(int startX) {
//         this.startX = startX;
//     }

//     public void setEndX(int endX) {
//         this.endX = endX;
//     }

//     public void setStartY(int startY) {
//         this.startY = startY;
//     }

//     public void setEndY(int endY) {
//         this.endY = endY;
//     }

//     public int getStartX() {
//         return startX;
//     }

//     public int getEndX() {
//         return endX;
//     }

//     public int getStartY() {
//         return startY;
//     }

//     public int getEndY() {
//         return endY;
//     }

//     public void setPoints(int x, int y) {
//         this.setStartX(x);
//         this.setEndX(x);
//         this.setStartY(y);
//         this.setEndY(y);
//     }

//     public void setStartPoint(int x, int y) {
//         this.setStartX(x);
//         this.setStartY(y);
//     }

//     public void setStartPoint(double x, double y) {
//         setStartPoint((int) x, (int) y);
//     }

//     public void setEndPoint(int x, int y) {
//         this.setEndX(x);
//         this.setEndY(y);
//     }

//     public void setEndPoint(double x, double y) {
//         this.setEndPoint((int) x, (int) y);
//     }

//     public int getWidth() {
//         return getMaxX() - getMinX();
//     }

//     public int getHeight() {
//         return getMaxY() - getMinY();
//     }

//     public int getMinX() {
//         return Math.min(startX, endX);
//     }

//     public int getMaxX() {
//         return Math.max(startX, endX);
//     }

//     public int getMinY() {
//         return Math.min(startY, endY);
//     }

//     public int getMaxY() {
//         return Math.max(startY, endY);
//     }

// }