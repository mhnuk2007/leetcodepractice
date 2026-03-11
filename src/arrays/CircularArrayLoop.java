package arrays;

/**
 * LeetCode 457: Circular Array Loop
 * Difficulty: Medium
 * <p>
 * You are given a circular array nums of positive and negative integers.
 * If a number k at an index is positive, move forward k steps.
 * If it's negative, move backward |k| steps.
 * <p>
 * Determine if there is a loop (cycle) in nums.
 * A cycle must:
 *   1. Start and end at the same index.
 *   2. Have length > 1 (no self-loops).
 *   3. Move entirely in one direction (all forward or all backward).
 * <p>
 * Example 1:
 * Input: nums = [2,-1,1,2,2]   Output: true   (cycle: 0→2→3→0)
 * <p>
 * Example 2:
 * Input: nums = [-1,-2,-3,-4,-5,6]   Output: false
 * <p>
 * Example 3:
 * Input: nums = [1,-1,5,1,4]   Output: true
 * <p>
 * Constraints:
 * 1 <= nums.length <= 5000
 * -1000 <= nums[i] <= 1000
 * nums[i] != 0
 */
public class CircularArrayLoop {

    /**
     * Detects if a circular array has a valid cycle.
     *
     * Algorithm: Floyd's fast/slow pointer + path marking
     *
     * For each unvisited index:
     *   - Run slow (1 step) and fast (2 steps) pointers.
     *   - If they meet → valid cycle found.
     *   - If either hits -1 (direction change, self-loop, or dead node) → no cycle from here.
     *   - After failure, zero-out the entire traversed path so future iterations skip it.
     *
     * Time Complexity:  O(n) amortized — each node is zeroed at most once
     * Space Complexity: O(1) — no extra data structures
     */
    public boolean circularArrayLoop(int[] nums) {
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) continue;  // already proven dead — skip

            boolean isPositive = nums[i] > 0;
            int slow = i, fast = i;

            // Phase 1: Floyd's cycle detection
            do {
                slow = advance(nums, slow, isPositive, n);
                fast = advance(nums, fast, isPositive, n);
                if (fast != -1)
                    fast = advance(nums, fast, isPositive, n);
                if (slow == -1 || fast == -1) break;
            } while (slow != fast);

            // Cycle exists only if both pointers met at a valid index
            if (slow != -1 && slow == fast) return true;

            // Phase 2: Path marking — zero out dead path to avoid re-walking
            int node = i;
            while (nums[node] != 0 && (nums[node] > 0) == isPositive) {
                int next = nextIndex(nums, node, n);
                nums[node] = 0;
                node = next;
            }
        }

        return false;
    }

    /**
     * Advances one step from idx in the given direction.
     * Returns -1 if the move is invalid:
     *   - idx is already -1 (propagate sentinel)
     *   - next node is zeroed (dead/visited node)
     *   - direction changes at next node
     *   - self-loop (next == idx)
     */
    private int advance(int[] nums, int idx, boolean isPositive, int n) {
        if (idx == -1) return -1;
        int next = nextIndex(nums, idx, n);
        if (nums[next] == 0) return -1;                         // dead node
        if ((nums[next] > 0) != isPositive || next == idx) return -1; // direction change or self-loop
        return next;
    }

    /**
     * Computes the raw next index with circular wrap-around.
     * Double-mod handles negative steps correctly in Java.
     * Used by both advance() and path marking (never returns -1).
     */
    private int nextIndex(int[] nums, int idx, int n) {
        return ((idx + nums[idx]) % n + n) % n;
    }

    // -------------------------------------------------------------------------
    // Main — test cases
    // -------------------------------------------------------------------------
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

        // Test Case 7: next of index 1 is self-loop, no valid cycle
        int[] nums7 = {1, 2};
        System.out.println("Test 7: " + solution.circularArrayLoop(nums7)); // Expected: false

        // Test Case 8: Mixed direction kills all candidates
        int[] nums8 = {-2, 1, -1, -2, -2};
        System.out.println("Test 8: " + solution.circularArrayLoop(nums8)); // Expected: false

        // Test Case 9: Path circles back to start, zeroed node guard tested
        int[] nums9 = {1, 1, 1, 1, -1};
        System.out.println("Test 9: " + solution.circularArrayLoop(nums9)); // Expected: false
    }
}