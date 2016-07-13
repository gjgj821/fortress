package com.ueffort.study.sort;

/**
 * 希尔排序:基于插入排序的快速的排序算法
 *      使间隔h的元素都是有序的，
 *      一个h有序数组就是h个互相独立的有序数组编织在一起组成一个数组
 *      权衡了字数组的规模和有序性：各个子数组都很短，排序之后字数组都是部分有序的
 * Created by GaoJie on 2016/7/13.
 */
public class Shell extends Example {
    public static void sort(Comparable[] a){
        // 将a[]按升序排列
        int N = a.length;
        int h = 1;
        while(h < N / 3) h = 3 * h + 1; // 1, 4, 13, 40, 121, 364, 1093, ...
        while(h >= 1){
            // 将数组变为h有序
            for (int i = h; i < N; i++){
                // 将a[i]插入到a[i-h], a[i-2*h], a[i-3*h]..之中
                for (int j = i; j >= h && less(a[j], a[j-h]); j -= h)
                    exch(a, j, j - h);
            }
            h = h / 3;
        }
    }
}
