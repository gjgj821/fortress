package com.ueffort.study.example;

import stdlib.StdIn;
import stdlib.StdOut;

import java.util.Arrays;

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
            StdOut.println("move " + n + " From " + a + " To " + c);
        }else{
            base(n-1, a, c, b);
            StdOut.println("move " + n + " From " + a + " To " + c);
            base(n-1, b, a, c);
        }
    }

    /**
     * 进阶Hanoi塔问题
     * 增加消耗,保证最低消耗的前提下移动
     * 输入:
     * 给出矩阵a[i][j]，表示将一个盘子从i柱子移到j柱子要花费a[i][j]: (1 ≤ tij ≤ 10000; i ≠ j).
     * 再给出n，问将这n个盘子从1柱子移到3柱子的最少花费: (1 ≤ n ≤ 40)
     * 属于动态规划, 使用记忆化搜索, 根据限制条件初始化
     */
    private static int[][] cost = new int[4][4];
    private static int[][][][] dt = new int[41][4][4][4];

    public static int high(int n, int a, int b, int c){
        if(n == 1) return Math.min(cost[a][c], cost[a][b] + cost[b][c]);
        if(dt[n][a][b][c] != -1) return dt[n][a][b][c];
        int tmp1 = high(n-1, a, c, b) + cost[a][b]+high(n-1, b, a, c);
        int tmp2 = high(n-1, a, b, c) + cost[a][b]+high(n-1, c, b, a) + cost[b][c] + high(n-1, a, b, c);
        dt[n][a][b][c] = Math.min(tmp1, tmp2);
        return dt[n][a][b][c];
    }

    public static void main(String[] args){
        // base
        String item = StdIn.readLine();
        base(Integer.parseInt(item), 'A', 'B', 'C');
        // high
        for(int i = 1; i <= 3; i ++){
            item = StdIn.readLine();
            String[] ii = item.split(" ");
            for(int j = 1; j <= 3; j++){
                if (i == j)
                    cost[i][j] = 0;
                else
                    cost[i][j] = Integer.parseInt(ii[j - 1]);
            }
        }
        for(int i = 1; i <= 40; i++){
            for(int j = 1; j <= 3; j++){
                for(int k = 1; k <= 3; k++){
                    Arrays.fill(dt[i][j][k], -1);
                }
            }
        }
        item = StdIn.readLine();
        StdOut.println("cost " + high(Integer.parseInt(item), 0, 1, 2));
    }

}
