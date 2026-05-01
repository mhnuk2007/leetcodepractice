package tree;

/**
 * LeetCode 226 — Invert Binary Tree
 *
 * Problem: Given the root of a binary tree, invert it (mirror it)
 * and return its root.
 *
 * Approach: Top-down (preorder) recursion.
 *   Swap left and right children at this node FIRST,
 *   then recurse into the (now swapped) left and right subtrees.
 *
 * Alternative: bottom-up (postorder) — recurse first, swap after.
 *   Both produce the same result. Top-down is slightly more intuitive.
 *
 * Time Complexity : O(n) — every node visited once
 * Space Complexity: O(h) — recursion stack depth equals tree height
 *                   O(log n) balanced, O(n) skewed
 */
public class InvertBinaryTree {

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

    // ─── Approach 1: Top-down (preorder) — swap then recurse ─────────────────
    public static TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        TreeNode temp  = root.left;  // swap children first
        root.left      = root.right;
        root.right     = temp;
        invertTree(root.left);       // then recurse into swapped children
        invertTree(root.right);
        return root;
    }

    // ─── Approach 2: Bottom-up (postorder) — recurse then swap ───────────────
    public static TreeNode invertTreeBottomUp(TreeNode root) {
        if (root == null) return null;
        TreeNode left  = invertTreeBottomUp(root.left);  // recurse first
        TreeNode right = invertTreeBottomUp(root.right);
        root.left  = right;                              // swap after children done
        root.right = left;
        return root;
    }

    // ─── Helper: inorder traversal to verify result ───────────────────────────
    public static void inorder(TreeNode node) {
        if (node == null) return;
        inorder(node.left);
        System.out.print(node.val + " ");
        inorder(node.right);
    }

    public static void main(String[] args) {

        // Test 1: Standard tree
        //      4                4
        //    /   \    →       /   \
        //   2     7          7     2
        //  / \   / \        / \   / \
        // 1   3 6   9      9   6 3   1
        System.out.println("=== Test 1: Standard tree ===");
        TreeNode root1 = new TreeNode(4);
        root1.left  = new TreeNode(2);
        root1.right = new TreeNode(7);
        root1.left.left   = new TreeNode(1);
        root1.left.right  = new TreeNode(3);
        root1.right.left  = new TreeNode(6);
        root1.right.right = new TreeNode(9);
        System.out.print("Before inorder: "); inorder(root1); System.out.println();
        invertTree(root1);
        System.out.print("After inorder:  "); inorder(root1); System.out.println();
        // before: 1 2 3 4 6 7 9
        // after:  9 7 6 4 3 2 1  ← reversed

        // Test 2: Bottom-up approach — same result
        System.out.println("\n=== Test 2: Bottom-up approach ===");
        TreeNode root2 = new TreeNode(4);
        root2.left  = new TreeNode(2);
        root2.right = new TreeNode(7);
        root2.left.left   = new TreeNode(1);
        root2.left.right  = new TreeNode(3);
        root2.right.left  = new TreeNode(6);
        root2.right.right = new TreeNode(9);
        System.out.print("Before inorder: "); inorder(root2); System.out.println();
        invertTreeBottomUp(root2);
        System.out.print("After inorder:  "); inorder(root2); System.out.println();
        // same result: 9 7 6 4 3 2 1

        // Test 3: Single node — nothing to swap
        System.out.println("\n=== Test 3: Single node ===");
        TreeNode root3 = new TreeNode(1);
        invertTree(root3);
        System.out.print("After inorder: "); inorder(root3); System.out.println(); // 1

        // Test 4: Empty tree
        System.out.println("\n=== Test 4: Empty tree ===");
        System.out.println(invertTree(null)); // null

        // Test 5: Left-skewed — becomes right-skewed
        System.out.println("\n=== Test 5: Left-skewed → right-skewed ===");
        TreeNode root5 = new TreeNode(1);
        root5.left = new TreeNode(2);
        root5.left.left = new TreeNode(3);
        System.out.print("Before inorder: "); inorder(root5); System.out.println();
        invertTree(root5);
        System.out.print("After inorder:  "); inorder(root5); System.out.println();
        // before: 3 2 1
        // after:  1 2 3  ← right-skewed, reads reversed
    }
}