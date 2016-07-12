package com.ueffort.study.sort;

/**
 * 插入排序:对于长度为N的且主键不重复的数组，平均排序需要N^2/4次比较和N^2/4次交换
 *     最坏情况需要N^2/2次比较和N^2/2次交换
 *     最好情况需要N-1次比较和0次交换
 * Created by GaoJie on 2016/7/12.
 */
public class Insertion extends Example{
    public static void sort(Comparable[] a){
        // 将a[]按升序排列
        int N = a.length;
        for (int i = 1; i < N; i++){
            // 将a[i]插入到a[i-1]、a[i-2]、a[i-3]中
            for(int j = i; j > 0 && less(a[j], a[j-1]); j--)
                exch(a, j, j-1);
        }
    }
}
