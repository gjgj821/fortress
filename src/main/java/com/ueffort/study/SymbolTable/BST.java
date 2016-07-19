package com.ueffort.study.SymbolTable;

import com.ueffort.study.base.Queue;

/**
 * 二叉查找树
 * 实现简单，能够进行有序相关的操作
 * 没有性能上界的保证，链接需要额外的空间
 * Created by GaoJie on 2016/7/18.
 */
public class BST extends ST{
    private Node root;
    private class Node{
        private Comparable key;
        private Object val;
        private Node left, right;  // 指向子树的链接
        private int N;  // 以该节点为根的自述中的节点总数
        public Node(Comparable key, Object val, int N){
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }
    @Override
    public void put(Comparable comparable, Object o) {
        root = put(root, comparable, o);
    }
    private Node put(Node x, Comparable comparable, Object o){
        // 如果key存在于以x为根节点的子树中则更新它的值；
        // 否则将以key和val为键值对的新节点插入到该子树中
        if (x == null) return new Node(comparable, o, 1);
        int cmp = comparable.compareTo(x.key);
        if (cmp < 0) x.left = put(x.left, comparable, o);
        else if (cmp > 0) x.right = put(x.right, comparable, o);
        else x.val = o;
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    @Override
    public Object get(Comparable comparable) {
        return get(root, comparable);
    }
    private Object get(Node x, Comparable comparable){
        // 在以x为根节点的子树中查找并返回key所对应的值；
        // 如果找不到则返回null
        if(x == null) return null;
        int cmp = comparable.compareTo(x.key);
        if (cmp < 0) return get(x.left, comparable);
        else if (cmp > 0) return get(x.right, comparable);
        else return x.val;
    }

    @Override
    public void delete(Comparable comparable) {
        root = delete(root, comparable);
    }

    /**
     * 将指向即将被删除节点的链接保存为t
     * 将x指向它的后继节点min(t.right)
     * 将x的右链接（原本指向一颗所有节点都大于x.key的二叉查找树）指向deleteMin(t.right)
     *     也就是在删除后所有的节点仍然都大于x.key的子二叉查找树
     * 将x的左链接(本为空)设为t.left(其下所有的键都小于被删除的节点和它的后继节点)
     * @param x
     * @param comparable
     * @return
     */
    private Node delete(Node x, Comparable comparable){
        if (x == null) return null;
        int cmp = comparable.compareTo(x.key);
        if (cmp < 0) x.left = delete(x.left, comparable);
        else if (cmp > 0) x.right = delete(x.right, comparable);
        else{
            if (x.right == null) return x.left;
            if (x.left == null) return x.right;
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }
    @Override
    public void deleteMin(){
        root = deleteMin(root);
    }
    private Node deleteMin(Node x){
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    @Override
    public boolean contains(Comparable comparable) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return size(root) > 0;
    }

    @Override
    public int size() {
        return size(root);
    }
    private int size(Node x){
        if (x == null) return 0;
        return x.N;
    }

    @Override
    public Comparable min() {
        return min(root).key;
    }
    private Node min(Node x){
        if (x.left == null) return x;
        return min(x.left);
    }

    @Override
    public Comparable max() {
        return max(root).key;
    }
    private Node max(Node x){
        if (x.right == null) return x;
        return max(x.right);
    }

    @Override
    public Comparable floor(Comparable comparable) {
        Node x = floor(root, comparable);
        if(x == null) return null;
        return x.key;
    }
    private Node floor(Node x, Comparable comparable){
        if (x == null) return null;
        int cmp = comparable.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp < 0) return floor(x.left, comparable);
        Node t = floor(x.right, comparable);
        if (t != null) return t;
        else return x;
    }

    @Override
    public Comparable ceiling(Comparable comparable) {
        Node x = ceiling(root, comparable);
        if (x == null) return null;
        return x.key;
    }
    private Node ceiling(Node x, Comparable comparable){
        if (x == null) return null;
        int cmp = comparable.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp > 0) return ceiling(x.right, comparable);
        Node t = ceiling(x.left, comparable);
        if(t != null) return t;
        else return x;
    }

    @Override
    public int rank(Comparable comparable) {
        return rank(root, comparable);
    }
    private int rank(Node x, Comparable comparable){
        if (x == null) return 0;
        int cmp = comparable.compareTo(x.key);
        if (cmp < 0) return rank(x.left, comparable);
        else if(cmp > 0) return 1 + size(x.left) + rank(x.right, comparable);
        else return size(x.left);
    }

    @Override
    public Comparable select(int k) {
        return select(root, k).key;
    }
    private Node select(Node x, int k){
        if (x == null) return null;
        int t = size(x.left);
        if (t > k) return select(x.left, k);
        else if(t < k) return select(x.right, k - t - 1);
        else return x;
    }
    @Override
    public Iterable keys(){
        return keys(min(), max());
    }

    @Override
    public Iterable keys(Comparable lo, Comparable hi) {
        Queue<Comparable> queue = new Queue<>();
        keys(root, queue, lo, hi);
        return queue;
    }
    private void keys(Node x, Queue<Comparable> queue, Comparable lo, Comparable hi){
        if (x == null) return ;
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if (cmplo < 0) keys(x.left ,queue, lo, hi);
        if (cmplo <= 0 && cmphi >= 0) queue.enqueue(x.key);
        if (cmphi > 0) keys(x.right, queue, lo, hi);
    }
}
