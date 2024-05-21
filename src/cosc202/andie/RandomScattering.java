package cosc202.andie;

import java.awt.image.BufferedImage;

public class RandomScattering implements ImageOperation, java.io.Serializable {

    private int scatterAmount;

    public RandomScattering(int scatterAmount) {
        this.scatterAmount = scatterAmount;
    }

    public BufferedImage apply(BufferedImage input) {
        BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null), input.isAlphaPremultiplied(), null);

        for (int y = 0; y < input.getHeight(); y++) {
            for (int x = 0; x < input.getWidth(); x++) {
                int newX = x + (int) (Math.random() * scatterAmount * 2 - scatterAmount);
                int newY = y + (int) (Math.random() * scatterAmount * 2 - scatterAmount);

                if (newX >= 0 && newX < input.getWidth() && newY >= 0 && newY < input.getHeight()) {
                    output.setRGB(newX, newY, input.getRGB(x, y));
                }
            }
        }
        return output;
    }

    
}
