package com.zifu.ArraySort;

import com.sm.huichuan.common.utils.NewJsonUtils;

public class InsertSort {

  public static void sort(int[] arrays) {
    int len = arrays.length;
    for (int start = 1; start < len; start++) {
      int cur = start - 1;
      int tmp = arrays[start];
      while (cur >= 0 && arrays[cur] > tmp) {
        arrays[cur + 1] = arrays[cur];
        cur--;
      }
      arrays[cur + 1] = tmp;
    }
  }

  public static void main(String[] args) {
    int[] arg = new int[]{4, 1, 8, 23, 100, 43, 6};
    sort(arg);
    System.out.println(NewJsonUtils.toJson(arg));
  }
}
