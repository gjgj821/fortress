package com.ueffort.study.String;

/**
 * 字母表
 * Created by GaoJie on 2016/8/8.
 */
public class Alphabet {
    private char[] cA;
    public Alphabet(String s){
        cA = s.toCharArray();
    }

    public int toIndex(char c){
        for(int i = 0; i < cA.length; i++)
            if(cA[i] == c) return i;
        return -1;
    }

    public char toChar(int index){
        return cA[index];
    }

    public boolean contains(char c){
        return toIndex(c) >= 0;
    }

    public int R(){
        return cA.length;
    }

    public int lgR(){
        return cA.length / 8;
    }

    public int[] toIndices(String s){
        char[] c = s.toCharArray();
        int[] indices = new int[c.length];
        for(int i = 0; i < c.length; i++)
            indices[i] = toIndex(c[i]);
        return indices;
    }

    public String toChars(int[] indices){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < indices.length; i++)
            sb.append(toChar(indices[i]));
        return sb.toString();
    }
}
