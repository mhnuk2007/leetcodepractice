package tree.advanced;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode 112 - Path Sum
 *
 * <p>Given the root of a binary tree and an integer targetSum, return true if the
 * tree has a root-to-leaf path such that adding up all the values along the path
 * equals targetSum. A leaf is a node with no children.
 *
 * <p>Pattern: DFS (pre-order) with running remainder
 * <ul>
 *   <li>Subtract current node value from target at each step</li>
 *   <li>At a leaf, check if remainder equals zero</li>
 * </ul>
 *
 * <p>Approaches:
 * <ol>
 *   <li>Recursive DFS  — clean, O(h) stack space</li>
 *   <li>Iterative DFS  — explicit stack with (node, remainder) pairs, O(h) space</li>
 * </ol>
 *
 * @author mhnuk2007
 * @version 1.0
 */
public class PathSum {

    // -------------------------------------------------------------------------
    // TreeNode definition
    // -------------------------------------------------------------------------

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {}

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // -------------------------------------------------------------------------
    // Approach 1: Recursive DFS
    // Time  : O(n) — visits every node in worst case
    // Space : O(h) — recursion stack, O(log n) balanced, O(n) skewed
    // -------------------------------------------------------------------------

    /**
     * Returns true if the tree has a root-to-leaf path summing to targetSum.
     *
     * <p>Subtracts the current node's value from targetSum at each step.
     * At a leaf, checks if the remaining target equals the leaf's value.
     *
     * @param root      root of the binary tree
     * @param targetSum target path sum
     * @return true if a valid root-to-leaf path exists
     */
    public static boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        if (root.left == null && root.right == null) return targetSum == root.val; // leaf check
        return hasPathSum(root.left,  targetSum - root.val)
                || hasPathSum(root.right, targetSum - root.val);
    }

    // -------------------------------------------------------------------------
    // Approach 2: Iterative DFS (explicit stack)
    // Time  : O(n)
    // Space : O(h) — stack holds at most h (node, remainder) pairs
    // -------------------------------------------------------------------------

    /**
     * Node + remaining sum pair for iterative DFS stack.
     *
     * <p>Avoids parallel stacks — keeps node and remainder tightly coupled,
     * eliminating any risk of index mismatch. Scales cleanly to Path Sum II (LC 113).
     */
    static class Pair {
        TreeNode node;
        int remainder;

        Pair(TreeNode node, int remainder) {
            this.node = node;
            this.remainder = remainder;
        }
    }

    /**
     * Returns true if the tree has a root-to-leaf path summing to targetSum
     * using an iterative DFS with a single Pair stack.
     *
     * <p>Each stack entry pairs a node with its remaining target sum.
     * At each leaf, the remainder is checked against zero.
     *
     * @param root      root of the binary tree
     * @param targetSum target path sum
     * @return true if a valid root-to-leaf path exists
     */
    public static boolean hasPathSum2(TreeNode root, int targetSum) {
        if (root == null) return false;
        Deque<Pair> stack = new ArrayDeque<>();
        stack.push(new Pair(root, targetSum - root.val));

        while (!stack.isEmpty()) {
            Pair curr = stack.pop();
            TreeNode node = curr.node;
            int remainder = curr.remainder;

            if (node.left == null && node.right == null && remainder == 0) return true;

            if (node.right != null) stack.push(new Pair(node.right, remainder - node.right.val));
            if (node.left  != null) stack.push(new Pair(node.left,  remainder - node.left.val));
        }
        return false;
    }

    // -------------------------------------------------------------------------
    // Main — Test Cases
    // -------------------------------------------------------------------------

    public static void main(String[] args) {
        // Test Case 1: path 5->4->11->2 = 22 → true
        //         5
        //        / \
        //       4   8
        //      /   / \
        //     11  13   4
        //    /  \       \
        //   7    2       1
        TreeNode root1 = new TreeNode(5);
        root1.left = new TreeNode(4);
        root1.right = new TreeNode(8);
        root1.left.left = new TreeNode(11);
        root1.right.left = new TreeNode(13);
        root1.right.right = new TreeNode(4);
        root1.left.left.left = new TreeNode(7);
        root1.left.left.right = new TreeNode(2);
        root1.right.right.right = new TreeNode(1);
        System.out.println("Test 1 - Recursive : " + hasPathSum(root1, 22));  // true
        System.out.println("Test 1 - Iterative : " + hasPathSum2(root1, 22)); // true

        System.out.println();

        // Test Case 2: no path → false
        TreeNode root2 = new TreeNode(1,
                new TreeNode(2),
                new TreeNode(3));
        System.out.println("Test 2 - Recursive : " + hasPathSum(root2, 5));  // false
        System.out.println("Test 2 - Iterative : " + hasPathSum2(root2, 5)); // false

        System.out.println();

        // Test Case 3: null root → false
        System.out.println("Test 3 - Recursive : " + hasPathSum(null, 0));  // false
        System.out.println("Test 3 - Iterative : " + hasPathSum2(null, 0)); // false

        System.out.println();

        // Test Case 4: single node equals target → true
        System.out.println("Test 4 - Recursive : " + hasPathSum(new TreeNode(1), 1));  // true
        System.out.println("Test 4 - Iterative : " + hasPathSum2(new TreeNode(1), 1)); // true

        System.out.println();

        // Test Case 5: single node does not equal target → false
        System.out.println("Test 5 - Recursive : " + hasPathSum(new TreeNode(1), 2));  // false
        System.out.println("Test 5 - Iterative : " + hasPathSum2(new TreeNode(1), 2)); // false

        System.out.println();

        // Test Case 6: negative values in path
        TreeNode root6 = new TreeNode(-2, null, new TreeNode(-3));
        System.out.println("Test 6 - Recursive : " + hasPathSum(root6, -5));  // true
        System.out.println("Test 6 - Iterative : " + hasPathSum2(root6, -5)); // true
    }
}