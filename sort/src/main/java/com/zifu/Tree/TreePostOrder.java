package com.zifu.Tree;

import java.util.Stack;
import lombok.Getter;
import lombok.Setter;

public class TreePostOrder {

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

  public static void postInvarse(TreeNode root) {
    if (root == null) {
      return;
    }

    Stack<TreeNode> stack = new Stack<>();
    stack.push(root);
    TreeNode cur = root.left;

    //利用这个来判断当有右子树时是否弹出
    TreeNode pre = null;

    while (cur != null || !stack.isEmpty()) {
      while (cur != null) {
        stack.push(cur);
        cur = cur.left;
      }

      TreeNode node = stack.pop();
      if (node.right == null) {
        System.out.print(node.val + " ");
        pre = node;
      } else {
        //有右子树，判断右子树是否处理过，处理则打印，否则再次入栈先处理右子树
        //还没有处理右子树
        if (node.right != pre) {
          stack.push(node);
          cur = node.right;
        } else {
          System.out.print(node.val + " ");
          pre = node;
        }
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
    postInvarse(a);
  }
}
