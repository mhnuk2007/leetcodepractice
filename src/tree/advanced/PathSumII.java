package tree.advanced;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * LeetCode 113 - Path Sum II
 *
 * <p>Given the root of a binary tree and an integer targetSum, return all
 * root-to-leaf paths where each path's sum equals targetSum.
 * A leaf is a node with no children.
 *
 * <p>Pattern: DFS (pre-order) with backtracking
 * <ul>
 *   <li>Maintain a running path list and subtract node value from target at each step</li>
 *   <li>At a leaf with remainder 0, snapshot the path into result</li>
 *   <li>Backtrack by removing the current node after visiting both children</li>
 * </ul>
 *
 * <p>Extends LC 112 (Path Sum) — instead of returning a boolean, collect all valid paths.
 *
 * <p>Approaches:
 * <ol>
 *   <li>Recursive DFS with backtracking — O(h) recursion stack, shared path list</li>
 *   <li>Iterative DFS with State stack  — each State carries its own path copy</li>
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
    // Approach 1: Recursive DFS with Backtracking
    // Time  : O(n²) — O(n) nodes × O(n) to copy path at each leaf
    // Space : O(h)  — recursion stack + shared path list (backtracked in place)
    // -------------------------------------------------------------------------

    /**
     * Returns all root-to-leaf paths whose values sum to targetSum.
     *
     * @param root      root of the binary tree
     * @param targetSum target path sum
     * @return list of all valid root-to-leaf paths
     */
    public static List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(root, targetSum, result, new ArrayList<>());
        return result;
    }

    /**
     * Recursive DFS helper with backtracking.
     *
     * <p>Adds current node to path, subtracts its value from sum, recurses into
     * children, then removes current node on return (backtrack).
     *
     * @param node   current node
     * @param sum    remaining sum after reaching this node
     * @param result accumulator for valid paths
     * @param path   current root-to-node path (mutated in place, backtracked)
     */
    private static void dfs(TreeNode node, int sum, List<List<Integer>> result, List<Integer> path) {
        if (node == null) return;
        path.add(node.val);
        sum -= node.val;
        if (node.left == null && node.right == null && sum == 0) {
            result.add(new ArrayList<>(path));  // snapshot — not a reference
        } else {
            dfs(node.left,  sum, result, path);
            dfs(node.right, sum, result, path);
        }
        path.remove(path.size() - 1);           // backtrack
    }

    // -------------------------------------------------------------------------
    // Approach 2: Iterative DFS with State Stack
    // Time  : O(n²) — O(n) nodes × O(n) to copy path per branch
    // Space : O(n * h) — each State carries its own path copy (no backtracking)
    // -------------------------------------------------------------------------

    /**
     * State for iterative DFS — holds node, remaining sum, and full path to this node.
     *
     * <p>Each branch gets its own path copy — no backtracking needed since
     * there is no shared mutable state. Trades extra space for iterative control.
     */
    static class State {
        TreeNode node;
        int sum;
        List<Integer> path;

        State(TreeNode node, int sum, List<Integer> path) {
            this.node = node;
            this.sum  = sum;
            this.path = path;
        }
    }

    /**
     * Returns all root-to-leaf paths whose values sum to targetSum using an
     * iterative DFS with an explicit State stack.
     *
     * <p>Each State carries a full copy of the path to that node — eliminating
     * the need for backtracking at the cost of O(n * h) space in the worst case.
     * Right child is pushed before left so left is processed first (DFS order).
     *
     * <p>Note: Uses {@code List.of()} (Java 9+). Replace with
     * {@code Arrays.asList()} for Java 8 compatibility.
     *
     * @param root      root of the binary tree
     * @param targetSum target path sum
     * @return list of all valid root-to-leaf paths
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

            // Push right first — left is processed first (LIFO)
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
        // Test Case 1: Single valid path 5->4->11->2 = 22
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
        System.out.println("Test 1 - Recursive : " + pathSum(root1, 22));  // [[5,4,11,2]]
        System.out.println("Test 1 - Iterative : " + pathSum2(root1, 22)); // [[5,4,11,2]]

        System.out.println();

        // Test Case 2: Multiple valid paths
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
        System.out.println("Test 2 - Recursive : " + pathSum(root2, 8));  // [[10,5,3,-2],[10,5,2,1]]
        System.out.println("Test 2 - Iterative : " + pathSum2(root2, 8)); // [[10,5,2,1],[10,5,3,-2]] (order may differ)

        System.out.println();

        // Test Case 3: No valid path
        TreeNode root3 = new TreeNode(1, new TreeNode(2), null);
        System.out.println("Test 3 - Recursive : " + pathSum(root3, 0));  // []
        System.out.println("Test 3 - Iterative : " + pathSum2(root3, 0)); // []

        System.out.println();

        // Test Case 4: Null root
        System.out.println("Test 4 - Recursive : " + pathSum(null, 0));  // []
        System.out.println("Test 4 - Iterative : " + pathSum2(null, 0)); // []

        System.out.println();

        // Test Case 5: Single node matching target
        System.out.println("Test 5 - Recursive : " + pathSum(new TreeNode(5), 5));  // [[5]]
        System.out.println("Test 5 - Iterative : " + pathSum2(new TreeNode(5), 5)); // [[5]]

        System.out.println();

        // Test Case 6: Negative values
        TreeNode root6 = new TreeNode(-2, null, new TreeNode(-3));
        System.out.println("Test 6 - Recursive : " + pathSum(root6, -5));  // [[-2,-3]]
        System.out.println("Test 6 - Iterative : " + pathSum2(root6, -5)); // [[-2,-3]]
    }
}