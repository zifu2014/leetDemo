package com.zifu.Tree;

import java.util.Stack;
import lombok.Getter;
import lombok.Setter;

public class TreeInOrder {

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

  public static void inTraverse(TreeNode root) {
    if (root == null) {
      return;
    }

    Stack<TreeNode> stack = new Stack<>();
    TreeNode cur = root;
    while (cur != null || !stack.isEmpty()) {
      while (cur != null) {
        stack.push(cur);
        cur = cur.left;
      }

      TreeNode node = stack.pop();
      System.out.print(node.val + " ");
      cur = node.right;
    }
  }

  public static void morrisInTraverse(TreeNode root) {
    if (root == null) {
      return;
    }

    TreeNode cur = root;
    while (cur != null) {
      if (cur.left != null) {
        TreeNode tmp = cur.left;
        while (tmp.right != null && tmp.right != cur) {
          tmp = tmp.right;
        }
        if (tmp.right == cur) {
          tmp.right = null;
          System.out.print(cur.val + " ");
          cur = cur.right;
        } else {
          tmp.right = cur;
          cur = cur.left;
        }
      } else {
        System.out.print(cur.val + " ");
        cur = cur.right;
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
    morrisInTraverse(a);
  }
}
