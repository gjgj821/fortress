package com.ueffort.study.graph;


import stdlib.In;
import stdlib.StdOut;

/**
 * 图搜索测试
 * Created by GaoJie on 2016/7/26.
 */
public class Test {
    public static void main(String[] args){
        Graph G = new Graph(new In(args[0]));
        int s = Integer.parseInt(args[1]);
        Search search = new DFS(G, s);
        for (int v = 0; v < G.V(); v++)
            if (search.marked(v))
                StdOut.print(v + " ");
        StdOut.println();

        if(search.count() != G.V())
            StdOut.print("NOT ");
        StdOut.println("connected");
    }
}
