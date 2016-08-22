package com.ueffort.study.graph;

import com.ueffort.study.base.Stack;

/**
 * 权重有向环
 * Created by GaoJie on 2016/8/8.
 */
public class EdgeWeightedCycle {
    private boolean[] marked;
    private DirectedEdge[] edgeTo;
    private Stack<DirectedEdge> cycle;  // 有向环中的所有顶点
    private boolean[] onStack;  // 递归调用的栈上的所有顶点

    public EdgeWeightedCycle(EdgeWeightedDigraph G) {
        onStack = new boolean[G.V()];
        edgeTo = new DirectedEdge[G.V()];
        marked = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++)
            if (!marked[v]) dfs(G, v);
    }

    /**
     * 使用onStack保留递归调用上的所有顶点
     * 如果v->w时，有顶点在栈上，则形成环
     * @param G
     * @param v
     */
    private void dfs(EdgeWeightedDigraph G, int v){
        onStack[v] = true;
        marked[v] = true;
        for(DirectedEdge e : G.adj(v)){
            int w = e.to();
            if (this.hasCycle()) return ;
            else if(!marked[w]){
                edgeTo[w] = e;
                dfs(G, w);
            }else if(onStack[w]){
                cycle = new Stack<DirectedEdge>();
                for (DirectedEdge x = e; x.to() != w; x = edgeTo[x.to()])
                    cycle.push(x);
                cycle.push(e);
            }
        }
        onStack[v] = false;
    }

    public boolean hasCycle(){
        return cycle != null;
    }

    public Iterable<DirectedEdge> cycle(){
        return cycle;
    }
}
