package heap;

import java.util.PriorityQueue;

/**
 *
 * Demonstrates finding the median from a data stream using
 * an optimal Two-Heaps approach.
 *
 * <p>
 * This approach maintains:
 * <ul>
 *     <li>Max Heap (left) → stores smaller half of numbers</li>
 *     <li>Min Heap (right) → stores larger half of numbers</li>
 * </ul>
 * </p>
 *
 * <p><b>Key Idea:</b></p>
 * <ul>
 *     <li>Balance both heaps so that size difference ≤ 1</li>
 *     <li>Median depends on heap sizes</li>
 * </ul>
 *
 * <p><b>Time Complexity:</b></p>
 * <ul>
 *     <li>addNum → O(log n)</li>
 *     <li>findMedian → O(1)</li>
 * </ul>
 *
 * <p><b>Space Complexity:</b> O(n)</p>
 */
public class MedianFromDataStreamOptimal {

    public static void main(String[] args) {

        MedianFinderOptimal obj = new MedianFinderOptimal();

        obj.addNum(1);
        obj.addNum(2);
        obj.addNum(3);

        double median1 = obj.findMedian();
        System.out.println("median1: " + median1); // Expected: 2.0

        obj.addNum(4);

        double median2 = obj.findMedian();
        System.out.println("median2: " + median2); // Expected: 2.5
    }
}

/**
 * Optimized Median Finder using two heaps.
 */
class MedianFinderOptimal {

    /** Max Heap → stores smaller half (largest at top) */
    private final PriorityQueue<Integer> maxHeap;

    /** Min Heap → stores larger half (smallest at top) */
    private final PriorityQueue<Integer> minHeap;

    /**
     * Initializes both heaps.
     */
    public MedianFinderOptimal() {
        // Max heap (reverse order)
        maxHeap = new PriorityQueue<>((a, b) -> b - a);

        // Min heap (natural order)
        minHeap = new PriorityQueue<>();
    }

    /**
     * Adds a number into the data structure.
     *
     * <p>
     * Steps:
     * <ol>
     *     <li>Add to maxHeap</li>
     *     <li>Move top of maxHeap → minHeap</li>
     *     <li>Balance sizes if needed</li>
     * </ol>
     * </p>
     *
     * @param num number to be added
     */
    public void addNum(int num) {

        // Step 1: Add to maxHeap
        maxHeap.offer(num);

        // Step 2: Ensure ordering (maxHeap top <= minHeap top)
        minHeap.offer(maxHeap.poll());

        // Step 3: Balance heaps (maxHeap can have 1 extra element)
        if (minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

    /**
     * Returns the median of current data stream.
     *
     * <p>
     * Cases:
     * <ul>
     *     <li>If sizes equal → average of both heap tops</li>
     *     <li>If unequal → top of maxHeap</li>
     * </ul>
     * </p>
     *
     * @return median value
     */
    public double findMedian() {

        // Even number of elements
        if (maxHeap.size() == minHeap.size()) {
            return ((double) maxHeap.peek() + minHeap.peek()) / 2;
        }

        // Odd number of elements
        return maxHeap.peek();
    }
}