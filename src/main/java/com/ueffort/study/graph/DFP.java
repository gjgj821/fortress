package com.ueffort.study.graph;

import com.ueffort.study.base.Stack;

/**
 * 深度优先搜索查找图中的路径
 * 深度优先搜索得到从给定起点到任意标记顶点的路径所需的时间与路径的长度成正比
 * Created by GaoJie on 2016/7/26.
 */
public class DFP extends Paths{
    private boolean[] marked;  // 这个顶点上调用过dfs
    private int[] edgeTo;  // 从起点到一个顶点的已知路径上的最后一个顶点
    private final int s;  // 起点

    public  DFP(Graph G, int s){
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        dfs(G, s);
    }

    private void dfs(Graph G, int v){
        marked[v] = true;
        for(int w:G.adj(v)){
            if (!marked[w]){
                edgeTo[w] = v;
                dfs(G, w);
            }
        }
    }

    @Override
    public boolean hasPathTo(int v) {
        return marked[v];
    }

    @Override
    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<>();
        for(int x = v; x != s; x = edgeTo[x])
            path.push(x);
        path.push(s);
        return path;
    }
}
