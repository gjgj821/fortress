package com.ueffort.study.sort;

/**
 * 自底向上的归并排序
 * 对于长度N的任意数组，自底向上的归并排序需要1/2NlgN~NlgN次比较，最多访问数组6NlgN次
 * Created by GaoJie on 2016/7/13.
 */
public class MergeBU extends Merge{
    public static void sort(Comparable[] a) {
        int N = a.length;
        aux = new Comparable[N];
        for (int sz = 1; sz < N; sz = sz + sz)
            for (int lo = 0; lo < N - sz; lo += sz + sz)
                merge(a, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, N - 1));
    }
}
