package tree.advanced;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * LeetCode 105 - Construct Binary Tree from Preorder and Inorder Traversal
 *
 * <p>Given two integer arrays preorder and inorder where:
 * <ul>
 *   <li>preorder is the preorder traversal of a binary tree</li>
 *   <li>inorder is the inorder traversal of the same tree</li>
 * </ul>
 * Construct and return the binary tree.
 *
 * <p>Key Insight:
 * <pre>
 *   Preorder: root | left subtree | right subtree
 *   Inorder:  left subtree | root | right subtree
 *
 *   preorder[idx] is always the current root.
 *   map.get(rootVal) gives the root's position in inorder,
 *   splitting it into left and right subtrees.
 * </pre>
 *
 * <p>Design decisions:
 * <ul>
 *   <li>HashMap for O(1) inorder index lookup (vs O(n) linear scan)</li>
 *   <li>int[] idx wrapper allows mutation of preorder index across recursive calls</li>
 *   <li>Both idx and map are local to buildTree — no static state, safe for multiple calls</li>
 * </ul>
 *
 * @author mhnuk2007
 * @version 1.0
 */
public class ConstructBinaryTree {

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
    // Approach: DFS with HashMap index lookup
    // Time  : O(n) — each node visited once, O(1) map lookup per node
    // Space : O(n) — HashMap + recursion stack O(h)
    // -------------------------------------------------------------------------

    /**
     * Constructs a binary tree from preorder and inorder traversal arrays.
     *
     * @param preorder preorder traversal of the binary tree
     * @param inorder  inorder traversal of the binary tree
     * @return root of the reconstructed binary tree
     */
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = inorder.length;
        int[] idx = {0};                            // mutable preorder index
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) map.put(inorder[i], i);
        return buildSubTrees(preorder, 0, n - 1, idx, map);
    }

    /**
     * Recursive DFS helper — builds subtree for inorder range [start, end].
     *
     * <p>preorder[idx[0]] is the current root. Its position in inorder (mid)
     * divides the remaining nodes into left subtree [start, mid-1] and
     * right subtree [mid+1, end].
     *
     * @param preorder preorder traversal array
     * @param start    inclusive left bound of current inorder range
     * @param end      inclusive right bound of current inorder range
     * @param idx      single-element array tracking current preorder index
     * @param map      inorder value → index lookup map
     * @return root of subtree built from current range
     */
    private static TreeNode buildSubTrees(int[] preorder, int start, int end,
                                int[] idx, Map<Integer, Integer> map) {
        if (start > end) return null;
        int rootVal = preorder[idx[0]++];
        TreeNode root = new TreeNode(rootVal);
        int mid = map.get(rootVal);                 // split point in inorder
        root.left  = buildSubTrees(preorder, start,   mid - 1, idx, map);
        root.right = buildSubTrees(preorder, mid + 1, end,     idx, map);
        return root;
    }

    // -------------------------------------------------------------------------
    // Helper — level-order serialization for readable test output
    // -------------------------------------------------------------------------

    /**
     * Serializes tree to level-order list. Null children shown as "null".
     * Trailing nulls are omitted for cleaner output.
     *
     * @param root root of the tree
     * @return level-order string representation
     */
    private static String levelOrder(TreeNode root) {
        if (root == null) return "[]";

        List<String> result = new ArrayList<>();
        Queue<TreeNode> queue = new java.util.LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

            if (node == null) {
                result.add("null");
            } else {
                result.add(String.valueOf(node.val));
                queue.offer(node.left);
                queue.offer(node.right);
            }
        }

        // trim trailing nulls
        int last = result.size() - 1;
        while (last >= 0 && result.get(last).equals("null")) last--;

        return result.subList(0, last + 1).toString();
    }

    // -------------------------------------------------------------------------
    // Main — Test Cases
    // -------------------------------------------------------------------------

    public static void main(String[] args) {
        // Test Case 1: Standard example
        // preorder=[3,9,20,15,7], inorder=[9,3,15,20,7]
        // Expected: [3,9,20,null,null,15,7]
        int[] pre1 = {3, 9, 20, 15, 7};
        int[] in1  = {9, 3, 15, 20, 7};
        TreeNode root1 = buildTree(pre1, in1);
        System.out.println("Test 1: " + levelOrder(root1)); // [3, 9, 20, null, null, 15, 7]
        System.out.println("  root=" + root1.val);          // 3
        System.out.println("  left=" + root1.left.val);     // 9
        System.out.println("  right=" + root1.right.val);   // 20

        System.out.println();

        // Test Case 2: Single node
        int[] pre2 = {1};
        int[] in2  = {1};
        TreeNode root2 = buildTree(pre2, in2);
        System.out.println("Test 2: " + levelOrder(root2)); // [1]

        System.out.println();

        // Test Case 3: Left-skewed tree
        // preorder=[1,2,3], inorder=[3,2,1]
        int[] pre3 = {1, 2, 3};
        int[] in3  = {3, 2, 1};
        TreeNode root3 = buildTree(pre3, in3);
        System.out.println("Test 3: " + levelOrder(root3)); // [1, 2, null, 3]

        System.out.println();

        // Test Case 4: Right-skewed tree
        // preorder=[1,2,3], inorder=[1,2,3]
        int[] pre4 = {1, 2, 3};
        int[] in4  = {1, 2, 3};
        TreeNode root4 = buildTree(pre4, in4);
        System.out.println("Test 4: " + levelOrder(root4)); // [1, null, 2, null, 3]

        System.out.println();

        // Test Case 5: Perfect binary tree
        // preorder=[1,2,4,5,3,6,7], inorder=[4,2,5,1,6,3,7]
        int[] pre5 = {1, 2, 4, 5, 3, 6, 7};
        int[] in5  = {4, 2, 5, 1, 6, 3, 7};
        TreeNode root5 = buildTree(pre5, in5);
        System.out.println("Test 5: " + levelOrder(root5)); // [1, 2, 3, 4, 5, 6, 7]

        System.out.println();

        // Test Case 6: Multiple calls — verifies no static state pollution
        TreeNode r1 = buildTree(pre1, in1);
        TreeNode r2 = buildTree(pre5, in5);
        System.out.println("Test 6a: " + levelOrder(r1)); // [3, 9, 20, null, null, 15, 7]
        System.out.println("Test 6b: " + levelOrder(r2)); // [1, 2, 3, 4, 5, 6, 7]
    }
}