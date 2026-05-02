package tree.advanced;

/**
 * LeetCode 236 - Lowest Common Ancestor of a Binary Tree
 *
 * <p>Given a binary tree and two nodes p and q, find their lowest common ancestor (LCA).
 * The LCA is the deepest node that has both p and q as descendants
 * (a node is a descendant of itself). Unlike LC 235, this tree is NOT a BST —
 * BST ordering cannot be used to prune the search.
 *
 * <p>Key Insight (Post-Order DFS):
 * <ul>
 *   <li>If current node is null, p, or q — return it immediately</li>
 *   <li>Recurse into both subtrees</li>
 *   <li>If both sides return non-null → p and q split here → current node is LCA</li>
 *   <li>If one side is null → both nodes are in the other subtree → propagate that result</li>
 * </ul>
 *
 * <p>Comparison with LC 235 (BST):
 * <ul>
 *   <li>LC 235 — exploits BST ordering, O(h) time, O(1) iterative space</li>
 *   <li>LC 236 — must search both subtrees, O(n) time, O(h) stack space</li>
 * </ul>
 *
 * <p>Node identity uses == (not .equals()) — LeetCode guarantees p and q are
 * actual references to nodes in the tree, not copies.
 *
 * @author mhnuk2007
 * @version 1.0
 */
public class LowestCommonAncestorOfBinaryTree {

    // -------------------------------------------------------------------------
    // TreeNode definition
    // -------------------------------------------------------------------------

    static class TreeNode {
        int val;
        TreeNode left, right;

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
    // Approach: Post-Order DFS
    // Time  : O(n) — must visit every node (no ordering to exploit)
    // Space : O(h) — recursion stack, O(log n) balanced, O(n) skewed
    // -------------------------------------------------------------------------

    /**
     * Returns the lowest common ancestor of nodes p and q in the binary tree.
     *
     * <p>Returns:
     * <ul>
     *   <li>null  — neither p nor q found in this subtree</li>
     *   <li>p/q   — one of the targets found (or LCA already identified deeper)</li>
     *   <li>root  — both left and right non-null, so this node is the LCA</li>
     * </ul>
     *
     * @param root root of the binary tree
     * @param p    first target node
     * @param q    second target node
     * @return lowest common ancestor of p and q
     */
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root; // base case
        TreeNode left  = lowestCommonAncestor(root.left,  p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) return root;          // split — LCA found
        return left != null ? left : right;                      // propagate non-null side
    }

    // -------------------------------------------------------------------------
    // Main — Test Cases
    // -------------------------------------------------------------------------
    //
    // Tree used in all tests:
    //
    //         3
    //        / \
    //       5   1
    //      / \ / \
    //     6  2 0  8
    //       / \
    //      7   4
    //
    // -------------------------------------------------------------------------

    public static void main(String[] args) {
        // Build tree with named node references for LCA queries
        TreeNode n7  = new TreeNode(7);
        TreeNode n4  = new TreeNode(4);
        TreeNode n6  = new TreeNode(6);
        TreeNode n2  = new TreeNode(2, n7, n4);
        TreeNode n5  = new TreeNode(5, n6, n2);
        TreeNode n0  = new TreeNode(0);
        TreeNode n8  = new TreeNode(8);
        TreeNode n1  = new TreeNode(1, n0, n8);
        TreeNode root = new TreeNode(3, n5, n1);

        // Test Case 1: p=5, q=1 → LCA = 3 (split at root)
        System.out.println("Test 1 (p=5, q=1)  : " + lowestCommonAncestor(root, n5, n1).val); // 3

        // Test Case 2: p=5, q=4 → LCA = 5 (q is descendant of p)
        System.out.println("Test 2 (p=5, q=4)  : " + lowestCommonAncestor(root, n5, n4).val); // 5

        // Test Case 3: p=6, q=4 → LCA = 5
        System.out.println("Test 3 (p=6, q=4)  : " + lowestCommonAncestor(root, n6, n4).val); // 5

        // Test Case 4: p=7, q=4 → LCA = 2
        System.out.println("Test 4 (p=7, q=4)  : " + lowestCommonAncestor(root, n7, n4).val); // 2

        // Test Case 5: p=6, q=2 → LCA = 5
        System.out.println("Test 5 (p=6, q=2)  : " + lowestCommonAncestor(root, n6, n2).val); // 5

        // Test Case 6: p=0, q=8 → LCA = 1
        System.out.println("Test 6 (p=0, q=8)  : " + lowestCommonAncestor(root, n0, n8).val); // 1

        // Test Case 7: p=7, q=8 → LCA = 3 (widest split)
        System.out.println("Test 7 (p=7, q=8)  : " + lowestCommonAncestor(root, n7, n8).val); // 3
    }
}