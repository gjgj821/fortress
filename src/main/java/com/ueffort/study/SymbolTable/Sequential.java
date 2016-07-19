package com.ueffort.study.SymbolTable;

import java.util.Comparator;
import java.util.Objects;

/**
 * 顺序查找（基于无序链表）
 * 适用于小型问题，对于大型符号表很慢
 * Created by GaoJie on 2016/7/18.
 */
public class Sequential extends ST {
    private Node first;

    @Override
    public void put(Comparable comparable, Object o) {
        for (Node x = first; x != null; x = x.next){
            if (comparable.equals(x.key)){
                x.val = o;
            }
        }
        first = new Node(comparable, o, first);
    }

    @Override
    public Object get(Comparable comparable) {
        for (Node x = first; x != null; x = x.next){
            if (comparable.equals(x.key))
                return x.val;
        }
        return null;
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
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Comparable min() {
        return null;
    }

    @Override
    public Comparable max() {
        return null;
    }

    @Override
    public Comparable floor(Comparable comparable) {
        return null;
    }

    @Override
    public Comparable ceiling(Comparable comparable) {
        return null;
    }

    @Override
    public int rank(Comparable comparable) {
        return 0;
    }

    @Override
    public Comparable select(int k) {
        return null;
    }

    @Override
    public Iterable keys(Comparable lo, Comparable hi) {
        return null;
    }

    private class Node{
        Comparable key;
        Object val;
        Node next;
        public Node(Comparable key, Object val, Node next){
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

}
