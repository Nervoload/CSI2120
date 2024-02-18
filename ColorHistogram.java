/*
 * Felipe Garcia Affonso 300290722
 * John Surette 300307306
 */

import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

public class ColorHistogram {

    ColorImage image;
    double[] histogram;
    int bins;
    int bits;

    public ColorHistogram (int d) {
        bits = d;
        bins = (int) Math.pow(2,(d * 3));
        histogram = new double[bins];

        for (int i = 0; i < histogram.length; i++) {
            histogram[i] = 0;
        }
    }

    public ColorHistogram (String filename) {
        try {
            // read file
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            // data1 is the bin amount
            String data1 = scanner.nextLine();
            // data2 is the histogram
            String data2 = scanner.nextLine();
            String[] stringHistogram = data2.split(" ");

            this.bins = Integer.parseInt(data1);
            this.histogram = new double[bins];

            for (int i = 0; i < stringHistogram.length; i++) {
                histogram[i] = Double.parseDouble(stringHistogram[i]);
            }

            scanner.close();
          } catch (FileNotFoundException e) {
            //e.printStackTrace();
          }
    }

    public void setImage(ColorImage image) {
        image.reduceColor(bits);

        int[][][] pixel = image.getPixels();

        for (int i = 0; i < pixel.length; i++) {
            for (int j = 0; j < pixel[i].length; j++) {
                //System.out.println(pixel[i][j][0] +" "+ pixel[i][j][1] +" "+ pixel[i][j][2]);
                int index = (pixel[i][j][0] << (2 * bits)) + (pixel[i][j][1] << bits) + pixel[i][j][2];
                histogram[index] += 1;
            }
        }
    }

    public double[] getHistogram() {
        return histogram;
    }

    public double compare(ColorHistogram hist) {
        double[] h2 = hist.getHistogram();
        double returnV = 0;

        int n;

        if (h2.length > histogram.length) {
            n = histogram.length;
        } else {
            n = h2.length;
        }

        for (int i = 0; i < n; i++) {
            returnV += Math.abs(histogram[i] - h2[i]);
        }

        return returnV;

    }

    public void save (String filename) {
        try {
            FileWriter myWriter = new FileWriter(filename + ".txt");
            myWriter.write(histogram.length + "\n");

            for (int i = 0; i < (histogram.length - 1); i++) {
                myWriter.write(histogram[i] + " ");
            }

            myWriter.write( (int) histogram[histogram.length - 1]);

            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
