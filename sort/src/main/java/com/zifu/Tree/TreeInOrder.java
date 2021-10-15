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
    stack.push(root);

    TreeNode cur = root.left;
    while (cur != null || !stack.isEmpty()) {
      /**
       * 不能从stack peek出来判断左子树，否则会陷入无限循环
       *       TreeNode tmp = stack.peek();
       *       while (tmp.left != null) {
       *         stack.push(tmp.left);
       *         tmp = tmp.left;
       *       }
       */
      while (cur != null) {
        stack.push(cur);
        cur = cur.left;
      }

      TreeNode node = stack.pop();
      System.out.print(node.val + " ");

      if (node.right != null) {
        cur = node.right;
      }
    }
  }

  public static void morrisInTraverse(TreeNode root) {
    if (root == null) {
      return;
    }
    TreeNode p1 = root, p2 = null;
    while (p1 != null) {
      p2 = p1.left;
      if (p2 != null) {
        //找到左子树最右子节点
        while (p2.right != null && p2.right != p1) {
          p2 = p2.right;
        }
        //第一次则右节点直接挂载,且p1为其左子节点返回
        if (p2.right == null) {
          p2.right = p1;
          p1 = p1.left;
          continue;
        } else {//第二次则输出中节点，并取消临时挂载
          System.out.print(p2.right.val + " ");
          p2.right = null;
          p1 = p1.right;
        }
      } else {
        System.out.print(p1.val + " ");
        p1 = p1.right;
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
