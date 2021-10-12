package com.zifu.ListSort;


import com.zifu.ListSort.InsertSort.Node;
import lombok.val;

public class QuitSort {

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

  public static void sort(Node begin, Node end) {
    if (begin == null || end == null || begin == end) {
      return;
    }
    Node first = begin;
    Node second = begin.next;

    int tmp = begin.val;
    while (second != null && second != end.next) {
      if (second.val <= tmp) {
        first = first.next;
        if (second != first) {
          int rr = first.val;
          first.val = second.val;
          second.val = rr;
        }
      }
      second = second.next;
    }

    if (first != begin) {
      int rr = first.val;
      first.val = begin.val;
      begin.val = rr;
    }
    sort(begin, first);
    sort(first.next, end);
  }


  public static void main(String[] args) {
    Node d = new Node(2, null);
    Node c = new Node(7, d);
    Node b = new Node(1, c);
    Node a = new Node(6, b);
    Node head = new Node(3, a);

    sort(head, d);

    while (head != null) {
      System.out.print(head.val + "->");
      head = head.next;
    }
  }
}
