/*
 * Felipe Garcia Affonso 300290722
 * John Surette 300307306
 */

public class ImageDistance implements Comparable<ImageDistance> {
    public Double distance;
    public String fileName;

    ImageDistance(String fileName, Double distance) {
        this.distance = distance;
        this.fileName = fileName;
    }

    @Override
    public int compareTo(ImageDistance i) {
        return distance.compareTo(i.distance);
    }

    @Override
    public String toString() {
        return fileName + " " + distance;
    }
}
