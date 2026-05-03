package dp.optimization;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 95 - Unique Binary Search Trees II
 *
 * <p>Given an integer n, return all structurally unique BSTs which have exactly
 * n nodes of unique values from 1 to n. Return the answer in any order.
 *
 * <p>Key Insight — Divide and Conquer:
 * <pre>
 *   For each root value i in [start, end]:
 *     left subtrees  = all BSTs built from [start, i-1]
 *     right subtrees = all BSTs built from [i+1, end]
 *     combine each left with each right under root i
 * </pre>
 *
 * <p>Relationship to LC 96 (Unique BSTs):
 * <ul>
 *   <li>LC 96 counts unique BSTs   → returns int (Catalan number)</li>
 *   <li>LC 95 enumerates all BSTs  → returns List of TreeNode roots</li>
 *   <li>generateTrees(n).size() == numTrees(n) — same Catalan count</li>
 * </ul>
 *
 * <p>Base case: start > end → return [null] (one empty subtree, not empty list).
 * Returning an empty list would cause the outer loop to produce zero combinations.
 *
 * <p>Note: Memoization on (start, end) is theoretically possible but rarely
 * beneficial in practice — each unique (start, end) range produces structurally
 * distinct trees that cannot be safely shared across roots due to node value differences.
 *
 * @author mhnuk2007
 * @version 1.0
 */
public class UniqueBSTII {

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
    // Approach: Recursive Divide and Conquer
    // Time  : O(4^n / n^(3/2)) — Catalan number of trees, O(n) nodes each
    // Space : O(4^n / n^(3/2)) — to store all generated trees
    // -------------------------------------------------------------------------

    /**
     * Returns all structurally unique BSTs with values 1..n.
     *
     * @param n number of nodes
     * @return list of root nodes of all unique BSTs
     */
    public static List<TreeNode> generateTrees(int n) {
        if (n == 0) return new ArrayList<>();
        return build(1, n);
    }

    /**
     * Recursively builds all unique BSTs using values in [start, end].
     *
     * <p>For each root value in [start, end], recursively generates all valid
     * left and right subtrees and combines them under the current root.
     * A fresh {@code TreeNode} is created for each (left, right) pair to
     * avoid shared node mutation across different trees.
     *
     * @param start inclusive lower bound of current range
     * @param end   inclusive upper bound of current range
     * @return list of root nodes of all unique BSTs for range [start, end]
     */
    private static List<TreeNode> build(int start, int end) {
        List<TreeNode> result = new ArrayList<>();
        if (start > end) {
            result.add(null);  // one empty subtree — must not return empty list
            return result;
        }
        for (int root = start; root <= end; root++) {
            List<TreeNode> leftTrees  = build(start, root - 1);
            List<TreeNode> rightTrees = build(root + 1, end);
            for (TreeNode left : leftTrees) {
                for (TreeNode right : rightTrees) {
                    TreeNode node = new TreeNode(root); // fresh node per combination
                    node.left  = left;
                    node.right = right;
                    result.add(node);
                }
            }
        }
        return result;
    }

    // -------------------------------------------------------------------------
    // Helper — serialize tree to string for readable test output
    // -------------------------------------------------------------------------

    /**
     * Serializes a BST to a bracket string for readable test output.
     * Format: val(left)(right), empty subtree shown as "_".
     *
     * @param node root of the tree
     * @return string representation
     */
    private static String serialize(TreeNode node) {
        if (node == null) return "_";
        return node.val + "(" + serialize(node.left) + ")(" + serialize(node.right) + ")";
    }

    // -------------------------------------------------------------------------
    // Main — Test Cases
    // -------------------------------------------------------------------------

    public static void main(String[] args) {
        // Test Case 1: n=1 → 1 unique BST
        List<TreeNode> t1 = generateTrees(1);
        System.out.println("Test 1 (n=1) count: " + t1.size()); // 1
        t1.forEach(r -> System.out.println("  " + serialize(r)));

        System.out.println();

        // Test Case 2: n=2 → 2 unique BSTs
        List<TreeNode> t2 = generateTrees(2);
        System.out.println("Test 2 (n=2) count: " + t2.size()); // 2
        t2.forEach(r -> System.out.println("  " + serialize(r)));

        System.out.println();

        // Test Case 3: n=3 → 5 unique BSTs
        List<TreeNode> t3 = generateTrees(3);
        System.out.println("Test 3 (n=3) count: " + t3.size()); // 5
        t3.forEach(r -> System.out.println("  " + serialize(r)));

        System.out.println();

        // Test Case 4: n=0 → empty list
        List<TreeNode> t4 = generateTrees(0);
        System.out.println("Test 4 (n=0) count: " + t4.size()); // 0

        System.out.println();

        // Test Case 5: n=5 → 42 unique BSTs (matches LC 96 Catalan count)
        System.out.println("Test 5 (n=5) count: " + generateTrees(5).size()); // 42
    }
}