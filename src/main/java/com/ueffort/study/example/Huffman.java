package com.ueffort.study.example;

import com.ueffort.study.base.Queue;
import com.ueffort.study.base.Stack;
import stdlib.StdIn;
import stdlib.StdOut;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;

/**
 * 霍夫曼编码:用于数据无损压缩
 * 出现概率高的字母使用较短的编码，反之出现概率低的则使用较长的编码，这便使编码之后的字符串的平均长度、期望值降低，从而达到无损压缩数据的目的
 *
 * （一）进行霍夫曼编码前，我们先创建一个霍夫曼树。
 *      ⒈将每个英文字母依照出现频率由小排到大，最小在左。
 *      ⒉每个字母都代表一个终端节点（叶节点），比较F.O.R.G.E.T六个字母中每个字母的出现频率，将最小的两个字母频率相加合成一个新的节点。如Fig.2所示，发现F与O的频率最小，故相加2+3=5。
 *      ⒊比较5.R.G.E.T，发现R与G的频率最小，故相加4+4=8。
 *      ⒋比较5.8.E.T，发现5与E的频率最小，故相加5+5=10。
 *      ⒌比较8.10.T，发现8与T的频率最小，故相加8+7=15。
 *      ⒍最后剩10.15，没有可以比较的对象，相加10+15=25。
 *  最后产生的树状图就是霍夫曼树。
 * （二）进行编码
 *      1.给霍夫曼树的所有左链接'0'与右链接'1'。
 *      2.从树根至树叶依序记录所有字母的编码。
 * Created by tutu on 16-7-26.
 */
public class Huffman {

    /**
     * Pseudo-code
     1:  begin
     2:     count frequencies of single characters (source units)
     3:     output(frequencies using Fibonacci Codes of degree 2)
     4:     sort them to non-decreasing sequence
     5:     create a leaf node (character, frequency c, left son = NULL, right son = NULL)
     6:  	   of the tree for each character and put nodes into queue F
     7:     while (|F|>=2) do
     8:      begin
     9:        pop the first two nodes (u1, u2) with the lowest
     10:  	      frequencies from sorted queue
     11:        create a node evaluated with sum of the chosen units,
     12:  	      successors are chosen units (eps, c(u1)+c(u2), u1, u2)
     13:        insert new node into queue
     14:      end
     15:     node evaluate with way from root to leaf node (left son 1, right son 0)
     16:     create output from coded intput characters
     17:  end
     */
    public static byte[] encode(HashMap map, char[] c){
        for(char cc : c){
            String s = (String) map.get(cc);

        }
        return null;
    }

    public static char[] decode(HashMap map, byte[] b){
        return null;
    }

    public static class Tree<Item> implements Comparator<Tree>{
        Tree left;
        Tree right;
        Item key;
        int freq;

        @Override
        public int compare(Tree t1, Tree t2) {
            return t1.freq > t2.freq ? 1 : t1.freq == t2.freq ? 0 : -1;
        }

        /**
         * 是否是支点
         * @return
         */
        public boolean isLeaf(){
            return this.left == null && this.right == null;
        }
    }

    public static Tree build(int[] freq){
        // 用优先队列处理更合适
        Stack<Tree> stack = new Stack<>();
        Tree left, right;
        for(int i = 0; i < freq.length; i++){
            if (freq[i] == 0) continue;
            Tree t = new Tree();
            t.key = i;
            t.freq = freq[i];
            stack.push(t);
        }
        while(stack.size() > 1){
            // todo sort: 最小的2个合并
            left = stack.pop();
            right = stack.pop();
            Tree t = new Tree();
            t.freq = left.freq + right.freq;
            t.left = left;
            t.right = right;
            stack.push(t);
        }
        return stack.top();
    }

    /**
     * 根据
     * @param t
     * @return
     */
    public static HashMap map(Tree t){
        HashMap m = new HashMap();
        Queue q = new Queue();
        code(t, q, m);
        return m;
    }

    private static void code(Tree t, Queue q, HashMap map){
        if(t.isLeaf()){
            String curcode = "";
            for (Object aQ : q) {
                int i = (int) aQ;
                curcode += "" + i;
            }
            map.put(t.key, curcode);
            return;
        }
        Queue lcode = q;
        Queue rcode = q;
        lcode.enqueue(0);
        rcode.enqueue(1);

        code(t.left, lcode, map);
        code(t.right, rcode, map);
    }

    public static void main(String[] args){
        String input = StdIn.readAll();
        char[] c = input.toCharArray();
        int[] freq = new int[255];
        Arrays.fill(freq, 0);
        for(char cc: c){
            freq[cc]++;
        }
        Tree root = Huffman.build(freq);
        HashMap map = Huffman.map(root);
        byte[] output = Huffman.encode(map, c);
        StdOut.println("compress ratio: " + output.length / c.length);
        StdOut.println("decode: ");
        StdOut.print(Huffman.decode(map, output));
    }
}
