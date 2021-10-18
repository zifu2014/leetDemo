package com.zifu.Tree;

import com.zifu.Tree.TreeInOrder.TreeNode;
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

  /**
   * 主体流程和中序很像，区别就是加了一个Pre前缀 让所有有右子树的非叶子节点在其右节点之后才打印出来
   *
   * @param root
   */
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

  /**
   * 大框架思路一样，不过需要额外的虚拟根节点dummy完成最后最上层的逆序输出 然后在节点第二次访问时(p2.right==p1),此时逆序输出p1.left->p2
   *
   * @param root
   */
  public static void morrisPostTraverse(TreeNode root) {
    if (root == null) {
      return;
    }
    TreeNode dummy = new TreeNode(-1);
    dummy.left = root;
    TreeNode p1 = dummy, p2 = null;
    while (p1 != null) {
      p2 = p1.left;
      if (p2 != null) {
        while (p2.right != null && p2.right != p1) {
          p2 = p2.right;
        }
        if (p2.right == null) {
          p2.right = p1;
          p1 = p1.left;
          continue;
        } else {
          reverse(p1.left, p2);
          print(p2, p1.left);
          reverse(p2, p1.left);
          p2.right = null;
          p1 = p1.right;
        }
      } else {
        p1 = p1.right;
      }
    }
  }

  /**
   * 思考哪里写错了
   * 参考 https://www.cnblogs.com/anniekim/archive/2013/06/15/morristraversal.html
   * @param start
   * @param end
   */
  private static void reverse(TreeNode start, TreeNode end) {
    if (start == end) {
      return;
    }
    TreeNode cur = start;
    TreeNode pre = null;
    while (cur != end.right) {
      TreeNode next = cur.right;
      cur.right = pre;
      pre = cur;
      cur = next;
    }
  }

  private static void print(TreeNode start, TreeNode end) {
    if (start == end) {
      System.out.print(start.val + " ");
    }
    TreeNode cur = start;
    while (cur != end.right) {
      System.out.print(cur.val + " ");
      cur = cur.right;
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
    morrisPostTraverse(a);
  }
}
