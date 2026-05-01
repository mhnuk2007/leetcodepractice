package tree;

import java.util.ArrayList;
import java.util.List;

public class MyBinaryTree {
    static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(){};
        TreeNode(int val){
            this.val = val;
        }
        TreeNode(int val, TreeNode left, TreeNode right){
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        MyBinaryTree tree = new MyBinaryTree();
        System.out.println(tree.preorderTraversal(root));

    }
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        preOrderDFS(root, res);
    return res;
    }

    private void preOrderDFS(TreeNode node, List<Integer> res) {
        if(node == null) return;
        res.add(node.val);
        preOrderDFS(node.left, res);
        preOrderDFS(node.right, res);
    }
}
