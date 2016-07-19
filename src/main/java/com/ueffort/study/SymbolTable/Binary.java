package com.ueffort.study.SymbolTable;

import com.ueffort.study.base.Queue;

/**
 * 二分查找（基于有序数组）
 * 最优的查找效率和空间需求，能够进行有序性相关的操作
 * 插入操作很慢
 * Created by GaoJie on 2016/7/18.
 */
public class Binary extends ST{
    private Comparable[] keys;
    private Object[] vals;
    private int N;
    public Binary(int capacity){
        keys = (Comparable[]) new Comparable[capacity];
        vals = (Object[]) new Object[capacity];
    }
    @Override
    public void put(Comparable comparable, Object o) {
        int i = rank(comparable);
        if (i < N && keys[i].compareTo(comparable) == 0){
            vals[i] = o;
            return ;
        }
        for (int j = N; j > i; j--){
            keys[j] = keys[j - 1];
            vals[j] = vals[j - 1];
        }
        keys[i] = comparable;
        vals[i] = o;
        N++;
    }

    @Override
    public Object get(Comparable comparable) {
        if(isEmpty()) return null;
        int i = rank(comparable);
        if (i < N && keys[i].compareTo(comparable) == 0) return vals[i];
        else return null;
    }

    @Override
    public void delete(Comparable comparable) {

    }

    @Override
    public boolean contains(Comparable comparable) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return N > 0;
    }

    @Override
    public int size() {
        return N;
    }

    @Override
    public Comparable min() {
        return keys[0];
    }

    @Override
    public Comparable max() {
        return keys[N - 1];
    }

    @Override
    public Comparable floor(Comparable comparable) {
        return null;
    }

    @Override
    public Comparable ceiling(Comparable comparable) {
        int i = rank(comparable);
        return keys[i];
    }

    @Override
    public int rank(Comparable comparable) {
        int lo = 0, hi = N - 1;
        while(lo <= hi){
            int mid = lo + (hi - lo) / 2;
            int cmp = comparable.compareTo(keys[mid]);
            if (cmp < 0) hi = mid - 1;
            else if(cmp > 0) lo = mid + 1;
            else return mid;
        }
        return lo;
    }

    @Override
    public Comparable select(int k) {
        return keys[k];
    }

    @Override
    public Iterable keys(Comparable lo, Comparable hi) {
        Queue<Comparable> q = new Queue<>();
        for (int i = rank(lo); i < rank(hi); i++){
            q.enqueue(keys[i]);
        }
        if (contains(hi)){
            q.enqueue(keys[rank(hi)]);
        }
        return q;
    }
}
