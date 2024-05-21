package cosc202.andie;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.*;

/**
 * <p>
 * BufferedImage Operation to for image convolution
 * </p>
 * 
 * <p>
 * This takes an image with a filter applied to it and applies and image
 * convolution so the convolution based filters are applied to the edge
 * </p>
 * 
 * @author Sebastian Chkourko
 * @version 1.0
 */
public class AndieConvolveOp implements BufferedImageOp {
    private Kernel kernel;
    private boolean offset;
    private int midVal = 127;

    /**
     * <p>
     * Construct AndieConvolveOp with a given kernel.
     * </p>
     * 
     * @param kernel The given {@code kernel}.
     */
    public AndieConvolveOp(Kernel kernel) {
        this(kernel, false);
    }

    /**
     * <p>
     * Construct AndieConvolveOp with a given kernel.
     * </p>
     * 
     * @param kernel The given {@code kernel}.
     * @param offset Whether the pixel values should be offset.
     * @see Kernel
     */
    AndieConvolveOp(Kernel kernel, boolean offset) {
        this.kernel = kernel;
        this.offset = offset;
    }

    public BufferedImage filter(BufferedImage src, BufferedImage dst) {
        if (src == null)
            throw new NullPointerException("src image is null");
        if (src == dst)
            throw new IllegalArgumentException("src image cannot be the same as the dst image");

        return filter(src, dst, 0, 0, src.getWidth(), src.getHeight());
    }

    /***
     * <p>
     * Performs a convolution on a {@code BufferedImage} across a given selection of
     * pixels.
     * </p>
     * 
     * 
     * @param src    The {@code BufferedImage} to filter.
     * @param dst    The destination for the filtered {@code BufferedImage}.
     * @param startX The X position at the start of the selection (inclusive).
     * @param startY The Y position at the start of the selection (inclusive).
     * @param endX   The X position at the end of the selection (exclusive).
     * @param endY   The Y position at the end of the selection (exclusive).
     * 
     * @return The filtered {@code BufferedImage}.
     * 
     * @throws NullPointerException     if {@code src} is null.
     * @throws IllegalArgumentException if {@code src} equals {@code dst}.
     */
    public BufferedImage filter(BufferedImage src, BufferedImage dst, int startX, int startY, int endX, int endY) {
        if (src == null)
            throw new NullPointerException("src image is null");
        if (src == dst)
            throw new IllegalArgumentException("src image cannot be the same as the dst image");

        // Get image information
        int height = src.getHeight();
        int width = src.getWidth();
        boolean alpha = src.getColorModel().hasAlpha();

        // Create arrays for the pixel value inputs and outputs
        int[] srcPixels = new int[height * width];
        int[] dstPixels = new int[height * width];

        // Get pixel values from src and put into srcPixels array
        src.getRGB(0, 0, width, height, srcPixels, 0, width);

        convolve(srcPixels, dstPixels, startX, startY, endX, endY, width, height, alpha);

        // Set dst pixel values from dstPixels array
        dst.setRGB(0, 0, width, height, dstPixels, 0, width);

        return dst;
    }

