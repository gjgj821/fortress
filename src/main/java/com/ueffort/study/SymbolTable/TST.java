package com.ueffort.study.SymbolTable;

/**
 * 基于三向单词查找树的符号表
 * Created by GaoJie on 2016/8/9.
 */
public class TST<Value> {
    private Node root;  //树的根节点
    private class Node{
        char c;
        Node left, mid, right;  // 左中右三向单词查找树
        Value val;  //和字符串相关联的值
    }

    public Value get(String key){
        Node x = get(root, key, 0);
        if (x == null) return null;
        return (Value) x.val;
    }

    private Node get(Node x, String key, int d){
        if(x == null) return null;
        char c = key.charAt(d);
        if(c < x.c) return get(x.left, key, d);
        else if(c > x.c) return get(x.right, key, d);
        else if(d < key.length() - 1) return get(x.mid, key, d + 1);
        else return x;
    }

    public void put(String key, Value val){
        root = put(root, key, val, 0);
    }

    private Node put(Node x, String key, Value val, int d){
        char c = key.charAt(d);
        if(x == null){
            x = new Node();
            x.c = c;
        }
        if(c < x.c) x.left = put(x.left, key, val, d);
        else if(c > x.c) x.right = put(x.right, key, val, d);
        else if(d < key.length() - 1) x.mid = put(x.mid, key, val, d + 1);
        else x.val = val;
        return x;
    }

    public String longestPrefixOf(String s){
        int length = search(root, s, 0, 0);
        return s.substring(0, length);
    }

    private int search(Node x, String s, int d, int length){
        if(x == null) return length;
        if(x.val != null) length = d;
        if(d == s.length()) return length;
        char c = s.charAt(d);
        Node n = null;
        if(c < x.c) n = x.left;
        else if(c > x.c) n = x.right;
        else if(d < s.length() - 1) n = x.mid;
        return search(n, s, d + 1, length);
    }
}
