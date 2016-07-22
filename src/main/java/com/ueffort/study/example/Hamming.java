package com.ueffort.study.example;

/**
 * 汉明距离计算（英语：Hamming distance）
 * 在实际应用中用于相似度计算
 * 通过对原有对象进行hash得到hashcode在判断2者间hashcode的汉名距离,得到相似度,一般用simhash
 * Created by tutu on 16-7-20.
 */
public class Hamming {
    /**
     * 两个等长字符串之间的汉明距离是两个字符串对应位置的不同字符的个数
     * 换句话说，它就是将一个字符串变换成另外一个字符串所需要替换的字符个数
     * @param s1
     * @param s2
     * @return
     */
    public int compute1(String s1, String s2) {
        int counter = 0;
        for (int k = 0; k < s1.length();k++) {
            if(s1.charAt(k) != s2.charAt(k)) {
                counter++;
            }
        }
        return counter;
    }

    /**
     * 整形直接求不相同的位即可
     * 直接将结果计算汉明重量即可得到2个整形的汉明距离
     * @param i1
     * @param i2
     * @return
     */
    public int compute2(int i1, int i2){
        return weight(i1 ^ i2);
    }

    /**
     * 汉明(Hamming-weight)重量计算
     * 整数二进制表示中1的个数
     * @param n
     * @return int
     */
    public int weight(int n){
        n = (n&0x55555555) + ((n>>1)&0x55555555);
        n = (n&0x33333333) + ((n>>2)&0x33333333);
        n = (n&0x0f0f0f0f) + ((n>>4)&0x0f0f0f0f);
        n = (n&0x00ff00ff) + ((n>>8)&0x00ff00ff);
        n = (n&0x0000ffff) + ((n>>16)&0x0000ffff);

        return n;
    }
}
