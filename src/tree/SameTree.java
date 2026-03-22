package tree;

/**
 * LeetCode 100 — Same Tree
 *
 * Problem: Given the roots of two binary trees p and q, return true if
 * they are structurally identical and all node values are equal.
 *
 * Approach: Bottom-up recursion.
 *   Base case 1: both null        → true  (identical empty subtrees)
 *   Base case 2: one null, one not → false (structure mismatch)
 *   Base case 3: values differ    → false (value mismatch)
 *   Combine: left subtrees same AND right subtrees same
 *
 * Time Complexity : O(n) — visits every node once
 * Space Complexity: O(h) — recursion stack depth equals tree height
 *                   O(log n) balanced, O(n) skewed
 */
public class SameTree {

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

    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;  // one null — structure mismatch
        if (p.val != q.val)         return false;  // value mismatch
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    public static void main(String[] args) {

        // Test 1: Identical trees
        System.out.println("=== Test 1: Identical trees ===");
        TreeNode p1 = new TreeNode(1);
        p1.left  = new TreeNode(2);
        p1.right = new TreeNode(3);
        TreeNode q1 = new TreeNode(1);
        q1.left  = new TreeNode(2);
        q1.right = new TreeNode(3);
        System.out.println(isSameTree(p1, q1)); // true

        // Test 2: Different values
        System.out.println("\n=== Test 2: Different values ===");
        TreeNode p2 = new TreeNode(1);
        p2.left  = new TreeNode(2);
        TreeNode q2 = new TreeNode(1);
        q2.left  = new TreeNode(3); // 3 != 2
        System.out.println(isSameTree(p2, q2)); // false

        // Test 3: Different structure — same values, different shape
        System.out.println("\n=== Test 3: Different structure ===");
        TreeNode p3 = new TreeNode(1);
        p3.left  = new TreeNode(2);
        TreeNode q3 = new TreeNode(1);
        q3.right = new TreeNode(2); // 2 is on right, not left
        System.out.println(isSameTree(p3, q3)); // false

        // Test 4: Both empty
        System.out.println("\n=== Test 4: Both empty ===");
        System.out.println(isSameTree(null, null)); // true

        // Test 5: One empty one not
        System.out.println("\n=== Test 5: One empty ===");
        System.out.println(isSameTree(new TreeNode(1), null)); // false

        // Test 6: Single node — same value
        System.out.println("\n=== Test 6: Single node same ===");
        System.out.println(isSameTree(new TreeNode(1), new TreeNode(1))); // true

        // Test 7: Single node — different value
        System.out.println("\n=== Test 7: Single node different ===");
        System.out.println(isSameTree(new TreeNode(1), new TreeNode(2))); // false
    }
}