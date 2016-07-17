package com.ueffort.study.sort;

import stdlib.StdRandom;

/**
 * 快速排序
 * Created by GaoJie on 2016/7/16.
 */
public class Quick extends Example {
    public static void sort(Comparable[] a){
        StdRandom.shuffle(a);  // 消除对输入的依赖
        sort(a, 0, a.length - 1);
    }
    protected static void sort(Comparable[] a, int lo, int hi){
        if (hi <= lo) return;
        int j = partition(a, lo, hi);  // 切分
        sort(a, lo, j - 1);  // 排序左半部分
        sort(a, j+1, hi);  // 排序右半部分
    }
    private static int partition(Comparable[] a, int lo, int hi){
        int i = lo, j = hi + 1;
        Comparable v = a[lo];
        while(true){
            while(less(a[++i], v)) if (i == hi) break;
            while(less(v, a[--j])) if (j == lo) break;
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }
}
