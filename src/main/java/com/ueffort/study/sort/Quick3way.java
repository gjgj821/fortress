package com.ueffort.study.sort;

/**
 * 三向切分的快速排序
 * Created by GaoJie on 2016/7/17.
 */
public class Quick3way extends Quick {
    protected static void sort(Comparable[] a, int lo, int hi){
         if (hi <= lo) return ;
         int lt = lo, i = lo + 1, gt = hi;
         Comparable v = a[lo];
         while (i <= gt){
              int cmp = a[i].compareTo(v);
              if (cmp < 0) exch(a, lt++, i++);
              else if(cmp > 0) exch(a, i, gt++);
              else i++;
         }
         sort(a, lo, lt - 1);
         sort(a, gt + 1, hi);
    }
}
