package com.zifu.ArraySort;

import com.sm.huichuan.common.utils.NewJsonUtils;

public class MergeSort {

  public static void sort(int[] arrays, int start, int end) {
    if (start >= end) {
      return;
    }
    int middle = (start + end) / 2;
    sort(arrays, start, middle);
    sort(arrays, middle + 1, end);
    merge(arrays, start, middle, end);
  }

  public static void merge(int[] a, int left, int mid, int right) {
    int[] tmp = new int[right - left + 1];
    int p1 = left, p2 = mid + 1, k = 0;

    while (p1 <= mid && p2 <= right) {
      if (a[p1] <= a[p2]) {
        tmp[k++] = a[p1++];
      } else {
        tmp[k++] = a[p2++];
      }
    }

    while (p1 <= mid) {
      tmp[k++] = a[p1++];
    }
    while (p2 <= right) {
      tmp[k++] = a[p2++];
    }

//    for (int n : tmp) {
//      a[left++] = n;
//    }
    for (int x = 0; x < tmp.length; x++) {
      a[x + left] = tmp[x];
    }
  }

  public static void main(String[] args) {
    int[] arg = new int[]{4, 1, 8, 23, 100, 43, 6};
    sort(arg, 0, arg.length - 1);
    System.out.println(NewJsonUtils.toJson(arg));
  }
}
