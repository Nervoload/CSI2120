/*
 * Felipe Garcia Affonso 300290722
 * John Surette 300307306
 */
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
// import JMF java API
import javax.imageio.ImageIO;

public class ColorImage {

    private int weight;
    private int height;
    private int depth; // number of bits per pixel
    private int[][][] pixels;

    
    public ColorImage(String filename) {
        try {
            BufferedImage img = ImageIO.read(new File(filename));
            weight = img.getWidth();
            height = img.getHeight();
            depth = img.getColorModel().getPixelSize();

            pixels = new int[weight][height][depth];

            for (int i = 0; i < weight; i++){
                for (int j = 0; j < height; j++) {
                    int pixel = img.getRGB(i, j);
                    int red = (pixel >> 16) & 0xff;
                    int green = (pixel >> 8) & 0xff;
                    int blue = (pixel) & 0xff;
                    pixels[i][j][0] = red;
                    pixels[i][j][1] = green;
                    pixels[i][j][2] = blue;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reduceColor(int d) {
        for (int i = 0; i < weight; i++){
            for (int j = 0; j < height; j++){
                //System.out.println(pixels[i][j][0]);
                pixels[i][j][0] = pixels[i][j][0] >> (8 - d);
                pixels[i][j][1] = pixels[i][j][1] >> (8 - d);
                pixels[i][j][2] = pixels[i][j][2] >> (8 - d);
            }
        }

        depth = d;
    }

    public int[][][] getPixels() {
        return pixels;
    }

    public int getDepth() {
        return depth;
    }
}
