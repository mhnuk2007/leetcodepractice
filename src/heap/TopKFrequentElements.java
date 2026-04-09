package heap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * LeetCode 347 - Top K Frequent Elements
 * <p>
 * Problem:
 * Given an integer array nums and an integer k, return the k most frequent elements.
 * The answer may be returned in any order.
 * <p>
 * Approach: Frequency map + Min-heap of size k
 * Build a frequency map, then maintain a min-heap of size k keyed by frequency.
 * When the heap exceeds k elements, evict the least frequent — so only the
 * top k most frequent elements survive.
 * <p>
 * Example:
 * nums = [1,1,1,2,2,3], k = 2
 * freq map : {1=3, 2=2, 3=1}
 * heap after all inserts : [2,1]  (3 was evicted — lowest frequency)
 * result   : [1,2]
 * <p>
 * Time  : O(n log k) — n insertions, each heap operation costs log k
 * Space : O(n) — frequency map; O(k) — heap
 */
public class TopKFrequentElements {

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;
        int[] result = topKFrequent(nums, k);

        int[] nums2 = {1, 2, 1, 2, 1, 2, 3, 1, 3, 2};
        int k2 = 2;
        int[] result2 = topKFrequent(nums2, k2);

        int[] nums3 = {1};
        int k3 = 1;
        int[] result3 = topKFrequent(nums3, k3);

        System.out.println("Top " + k  + " frequent elements: " + Arrays.toString(result));
        System.out.println("Top " + k2 + " frequent elements: " + Arrays.toString(result2));
        System.out.println("Top " + k3 + " frequent elements: " + Arrays.toString(result3));
    }

    /**
     * Returns the k most frequent elements in nums.
     *
     * @param nums input array of integers
     * @param k    number of top frequent elements to return
     * @return array of k most frequent elements in descending frequency order
     */
    private static int[] topKFrequent(int[] nums, int k) {
        // Step 1: Build frequency map
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        // Step 2: Min-heap ordered by frequency — least frequent element at top
        PriorityQueue<Integer> pq =
                new PriorityQueue<>((a, b) -> Integer.compare(freq.get(a), freq.get(b)));

        // Step 3: Maintain heap of size k — evict least frequent when exceeded
        for (int key : freq.keySet()) {
            pq.offer(key);
            if (pq.size() > k) {
                pq.poll();
            }
        }

        // Step 4: Build result — poll gives ascending frequency, fill backwards
        //         so result[0] holds the most frequent element
        int[] result = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            result[i] = pq.poll();
        }

        return result;
    }
}