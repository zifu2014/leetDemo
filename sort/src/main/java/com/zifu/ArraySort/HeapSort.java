package com.zifu.ArraySort;

import com.sm.huichuan.common.utils.NewJsonUtils;

public class HeapSort {

  public static void sort(int[] arrays) {
    //构建索引初始为1的数组，方便下面计算
    int[] nums = new int[arrays.length + 1];
    int i = 1;
    for (int num : arrays) {
      nums[i++] = num;
    }

    //下沉建堆,从第一个非叶子节点，轮训直到顶端，结束后此时数组为大顶堆
    for (int start = nums.length >> 1; start >= 1; start--) {
      //注意这里填写的end，是原声数组长度，正好对标nums最后一个索引
      down(nums, start, arrays.length);
    }

    //不断切断大顶堆顶部和尾部，重新下沉
    int l = arrays.length;
    while (l > 1) {
      int temp = nums[1];
      nums[1] = nums[l];
      nums[l] = temp;
      l--;
      down(nums, 1, l);
    }
    for (int index = 1, s = 0; index < nums.length; ) {
      arrays[s++] = nums[index++];
    }
  }

  public static void down(int[] array, int start, int end) {
    while (start << 1 <= end) {
      int index = start << 1;
      //当左右子节点都有，才比较两者大小，选其大
      if (index + 1 <= end && array[index + 1] > array[index]) {
        index++;
      }
      //当父节点非最大，则同最大子节点交换
      if (array[start] < array[index]) {
        int temp = array[start];
        array[start] = array[index];
        array[index] = temp;
        start = index;
      } else {
        break;
      }
    }
  }

  public static void main(String[] args) {
    int[] arg = new int[]{4, 1, 8, 23, 100, 43, 6};
    sort(arg);
    System.out.println(NewJsonUtils.toJson(arg));
  }
}
