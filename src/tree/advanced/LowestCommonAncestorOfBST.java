package tree.advanced;

/**
 * LeetCode 235 - Lowest Common Ancestor of a Binary Search Tree
 *
 * <p>Given a BST and two nodes p and q, find their lowest common ancestor (LCA).
 * The LCA is defined as the deepest node that has both p and q as descendants
 * (a node is a descendant of itself).
 *
 * <p>Key Insight (BST property):
 * <ul>
 *   <li>Both p and q smaller than root → LCA is in left subtree</li>
 *   <li>Both p and q larger than root  → LCA is in right subtree</li>
 *   <li>Otherwise (split) → current root is the LCA</li>
 * </ul>
 *
 * <p>This is more efficient than the general binary tree LCA (LC 236) because
 * BST ordering eliminates the need to search both subtrees.
 *
 * <p>Approaches:
 * <ol>
 *   <li>Iterative — traverse using while loop, O(1) space</li>
 *   <li>Recursive — cleaner code, O(h) stack space</li>
 * </ol>
 *
 * @author mhnuk2007
 * @version 1.0
 */
public class LowestCommonAncestorOfBST {

    // -------------------------------------------------------------------------
    // TreeNode definition
    // -------------------------------------------------------------------------

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

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
    // Approach 1: Iterative
    // Time  : O(h) — h = tree height, O(log n) balanced, O(n) skewed
    // Space : O(1) — no recursion stack
    // -------------------------------------------------------------------------

    /**
     * Returns the lowest common ancestor of nodes p and q in the BST
     * using an iterative approach.
     *
     * <p>Traverses the tree using BST ordering:
     * <ul>
     *   <li>Both left  → move to root.left</li>
     *   <li>Both right → move to root.right</li>
     *   <li>Split      → current node is LCA</li>
     * </ul>
     *
     * @param root root of the BST
     * @param p    first node
     * @param q    second node
     * @return lowest common ancestor of p and q
     */
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        while (root != null) {
            if (p.val < root.val && q.val < root.val) root = root.left;         // both left
            else if (p.val > root.val && q.val > root.val) root = root.right;   // both right
            else return root;                                                   // split — LCA
        }
        return null;
    }

    // -------------------------------------------------------------------------
    // Approach 2: Recursive
    // Time  : O(h)
    // Space : O(h) — recursion stack
    // -------------------------------------------------------------------------

    /**
     * Returns the lowest common ancestor of nodes p and q in the BST
     * using a recursive approach.
     *
     * <p>Applies the same BST ordering logic as the iterative approach,
     * expressed as a tail-recursive traversal.
     *
     * @param root root of the BST
     * @param p    first node
     * @param q    second node
     * @return lowest common ancestor of p and q
     */
    public static TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        if (p.val < root.val && q.val < root.val)           // both left
            return lowestCommonAncestor2(root.left, p, q);
        if (p.val > root.val && q.val > root.val)           // both right
            return lowestCommonAncestor2(root.right, p, q);
        return root;                                         // split — LCA
    }

    // -------------------------------------------------------------------------
    // Main — Test Cases
    // -------------------------------------------------------------------------
    //
    // BST used in all tests:
    //
    //         6
    //        / \
    //       2   8
    //      / \ / \
    //     0  4 7  9
    //       / \
    //      3   5
    //
    // -------------------------------------------------------------------------

    public static void main(String[] args) {
        TreeNode root = new TreeNode(6,
                new TreeNode(2,
                        new TreeNode(0),
                        new TreeNode(4, new TreeNode(3), new TreeNode(5))),
                new TreeNode(8,
                        new TreeNode(7),
                        new TreeNode(9)));

        // Test Case 1: p=2, q=8 → LCA = 6 (split at root)
        TreeNode p1 = new TreeNode(2), q1 = new TreeNode(8);
        System.out.println("Test 1 - Iterative : " + lowestCommonAncestor(root, p1, q1).val);  // 6
        System.out.println("Test 1 - Recursive : " + lowestCommonAncestor2(root, p1, q1).val); // 6

        System.out.println();

        // Test Case 2: p=2, q=4 → LCA = 2 (q is descendant of p)
        TreeNode p2 = new TreeNode(2), q2 = new TreeNode(4);
        System.out.println("Test 2 - Iterative : " + lowestCommonAncestor(root, p2, q2).val);  // 2
        System.out.println("Test 2 - Recursive : " + lowestCommonAncestor2(root, p2, q2).val); // 2

        System.out.println();

        // Test Case 3: p=3, q=5 → LCA = 4
        TreeNode p3 = new TreeNode(3), q3 = new TreeNode(5);
        System.out.println("Test 3 - Iterative : " + lowestCommonAncestor(root, p3, q3).val);  // 4
        System.out.println("Test 3 - Recursive : " + lowestCommonAncestor2(root, p3, q3).val); // 4

        System.out.println();

        // Test Case 4: p=7, q=9 → LCA = 8
        TreeNode p4 = new TreeNode(7), q4 = new TreeNode(9);
        System.out.println("Test 4 - Iterative : " + lowestCommonAncestor(root, p4, q4).val);  // 8
        System.out.println("Test 4 - Recursive : " + lowestCommonAncestor2(root, p4, q4).val); // 8

        System.out.println();

        // Test Case 5: p=0, q=9 → LCA = 6 (widest split)
        TreeNode p5 = new TreeNode(0), q5 = new TreeNode(9);
        System.out.println("Test 5 - Iterative : " + lowestCommonAncestor(root, p5, q5).val);  // 6
        System.out.println("Test 5 - Recursive : " + lowestCommonAncestor2(root, p5, q5).val); // 6
    }
}