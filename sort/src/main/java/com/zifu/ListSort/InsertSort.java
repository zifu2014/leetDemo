package com.zifu.ListSort;

import com.google.common.collect.Lists;
import com.sm.huichuan.common.utils.NewJsonUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InsertSort {

  public static class Node {

    private int val;
    private Node next;

    public Node(int val) {
      this.val = val;
      this.next = null;
    }

    public Node(int val, Node next) {
      this.val = val;
      this.next = next;
    }
  }

  public static Node sort(Node head) {
    if (head == null || head.next == null) {
      return null;
    }
    Node dummy = new Node(-1, head);
    Node pre = dummy.next;
    Node cur = pre.next;
    while (cur != null) {
      if (cur.val >= pre.val) {
        cur = cur.next;
        pre = pre.next;
      } else {
        pre.next = cur.next;

        Node a = dummy;
        Node b = a.next;
        while (b.val <= cur.val) {
          a = a.next;
          b = b.next;
        }
        a.next = cur;
        cur.next = b;
        cur = pre.next;
      }
    }
    return dummy.next;
  }

  public static void main(String[] args) {
    Node d = new Node(2, null);
    Node c = new Node(7, d);
    Node b = new Node(1, c);
    Node a = new Node(6, b);
    Node head = new Node(3, a);

    Node newHead = sort(head);

    while (newHead != null) {
      System.out.print(newHead.val + "->");
      newHead = newHead.next;
    }
  }
}
