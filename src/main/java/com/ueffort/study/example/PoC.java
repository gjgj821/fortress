package com.ueffort.study.example;

import com.ueffort.study.base.Stack;
import stdlib.StdIn;
import stdlib.StdOut;

public class PoC {

    /**
     * 全排列:
     * 输入一个字符串，打印出该字符串中字符的所有排列。
     *      例如输入字符串abc，则输出由字符a，b，c所能排列出来的所有字符串
     *      abc，acb，bac，bca，cab和cba
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
     * Created by tutu on 16-8-4.
     */
    public static void combination(char[] s){

    }

    public static void main(String[] args){
        String s = StdIn.readLine();
        permutation(s.toCharArray(), 0, s.length());

    }
}
