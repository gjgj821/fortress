package com.ueffort.study.example;

import stdlib.StdIn;
import stdlib.StdOut;

import java.util.Arrays;

/**
 * 循环染色
 * 用红、蓝两种颜色对围成一圈的8个棋子染色。规定：若某两种染色方案通过旋转的方式可以重合，则只算一种。
 * Created by tutu on 16-7-18.
 */
public class LoopColor {
    private int number;
    public LoopColor(int a){
        number = a;
    }

    public int compute(){
        int m = 1 << number;  // 最多多少种方案
        int[] p = new int[m];  // 记录所有方案
        int a, b;
        int c = 0;
        Arrays.fill(p, 1);
        for(int i = 0; i < m; i++){  // 循环所有方案
            if (p[i] == 0) continue;
            a = i;
            for(int j = 0; j <= number; j++){
                // 每次移动一位就遍历所有同类旋转方案
                b = left(a);
                if (b == i) break;
                if (b > i){  // 之后的方案无效
                    p[b] = 0;
                }else{
                    p[i] = 0;
                    break;
                }
                a = b;
            }
            if(p[i] == 1) c++;
        }
        return c;
    }

    /**
     * 向左移动一位
     * @param a
     * @return
     */
    private int left(int a){
        int high = a >> (number - 1);
        a &= (1 << (number - 1)) - 1;
        a <<= 1;
        a |= high;
        return a;
    }

    public static void main(String[] args){
        String in = StdIn.readLine();
        LoopColor l = new LoopColor(Integer.parseInt(in));
        int times = l.compute();
        StdOut.println(times);
    }
}
