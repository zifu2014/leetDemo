package com.zifu.ArraySort;

import com.sm.huichuan.common.utils.NewJsonUtils;

public class PopSort {

  public static void sort(int[] arrays) {
    //若上一次没有冒泡说明前缀已经有序，可直接退出
    boolean flag = false;
    //第一层控制总冒泡次数，为length-1次
    for (int i = 0 ; i < arrays.length -1 ; i++) {
      if (flag){
        return;
      }
      flag = true;
      //第二层控制每次冒泡的区间
      for (int j = 1; j < arrays.length - i; j ++) {
        if (arrays[j-1] > arrays[j] ) {
          //有任意一次冒泡，则置为false代表下次还要继续
          flag = false;
          int tmp = arrays[j-1];
          arrays[j-1] = arrays[j];
          arrays[j] = tmp;
        }
      }
    }
  }

  public static void main(String[] args) {
    int[] arg = new int[]{4, 1, 8, 23, 100, 43, 6};
    sort(arg);
    System.out.println(NewJsonUtils.toJson(arg));
  }
}
