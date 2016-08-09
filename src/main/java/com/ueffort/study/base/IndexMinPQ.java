package com.ueffort.study.base;

/**
 * 索引优先队列
 * Created by GaoJie on 2016/8/6.
 */
public class IndexMinPQ<Key extends Comparable<Key>>  {
    private Node[] pq;  // 基于堆的完全二叉树
    private int N = 0;  // 存储与pq[1..N]中，pq[0]没有使用
    private Node[] iq;  // 保存索引关系

    private class Node{
        private int index;
        private Key key;
        private int priority;
        public Node(int index, Key key, int priority){
            this.index = index;
            this.key = key;
            this.priority = priority;
        }
    }
    public IndexMinPQ(int maxN){
        pq = (Node[]) new Object[maxN + 1];
        iq = (Node[]) new Object[maxN + 1];
    }
    public boolean isEmpty(){
        return N == 0;
    }
    public int size(){
        return N;
    }
    public void insert(int index, Key v){
        N++;
        Node n = new Node(index, v, N);
        pq[N] = n;
        iq[index] = n;
        swim(N);
    }

    public void change(int index, Key k){
        iq[index].key = k;
        swim(iq[index].priority);
    }
    public Key delMin(){
        Node min = pq[0];  // 从根节点得到最小元素
        exch(1, N--);  // 将其和最后一个节点交换

        pq[N + 1] = null;  // 防止对象游离
        iq[min.index] = null;
        sink(1);  // 恢复堆的有序性
        return min.key;
    }
    public boolean contains(int index){
        return iq[index] != null;
    }
    private boolean more(int i, int j){
        return pq[i].key.compareTo(pq[j].key) > 0;
    }
    private void exch(int i, int j){
        Node t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
        pq[i].priority = i;
        pq[j].priority = j;
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
