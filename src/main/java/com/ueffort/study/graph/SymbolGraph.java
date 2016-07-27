package com.ueffort.study.graph;

import com.ueffort.study.SymbolTable.ST;
import stdlib.In;
import stdlib.StdIn;
import stdlib.StdOut;

/**
 * 符号图
 * Created by GaoJie on 2016/7/27.
 */
public class SymbolGraph {
    private ST<String, Integer> st;
    private String[] keys;
    private Graph G;

    public SymbolGraph(String filename, String delim){
        st = new ST<String, Integer>();
        In in = new In(filename);
        while (in.hasNextLine()){
            String[] a = in.readLine().split(delim);

            for (int i = 0; i < a.length; i++)
                if (!st.contains(a[i]))  // 为每个不同字符串关联个索引
                    st.put(a[i], st.size());
        }
        keys = new String[st.size()];  // 用来获取顶点名的反向缩影是一个数组

        for(String name : st.keys())
            keys[st.get(name)] = name;

        G = new Graph(st.size());
        in = new In(filename);
        while (in.hasNextLine()){
            String[] a = in.readLine().split(delim);  // 将每一行的第一个顶点和改行的其他顶点相连
            int v = st.get(a[0]);
            for (int i = 1; i < a.length; i++)
                G.addEdge(v, st.get(a[i]));
        }
    }

    public boolean contains(String key){
        return st.contains(key);
    }

    public int index(String key){
        return st.get(key);
    }

    public String name(int v){
        return keys[v];
    }

    public Graph G(){
        return G;
    }

    public static void main(String[] args){
        String filename = args[0];
        String delim = args[1];
        SymbolGraph sg = new SymbolGraph(filename, delim);
        Graph G = sg.G();
        while (StdIn.hasNextLine()){
            String source = StdIn.readLine();
            for (int w : G.adj(sg.index(source)))
                StdOut.println(" " + sg.name(w));
        }
    }
}
