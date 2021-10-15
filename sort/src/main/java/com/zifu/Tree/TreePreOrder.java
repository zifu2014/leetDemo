package com.zifu.Tree;

import java.util.Stack;
import lombok.Getter;
import lombok.Setter;

public class TreePreOrder {

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

  /**
   * 迭代法前序遍历
   * 时间复杂度：O(n) 所有节点遍历一次
   * 空间复杂度：O(logn) logn 为树的高度
   *
   * 虽然从时间复杂度来看，递归和迭代都是O(N)，但是这里的常系数相差甚大
   * 迭代 显示栈的出入就是push pop，但是递归栈的出入就很重了
   * 也就是说递归 O(a * N)，迭代是O(b * N)，这里的a>>b
   */
  public static void preTraverse(TreeNode root) {
    if (root == null) {
      return;
    }
    Stack<TreeNode> stack = new Stack<>();
    stack.push(root);
    while (!stack.isEmpty()) {
      TreeNode tmp = stack.pop();
      System.out.print(tmp.val + " ");
      if (tmp.right != null) {
        stack.push(tmp.right);
      }
      if (tmp.left != null) {
        stack.push(tmp.left);
      }
    }
  }

  /**
   * Morris 遍历的核心思想是利用树的大量空闲指针，实现空间开销的极限缩减
   * 1 新建临时节点P1 = root；
   * 2 如果P1.left为空，将P1加入答案，并P1 = P1.right；
   * 3 如果P1.left不为空，在P1的左子树中找到最右叶子节点P2;
   *    如果P2.right为空(没挂载过)，将P2.right=P1。然后将P1打印，之后P1=P1.left(这个逻辑分支前提是P1.left!=null)
   *    如果P2.right == P1，将P2.right重新设为空(取消挂载)。P1更新为P1.right(第二次遍历了 转到右子树)。
   * 重复步骤 2 和步骤 3，直到遍历结束。
   * 这样我们利用 Morris 遍历的方法，前序遍历该二叉树，即可实现线性时间与常数空间的遍历。
   * @param root
   */
  public static void morrisPreTraverse(TreeNode root) {
    if (root == null) {
      return;
    }
    
    TreeNode p1 = root, p2;
    while (p1 != null) {
      p2 = p1.left;
      if (p2 != null) {
        while (p2.right != null && p2.right != p1) {
          p2 = p2.right;
        }
        if (p2.right == null) {
          System.out.print(p1.val + " ");
          p2.right = p1;
          p1 = p1.left;
          continue;
        } else {
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
    TreeNode b = new TreeNode(3);
    TreeNode c = new TreeNode(6);
    TreeNode d = new TreeNode(2);
    TreeNode e = new TreeNode(4);

    a.left = b;
    a.right = c;
    b.left = d;
    b.right = e;
    morrisPreTraverse(a);
  }
}
