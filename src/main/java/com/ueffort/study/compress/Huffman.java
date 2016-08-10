package com.ueffort.study.compress;

import com.ueffort.study.SymbolTable.Binary;
import com.ueffort.study.base.MinPQ;
import stdlib.BinaryStdIn;
import stdlib.BinaryStdOut;

/**
 * 霍夫曼压缩：
 *      通过构建单词查找树，根据字符出现的频率得到每个字符的压缩后的进制
 *      压缩进制：左0，右1的规则遍历每个树叶节点，得到对应的2进制表示方式
 * Created by GaoJie on 2016/8/10.
 */
public class Huffman {
    private static int R = 256;

    private static class Node implements Comparable<Node>{
        private char ch;
        private int freq;
        private final Node left, right;

        Node(char ch, int freq, Node left, Node right){
            this.ch = ch;
            this.freq = freq;
            this.left = left;
            this.right = right;
        }

        public boolean isLeaf(){
            return left == null && right == null;
        }

        public int compareTo(Node that){
            return this.freq - that.freq;
        }
    }

    public static void expand(){
        Node root = readTrie();
        int N = BinaryStdIn.readInt();
        for(int i = 0; i < N; i++){
            Node x = root;
            while(!x.isLeaf())
                if(BinaryStdIn.readBoolean())
                    x = x.right;
                else x = x.left;
            BinaryStdOut.write(x.ch);
        }
        BinaryStdOut.close();
    }

    public static void compress(){
        String s = BinaryStdIn.readString();
        char[] input = s.toCharArray();

        int[] freq = new int[R];
        for(int i = 0; i < input.length; i++)
            freq[input[i]]++;

        // 构造编码树
        Node root = buildTrie(freq);
        String[] st = new String[R];
        buildCode(st, root, "");
        writeTrie(root);
        BinaryStdOut.write(input.length);

        for(int i =0; i < input.length; i++){
            String code = st[input[i]];
            for(int j = 0; j < code.length(); j++)
                if(code.charAt(j) == '1')
                    BinaryStdOut.write(true);
                else BinaryStdOut.write(false);
        }
    }
    private static Node buildTrie(int[] freq){
        MinPQ<Node> pq = new MinPQ<>(R);
        for(char c = 0; c < R; c++)
            if(freq[c] > 0)
                pq.insert(new Node(c, freq[c], null, null));

        while(pq.size() > 1){
            Node x = pq.delMin();
            Node y = pq.delMin();
            Node parent = new Node('\0', x.freq + y.freq, x, y);
            pq.insert(parent);
        }
        return pq.delMin();
    }

    /**
     * 使用单词查找树构造编译表
     * @param root
     * @return
     */
    private static String[] buildCode(Node root){
        String[] st = new String[R];
        buildCode(st, root, "");
        return st;
    }

    /**
     * 使用单词查找树构造编译表（递归）
     * @param st
     * @param x
     * @param s
     */
    private static void buildCode(String[] st, Node x, String s){
        if(x.isLeaf()){
            st[x.ch] = s;
            return;
        }
        buildCode(st, x.left, s + '0');
        buildCode(st, x.right, s + '1');
    }

    /**
     * 输出单词查找树的比特字符串
     * @param x
     */
    private static void writeTrie(Node x){
        if(x.isLeaf()){
            BinaryStdOut.write(true);
            BinaryStdOut.write(x.ch);
            return;
        }
        BinaryStdOut.write(false);
        writeTrie(x.left);
        writeTrie(x.right);
    }

    private static Node readTrie(){
        if(BinaryStdIn.readBoolean())
            return new Node(BinaryStdIn.readChar(), 0, null, null);
        return new Node('\0', 0, readTrie(), readTrie());
    }
}
