/*
 * Felipe Garcia Affonso 300290722
 * John Surette 300307306
 */

import java.util.PriorityQueue;

public class SimilaritySearch {
    public static void main(String[] args) {
        String imagePath = "./queryImages/" + args[0];
        String dataSetPath = "./" + args[1];
        PriorityQueue<ImageDistance> minHeap = new PriorityQueue<ImageDistance>();

        ColorImage image = new ColorImage(imagePath);
        ColorHistogram imageHistogram = new ColorHistogram(3);

        imageHistogram.setImage(image);

        for (int i = 0; i < 5000; i++) {
            ColorHistogram h = new ColorHistogram(dataSetPath + "/" + i + ".jpg.txt");

            if (h.getHistogram() != null) {
                minHeap.add(new ImageDistance(i + ".jpg", imageHistogram.compare(h)));
            }
        }

        for (int i = 0; i < 5; i++) {
            System.out.println(minHeap.poll().toString());
        }
    }
}
