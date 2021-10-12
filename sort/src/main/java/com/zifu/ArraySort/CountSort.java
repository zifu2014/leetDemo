package com.zifu.ArraySort;

import com.sm.huichuan.common.utils.NewJsonUtils;

public class CountSort {

  public static void sort(int[] array) {
    //获取最大最小值
    int min = 0, max = 0;
    for (int n : array) {
      if (n < min) {
        min = n;
      }
      if (n > max) {
        max = n;
      }
    }
    System.out.println(min);
    System.out.println(max);

    //开辟计数数组
    int[] cache = new int[max - min + 1];
    for (int i = 0; i < array.length; i++) {
      cache[array[i] - min]++;
    }

    System.out.println(NewJsonUtils.toJson(cache));
    int j = 0;
    for (int i = 0; i < cache.length; i++) {
      while (cache[i] >= 1) {
        array[j++] = i + min;
        cache[i]--;
      }
    }
  }

  public static void main(String[] args) {
    int[] arg = new int[]{4, 1, -8, 23, 100, 43, 6};
    sort(arg);
    System.out.println(NewJsonUtils.toJson(arg));
  }
}
