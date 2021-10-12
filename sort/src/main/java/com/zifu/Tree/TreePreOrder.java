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
   * 虽然从时间复杂度来看，递归和迭代都是O(logn)，但是这里的常系数相差甚大
   * 迭代 显示栈的出入就是push pop，但是递归栈的出入就很重了
   * 也就是说递归 O(a *Logn)，迭代是O(b*Logn)，这里的a>>b
   */
  public static void preTraverse(TreeNode root) {
    Stack<TreeNode> stack = new Stack<>();
    if (root == null) {
      return;
    }
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
   * Morris 遍历的核心思想是利用树的大量空闲指针，实现空间开销的极限缩减。其前序遍历规则总结如下：
   * 新建临时节点，令该节点为 root；
   * 如果当前节点的左子节点为空，将当前节点加入答案，并遍历当前节点的右子节点；
   * 如果当前节点的左子节点不为空，在当前节点的左子树中找到当前节点在中序遍历下的前驱节点：
   * 如果前驱节点的右子节点为空，将前驱节点的右子节点设置为当前节点。然后将当前节点加入答案，并将前驱节点的右子节点更新为当前节点。当前节点更新为当前节点的左子节点。
   * 如果前驱节点的右子节点为当前节点，将它的右子节点重新设为空。当前节点更新为当前节点的右子节点。
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
