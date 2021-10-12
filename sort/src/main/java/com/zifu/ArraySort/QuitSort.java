package com.zifu.ArraySort;

import com.sm.huichuan.common.utils.NewJsonUtils;

public class QuitSort {

  public static void sort(int[] arrays, int start, int end) {
    if (start >= end) {
      return;
    }
    //以第一个索引为基准
    int tmp = arrays[start], tmpIndex = start;
    int left = start, right = end;
    while (left < right) {
      while (left < right && arrays[right] >= tmp) {
        right--;
      }
      if (left < right) {
        arrays[tmpIndex] = arrays[right];
        tmpIndex = right;
      }
      while (left < right && arrays[left] <= tmp) {
        left++;
      }
      if (left < right) {
        arrays[tmpIndex] = arrays[left];
        tmpIndex = left;
      }
    }
    arrays[left] = tmp;
    sort(arrays, start, left - 1);
    sort(arrays, left + 1, end);
  }

  public static void main(String[] args) {
    int[] arg = new int[]{4, 1, -8, 23, 100, 43, 6};
    sort(arg, 0, arg.length - 1);
    System.out.println(NewJsonUtils.toJson(arg));
  }
}
