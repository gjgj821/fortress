package com.ueffort.study.example;

import stdlib.StdIn;

/**
 * Tower of Hanoi(汉诺塔)与进阶
 * Created by tutu on 16-7-13.
 */
public class Hanoi {
    /**
     * 基本的Hanoi塔问题
     * 有三根杆子A，B，C。A杆上有N个(N>1)穿孔圆盘，盘的尺寸由下到上依次变小。
     * 要求按下列规则将所有圆盘移至C杆：
     *      每次只能移动一个圆盘；
     *      大盘不能叠在小盘上面。
     * 提示：可将圆盘临时置于B杆，也可将从A杆移出的圆盘重新移回A杆，但都必须遵循上述两条规则。
     */
    public static void base(int n, char a, char b, char c){
        if(n == 1){
            System.out.println("move " + n + " From " + a + " To " + c);
        }else{
            base(n-1, a, c, b);
            System.out.println("move " + n + " From " + a + " To " + c);
            base(n-1, b, a, c);
        }
    }

    public static void main(String[] args){
        String item = StdIn.readLine();
        base(Integer.parseInt(item), 'A', 'B', 'C');
    }

}
