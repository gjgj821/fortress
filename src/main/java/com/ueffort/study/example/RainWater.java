package com.ueffort.study.example;

import com.ueffort.study.base.Queue;
import stdlib.StdIn;
import stdlib.StdOut;

/**
 * 收集雨水问题
 * 给定n个非负整数，表示直方图的方柱的高度，
 * 同时，每个方柱的宽度假定都为1，
 * 若这样形状的容器收集雨水，可以盛多少雨水。
 * Created by tutu on 16-7-19.
 */
public class RainWater {
    /**
     * 通过左右依次访问,获取较高点后
     *      1. 按较低点计算注入雨水
     *      2. 另一边较高,确保雨水可以保存
     *      3. 较低边遍历,填充,直到遇到更高点
     * @return int
     */
    public static int trap(int[] a){
        int t = 0;
        int left = 0, right = a.length - 1;
        int l, r, h = 0;
        while(left < right){
            l = a[left];
            r = a[right];
            if (l < r){
                h = Math.max(h, l);
                t += h - l;
                left++;
            }else{
                h = Math.max(h, r);
                t += h -r;
                right--;
            }
        }
        return t;
    }

    public static void main(String[] args){
        String in = StdIn.readLine();
        String[] i = in.split(" ");
        int[] ii = new int[i.length];
        for(int j = 0; j < i.length; j ++){
            ii[j] = Integer.parseInt(i[j]);
        }
        StdOut.print(RainWater.trap(ii));
    }
}
