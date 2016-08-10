package com.ueffort.study.compress;

import com.ueffort.study.SymbolTable.Binary;
import com.ueffort.study.SymbolTable.TST;
import stdlib.BinaryStdIn;
import stdlib.BinaryStdOut;

/**
 * LZW:
 *      展开时不需要编译表
 *      通过生成最长前缀的单词查找树
 * Created by GaoJie on 2016/8/10.
 */
public class LZW {
    private static final int R = 256;
    private static final int L = 4096;
    private static final int W = 12;

    public static void compress(){
        String input = BinaryStdIn.readString();
        TST<Integer> st = new TST<>();

        for(int i = 0; i < R; i++)
            st.put("" + (char) i, i);
        int code = R + 1;

        while(input.length() > 0){
            String s = st.longestPrefixOf(input);  //找到匹配的最长前缀
            BinaryStdOut.write(st.get(s), W);

            int t = s.length();
            if(t < input.length() && code < L)
                st.put(input.substring(0, t + 1), code++);
            input = input.substring(t);  // 从输入中读取s
        }
        BinaryStdOut.write(R, W);
        BinaryStdOut.close();
    }

    public static void expand(){
        String[] st = new String[L];
        int i;

        // 用字符初始化编译表
        for(i = 0; i < R; i++)
            st[i] = "" + (char) i;
        st[i++] = " ";

        int codeword = BinaryStdIn.readInt(W);
        String val = st[codeword];
        while(true){
            BinaryStdOut.write(val);  //输出当前子字符串
            codeword = BinaryStdIn.readInt(W);
            if(codeword == R) break;
            String s = st[codeword];  // 获取下一个编码
            if(i == codeword)
                s = val + val.charAt(0);  //根据上一个字符串的首字母得到编码的字符串
            if(i < L)
                st[i++] = val + s.charAt(0);  // 为编译表添加新的条目
            val = s;
        }
        BinaryStdOut.close();
    }
}
