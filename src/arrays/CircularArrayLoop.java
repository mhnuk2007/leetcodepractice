package arrays;

/**
 * LeetCode 457: Circular Array Loop
 * <p>
 * You are given a circular array nums of positive and negative integers. A move from an index i is based on nums[i].
 * If nums[i] is positive, move forward nums[i] steps. If negative, move backward |nums[i]| steps.
 * The array is circular, so moving forward from the last element lands on the first, and vice versa.
 * <p>
 * A cycle is valid if it meets three conditions:
 * 1. It starts and ends at the same index.
 * 2. Its length is greater than 1.
 * 3. All moves within the cycle are in the same direction (all positive or all negative).
 * <p>
 * This solution uses an optimized approach that combines Floyd's Cycle Detection (fast/slow pointers)
 * with path marking to avoid re-checking dead-end paths, achieving an O(n) time complexity.
 */
public class CircularArrayLoop {

    /**
     * Detects if a valid cycle exists in the circular array.
     * <p>
     * The main loop iterates through each index `i` of the array. For each index, it attempts to find a cycle.
     * To avoid redundant checks, any path that is confirmed not to lead to a valid cycle is "zeroed out".
     * If `nums[i]` is 0, it means this index has been visited as part of a previous failed path, so we can skip it.
     *
     * @param nums The input circular array of integers.
     * @return true if a valid cycle exists, false otherwise.
     */
    public boolean circularArrayLoop(int[] nums) {
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                continue; // This index has been visited and is part of a non-cyclic or invalid path.
            }

            // Use fast and slow pointers to detect a cycle starting from index i.
            int slow = i, fast = i;
            boolean isForward = nums[i] > 0; // Determine the required direction for this path.

            // Phase 1: Floyd's cycle detection.
            do {
                slow = getNextIndex(nums, slow, isForward, n);
                fast = getNextIndex(nums, fast, isForward, n);
                if (fast != -1) {
                    fast = getNextIndex(nums, fast, isForward, n);
                }
            } while (slow != -1 && fast != -1 && slow != fast);

            // If a cycle is found (slow and fast met at a valid index), we're done.
            if (slow != -1 && slow == fast) {
                return true;
            }

            // Phase 2: No valid cycle found from index i. Mark the entire path as invalid (0)
            // to prevent re-checking these nodes in future iterations.
            int current = i;
            boolean pathDirection = nums[current] > 0;
            while (pathDirection == isForward && nums[current] != 0) {
                int next = getNextIndex(nums, current, isForward, n);
                nums[current] = 0; // Mark as visited and invalid.
                current = next;
                if (current == -1) break;
            }
        }

        return false;
    }

    /**
     * Calculates the next valid index in the path.
     *
     * @return The next index if the move is valid, otherwise -1.
     * A move is invalid if:
     * - The direction of movement changes.
     * - It results in a self-loop (a cycle of length 1).
     */
    private int getNextIndex(int[] nums, int currentIndex, boolean isForward, int n) {
        // Check if the direction of the next step is consistent with the path's direction.
        boolean nextIsForward = nums[currentIndex] > 0;
        if (isForward != nextIsForward) {
            return -1; // Direction changed, invalid path.
        }

        // Calculate the next index with wrap-around.
        // The `((currentIndex + nums[currentIndex]) % n + n) % n` formula correctly handles negative numbers.
        int nextIndex = ((currentIndex + nums[currentIndex]) % n + n) % n;

        // Check for self-loops.
        if (nextIndex == currentIndex) {
            return -1; // Self-loop, invalid cycle.
        }

        return nextIndex;
    }

    public static void main(String[] args) {
        CircularArrayLoop solution = new CircularArrayLoop();

        // Test Case 1: Forward cycle 0→2→3→0
        int[] nums1 = {2, -1, 1, 2, 2};
        System.out.println("Test 1: " + solution.circularArrayLoop(nums1)); // Expected: true

        // Test Case 2: Direction changes break all paths
        int[] nums2 = {-1, -2, -3, -4, -5, 6};
        System.out.println("Test 2: " + solution.circularArrayLoop(nums2)); // Expected: false

        // Test Case 3: Valid forward cycle exists
        int[] nums3 = {1, -1, 5, 1, 4};
        System.out.println("Test 3: " + solution.circularArrayLoop(nums3)); // Expected: true

        // Test Case 4: All forward, long cycle 0→2→4→1→3→0
        int[] nums4 = {2, 2, 2, 2, 2};
        System.out.println("Test 4: " + solution.circularArrayLoop(nums4)); // Expected: true

        // Test Case 5: Self-loop at index 0 (3%3=0), valid cycle at 1→2→1
        int[] nums5 = {3, 1, 2};
        System.out.println("Test 5: " + solution.circularArrayLoop(nums5)); // Expected: true

        // Test Case 6: Single element — can't form cycle of length > 1
        int[] nums6 = {1};
        System.out.println("Test 6: " + solution.circularArrayLoop(nums6)); // Expected: false
    }
}
