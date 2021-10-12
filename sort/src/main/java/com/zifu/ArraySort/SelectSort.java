package com.zifu.ArraySort;

import com.sm.huichuan.common.utils.NewJsonUtils;

public class SelectSort {

  public static void sort(int[] arrays) {
    for (int i = 0; i < arrays.length - 1; i++) {
      int maxIndex = 0;
      int j;
      for (j = 1; j < arrays.length - i; j++) {
        if (arrays[j] > arrays[maxIndex]) {
          maxIndex = j;
        }
      }
      //上面循环结束的j需要--，才是最后索引
      if (j - 1 != maxIndex) {
        int tmp = arrays[j - 1];
        arrays[j - 1] = arrays[maxIndex];
        arrays[maxIndex] = tmp;
      }
    }
  }

  public static void main(String[] args) {
    int[] arg = new int[]{4, 1, -8, 23, 100, 43, 6};
    sort(arg);
    System.out.println(NewJsonUtils.toJson(arg));
  }
}
