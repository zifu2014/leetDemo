package com.zifu.ArraySort;

import com.sm.huichuan.common.utils.NewJsonUtils;

public class InsertSort {

  public static void sort(int[] arrays) {
    for (int i = 1; i < arrays.length; i++) {
      int temp = arrays[i];
      int j = i;
      while (j - 1 >= 0) {
        if (temp < arrays[j - 1]) {
          arrays[j] = arrays[j - 1];
          j = j - 1;
        } else {
          break;
        }
      }
      arrays[j] = temp;
    }
  }

  public static void main(String[] args) {
    int[] arg = new int[]{4, 1, 8, 23, 100, 43, 6};
    sort(arg);
    System.out.println(NewJsonUtils.toJson(arg));
  }
}
