package com.zifu.ArraySort;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.sm.huichuan.common.utils.NewJsonUtils;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * 桶排序核心思想是将数据按范围均匀分布到N个桶排序，每个桶实际存储数量级越相似越好
 * 其时间复杂度(桶内部若使用快排)：假设数量级为N分为M个桶
 * O(N)+M*O(N/M*logN/M) = O(N)+NO(logN-logM)
 * <p>
 * 可见如果M等于N那么复杂度就会变成O(N)，其实也就变成计数排序了
 * <p>
 * 如果数据分布极其不均 比如[1,2,...100,100000]，此时分成100个桶，但只有2个桶有数
 * 实际起作用的M趋近于0 则为O(N)+O(NlogN) 还不如直接快排来的好
 * <p>
 * 如果直接分成(max-min)/1个桶，相当于浪费了大部分桶 O(100000) vs O(100log100) 你懂的
 * 所以用桶排序 对数据集要求其是能够均匀分布的
 *
 * @author zifu.lzf
 * @date 2021/12/14
 */
public class BucketSort {

    public static void sort(int[] array) {
        int bucketNum = 2;
        //获取最大值、最小值 100 1
        int min = 1;
        int max = 100;
        //分桶算法：根据最大值和最小值 + 桶个数 ->
        // 计算每个桶内存储预计范围bucketSize
        //之后轮训cur-min/bucketSize得出桶下标
        int bucketSize = (max - min) / bucketNum + 1;

        //用二维数组效率很慢，首先空间不一定是连续的，下标迅速访问优势不再，其次不断的空间重分配性能损耗也很大
//        int[][] tmp = new int[bucketNum][0];
//        for (int num : array) {
//            int index = (num - min) / bucketSize;
//            int len = tmp[index].length;
//            tmp[index] = Arrays.copyOf(tmp[index], len + 1);
//            tmp[index][len] = num;
//        }
//
//        for (int[] cur : tmp) {
//            Arrays.sort(cur);
//        }
//
//        int i = 0;
//        for (int[] cur : tmp) {
//            for (int n : cur) {
//                array[i++] = n;
//            }
//        }

        //这里用嵌套链表解决
        List<ArrayList<Integer>> xp = new ArrayList<>();
        for (int i = 0; i < bucketNum; i++) {
            xp.add(new ArrayList<>());
        }
        
        for (int num : array) {
            int index = (num - min) / bucketSize;
            Collections.addAll(xp.get(index), num);
        }

        for (List<Integer> cur : xp) {
            Collections.sort(cur);
        }
        int i = 0;
        for (List<Integer> cur : xp) {
            for (int n : cur) {
                array[i++] = n;
            }
        }
    }

    public static void main(String[] args) {
        int[] arg = new int[]{4, 1, 8, 23, 100, 43, 6};
        sort(arg);
        System.out.println(JSON.toJSONString(arg));
    }
}
