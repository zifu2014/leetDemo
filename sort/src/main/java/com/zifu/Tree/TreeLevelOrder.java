package com.zifu.Tree;

import com.zifu.Tree.TreePostOrder.TreeNode;
import java.util.LinkedList;
import java.util.Queue;
import lombok.Getter;
import lombok.Setter;

public class TreeLevelOrder {

  @Getter
  @Setter
  public static class TreeNode {

    private int val;
    private TreeNode left;
    private TreeNode right;

    public TreeNode(int val) {
      this.val = val;
    }
  }


  public static void levelOrder(TreeNode root) {
    if (root == null) {
      return;
    }
    Queue<TreeNode> cache = new LinkedList<>();
    cache.add(root);
    while (!cache.isEmpty()) {
      TreeNode node = cache.poll();
      System.out.print(node.val + " ");
      if (node.left != null) {
        cache.add(node.left);
      }
      if (node.right != null) {
        cache.add(node.right);
      }
    }
  }

  public static void main(String[] args) {
    TreeNode a = new TreeNode(5);
    TreeNode b = new TreeNode(2);
    TreeNode c = new TreeNode(6);
    TreeNode d = new TreeNode(1);
    TreeNode e = new TreeNode(4);
    TreeNode f = new TreeNode(3);

    a.left = b;
    a.right = c;
    b.left = d;
    b.right = e;
    e.left = f;
    levelOrder(a);
  }
}
