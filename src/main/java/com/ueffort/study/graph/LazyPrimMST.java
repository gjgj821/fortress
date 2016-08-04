package com.ueffort.study.graph;

import com.ueffort.study.base.MinPQ;
import com.ueffort.study.base.Queue;

/**
 * 最小生成树的Prim算法: Lazy
 *      基本的Prim算法，还可以优化MinPQ的数量
 * Created by GaoJie on 2016/8/4.
 */
public class LazyPrimMST {
    private boolean[] marked;  // 最小生成树的顶点
    private Queue<Edge> mst;  // 最小生成树的边
    private MinPQ<Edge> pq;  // 横切边（包括失效的边）

    public LazyPrimMST(EdgeWeightedGraph G){
        pq = new MinPQ<>(G.V());
        marked = new boolean[G.V()];
        mst = new Queue<>();

        visit(G, 0);  // 假设G是连通的
        while(!pq.isEmpty()){
            Edge e = pq.delMin();  // 得到当前最小权重的边

            int v = e.either(), w = e.other(v);
            if (marked[v] && marked[w]) continue;
            mst.enqueue(e);  // 跳过失效的边
            if (!marked[v]) visit(G, v);  // 将顶点添加到树中
            if (!marked[w]) visit(G, w);
        }
    }

    /**
     * 标记顶点v并将所有链接v和未被标记的顶点的边加入pq
     * @param G
     * @param v
     */
    private void visit(EdgeWeightedGraph G, int v){
        marked[v] = true;
        for (Edge e: G.adj(v))
            if (!marked[e.other(v)]) pq.insert(e);
    }

    public Iterable<Edge> edges(){
        return mst;
    }

    public double weight(){
        return 0.0;
    }
}
