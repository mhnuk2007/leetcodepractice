package tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * LC 199 — Binary Tree Right Side View
 *
 * Problem: Given the root of a binary tree, imagine standing on the right
 * side and return the values of the nodes you can see ordered top to bottom.
 * The rightmost node at each level is visible.
 *
 * Approach 1 — BFS (level order):
 *   Process level by level using the level-size trick.
 *   The last node polled in each level iteration is the rightmost — collect it.
 *
 * Approach 2 — DFS (preorder Right → Left):
 *   Visit root, then recurse RIGHT before LEFT.
 *   First node seen at each depth is the rightmost — track by depth.
 *
 * Time Complexity : O(n) — both approaches
 * Space Complexity: O(w) BFS (max level width), O(h) DFS (recursion height)
 */
public class RightSideView {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // ─── Approach 1: BFS — last node of each level ───────────────────────────
    public static List<Integer> rightSideViewBFS(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int n = queue.size();

            for (int i = 0; i < n; i++) {
                TreeNode node = queue.poll();

                // last node in this level — rightmost visible
                if (i == n - 1) result.add(node.val);

                if (node.left  != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }
        return result;
    }

    // ─── Approach 2: DFS — recurse right before left ─────────────────────────
    // Visit right child first — first node seen at each depth is rightmost.
    // Pass depth down (top-down), add to result only when depth == result.size()
    // meaning this is the first node we've seen at this depth level.
    public static List<Integer> rightSideViewDFS(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        dfs(root, 0, result);
        return result;
    }

    private static void dfs(TreeNode node, int depth, List<Integer> list) {
        if (node == null) return;

        // first node seen at this depth = rightmost (we visit right first)
        if (depth == list.size()) list.add(node.val);

        dfs(node.right, depth + 1, list); // right first — seen before left
        dfs(node.left,  depth + 1, list);
    }

    public static void main(String[] args) {

        // Test 1: LeetCode standard — [1,2,3,null,5,null,4]
        //        1          → see 1
        //       / \
        //      2   3        → see 3
        //       \   \
        //        5   4      → see 4
        System.out.println("=== Test 1: LeetCode standard ===");
        TreeNode root1 = new TreeNode(1);
        root1.left  = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.left.right  = new TreeNode(5);
        root1.right.right = new TreeNode(4);
        System.out.println("BFS: " + rightSideViewBFS(root1)); // [1, 3, 4]
        System.out.println("DFS: " + rightSideViewDFS(root1)); // [1, 3, 4]

        // Test 2: Left-heavy — right side reveals left subtree nodes
        //        1          → see 1
        //       /
        //      2            → see 2
        //     /
        //    3              → see 3
        System.out.println("\n=== Test 2: Left-heavy tree ===");
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.left.left = new TreeNode(3);
        System.out.println("BFS: " + rightSideViewBFS(root2)); // [1, 2, 3]
        System.out.println("DFS: " + rightSideViewDFS(root2)); // [1, 2, 3]

        // Test 3: Full balanced tree
        //        1          → see 1
        //       / \
        //      2   3        → see 3
        //     / \ / \
        //    4  5 6  7      → see 7
        System.out.println("\n=== Test 3: Full balanced tree ===");
        TreeNode root3 = new TreeNode(1);
        root3.left  = new TreeNode(2);
        root3.right = new TreeNode(3);
        root3.left.left   = new TreeNode(4);
        root3.left.right  = new TreeNode(5);
        root3.right.left  = new TreeNode(6);
        root3.right.right = new TreeNode(7);
        System.out.println("BFS: " + rightSideViewBFS(root3)); // [1, 3, 7]
        System.out.println("DFS: " + rightSideViewDFS(root3)); // [1, 3, 7]

        // Test 4: Single node
        System.out.println("\n=== Test 4: Single node ===");
        System.out.println("BFS: " + rightSideViewBFS(new TreeNode(1))); // [1]
        System.out.println("DFS: " + rightSideViewDFS(new TreeNode(1))); // [1]

        // Test 5: Empty tree
        System.out.println("\n=== Test 5: Empty tree ===");
        System.out.println("BFS: " + rightSideViewBFS(null)); // []
        System.out.println("DFS: " + rightSideViewDFS(null)); // []

        // Test 6: Right-skewed
        System.out.println("\n=== Test 6: Right-skewed ===");
        TreeNode root6 = new TreeNode(1);
        root6.right = new TreeNode(2);
        root6.right.right = new TreeNode(3);
        System.out.println("BFS: " + rightSideViewBFS(root6)); // [1, 2, 3]
        System.out.println("DFS: " + rightSideViewDFS(root6)); // [1, 2, 3]
    }
}