package tree.advanced;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * LeetCode 113 - Path Sum II
 *
 * <p>Given the root of a binary tree and an integer targetSum, return all root-to-leaf paths
 * where each path's sum equals targetSum. A leaf is a node with no children.
 *
 * <p>Pattern: DFS (pre-order) with backtracking to collect all valid paths.
 * <ul>
 *   <li>Maintain a running list of nodes in the current path.</li>
 *   <li>Subtract current node value from target at each step.</li>
 *   <li>At a leaf, if the remaining sum is zero, add a copy of the current path to the results.</li>
 *   <li>Backtrack by removing the current node from the path after visiting its children.</li>
 * </ul>
 *
 * <p>Approaches:
 * <ol>
 *   <li>Recursive DFS with backtracking — clean, O(h) stack space for recursion.</li>
 *   <li>Iterative DFS with explicit stack storing (node, current_sum, current_path) — O(h) space for stack.</li>
 * </ol>
 *
 * @author mhnuk2007
 * @version 1.0
 */
public class PathSumII {

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
    // Approach 1: Recursive DFS with backtracking
    // Time  : O(N) — visits every node once. Copying paths can be O(N*H) in worst case.
    // Space : O(H) — recursion stack depth, O(N) for result list.
    // -------------------------------------------------------------------------

    /**
     * Finds all root-to-leaf paths in the binary tree that sum up to the targetSum.
     *
     * @param root      The root of the binary tree.
     * @param targetSum The target sum.
     * @return A list of lists, where each inner list represents a valid path.
     */
    public static List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        dfs(root, targetSum, result, path);
        return result;
    }

    /**
     * Helper method for recursive DFS to find paths with the target sum.
     *
     * @param node   The current node being visited.
     * @param sum    The remaining sum needed to reach the target.
     * @param result The list to store all valid paths.
     * @param path   The current path from the root to the current node.
     */
    static void dfs(TreeNode node, int sum, List<List<Integer>> result, List<Integer> path) {
        if (node == null) return;

        path.add(node.val);
        sum -= node.val;

        if (node.left == null && node.right == null && sum == 0) {
            result.add(new ArrayList<>(path)); // Found a valid path, add a copy
        } else {
            dfs(node.left, sum, result, path);
            dfs(node.right, sum, result, path);
        }
        path.remove(path.size() - 1); // Backtrack: remove the current node from the path
    }

    // -------------------------------------------------------------------------
    // Approach 2: Iterative DFS (explicit stack)
    // Time  : O(N) — visits every node once. Copying paths can be O(N*H) in worst case.
    // Space : O(H) — stack holds at most H states, O(N) for result list.
    // -------------------------------------------------------------------------

    /**
     * Represents the state of a node during iterative DFS, including the node itself,
     * the remaining sum, and the path taken to reach this node.
     */
    static class State {
        TreeNode node;
        int sum;
        List<Integer> path;

        State(TreeNode node, int sum, List<Integer> path) {
            this.node = node;
            this.sum = sum;
            this.path = path;
        }
    }

    /**
     * Finds all root-to-leaf paths in the binary tree that sum up to the targetSum
     * using an iterative DFS with an explicit stack.
     *
     * @param root      The root of the binary tree.
     * @param targetSum The target sum.
     * @return A list of lists, where each inner list represents a valid path.
     */
    public static List<List<Integer>> pathSum2(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Deque<State> stack = new ArrayDeque<>();
        stack.push(new State(root, targetSum - root.val, new ArrayList<>(List.of(root.val))));

        while (!stack.isEmpty()) {
            State curr = stack.pop();
            TreeNode node = curr.node;

            if (node.left == null && node.right == null && curr.sum == 0) {
                result.add(new ArrayList<>(curr.path));
            }

            // Push right child first so left child is processed first (DFS order)
            if (node.right != null) {
                List<Integer> newPath = new ArrayList<>(curr.path);
                newPath.add(node.right.val);
                stack.push(new State(node.right, curr.sum - node.right.val, newPath));
            }

            if (node.left != null) {
                List<Integer> newPath = new ArrayList<>(curr.path);
                newPath.add(node.left.val);
                stack.push(new State(node.left, curr.sum - node.left.val, newPath));
            }
        }

        return result;
    }

    // -------------------------------------------------------------------------
    // Main — Test Cases
    // -------------------------------------------------------------------------

    public static void main(String[] args) {
        // Test Case 1: path 5->4->11->2 = 22 → true
        //         5
        //        / \
        //       4   8
        //      /   / \
        //     11  13   4
        //    /  \       \
        //   7    2       1
        TreeNode root1 = new TreeNode(5);
        root1.left = new TreeNode(4);
        root1.right = new TreeNode(8);
        root1.left.left = new TreeNode(11);
        root1.right.left = new TreeNode(13);
        root1.right.right = new TreeNode(4);
        root1.left.left.left = new TreeNode(7);
        root1.left.left.right = new TreeNode(2);
        root1.right.right.right = new TreeNode(1);


        System.out.println("Test 1 - Recursive : " + pathSum(root1, 22));  // Expected: [[5, 4, 11, 2]]
        System.out.println("Test 1 - Iterative : " + pathSum2(root1, 22)); // Expected: [[5, 4, 11, 2]]

        System.out.println();

        // Test Case 2: Multiple paths
        //       10
        //      /  \
        //     5   -3
        //    / \    \
        //   3   2   11
        //  / \   \
        // 3  -2   1
        TreeNode root2 = new TreeNode(10);
        root2.left = new TreeNode(5);
        root2.right = new TreeNode(-3);
        root2.left.left = new TreeNode(3);
        root2.left.right = new TreeNode(2);
        root2.right.right = new TreeNode(11);
        root2.left.left.left = new TreeNode(3);
        root2.left.left.right = new TreeNode(-2);
        root2.left.right.right = new TreeNode(1);
        System.out.println("Test 2 - Recursive : " + pathSum(root2, 8)); // Expected: [[10, 5, 3, -2], [10, 5, 2, 1]]
        System.out.println("Test 2 - Iterative : " + pathSum2(root2, 8)); // Expected: [[10, 5, 2, 1], [10, 5, 3, -2]] (order might vary)

        System.out.println();

        // Test Case 3: No path
        TreeNode root3 = new TreeNode(1, new TreeNode(2), null);
        System.out.println("Test 3 - Recursive : " + pathSum(root3, 0)); // Expected: []
        System.out.println("Test 3 - Iterative : " + pathSum2(root3, 0)); // Expected: []

        System.out.println();

        // Test Case 4: Null root
        System.out.println("Test 4 - Recursive : " + pathSum(null, 0)); // Expected: []
        System.out.println("Test 4 - Iterative : " + pathSum2(null, 0)); // Expected: []
    }
}