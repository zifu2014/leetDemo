package com.zifu.ArraySort;

import com.sm.huichuan.common.utils.NewJsonUtils;

public class ShellSort {

  public static void sort(int[] arrays) {
    int len = arrays.length;
    int step = len / 2;

    while (step > 0) {
      for (int start = step; start < len; start++) {
        int cur = start - step;
        int tmp = arrays[start];
        while (cur >= 0 && arrays[cur] > tmp) {
          arrays[cur + step] = arrays[cur];
          cur = cur - step;
        }
        arrays[cur + step] = tmp;
      }
      step = step / 2;
    }
  }

  public static void main(String[] args) {
    int[] arg = new int[]{4, 1, 8, 23, -2, 43, 6};
    sort(arg);
    System.out.println(NewJsonUtils.toJson(arg));
  }
}
