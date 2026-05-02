package tree.advanced;

/**
 * LeetCode 110 - Balanced Binary Tree
 *
 * <p>Given a binary tree, determine if it is height-balanced. A binary tree is
 * height-balanced if for every node, the depth of the two subtrees never
 * differs by more than one.
 *
 * <p>Pattern: Post-order traversal with early exit
 * <ul>
 *   <li>Compute left and right subtree heights bottom-up</li>
 *   <li>Return -1 (sentinel) immediately when imbalance is detected</li>
 *   <li>Avoids redundant recomputation — O(n) vs O(n log n) naive approach</li>
 * </ul>
 *
 * <p>Sentinel convention:
 * <ul>
 *   <li>-1 = subtree is unbalanced (propagated up immediately)</li>
 *   <li>>=0 = valid height of subtree</li>
 * </ul>
 *
 * <p>Naive approach (top-down) computes height separately for each node:
 * O(n log n) for balanced trees, O(n²) for skewed trees.
 * This bottom-up approach computes height and balance in one pass: O(n).
 *
 * @author mhnuk2007
 * @version 1.0
 */
public class BalancedBinaryTree {

    // -------------------------------------------------------------------------
    // TreeNode definition
    // -------------------------------------------------------------------------

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

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
    // Approach: Bottom-Up Post-Order with Early Exit
    // Time  : O(n) — each node visited exactly once
    // Space : O(h) — recursion stack, h = tree height (O(log n) balanced,
    //                O(n) skewed)
    // -------------------------------------------------------------------------

    /**
     * Returns true if the binary tree rooted at {@code root} is height-balanced.
     *
     * @param root root of the binary tree
     * @return true if balanced, false otherwise
     */
    public static boolean isBalanced(TreeNode root) {
        return checkHeight(root) != -1;
    }

    /**
     * Post-order helper — returns the height of the subtree rooted at
     * {@code node}, or -1 if any subtree is unbalanced.
     *
     * <p>Early exit: once -1 is returned by either child, it propagates
     * immediately without further computation.
     *
     * @param node current node
     * @return height of subtree if balanced, -1 if unbalanced
     */
    private static int checkHeight(TreeNode node) {
        if (node == null) return 0;                         // base case: null has height 0
        int left = checkHeight(node.left);
        if (left == -1) return -1;                          // left subtree unbalanced — exit
        int right = checkHeight(node.right);
        if (right == -1) return -1;                         // right subtree unbalanced — exit
        if (Math.abs(left - right) > 1) return -1;         // current node unbalanced — exit
        return Math.max(left, right) + 1;                  // valid height
    }

    // -------------------------------------------------------------------------
    // Main — Test Cases
    // -------------------------------------------------------------------------

    public static void main(String[] args) {
        // Test Case 1: Balanced tree
        //        3
        //       / \
        //      9  20
        //        /  \
        //       15   7
        TreeNode t1 = new TreeNode(3,
                new TreeNode(9),
                new TreeNode(20,
                        new TreeNode(15),
                        new TreeNode(7)));
        System.out.println("Test 1 (balanced)        : " + isBalanced(t1)); // true

        // Test Case 2: Unbalanced tree — left subtree too deep
        //        1
        //       / \
        //      2   2
        //     / \
        //    3   3
        //   / \
        //  4   4
        TreeNode t2 = new TreeNode(1,
                new TreeNode(2,
                        new TreeNode(3,
                                new TreeNode(4),
                                new TreeNode(4)),
                        new TreeNode(3)),
                new TreeNode(2));
        System.out.println("Test 2 (unbalanced)      : " + isBalanced(t2)); // false

        // Test Case 3: Empty tree — trivially balanced
        System.out.println("Test 3 (null)            : " + isBalanced(null)); // true

        // Test Case 4: Single node
        System.out.println("Test 4 (single node)     : " + isBalanced(new TreeNode(1))); // true

        // Test Case 5: Two nodes — balanced (height diff = 1)
        //    1
        //   /
        //  2
        TreeNode t5 = new TreeNode(1, new TreeNode(2), null);
        System.out.println("Test 5 (two nodes)       : " + isBalanced(t5)); // true

        // Test Case 6: Right-skewed — unbalanced
        //  1
        //   \
        //    2
        //     \
        //      3
        TreeNode t6 = new TreeNode(1, null,
                new TreeNode(2, null,
                        new TreeNode(3)));
        System.out.println("Test 6 (right-skewed)    : " + isBalanced(t6)); // false

        // Test Case 7: Perfect binary tree — balanced
        //        1
        //       / \
        //      2   3
        //     / \ / \
        //    4  5 6  7
        TreeNode t7 = new TreeNode(1,
                new TreeNode(2, new TreeNode(4), new TreeNode(5)),
                new TreeNode(3, new TreeNode(6), new TreeNode(7)));
        System.out.println("Test 7 (perfect tree)    : " + isBalanced(t7)); // true
    }
}