package tree.advanced;

/**
 * LeetCode 543 - Diameter of Binary Tree
 *
 * <p>Given the root of a binary tree, return the length of the diameter of the tree.
 * The diameter is the length of the longest path between any two nodes — the path
 * may or may not pass through the root. Length is measured in number of edges.
 *
 * <p>Key Insight:
 * The diameter through any node = left subtree height + right subtree height.
 * The global maximum of this value across all nodes is the answer.
 *
 * <p>Pattern: Post-order traversal (bottom-up)
 * <ul>
 *   <li>Compute left and right heights recursively</li>
 *   <li>Update global max with left + right at each node</li>
 *   <li>Return height (max(left, right) + 1) to parent</li>
 * </ul>
 *
 * <p>Why int[] for maxDiameter:
 * Java passes primitives by value — updates inside a recursive helper are not
 * visible to the caller. Wrapping in int[] passes the array reference, making
 * updates at maxDiameter[0] visible across all recursive calls.
 *
 * <p>Complexity:
 * <ul>
 *   <li>Time  : O(n) — each node visited exactly once</li>
 *   <li>Space : O(h) — recursion stack, h = tree height</li>
 * </ul>
 *
 * @author mhnuk2007
 * @version 1.0
 */
public class DiameterOfBinaryTree {

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
    // Approach: Post-Order with int[] mutable accumulator
    // Time  : O(n)
    // Space : O(h) — O(log n) balanced, O(n) skewed
    // -------------------------------------------------------------------------

    /**
     * Returns the diameter of the binary tree rooted at {@code root}.
     *
     * @param root root of the binary tree
     * @return length of the longest path between any two nodes (in edges)
     */
    public static int diameterOfBinaryTree(TreeNode root) {
        int[] maxDiameter = {0};
        height(root, maxDiameter);
        return maxDiameter[0];
    }

    /**
     * Post-order helper — computes height of subtree rooted at {@code node}
     * and updates {@code maxDiameter[0]} with the longest path through this node.
     *
     * <p>Diameter through a node = left height + right height (number of edges).
     * Height returned = max(left, right) + 1.
     *
     * @param node        current node
     * @param maxDiameter single-element array tracking global max diameter
     * @return height of subtree rooted at node
     */
    private static int height(TreeNode node, int[] maxDiameter) {
        if (node == null) return 0;
        int left  = height(node.left,  maxDiameter);
        int right = height(node.right, maxDiameter);
        maxDiameter[0] = Math.max(maxDiameter[0], left + right); // path through this node
        return Math.max(left, right) + 1;
    }

    // -------------------------------------------------------------------------
    // Main — Test Cases
    // -------------------------------------------------------------------------

    public static void main(String[] args) {
        // Test Case 1: Diameter = 4 (path: 4→2→1→3→6 or 4→2→1→3→7)
        //        1
        //       / \
        //      2   3
        //     / \ / \
        //    4  5 6  7
        TreeNode t1 = new TreeNode(1,
                new TreeNode(2, new TreeNode(4), new TreeNode(5)),
                new TreeNode(3, new TreeNode(6), new TreeNode(7)));
        System.out.println("Test 1 (perfect tree)    : " + diameterOfBinaryTree(t1)); // 4

        // Test Case 2: Diameter = 3 (path: 4→2→1→3 or 5→2→1→3)
        //      1
        //     / \
        //    2   3
        //   / \
        //  4   5
        TreeNode t2 = new TreeNode(1,
                new TreeNode(2, new TreeNode(4), new TreeNode(5)),
                new TreeNode(3));
        System.out.println("Test 2 (LC example 1)    : " + diameterOfBinaryTree(t2)); // 3

        // Test Case 3: Diameter = 1 — single edge
        //  1
        //   \
        //    2
        TreeNode t3 = new TreeNode(1, null, new TreeNode(2));
        System.out.println("Test 3 (two nodes)       : " + diameterOfBinaryTree(t3)); // 1

        // Test Case 4: Single node — diameter is 0
        System.out.println("Test 4 (single node)     : " + diameterOfBinaryTree(new TreeNode(1))); // 0

        // Test Case 5: null root
        System.out.println("Test 5 (null)            : " + diameterOfBinaryTree(null)); // 0

        // Test Case 6: Right-skewed — diameter = 3
        //  1
        //   \
        //    2
        //     \
        //      3
        //       \
        //        4
        TreeNode t6 = new TreeNode(1, null,
                new TreeNode(2, null,
                        new TreeNode(3, null,
                                new TreeNode(4))));
        System.out.println("Test 6 (right-skewed)    : " + diameterOfBinaryTree(t6)); // 3

        // Test Case 7: Diameter does not pass through root
        //        1
        //       /
        //      2
        //     / \
        //    3   4
        //   /     \
        //  5       6
        TreeNode t7 = new TreeNode(1,
                new TreeNode(2,
                        new TreeNode(3, new TreeNode(5), null),
                        new TreeNode(4, null, new TreeNode(6))),
                null);
        System.out.println("Test 7 (path not through root): " + diameterOfBinaryTree(t7)); // 4
    }
}