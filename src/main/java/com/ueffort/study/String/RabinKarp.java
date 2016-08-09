package com.ueffort.study.String;

/**
 * Rabin-Karp指纹字符串查找
 *      通过对子字符串进行hash操作，匹配hash值减少每个字符的比较过程
 *      比较次数为线性级别
 * Created by GaoJie on 2016/8/9.
 */
public class RabinKarp {
    private String pat;  // 模式匹配字符串
    private long patHash;  //模式字符串的散列值
    private int M;  // 模式匹配字符串的长度
    private long Q;  // 一个很大的素数
    private int R = 256;  // 字母表的大小
    private long RM;  // R^(M - 1) % Q

    public RabinKarp(String pat){
        this.pat = pat;
        this.M = pat.length();
        Q = longRandomPrime();
        RM = 1;
        for (int i = 1; i <= M - 1; i++)
            RM = (R * RM) % Q;
        patHash = hash(pat, M);
    }

    public boolean check(int i){
        return true;
    }

    private long hash(String key, int M){
        long h = 0;
        for(int j = 0; j < M; j++)
            h = (R * h + key.charAt(j)) % Q;
        return h;
    }

    private int search(String txt){
        int N = txt.length();
        long txtHash = hash(txt, M);
        if(patHash == txtHash && check(0)) return 0;  // 一开始就匹配成功
        for(int i = M; i < N; i++){
            // 减去第一个数字，加上最后一个数字，再次检查匹配
            txtHash = (txtHash + Q - RM * txt.charAt(i - M) % Q) % Q;
            txtHash = (txtHash * R + txt.charAt(i)) % Q;
            if(patHash == txtHash)
                if(check(i - M + 1)) return i - M + 1;  // 找到匹配
        }
        return N;  // 未找到匹配
    }
}
