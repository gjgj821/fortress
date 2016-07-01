package com.ueffort.study;

import stdlib.StdOut;
import stdlib.StdRandom;

/**
 * 累加器
 * Created by GaoJie on 2016/7/1.
 */
public class Accumulator {
    private double total;
    private int N;
    public void addDataValue(double val){
        N++;
        total += val;
    }
    public double mean(){
        return total / N;
    }
    public String toString(){
        return "Mean (" + N + " values): "
                + String.format("%7.5f", mean());
    }
    public static void main(String[] args){
        int T = Integer.parseInt(args[0]);
        Accumulator a = new Accumulator();
        for (int t = 0; t < T; t++){
            a.addDataValue(StdRandom.random());
        }
        StdOut.println(a);
    }
}
