package com.ueffort.study.compress;

import com.ueffort.study.String.Alphabet;
import stdlib.BinaryStdIn;
import stdlib.BinaryStdOut;

/**
 * 双位编码：
 *      将字符转换为数字，通过字母表实现
 *      字符是8位，数字是2位，压缩率25%
 * Created by GaoJie on 2016/8/10.
 */
public class Double {
    public static void compress(){
        Alphabet DNA = new Alphabet("ACTG");
        String s = BinaryStdIn.readString();
        int N = s.length();
        BinaryStdOut.write(N);

        for(int i = 0; i < N; i++){
            int d = DNA.toIndex(s.charAt(i));
            BinaryStdOut.write(d, DNA.lgR());
        }
        BinaryStdOut.close();
    }

    public static void expand(){
        Alphabet DNA = new Alphabet("ACTG");
        int w = DNA.lgR();
        int N = BinaryStdIn.readInt();

        for(int i = 0; i < N; i++){
            char c = BinaryStdIn.readChar(w);
            BinaryStdOut.write(DNA.toChar(c));
        }
        BinaryStdOut.close();
    }
}
