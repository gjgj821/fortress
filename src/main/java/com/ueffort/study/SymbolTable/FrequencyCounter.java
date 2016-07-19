package com.ueffort.study.SymbolTable;

import stdlib.StdIn;
import stdlib.StdOut;

/**
 * 频次统计
 * Created by GaoJie on 2016/7/18.
 */
public class FrequencyCounter {
    public static void main(String[] args){
        int minlen = Integer.parseInt(args[0]);  // 最小键长
        ST<String, Integer> st = new Binary(5);
        while(!StdIn.isEmpty()){
            String word = StdIn.readString();
            if (word.length() < minlen) continue;
            if (!st.contains(word)) st.put(word, 1);
            else st.put(word, st.get(word) + 1);
        }
        String max = " ";
        st.put(max, 0);
        for(String word : st.keys()){
            if (st.get(word) > st.get(max)){
                max = word;
            }
        }
        StdOut.println(max + " " + st.get(max));
    }
}
