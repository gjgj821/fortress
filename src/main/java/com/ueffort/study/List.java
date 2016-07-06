package com.ueffort.study;

import java.util.Iterator;

/**
 * 双链表
 * Created by tutu on 16-7-6.
 */
public class List<Item> implements Iterable<Item> {
  private Node first;  // 栈顶（最近添加的元素）
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
  public void push(Item item){
    // 向栈顶添加元素
    Node oldfirst = first;
    first = new Node();
    first.item = item;
    first.next = oldfirst;
    if (oldfirst != null) {
      oldfirst.prev = first;
    }
    N++;
  }
  public Item pop(){
    // 从栈顶删除元素
    Item item = first.item;
    first = first.next;
    first.prev = null;
    N--;
    return item;
  }
  public int index(Item item){
    return 0;
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
