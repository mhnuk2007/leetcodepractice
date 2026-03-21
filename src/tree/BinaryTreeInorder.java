package tree;

import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

/**
 * LeetCode 94 — Binary Tree Inorder Traversal
 *
 * Problem: Given the root of a binary tree, return the inorder traversal
 * of its node values — Left → Root → Right.
 *
 * Key property: Inorder on a BST produces values in sorted ascending order.
 *
 * Approach 1 — Recursive:
 *   Recurse left, add root, recurse right.
 *   Simple and clean — implicit call stack handles backtracking.
 *
 * Approach 2 — Iterative with explicit stack:
 *   Go as far left as possible pushing nodes onto stack.
 *   When null is hit, pop and process, then move to right subtree.
 *   Repeat until stack empty and current node null.
 *
 * Time Complexity : O(n) — both approaches
 * Space Complexity: O(h) — both approaches
 *                   O(log n) balanced, O(n) skewed
 */
public class BinaryTreeInorder {

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
    public List<Integer> inorderRecursive(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        dfs(root, res);
        return res;
    }

    private void dfs(TreeNode node, List<Integer> res) {
        if (node == null) return;
        dfs(node.left, res);       // go left first
        res.add(node.val);         // process root in middle
        dfs(node.right, res);      // go right last
    }

    // ─── Approach 2: Iterative ────────────────────────────────────────────────
    public List<Integer> inorderIterative(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode curr = root;

        while (curr != null || !stack.isEmpty()) {
            // go as far left as possible — push every node onto stack
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            // curr is null — leftmost node reached, pop and process
            curr = stack.pop();
            res.add(curr.val);     // process node
            curr = curr.right;     // move to right subtree and repeat
        }
        return res;
    }

    public static void main(String[] args) {

        BinaryTreeInorder sol = new BinaryTreeInorder();

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
        System.out.println("Recursive: " + sol.inorderRecursive(root1)); // [1, 3, 2]
        System.out.println("Iterative: " + sol.inorderIterative(root1)); // [1, 3, 2]

        // Test 2: BST — inorder produces sorted output
        //        4
        //      /   \
        //     2     6
        //    / \   / \
        //   1   3 5   7
        System.out.println("\n=== Test 2: BST → sorted output ===");
        TreeNode root2 = new TreeNode(4);
        root2.left  = new TreeNode(2);
        root2.right = new TreeNode(6);
        root2.left.left   = new TreeNode(1);
        root2.left.right  = new TreeNode(3);
        root2.right.left  = new TreeNode(5);
        root2.right.right = new TreeNode(7);
        System.out.println("Recursive: " + sol.inorderRecursive(root2)); // [1,2,3,4,5,6,7]
        System.out.println("Iterative: " + sol.inorderIterative(root2)); // [1,2,3,4,5,6,7]

        // Test 3: Single node
        System.out.println("\n=== Test 3: Single node ===");
        TreeNode root3 = new TreeNode(1);
        System.out.println("Recursive: " + sol.inorderRecursive(root3)); // [1]
        System.out.println("Iterative: " + sol.inorderIterative(root3)); // [1]

        // Test 4: Empty tree
        System.out.println("\n=== Test 4: Empty tree ===");
        System.out.println("Recursive: " + sol.inorderRecursive(null));  // []
        System.out.println("Iterative: " + sol.inorderIterative(null));  // []

        // Test 5: Left-skewed
        System.out.println("\n=== Test 5: Left-skewed ===");
        TreeNode root5 = new TreeNode(3);
        root5.left = new TreeNode(2);
        root5.left.left = new TreeNode(1);
        System.out.println("Recursive: " + sol.inorderRecursive(root5)); // [1, 2, 3]
        System.out.println("Iterative: " + sol.inorderIterative(root5)); // [1, 2, 3]
    }
}