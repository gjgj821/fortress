package com.ueffort.study.example;

import stdlib.StdIn;
import stdlib.StdOut;

public class PoC {

    /**
     * 全排列:
     * 输入一个字符串，打印出该字符串中字符的所有排列。
     *      例如输入字符串abc，则输出由字符a，b，c所能排列出来的所有字符串
     *      abc，acb，bac，bca，cab和cba
     * 思路:
     *      从头开始依次固定住,交换后续的位置
     *
     * Created by tutu on 16-8-2.
     */
    public static void permutation(char[] s, int from, int to) {
        if(to<=1)
            return;
        if(from == to){
            System.out.println(s);
        }else{
            for(int i = from; i < to; i++){
                swap(s, i, from);
                permutation(s, from+1, to);
                swap(s, from, i);
            }
        }
    }

    private static void swap(char[] s, int i1, int i2){
        char tmp = s[i1];
        s[i1] = s[i2];
        s[i2] = tmp;
    }

    /**
     * 全组合:
     * 输入一个字符串，打印出该字符串中字符的所有组合。
     *      例如输入字符串abc，则输出由字符a，b，c所能排列出来的所有字符串
     *      a, ab, abc, b, bc, c, ac
     * 思路：
     *      建立一个位图用于增量处理组合情况
     *      位图的长度就是字符的数量，循环增1
     *      通过判断当前数的每位的状态来显示字符，正好符合全组合的情况
     * Created by tutu on 16-8-4.
     */
    public static void combination(char[] s){
        int t = 1 << s.length;
        for(int i = 0; i < t; i++){
            StringBuilder sb = new StringBuilder(s.length);
            for(int j = 0; j < s.length; j++){
                if((i & 1 << j) > 0) sb.append(s[j]);
            }
            StdOut.println(sb.toString());
        }
    }

    public static void main(String[] args){
        String s = StdIn.readLine();
        StdOut.println("Permutation:");
        permutation(s.toCharArray(), 0, s.length());
        StdOut.println("Conbination:");
        combination(s.toCharArray());
    }
}
