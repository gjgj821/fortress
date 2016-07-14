package com.ueffort.study.example;

/**
 * 大整数算法
 * Created by tutu on 16-7-14.
 */
public class BigInt {
    /**
     * 大整数加法
     * 求2个不超过200位非负整数的和
     * 先将2个整数按int数组传入,高位在前,低位在后排序
     * 优化:
     *      输入可以将每一位设置为万进制,用以加快计算
     * @param a Int[]
     * @param b Int[]
     * @return Int[]
     */
    public static int[] add(int[] a, int[] b){
        int diff = a.length - b.length;
        int[] c = new int[Math.max(a.length, b.length) + 1];
        Carry carry = new Carry();
        // 保证a的位数大于b
        if (diff < 0){
            c = b;
            b = a;
            a = c;
        }
        for(int i = c.length; i >= 0; i--){
            carry.out = 0;
            c[i] = innerAdd(i - 1 >= 0 ? a[i - 1] : 0, i - 1 - diff >= 0 ? b[i - 1 - diff] : 0, carry);
            carry.in = carry.out;
        }
        // 最后进位
        if(carry.in == 1){
            c[0] = carry.in;
        }
        return c;
    }

    private static class Carry{
        public int in = 0;  // 本位需要进位
        public int out = 0; // 上位需要进位
    }
    private static int innerAdd(int a, int b, Carry carry){
        int c = a + b + carry.in;
        carry.out = c >> 32;
        return c & 0xFFFFFFFF;
    }
}
