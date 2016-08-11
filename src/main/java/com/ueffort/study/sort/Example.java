package com.ueffort.study.sort;

import stdlib.In;
import stdlib.StdOut;

/**
 * 排序算法模板
 * 成本模型：
 *     在研究排序算法时，需要计算比较和交换的数量
 *     对于不交换元素的算法，计算访问数组的次数
 * Created by GaoJie on 2016/7/12.
 */
abstract public class Example {
    public static void sort(Comparable[] a){}
    protected static boolean less(Comparable v, Comparable w){
        return v.compareTo(w) < 0;
    }
    public static void exch(Comparable[] a, int i, int j){
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
    protected static void show(Comparable[] a){
        //在单行中打印数组
        for (int i = 0; i < a.length; i++){
            StdOut.print(a[i] + "");
        }
        StdOut.println();
    }
    public static boolean isSorted(Comparable[] a){
        //测试数组元素是否有序
        for (int i = 1; i < a.length; i++){
            if (less(a[i], a[i-1])) return false;
        }
        return true;
    }
    public static void main(String[] args){
        // 从标准输入读取字符串，将它们排序并输出
        String[] a = In.readStrings();
        sort(a);
        assert isSorted(a);
        show(a);
    }
}
