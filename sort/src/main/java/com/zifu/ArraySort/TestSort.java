package com.zifu.ArraySort;

import com.alibaba.fastjson.JSON;
import com.sm.huichuan.common.utils.NewJsonUtils;

/**
 * @author zifu.lzf
 * @date 2021/12/7
 */
public class TestSort {

    public static void sort(int[] arrays) {
        int len = arrays.length;
        int[] calc = new int[len + 1];
        int start = 1;
        //移动成下标1开始的临时数组，方便后续左右取值
        for (int num : arrays) {
            calc[start++] = num;
        }
        System.out.println(JSON.toJSONString(calc));

        //通过下沉构建堆(升序 小顶堆)
        for (start = len / 2; start >= 1; start--) {
            down(start, len, calc);
        }
        System.out.println(JSON.toJSONString(calc));

        //每次获取首尾交换，并重构小顶堆 最后是一个从大到小的结果
        for (int i = len; i > 1; ) {
            int tmp = calc[i];
            calc[i] = calc[1];
            calc[1] = tmp;
            System.out.println("before " + JSON.toJSONString(calc));
            down(1, --i, calc);
            System.out.println("after " + JSON.toJSONString(calc));
        }
        for (int index = 1, s = 0; index < calc.length; ) {
            arrays[s++] = calc[index++];
        }
    }

    private static void down(int cur, int end, int[] calc) {
        while (cur * 2 <= end) {
            int swap = cur * 2;
            int right = cur * 2 + 1;
            if (right <= end && calc[right] < calc[swap]) {
                swap = right;
            }
            if (calc[swap] < calc[cur]) {
                int tmp = calc[swap];
                calc[swap] = calc[cur];
                calc[cur] = tmp;
                cur = swap;
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
