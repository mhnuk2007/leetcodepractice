package heap;

import java.util.PriorityQueue;

/**
 * Demonstrates usage of:
 * 1. Java built-in PriorityQueue (Min Heap & Max Heap)
 * 2. Custom MinHeap implementation
 * 3. Custom MaxHeap implementation
 * <p>
 * This class compares outputs to validate correctness of custom heap implementations.
 * <p>
 * Key Concepts:
 * - PriorityQueue (default: Min Heap)
 * - Custom comparator for Max Heap
 * - Heap operations: insert, pop, peek
 *
 * @author Honey
 */
public class Main {

    /**
     * Entry point of the program.
     * Demonstrates heap operations on sample input.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {

        // Input array
        int[] nums = {1, 2, 1, 2, 1, 2, 3, 1, 3, 2};

        // Custom heap implementations
        MaxHeap maxHeap = new MaxHeap();
        MinHeap minHeap = new MinHeap();

        // -------------------------------
        // 1. PriorityQueue as Min Heap
        // -------------------------------
        System.out.print("PQ MinHeap: ");

        // Default PriorityQueue = Min Heap
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

        // Insert elements
        for (int num : nums) {
            priorityQueue.offer(num); // preferred over add()
        }

        // Extract elements in ascending order
        while (!priorityQueue.isEmpty()) {
            System.out.print(priorityQueue.poll() + " ");
        }

        System.out.println();

        // -------------------------------
        // 2. PriorityQueue as Max Heap
        // -------------------------------
        System.out.print("PQ MaxHeap: ");

        // Custom comparator for Max Heap
        PriorityQueue<Integer> priorityQueueMax =
                new PriorityQueue<>((a, b) -> b - a);

        // Insert elements
        for (int num : nums) {
            priorityQueueMax.offer(num);
        }

        // Extract elements in descending order
        while (!priorityQueueMax.isEmpty()) {
            System.out.print(priorityQueueMax.poll() + " ");
        }

        System.out.println();

        // -------------------------------
        // 3. Custom Heaps (Min + Max)
        // -------------------------------

        // Insert into both heaps in single loop (optimized)
        for (int num : nums) {
            maxHeap.insert(num);
            minHeap.insert(num);
        }

        // Custom MinHeap (ascending order)
        System.out.print("Custom MinHeap: ");
        while (!minHeap.isEmpty()) {
            System.out.print(minHeap.pop() + " ");
        }

        System.out.println();

        // Custom MaxHeap (descending order)
        System.out.print("Custom MaxHeap: ");
        while (!maxHeap.isEmpty()) {
            System.out.print(maxHeap.pop() + " ");
        }
    }
}