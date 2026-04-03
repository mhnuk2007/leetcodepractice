package recursion;

/**
 * LeetCode 700 - Search in a Binary Search Tree
 *
 * Problem:
 *   Given the root of a BST and an integer val, find the node whose value
 *   equals val and return the subtree rooted at that node. Return null if
 *   not found.
 *
 * Approach: Recursive BST search
 *   BST property — left subtree < root < right subtree — lets us eliminate
 *   half the tree at each step.
 *   If val < root.val → search left subtree
 *   If val > root.val → search right subtree
 *   If val == root.val → return current node
 *
 * Example:
 *        8
 *       / \
 *      3   10
 *     / \    \
 *    1   6   14
 *       /
 *      4
 *   search(6) → returns subtree rooted at 6: [6, 4]
 *   search(100) → null
 *
 * Example:
 *   Input: root = [4,2,7,1,3], val = 2
 *
 *        4
 *       / \
 *      2   7
 *     / \
 *    1   3
 *
 *   search(2) → returns subtree rooted at 2: [2,1,3]
 *   search(100) → null
 * Time  : O(h) — h = height of tree; O(log n) balanced, O(n) skewed
 * Space : O(h) — recursion depth
 */
public class SearchInBST {

    public static void main(String[] args) {
        TreeNode root = buildBST();

        // Test 1: internal node with children
        System.out.println("Search 2:");
        printSubTree(searchBST(root, 2));
        // Expected: 2 1 3

        // Test 2: root value
        System.out.println("Search 4:");
        printSubTree(searchBST(root, 4));
        // Expected: 4 2 1 3 7

        // Test 3: leaf node
        System.out.println("Search 7:");
        printSubTree(searchBST(root, 7));
        // Expected: 7

        // Test 4: not found
        System.out.println("Search 100:");
        printSubTree(searchBST(root, 100));
        // Expected: Not found (null)

        // Test 5: iterative search
        System.out.println("Iterative search 2:");
        printSubTree(searchBSTIterative(root, 2));
        // Expected: 2 1 3
    }

    public static TreeNode searchBST(TreeNode root, int val) {
        if (root == null) return null;                                     // not found
        if (root.val == val) return root;                                  // found
        return val < root.val
                ? searchBST(root.left, val)                               // search left
                : searchBST(root.right, val);                             // search right
    }

    public static TreeNode searchBSTIterative(TreeNode root, int val) {
        while (root != null) {
            if (root.val == val) return root;                                  // found
            root = val < root.val ? root.left : root.right;                   // move left or right
        }
        return null;                                                           // not found
    }

    private static TreeNode buildBST() {
        TreeNode root    = new TreeNode(4);
        root.left        = new TreeNode(2);
        root.right       = new TreeNode(7);
        root.left.left   = new TreeNode(1);
        root.left.right  = new TreeNode(3);
        return root;
    }

    private static void printSubTree(TreeNode node) {
        if (node == null) { System.out.println("Not found (null)"); return; }
        preorder(node);
        System.out.println();
    }

    private static void preorder(TreeNode node) {
        if (node == null) return;
        System.out.print(node.val + " ");
        preorder(node.left);
        preorder(node.right);
    }
}
class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right){
        this.val = val;
        this.left = left;
        this.right = right;
    }
}