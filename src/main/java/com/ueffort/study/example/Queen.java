package com.ueffort.study.example;

/**
 * 八皇后：
 *      在 8×8 的国际象棋棋盘上放置八个皇后，使得任何一个皇后都无法直接吃掉其他的皇后
 *      为了达到此目的，任两个皇后都不能处于同一条横行、纵行或斜线上。
 * 八皇后问题可以推广为更一般的n皇后摆放问题：
 *      这时棋盘的大小变为n×n，而皇后个数也变成n。
 *      当且仅当 n = 1 或 n ≥ 4 时问题有解。
 * Created by GaoJie on 2016/8/9.
 */
public class Queen {
    /**
     * 给定N，返回基础的摆放方式
     *      默认将0，0点作为第一个棋子点
     *      可以通过将结果进行上下左右平移得到N*N种摆放方法
     * @param N
     * @return
     */
    public static int[][] compute(int N){
        int[][] q = new int[N][N];

    }
}
