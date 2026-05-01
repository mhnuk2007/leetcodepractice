package tree;

/**
 * LC 222 — Count Complete Tree Nodes
 *
 * Problem: Given the root of a binary tree, return the number of nodes.
 *
 * Approach: Recursive DFS — count left subtree + right subtree + 1 (this node).
 * Base case: null node contributes 0.
 *
 * Time Complexity : O(n) — every node visited once
 * Space Complexity: O(h) — recursion stack depth equals tree height
 *                   O(log n) balanced, O(n) skewed
 */
public class CountNodes {

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

    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    public static void main(String[] args) {

        CountNodes sol = new CountNodes();

        // Test 1: Full tree — 7 nodes
        System.out.println("=== Test 1: Full tree ===");
        TreeNode root1 = new TreeNode(1);
        root1.left  = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.left.left   = new TreeNode(4);
        root1.left.right  = new TreeNode(5);
        root1.right.left  = new TreeNode(6);
        root1.right.right = new TreeNode(7);
        System.out.println(sol.countNodes(root1)); // 7

        // Test 2: Single node
        System.out.println("\n=== Test 2: Single node ===");
        System.out.println(sol.countNodes(new TreeNode(1))); // 1

        // Test 3: Empty tree
        System.out.println("\n=== Test 3: Empty tree ===");
        System.out.println(sol.countNodes(null)); // 0

        // Test 4: Left-skewed
        System.out.println("\n=== Test 4: Left-skewed ===");
        TreeNode root4 = new TreeNode(1);
        root4.left = new TreeNode(2);
        root4.left.left = new TreeNode(3);
        System.out.println(sol.countNodes(root4)); // 3

        // Test 5: Unbalanced
        System.out.println("\n=== Test 5: Unbalanced ===");
        TreeNode root5 = new TreeNode(1);
        root5.left  = new TreeNode(2);
        root5.right = new TreeNode(3);
        root5.left.left = new TreeNode(4);
        System.out.println(sol.countNodes(root5)); // 4
    }
}