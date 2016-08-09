package com.ueffort.study.String;

import com.ueffort.study.sort.Insertion;

/**
 * 高位优先的字符串排序
 * Created by GaoJie on 2016/8/8.
 */
public class MSD {
    protected static int R = 256;
    protected static final int M = 15;  // 小数组的切换阀值
    protected static String[] aux;  // 数据分类的辅助数组
    protected static int charAt(String s, int d){
        if(d < s.length())
            return s.charAt(d);
        else
            return -1;
    }

    public static void sort(String[] a){
        int N = a.length;
        aux = new String[N];
        sort(a, 0, N - 1, 0);
    }

    protected static void sort(String[] a, int lo, int hi, int d){
        if(hi <= lo + M){
            //用插入排序加快排序
            Insertion.sort(a, lo, hi, d);
            return ;
        }

        int[] count = new int[R + 2];  // 计算频率
        for(int i = lo; i <= hi; i++)
            count[charAt(a[i], d) + 2]++;

        for(int r = 0; r < R + 1; r++)
            count[r + 1] += count[r];  // 将频率转换为索引

        for(int i = lo; i <= hi; i++)
            aux[count[charAt(a[i], d) + 1]++] = a[i];

        for(int i = lo; i <= hi; i++)
            a[i] = aux[i - lo];

        for(int r = 0; r < R; r++)
            sort(a, lo + count[r], lo + count[r + 1] - 1, d + 1);
    }
}
