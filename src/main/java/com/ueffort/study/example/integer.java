package com.ueffort.study.example;

/**
 * 数字的相关算法
 * Created by tutu on 16-7-18.
 */
public class integer {
    /**
     * 实数的整数次幂
     * @param d
     * @param y
     * @return
     */
    public double power(double d, int y){
        if(y > 0){
            return power_positive(d, y);
        } else {
            return power_negative(d, y);
        }
    }
    private double power_positive(double n, int p){
        double pow = 1;
        if(p > 0)
            pow = n * power_positive(n, p - 1);
        return pow;
    }
    private double power_negative(double n, int p){
        double pow = 1;
        if(p < 0)
            pow = power_negative(n, 1 + p) / n;
        return pow;
    }
}
