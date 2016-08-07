package com.ueffort.study.graph;

import stdlib.StdOut;

/**
 * 拓扑排序
 * Created by GaoJie on 2016/8/1.
 */
public class Topological {
    private Iterable<Integer> order;  // 顶点的拓扑排序

    public Topological(Digraph G){
        DirectedCycle cycleFinder = new DirectedCycle(G);
        if(!cycleFinder.hasCycle()){
            DFO dfs = new DFO(G);
            order = dfs.reversePost();
        }
    }

    public Topological(EdgeWeightedDigraph G){

    }

    public Iterable<Integer> order(){
        return order;
    }

    public boolean isDAG(){
        return order != null;
    }

    public static void main(String[] args){
        String filename = args[0];
        String separator = args[1];
        SymbolDigraph sd = new SymbolDigraph(filename, separator);

        Topological top = new Topological(sd.G());

        for(int v : top.order())
            StdOut.println(sd.name(v));
    }
}
