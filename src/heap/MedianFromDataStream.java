package heap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * LeetCode 295 - Find Median From Data Stream
 * Demonstrates finding the median from a data stream using a brute-force approach.
 *
 * <p>
 * This implementation stores all incoming numbers in a list and sorts the list
 * every time the median is requested.
 * </p>
 *
 * <p><b>Example:</b></p>
 * <pre>
 * Input sequence: 1, 2, 3
 * Sorted: [1, 2, 3]
 * Median: 2
 *
 * Add 4:
 * Sorted: [1, 2, 3, 4]
 * Median: (2 + 3) / 2 = 2.5
 * </pre>
 *
 * <p><b>Time Complexity:</b></p>
 * <ul>
 *     <li>addNum → O(1)</li>
 *     <li>findMedian → O(n log n) (due to sorting)</li>
 * </ul>
 *
 * <p><b>Space Complexity:</b> O(n)</p>
 */
public class MedianFromDataStream {

    public static void main(String[] args) {

        // Create MedianFinder instance
        MedianFinder obj1 = new MedianFinder();

        // Add elements to data stream
        obj1.addNum(1);
        obj1.addNum(2);
        obj1.addNum(3);

        // Find median after inserting 3 elements
        double median1 = obj1.findMedian();
        System.out.println("median1: " + median1); // Expected: 2.0

        // Add another element
        obj1.addNum(4);

        // Find median again
        double median2 = obj1.findMedian();
        System.out.println("median2: " + median2); // Expected: 2.5
    }
}

/**
 * A simple implementation of Median Finder using a list.
 *
 * <p>
 * Numbers are stored in an ArrayList. Each time the median is requested,
 * the list is sorted to compute the median.
 * </p>
 *
 * <p><b>Note:</b> This approach is not optimal for large data streams.
 * A better approach uses two heaps (min-heap + max-heap) for O(log n) insertion
 * and O(1) median retrieval.
 * </p>
 */
class MedianFinder {

    /** Stores all elements of the data stream */
    private final List<Integer> list;

    /**
     * Initializes the data structure.
     */
    public MedianFinder() {
        list = new ArrayList<>();
    }

    /**
     * Adds a number to the data stream.
     *
     * @param num the number to be added
     */
    public void addNum(int num) {
        list.add(num);
    }

    /**
     * Returns the median of all elements in the data stream.
     *
     * <p>
     * If the number of elements is:
     * <ul>
     *     <li>Odd → return middle element</li>
     *     <li>Even → return average of two middle elements</li>
     * </ul>
     * </p>
     *
     * @return the median value as a double
     */
    public double findMedian() {

        // Sort the list before computing median
        Collections.sort(list);

        int n = list.size();

        // Even number of elements
        if (n % 2 == 0) {
            return ((double) list.get(n / 2) + list.get(n / 2 - 1)) / 2;
        }

        // Odd number of elements
        return list.get(n / 2);
    }
}

/**
 * Usage:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double median = obj.findMedian();
 */