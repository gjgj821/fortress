package com.ueffort.study.sort;

/**
 * 归并排序
 * 对于长度为N的任意数组，自顶向下的归并排序需要1/2NlgN ~ NlgN次比较
 *                                          需要最多6NlgN次访问数组
 * 对于小规模字数组使用插入排序
 * Created by GaoJie on 2016/7/13.
 */
public class Merge extends Example{
    protected static Comparable[] aux;
    public static void sort(Comparable[] a){
        aux = new Comparable[a.length];
        sort(a, 0, a.length - 1);
    }
    private static void sort(Comparable[] a, int lo, int hi){
        // 将数组a[lo..hi]排序
        if (hi <= lo) return ;
        int mid = lo + (hi - lo) / 2;
        sort(a, lo, mid); //将左半边排序
        sort(a, mid + 1, hi);  //将右半边排序
        merge(a, lo, mid, hi); //归并结果
    }
    public static void merge(Comparable[] a, int lo, int mid, int hi){
        // 将a[lo..mid] 和 a[mid+1..hi] 归并
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++){
            // 将a[lo..hi]复制到aux[lo..hi]
            aux[k] = a[k];
        }
        for (int k = lo; k <= hi; k++){
            if (less(aux[j], aux[i])) a[k] = aux[j++];
            else a[k] = aux[i++];
        }
    }
}
