package tree.advanced;

import java.util.*;

/**
 * LeetCode 106 - Construct Binary Tree from Inorder and Postorder Traversal
 *
 * <p>Given two integer arrays inorder and postorder where inorder is the inorder traversal
 * of a binary tree and postorder is the postorder traversal of the same tree,
 * construct and return the binary tree.
 *
 * <p>Pattern: Tree Construction from Traversals
 * <ul>
 *   <li>The last element in postorder traversal is always the root of the current subtree.</li>
 *   <li>Inorder traversal splits the left and right subtrees.</li>
 * </ul>
 *
 * <p>Approaches:
 * <ol>
 *   <li>Recursive approach: Identify root from postorder, find its position in inorder to
 *       divide into left and right subtrees, then recursively build.</li>
 * </ol>
 *
 * @author mhnuk2007
 * @version 1.0
 */
public class ConstructBinaryTree106 {

    // -------------------------------------------------------------------------
    // TreeNode definition
    // -------------------------------------------------------------------------

    static class TreeNode {
        int val;
        TreeNode left, right;

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
    // Approach 1: Recursive Construction
    // Time  : O(N) — Each node is processed once. HashMap lookup is O(1).
    // Space : O(N) — HashMap stores N elements. Recursion stack depth is O(H), where H is tree height.
    // -------------------------------------------------------------------------

    /**
     * Constructs a binary tree from its inorder and postorder traversal arrays.
     *
     * <p>This method initializes a map for quick lookup of element indices in the inorder
     * array and sets up the starting index for the postorder array. It then calls
     * a recursive helper to build the tree.
     *
     * @param inorder   The inorder traversal of the tree.
     * @param postorder The postorder traversal of the tree.
     * @return The root of the constructed binary tree.
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int n = inorder.length;
        int[] idx = {n - 1}; // Using an array to pass postorder index by reference
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(inorder[i], i);
        }
        return buildSubTrees(postorder, 0, n - 1, idx, map);
    }

    /**
     * Recursive helper method to build the binary tree.
     *
     * <p>It takes a segment of the inorder array (defined by start and end)
     * and the postorder array to construct a subtree. The root of the current subtree
     * is determined by the current element in the postorder array (accessed via idx).
     *
     * @param postorder The postorder traversal array.
     * @param start     The starting index of the current subtree in the inorder array.
     * @param end       The ending index of the current subtree in the inorder array.
     * @param idx       A single-element array holding the current index in the postorder array.
     * @param map       A map from node value to its index in the inorder array.
     * @return The root of the constructed subtree.
     */
    private TreeNode buildSubTrees(int[] postorder, int start, int end, int[] idx, Map<Integer, Integer> map) {
        if (start > end) return null;

        int rootVal = postorder[idx[0]--]; // Get root value from postorder and decrement index
        TreeNode root = new TreeNode(rootVal);

        int mid = map.get(rootVal); // Find root's index in inorder

        // Recursively build the right subtree first (because postorder is right-to-left for subtrees)
        root.right = buildSubTrees(postorder, mid + 1, end, idx, map);
        // Recursively build the left subtree
        root.left = buildSubTrees(postorder, start, mid - 1, idx, map);

        return root;
    }


    // -------------------------------------------------------------------------
    // Helper — level-order serialization for readable test output
    // -------------------------------------------------------------------------

    /**
     * Serializes tree to level-order list. Null children shown as "null".
     * Trailing nulls are omitted for cleaner output.
     * This utility is used for verifying test cases.
     *
     * @param root root of the tree
     * @return level-order string representation
     */
    private static String levelOrder(TreeNode root) {
        if (root == null) return "[]";

        List<String> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
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
        ConstructBinaryTree106 solver = new ConstructBinaryTree106();

        // Test Case 1: LeetCode example
        // Inorder:   [9,3,15,20,7]
        // Postorder: [9,15,7,20,3]
        // Expected Tree (Level Order): [3, 9, 20, null, null, 15, 7]
        int[] inorder1 = {9, 3, 15, 20, 7};
        int[] postorder1 = {9, 15, 7, 20, 3};
        TreeNode root1 = solver.buildTree(inorder1, postorder1);
        System.out.println("Test 1 - Constructed Tree: " + levelOrder(root1)); // Expected: [3, 9, 20, null, null, 15, 7]

        System.out.println();

        // Test Case 2: Single node tree
        // Inorder:   [-1]
        // Postorder: [-1]
        // Expected Tree (Level Order): [-1]
        int[] inorder2 = {-1};
        int[] postorder2 = {-1};
        TreeNode root2 = solver.buildTree(inorder2, postorder2);
        System.out.println("Test 2 - Constructed Tree: " + levelOrder(root2)); // Expected: [-1]

        System.out.println();

        // Test Case 3: Empty tree
        // Inorder:   []
        // Postorder: []
        // Expected Tree (Level Order): []
        int[] inorder3 = {};
        int[] postorder3 = {};
        TreeNode root3 = solver.buildTree(inorder3, postorder3);
        System.out.println("Test 3 - Constructed Tree: " + levelOrder(root3)); // Expected: []

        System.out.println();

        // Test Case 4: Skewed tree (left)
        // Inorder:   [4,2,1,3]
        // Postorder: [4,1,3,2]
        // Expected Tree (Level Order): [2, 4, 3, null, null, 1]
        int[] inorder4 = {4, 2, 1, 3};
        int[] postorder4 = {4, 1, 3, 2};
        TreeNode root4 = solver.buildTree(inorder4, postorder4);
        System.out.println("Test 4 - Constructed Tree: " + levelOrder(root4)); // Expected: [2, 4, 3, null, null, 1]

        System.out.println();

        // Test Case 5: Skewed tree (right)
        // Inorder:   [1,2,3,4]
        // Postorder: [1,3,4,2]
        // Expected Tree (Level Order): [2, 1, 4, null, null, 3]
        int[] inorder5 = {1, 2, 3, 4};
        int[] postorder5 = {1, 3, 4, 2};
        TreeNode root5 = solver.buildTree(inorder5, postorder5);
        System.out.println("Test 5 - Constructed Tree: " + levelOrder(root5)); // Expected: [2, 1, 4, null, null, 3]
    }
}
