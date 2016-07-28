package com.ueffort.study.base;

import stdlib.StdIn;
import stdlib.StdOut;

/**
 * 最小优先队列
 * Created by GaoJie on 2016/7/28.
 */
public class MinPQ<Key extends Comparable<Key>> {
    private Key[] pq;  // 基于堆的完全二叉树
    private int N = 0;  // 存储与pq[1..N]中，pq[0]没有使用

    public MinPQ(int maxN){
        pq = (Key[]) new Comparable[maxN + 1];
    }
    public boolean isEmpty(){
        return N == 0;
    }
    public int size(){
        return N;
    }
    public void insert(Key v){
        pq[++N] = v;
        swim(N);
    }
    public Key delMin(){
        Key min = pq[1];  // 从根节点得到最小元素
        exch(1, N--);  // 将其和最后一个节点交换
        pq[N + 1] = null;  // 防止对象游离
        sink(1);  // 恢复堆的有序性
        return min;
    }
    private boolean more(int i, int j){
        return pq[i].compareTo(pq[j]) > 0;
    }
    public Key top(){
        return pq[1];
    }
    private void exch(int i, int j){
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }

    /**
     * 由下至上的堆有序化：上浮
     * 针对插入元素，加入到末尾，进行上浮操作
     * @param k
     */
    private void swim(int k){
        while(k > 1 && more(k / 2, k)){
            exch(k / 2, k);
            k = k / 2;
        }
    }

    /**
     * 由上至下的堆有序化：下沉
     * 删除最大，从顶端移除，进行下沉操作
     * @param k
     */
    private void sink(int k){
        while(2 * k <= N){
            int j = 2 * k;
            if (j < N && more(j, j+1)) j++;
            if (!more(k, j))break;
            exch(k, j);
            k = j;
        }
    }
}
