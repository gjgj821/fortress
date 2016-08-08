package com.ueffort.study.String;

/**
 * 低位优先的字符串排序
 *      适用于所有字符串长度相同的情况下
 *      只需要循环长度次数，即可将字符串排序
 *      建索引计数法：
 *          1. 分组，计算频次
 *          2. 以频次顺位延长索引表
 *          3. 将字符串根据当前循环键分组
 *          4. 自动排序该键的字符串
 * Created by GaoJie on 2016/8/8.
 */
public class LSD {
    public static void sort(String[] a, int W){
        int N = a.length;
        int R = 256;
        String[] aux = new String[N];

        for(int d = W - 1; d >= 0; d--){
            int[] count = new int[R + 1];  // 计算出现频率
            for (int i = 0; i < N; i++)
                count[a[i].charAt(d) + 1]++;

            for(int r = 0; r < R; r++)
                count[r + 1] = count[r];  // 将频率转换为索引

            for(int i = 0; i < N; i++)
                aux[count[a[i].charAt(d)]++] = a[i];  // 将元素分类

            for(int i = 0; i < N; i++)
                a[i] = aux[i];
        }
    }
}
