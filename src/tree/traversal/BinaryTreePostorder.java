package tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * LeetCode 145 — Binary Tree Postorder Traversal
 *
 * Problem: Given the root of a binary tree, return the postorder traversal
 * of its node values — Left → Right → Root.
 *
 * Approach 1 — Recursive:
 *   Recurse left, recurse right, then add root. Natural bottom-up order.
 *
 * Approach 2 — Iterative:
 *   Postorder (L→R→Root) is the reverse of modified preorder (Root→R→L).
 *   Insert each popped node at index 0 — front insertion reverses order.
 *
 * Time Complexity : O(n) — both approaches
 * Space Complexity: O(h) — recursion stack / explicit stack depth = tree height
 */
public class BinaryTreePostorder {

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

    // ─── Approach 1: Recursive ────────────────────────────────────────────────
    public static List<Integer> postorderRecursive(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        dfs(root, result);
        return result;
    }

    private static void dfs(TreeNode node, List<Integer> list) {
        if (node == null) return;
        dfs(node.left,  list);
        dfs(node.right, list);
        list.add(node.val);    // root added last
    }

    // ─── Approach 2: Iterative ────────────────────────────────────────────────
    public static List<Integer> postorderIterative(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            result.add(0, node.val);          // insert at front
            if (node.left  != null) stack.push(node.left);
            if (node.right != null) stack.push(node.right);
        }
        return result;
    }

    public static void main(String[] args) {

        //            1
        //          /   \
        //         2     3
        //        / \   / \
        //       4   5 6   7
        //          /     /
        //         8     9
        TreeNode root = new TreeNode(1);
        root.left  = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left        = new TreeNode(4);
        root.left.right       = new TreeNode(5);
        root.right.left       = new TreeNode(6);
        root.right.right      = new TreeNode(7);
        root.left.right.left  = new TreeNode(8);
        root.right.right.left = new TreeNode(9);

        // Test 1: Main tree
        System.out.println("=== Test 1: Main tree ===");
        System.out.println("Recursive: " + postorderRecursive(root));
        System.out.println("Iterative: " + postorderIterative(root));
        // expected: [4, 8, 5, 2, 6, 9, 7, 3, 1]

        // Test 2: Single node
        System.out.println("\n=== Test 2: Single node ===");
        System.out.println("Recursive: " + postorderRecursive(new TreeNode(1))); // [1]
        System.out.println("Iterative: " + postorderIterative(new TreeNode(1))); // [1]

        // Test 3: Empty tree
        System.out.println("\n=== Test 3: Empty tree ===");
        System.out.println("Recursive: " + postorderRecursive(null)); // []
        System.out.println("Iterative: " + postorderIterative(null)); // []

        // Test 4: Left-skewed
        System.out.println("\n=== Test 4: Left-skewed ===");
        TreeNode root4 = new TreeNode(1);
        root4.left = new TreeNode(2);
        root4.left.left = new TreeNode(3);
        System.out.println("Recursive: " + postorderRecursive(root4)); // [3, 2, 1]
        System.out.println("Iterative: " + postorderIterative(root4)); // [3, 2, 1]
    }
}