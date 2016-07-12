package com.ueffort.study.base;

import java.util.Iterator;

/**
 * 背包
 * Created by GaoJie on 2016/7/4.
 */
public class Bag<Item> implements Iterable<Item> {
    private Node first;  // 链表的首结点
    private class Node{
        Item item;
        Node next;
    }
    public void add(Item item){
        // 和Stack的push方法完全相同
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
    }

    @Override
    public Iterator<Item> iterator(){
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
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
