package recursion;

/**
 * LeetCode 98 - Validate Binary Search Tree
 *
 * Problem:
 *   Given the root of a binary tree, determine if it is a valid BST.
 *   A valid BST: left subtree nodes < root < right subtree nodes.
 *   This must hold for every node, not just immediate children.
 *
 * Approach: Recursive range validation
 *   Pass valid range (min, max) down the tree.
 *   Each node must satisfy min < node.val < max.
 *   Left child tightens the upper bound to node.val.
 *   Right child tightens the lower bound to node.val.
 *   Use Long bounds to safely handle Integer.MIN_VALUE / MAX_VALUE edge cases.
 *
 * Example:
 *   Input: [2,1,3] → true
 *        2
 *       / \
 *      1   3
 *
 *   Input: [5,1,4,null,null,3,6] → false (4 is in right subtree of 5 but 4 < 5)
 *        5
 *       / \
 *      1   4
 *         / \
 *        3   6
 *
 * Time  : O(n) — each node visited once
 * Space : O(h) — recursion depth; O(log n) balanced, O(n) skewed
 */
public class ValidateBST {

    public static void main(String[] args) {
        // Test 1: valid BST
        TreeNode root1 = new TreeNode(2);
        root1.left  = new TreeNode(1);
        root1.right = new TreeNode(3);
        System.out.println("isValidBST([2,1,3]):           " + isValidBST(root1));
        // Expected: true

        // Test 2: invalid BST
        TreeNode root2 = new TreeNode(5);
        root2.left        = new TreeNode(1);
        root2.right       = new TreeNode(4);
        root2.right.left  = new TreeNode(3);
        root2.right.right = new TreeNode(6);
        System.out.println("isValidBST([5,1,4,null,null,3,6]): " + isValidBST(root2));
        // Expected: false

        // Test 3: single node
        System.out.println("isValidBST([1]):                " + isValidBST(new TreeNode(1)));
        // Expected: true

        // Test 4: Integer.MIN_VALUE edge case
        TreeNode root3 = new TreeNode(Integer.MIN_VALUE);
        System.out.println("isValidBST([MIN_VALUE]):        " + isValidBST(root3));
        // Expected: true
    }

    public static boolean isValidBST(TreeNode root) {
        return helper(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private static boolean helper(TreeNode node, long min, long max) {
        if (node == null) return true;                                     // base case
        if (node.val <= min || node.val >= max) return false;             // violates BST property
        return helper(node.left, min, node.val)                           // tighten upper bound
                && helper(node.right, node.val, max);                         // tighten lower bound
    }
}