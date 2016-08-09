package com.ueffort.study.String;

import com.ueffort.study.sort.Example;
import com.ueffort.study.sort.SortCompare;

/**
 * 三向字符串快速排序
 * Created by GaoJie on 2016/8/9.
 */
public class Quick3string extends MSD {
    @Override
    protected static void sort(String[] a, int lo, int hi, int d){
        if(hi <= lo) return;
        int lt = lo, gt = hi;
        int v = charAt(a[lo], d);
        int i = lo + 1;
        while(i <= gt){
            int t = charAt(a[i], d);
            if(t < v) Example.exch(a, lt++, i++);
            else if(t > v) Example.exch(a, i, gt--);
            else i++;
        }
        sort(a, lo, lt - 1, d);
        if (v >= 0) sort(a, lt, gt, d + 1);
        sort(a, gt + 1, hi, d);
    }
}
