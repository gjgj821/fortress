package com.ueffort.study.base;

import java.util.Iterator;

/**
 * 双链表
 * Created by tutu on 16-7-6.
 */
public class DoubleNode<Item> implements Iterable<Item> {
    private Node first;  // 表头
    private Node last;  // 表尾
    private int N;  // 元素数量

    private class Node{
        // 定义了结点的嵌套类
        Item item;
        Node next;
        Node prev;
    }

    public boolean isEmpty(){
        return first == null;  // 或：N == 0
    }
    public int size(){
        return N;
    }
    public void append(Item item){
        // 向表头添加元素
        Node old = first;
        first = new Node();
        first.item = item;
        first.next = old;
        if (old != null) {
            old.prev = first;
        }else{
            last = first;
        }
        N++;
    }
    public Item shift(){
        // 从表头删除元素
        Item item = first.item;
        first = first.next;
        first.prev = null;
        N--;
        return item;
    }
    public void push(Item item){
        // 向表尾添加元素
        Node old = last;
        last = new Node();
        last.item = item;
        last.prev = old;
        if (old != null) {
            old.next = last;
        }else{
            first = last;
        }
        N++;
    }
    public Item pop(){
        // 从表尾删除元素
        Item item = last.item;
        last = last.prev;
        last.next = null;
        N--;
        return item;
    }
    public void delete(Item item){
        Node now = first;
        while(now.next != null){
            if(now.item != item) continue;
            Node left = now.prev;
            Node right = now.next;
            left.next = right;
            right.prev = left;
            now = null;
        }
        return ;
    }

    @Override
    public Iterator<Item> iterator(){
        return new ListIterator();
    }
    private class ListIterator implements Iterator<Item>{
        private Node current = first;
        public boolean hasNext(){
            return current != null;
        }
        public void remove(){

        }
        public Item next(){
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}