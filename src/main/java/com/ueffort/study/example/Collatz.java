package com.ueffort.study.example;

import com.ueffort.study.base.Queue;
import stdlib.StdIn;
import stdlib.StdOut;

/**
 * 考拉兹猜想
 * 考拉兹猜想（英语：Collatz conjecture），又称为奇偶归一猜想、3n＋1猜想、冰雹猜想、角谷猜想、哈塞猜想、乌拉姆猜想或叙拉古猜想，
 * 是指对于每一个正整数，
 *      如果它是奇数，则对它乘3再加1，
 *      如果它是偶数，则对它除以2，
 *      如此循环，最终都能够得到1。
 * Created by tutu on 16-7-18.
 */
public class Collatz {
    private int number;
    public Collatz(int a){
        number = a;
    }
    /**
     * 根据猜想计算
     * @return
     */
    public Queue compute(){
        Queue<Integer> q = new Queue<>();
        while(number != 1){
            q.enqueue(number);
            if(number % 2 == 0){
                number /= 2;
            }else{
                number = number * 3 + 1;
            }
        }
        q.enqueue(number);
        return q;
    }

    public static void main(String[] args){
        String in = StdIn.readLine();
        Collatz c = new Collatz(Integer.parseInt(in));
        Queue q = c.compute();
        while(!q.isEmpty()){
            StdOut.print(q.dequeue() + " ");
        }
    }
}
