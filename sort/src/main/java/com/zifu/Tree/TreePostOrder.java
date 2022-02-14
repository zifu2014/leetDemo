package com.zifu.Tree;

import com.google.gson.internal.$Gson$Types;
import com.zifu.Tree.TreeInOrder.TreeNode;

import java.util.Deque;
import java.util.LinkedList;
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

        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode cur = root;
        TreeNode pre = null;
        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            TreeNode tmp = stack.peek();
            if (tmp.right != null) {
                if (tmp.right == pre) {
                    tmp = stack.pop();
                    pre = tmp;
                    System.out.print(tmp.val + " ");
                } else {
                    cur = tmp.right;
                }
            } else {
                tmp = stack.pop();
                pre = tmp;
                System.out.print(tmp.val + " ");
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
        TreeNode cur = dummy;
        while (cur != null) {
            if (cur.left != null) {
                TreeNode tmp = cur.left;
                while (tmp.right != null && tmp.right != cur) {
                    tmp = tmp.right;
                }
                if (tmp.right == null) {
                    tmp.right = cur;
                    cur = cur.left;
                } else {
                    //把cur的left到右子节点这条链倒序输出
                    TreeNode start = cur.left;
                    TreeNode end = tmp;
                    print(start, end);
                    cur = cur.right;
                    tmp.right = null;
                }
            } else {
                cur = cur.right;
            }
        }
    }

    private static void print(TreeNode start, TreeNode end) {
        Deque<TreeNode> cache = new LinkedList<>();
        while (true) {
            cache.push(start);
            if (start == end) {
                break;
            }
            start = start.right;
        }
        while (!cache.isEmpty()) {
            System.out.print(cache.pop().val + " ");
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
