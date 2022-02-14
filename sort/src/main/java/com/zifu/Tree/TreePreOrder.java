package com.zifu.Tree;

import java.util.Deque;
import java.util.LinkedList;
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
    Deque<TreeNode> stack = new LinkedList<>();
    stack.push(root);
    while (!stack.isEmpty()) {
      TreeNode cur = stack.pop();
      System.out.print(cur.val + " ");

      if (cur.right != null) {
        stack.push(cur.right);
      }
      if (cur.left != null) {
        stack.push(cur.left);
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

    TreeNode cur = root;
    while (cur != null) {
      if (cur.left != null) {
        TreeNode tmp = cur.left;
        while (tmp.right != null && tmp.right != cur) {
          tmp = tmp.right;
        }
        if (tmp.right == cur) {
          tmp.right = null;
          cur = cur.right;
        } else {
          tmp.right = cur;
          System.out.print(cur.val + " ");
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
