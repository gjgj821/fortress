package com.ueffort.study.sort;

/**
 * 选择排序：对于长度为N的数组，需要大约N^2/2次比较和N次交换
 * 运行时间和输入无关
 * 数据的移动是最少的
 * Created by GaoJie on 2016/7/12.
 */
public class Selection extends Example {
    public static void sort(Comparable[] a){
        // 将a[]按升序排列
        int N = a.length;
        for (int i = 0; i < N; i++){
            // 将a[i]和a[i+1..N]中最小的元素交换
            int min = i;
            for (int j = i + 1; j < N; j++){
                if (less(a[j], a[min])) min = j;
            }
            exch(a, i, min);
        }
    }
}
