import java.awt.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
    Time complexity is O(n^2) due to hash set search optimization. This brings the algorithm from
    O(n^3) in its naive form down to O(n^2). This approach I suspect might be optimal.
    Space complexity is O(n) due to the number of points that are being stored in the hash set.

    The algorithm works fine with the given example.
    It also works with the example in the driver code below.
*/

public class CountRectangles {

    public static int countRectangles(Point[] points) {
        int count = 0;

        // Add all points to a set for quick lookup (setup)
        // Complexity: O(n)
        Set<Point> pointSet = new HashSet<>(Arrays.asList(points));

        // Check each pair of points (alongside with their "twin" counterparts)
        // to see if they form a rectangle
        // Complexity: O(n^2)
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                Point p1 = points[i];
                Point p2 = points[j];
                if (p1.x == p2.x || p1.y == p2.y) {
                    continue; // Opposite corners of the rectangle are on different rows and columns
                }
                Point p3 = new Point(p1.x, p2.y); // Counterparts used for the rectangle test
                Point p4 = new Point(p2.x, p1.y);

                // Hash set lookup time complexity is O(1), assuming a good hash function is used
                if (pointSet.contains(p3) && pointSet.contains(p4)) {
                    count++; // Rectangle found (twice)
                }
            }
        }
        return count / 2; // Make sure rectangles are counted only once
    }

    public static void main(String[] args) {
        Point[] points = {
                new Point(4, 1), new Point(5, 5), new Point(4, 2),
                new Point(2, 1), new Point(5, 2), new Point(5, 1),
                new Point(2, 2), new Point(4, 5)
        };
        int rectangleCount = countRectangles(points);
        System.out.println("Number of rectangles: " + rectangleCount);
    }
}
