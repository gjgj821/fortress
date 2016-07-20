package com.ueffort.study.SymbolTable;

/**
 * 红黑二叉查找树
 * 最优的查找和插入效率，能够进行有序性相关的操作
 * 链接需要额外的空间
 * Created by GaoJie on 2016/7/18.
 */
public class RedBlackBST extends ST {
    private Node root;

    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private class Node{
        Comparable key;
        Object val;
        Node left, right;
        int N;  // 这棵子树中的节点总数
        boolean color;  // 由其父节点指向它的链接的颜色

        public Node(Comparable key, Object val, int N, boolean color){
            this.key = key;
            this.val = val;
            this.N = N;
            this.color = color;
        }
    }

    private boolean isRed(Node x){
        if (x == null) return false;
        return x.color == RED;
    }
    private Node rotateLeft(Node h){
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);
        return x;
    }

    private Node rotateRight(Node h){
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);
        return x;
    }

    @Override
    public void put(Comparable comparable, Object o) {
        root = put(root, comparable, o);
    }
    private Node put(Node h, Comparable comparable, Object o){
        if (h == null)
            return new Node(comparable, o, 1, RED);

        int cmp = comparable.compareTo(h.key);
        if (cmp < 0) h.left = put(h.left, comparable, o);
        else if(cmp > 0) h.right = put(h.right, comparable, o);
        else h.val = o;

        if (isRed(h.right) && !isRed(h.left)) h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right)) flipColors(h);

        h.N = size(h.left) + size(h.right) + 1;
        return h;
    }
    public void flipColors(Node h){

    }
    @Override
    public Object get(Comparable comparable) {
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
    private int size(Node n){
        return n.N;
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
}
