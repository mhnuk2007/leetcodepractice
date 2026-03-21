package tree;

import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

/**
 * LeetCode 144 — Binary Tree Preorder Traversal
 *
 * Problem: Given the root of a binary tree, return the preorder traversal
 * of its node values — Root → Left → Right.
 *
 * Approach 1 — Recursive DFS:
 *   Visit root, recurse left, recurse right.
 *   Call stack acts as the implicit stack.
 *
 * Approach 2 — Iterative with explicit stack:
 *   Push root, then loop: pop → add → push right → push left.
 *   Right pushed before left so left is popped (processed) first.
 *
 * Time Complexity : O(n) — both approaches
 * Space Complexity: O(h) — both approaches
 *                   O(log n) balanced, O(n) skewed
 */
public class BinaryTreePreorder {

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
    public List<Integer> preorderRecursive(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        dfs(root, res);
        return res;
    }

    private void dfs(TreeNode node, List<Integer> res) {
        if (node == null) return;
        res.add(node.val);         // root first
        dfs(node.left, res);       // then left
        dfs(node.right, res);      // then right
    }

    // ─── Approach 2: Iterative ────────────────────────────────────────────────
    public List<Integer> preorderIterative(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            res.add(node.val);
            if (node.right != null) stack.push(node.right); // right pushed first
            if (node.left  != null) stack.push(node.left);  // left on top → processed first
        }
        return res;
    }

    public static void main(String[] args) {

        BinaryTreePreorder sol = new BinaryTreePreorder();

        // Test 1: LeetCode example — [1, null, 2, 3]
        //   1
        //    \
        //     2
        //    /
        //   3
        System.out.println("=== Test 1: [1, null, 2, 3] ===");
        TreeNode root1 = new TreeNode(1);
        root1.right = new TreeNode(2);
        root1.right.left = new TreeNode(3);
        System.out.println("Recursive: " + sol.preorderRecursive(root1)); // [1, 2, 3]
        System.out.println("Iterative: " + sol.preorderIterative(root1)); // [1, 2, 3]

        // Test 2: Full tree
        //        1
        //      /   \
        //     2     3
        //    / \
        //   4   5
        System.out.println("\n=== Test 2: Full tree ===");
        TreeNode root2 = new TreeNode(1);
        root2.left  = new TreeNode(2);
        root2.right = new TreeNode(3);
        root2.left.left  = new TreeNode(4);
        root2.left.right = new TreeNode(5);
        System.out.println("Recursive: " + sol.preorderRecursive(root2)); // [1, 2, 4, 5, 3]
        System.out.println("Iterative: " + sol.preorderIterative(root2)); // [1, 2, 4, 5, 3]

        // Test 3: Single node
        System.out.println("\n=== Test 3: Single node ===");
        TreeNode root3 = new TreeNode(1);
        System.out.println("Recursive: " + sol.preorderRecursive(root3)); // [1]
        System.out.println("Iterative: " + sol.preorderIterative(root3)); // [1]

        // Test 4: Empty tree
        System.out.println("\n=== Test 4: Empty tree ===");
        System.out.println("Recursive: " + sol.preorderRecursive(null)); // []
        System.out.println("Iterative: " + sol.preorderIterative(null)); // []

        // Test 5: Left-skewed
        System.out.println("\n=== Test 5: Left-skewed ===");
        TreeNode root5 = new TreeNode(1);
        root5.left = new TreeNode(2);
        root5.left.left = new TreeNode(3);
        System.out.println("Recursive: " + sol.preorderRecursive(root5)); // [1, 2, 3]
        System.out.println("Iterative: " + sol.preorderIterative(root5)); // [1, 2, 3]
    }
}