    /**
     * <p>
     * Perform the convolve operation on the image, taking pixel values from
     * srcPixels and outputting the computation to dstPixels.
     * </p>
     * 
     * @param srcPixels The pixels of the source image.
     * @param dstPixels The destination to output the convolve computation.
     * @param startX    X lower bound on selection (inclusive).
     * @param startY    Y lowe bound on selection (inclusive)
     * @param endX      X upper bound on selection (exclusive).
     * @param endY      Y upper bound on selection (exclusive).
     * @param hasAlpha  True if the image has an alpha channel; false otherwise.
     */
    private void convolve(int[] srcPixels, int[] dstPixels, int startX, int startY, int endX, int endY, int width,
            int height, boolean hasAlpha) {
        float[] matrix = kernel.getKernelData(null);

        int kWidth = kernel.getWidth();
        int radius = kWidth / 2;

        // Index for the pixel arrays
        int imgPos = 0;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (y < startY || y > endY || x < startX || x > endX) {
                    dstPixels[imgPos] = srcPixels[imgPos];
                    imgPos++;
                    continue;
                }

                float a = 0, r = 0, g = 0, b = 0;

                // Index for the matrix array
                int matrixPos = 0;

                for (int ky = -radius; ky <= radius; ky++) {
                    for (int kx = -radius; kx <= radius; kx++) {
                        int argbIndex;

                        // Edge handling
                        if (y + ky < 0) {
                            if (x + kx < 0)
                                argbIndex = imgPos; // Top L corner
                            else if (x + kx >= width)
                                argbIndex = width - 1; // Top R corner
                            else
                                argbIndex = imgPos + kx; // First row
                        } else if (y + ky >= height) {
                            if (x + kx < 0)
                                argbIndex = imgPos; // Bottom L corner
                            else if (x + kx >= width)
                                argbIndex = width * height - 1; // Bottom R corner
                            else
                                argbIndex = imgPos + kx; // Last row
                        } else if (x + kx < 0 || x + kx >= width)
                            argbIndex = imgPos + (ky * width); // First or last column
                        else
                            argbIndex = imgPos + kx + (ky * width); // Any other pixels

                        Color srcColour = new Color(srcPixels[argbIndex], hasAlpha);

                        if (hasAlpha)
                            a += matrix[matrixPos] * (srcColour.getAlpha());
                        r += matrix[matrixPos] * (srcColour.getRed());
                        g += matrix[matrixPos] * (srcColour.getGreen());
                        b += matrix[matrixPos] * (srcColour.getBlue());

                        matrixPos++;
                    }
                }

                Color dstColor;

                int intr = (int) Math.round(r);
                int intg = (int) Math.round(g);
                int intb = (int) Math.round(b);

                // Handling offset
                if (offset) {
                    intr += midVal;
                    intg += midVal;
                    intb += midVal;

                }
                // Handling values out of range
                if (intr > 255)
                    intr = 255;
                if (intr < 0)
                    intr = 0;
                if (intg > 255)
                    intg = 255;
                if (intg < 0)
                    intg = 0;
                if (intb > 255)
                    intb = 255;
                if (intb < 0)
                    intb = 0;

                if (hasAlpha) {
                    int inta = (int) Math.round(a);

                    // Handling offset
                    if (offset)
                        inta += midVal;

                    // Handling values out of range
                    if (inta > 255)
                        inta = 255;
                    if (inta < 0)
                        inta = 0;

                    dstColor = new Color(intr, intg, intb, inta);
                } else
                    dstColor = new Color(intr, intg, intb);

                dstPixels[imgPos] = dstColor.getRGB();
                imgPos++;
            }
        }

    }

    /**
     * <p>
     * This operation has no {@code RenderingHints} set, and so this returns null.
     * </p>
     * 
     * @return This operation's {@code RenderingHints}.
     */
    public RenderingHints getRenderingHints() {
        return null;
    }

    /**
     * <p>
     * Creates a zeroed destination image with the correct size and number of bands,
     * given the {@code src}.
     * </p>
     * 
     * @param src    The {@code BufferedImage} to be filtered.
     * @param destCM The {@code ColorModel} of the destination, if null, the
     *               {@code ColorModel} of the {@code src} is used.
     * @return The zeroed destination image.
     */
    public BufferedImage createCompatibleDestImage(BufferedImage src, ColorModel destCM) {
        if (destCM == null)
            destCM = src.getColorModel();

        return new BufferedImage(destCM, src.getData().createCompatibleWritableRaster(src.getWidth(), src.getHeight()),
                destCM.isAlphaPremultiplied(), null);
    }

    /**
     * <p>
     * Gets the bounding box of the filtered destination image.
     * </p>
     * 
     * @param src The {@code BufferedImage} to be filtered.
     * @return The {@code Rectangle2D} representing the bounding box of the
     *         destination image.
     */
    public Rectangle2D getBounds2D(BufferedImage src) {
        return new Rectangle(0, 0, src.getWidth(), src.getHeight());
    }

    /**
     * <p>
     * Gets the location of the destination point given a point in the source. If
     * dstPt is not null, it will be used to store the {@code Point2D} value.
     * </p>
     * 
     * <p>
     * The dstPt will be equal to the dstPt.
     * </p>
     * 
     * @param srcPt The {@code Point2D} representing the point in the source image.
     * @param dstPt The {@code Point2D} to store the result in.
     * @return The {@code Point2D} of the destination point.
     * 
     */
    public Point2D getPoint2D(Point2D srcPt, Point2D dstPt) {
        if (dstPt == null) {
            dstPt = new Point2D.Float();
        }
        dstPt.setLocation(srcPt.getX(), srcPt.getY());

        return dstPt;
    }

}
