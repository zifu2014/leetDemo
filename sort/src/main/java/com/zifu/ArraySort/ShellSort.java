package com.zifu.ArraySort;

import com.sm.huichuan.common.utils.NewJsonUtils;

public class ShellSort {

  public static void sort(int[] arrays) {
    for (int n = arrays.length >> 1; n >= 1; n = n >> 1) {
      for (int i = n; i < arrays.length; i++) {
        int temp = arrays[i];
        int j = i;
        while (j - n >= 0) {
          if (temp < arrays[j - n]) {
            arrays[j] = arrays[j - n];
            j = j - n;
          } else {
            break;
          }
        }
        arrays[j] = temp;
      }
    }
  }

  public static void main(String[] args) {
    int[] arg = new int[]{4, 1, 8, 23, -2, 43, 6};
    sort(arg);
    System.out.println(NewJsonUtils.toJson(arg));
  }
}
