package com.ueffort.study.graph;

/**
 * 深度优先搜索： DepthFirstSearch
 * 深度优先搜索标记与起点连通的所有顶点所需的时间和顶点的度数之和成正比
 * Created by GaoJie on 2016/7/26.
 */
public class DFS extends Search{
    private boolean[] marked;
    private int count;

    public DFS(Graph G, int s){
        marked = new boolean[G.V()];
        dfs(G, s);
    }

    private void dfs(Graph G, int v){
        marked[v] = true;
        count++;
        for (int w : G.adj(v))
            if (!marked[w]) dfs(G, w);
    }

    @Override
    public boolean marked(int w){
        return marked[w];
    }

    @Override
    public int count(){
        return count;
    }
}
