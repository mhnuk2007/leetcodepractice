package tree;

/**
 * LC 104 — Maximum Depth of Binary Tree
 *
 * Problem: Given the root of a binary tree, return its maximum depth —
 * the number of nodes along the longest path from root to a leaf.
 *
 * Approach: Bottom-up recursion.
 *   Ask left subtree: what is your height?
 *   Ask right subtree: what is your height?
 *   Return 1 + max(left, right) — this node adds one level.
 *
 * Base case: null node has height 0.
 *
 * Time Complexity : O(n) — every node visited once
 * Space Complexity: O(h) — recursion stack depth equals tree height
 *                   O(log n) balanced, O(n) skewed
 */
public class MaxDepth {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    public static void main(String[] args) {

        MaxDepth sol = new MaxDepth();

        // Test 1: Full balanced tree — depth 3
        System.out.println("=== Test 1: Full balanced tree ===");
        TreeNode root1 = new TreeNode(1);
        root1.left  = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.left.left   = new TreeNode(4);
        root1.left.right  = new TreeNode(5);
        root1.right.left  = new TreeNode(6);
        root1.right.right = new TreeNode(7);
        System.out.println(sol.maxDepth(root1)); // 3

        // Test 2: LeetCode example [3, 9, 20, null, null, 15, 7]
        System.out.println("\n=== Test 2: LeetCode example ===");
        TreeNode root2 = new TreeNode(3);
        root2.left  = new TreeNode(9);
        root2.right = new TreeNode(20);
        root2.right.left  = new TreeNode(15);
        root2.right.right = new TreeNode(7);
        System.out.println(sol.maxDepth(root2)); // 3

        // Test 3: Left-skewed — depth equals node count
        System.out.println("\n=== Test 3: Left-skewed ===");
        TreeNode root3 = new TreeNode(1);
        root3.left = new TreeNode(2);
        root3.left.left = new TreeNode(3);
        root3.left.left.left = new TreeNode(4);
        System.out.println(sol.maxDepth(root3)); // 4

        // Test 4: Single node
        System.out.println("\n=== Test 4: Single node ===");
        System.out.println(sol.maxDepth(new TreeNode(1))); // 1

        // Test 5: Empty tree
        System.out.println("\n=== Test 5: Empty tree ===");
        System.out.println(sol.maxDepth(null)); // 0

        // Test 6: Unbalanced — deeper on right side
        System.out.println("\n=== Test 6: Unbalanced ===");
        TreeNode root6 = new TreeNode(1);
        root6.left  = new TreeNode(2);
        root6.right = new TreeNode(3);
        root6.right.right = new TreeNode(4);
        root6.right.right.right = new TreeNode(5);
        System.out.println(sol.maxDepth(root6)); // 4
    }
}