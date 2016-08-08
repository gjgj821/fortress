package com.ueffort.study.graph;

import com.ueffort.study.base.Bag;
import com.ueffort.study.base.IndexMinPQ;

/**
 * 最小生成树Prim算法：
 *  将不在树中的v的权重记录在索引优先队列中
 *  每个v都只记录下权重最小的那条边的权重
 * Created by GaoJie on 2016/8/4.
 */
public class PrimMST {
    private Edge[] edgeTo;  // 距离树最近的边
    private double[] distTo;
    private boolean[] marked;
    private IndexMinPQ<Double> pq;  // 有效的横切边

    public PrimMST(EdgeWeightedGraph G){
        edgeTo = new Edge[G.V()];
        distTo = new double[G.V()];
        marked = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;
        pq = new IndexMinPQ<Double>(G.V());

        distTo[0] = 0.0;
        pq.insert(0, 0.0);  // 用顶点0和权重0初始化
        while(!pq.isEmpty())
            visit(G, pq.delMin());  // 将最近的顶点添加到树中
    }

    private void visit(EdgeWeightedGraph G, int v){
        marked[v] = true;
        for (Edge e : G.adj(v)){
            int w = e.other(v);

            if(marked[w]) continue;  //v-w失效
            if(e.weight() < distTo[w]){
                // 连接w和树的最佳边Edge变为e
                edgeTo[w] = e;

                distTo[w] = e.weight();
                if (pq.contains(w)) pq.change(w, distTo[w]);
                else pq.insert(w, distTo[w]);
            }
        }
    }

    public Iterable<Edge> edges(){
        Bag<Edge> mst = new Bag<>();
        for (int v = 1; v < edgeTo.length; v++)
            mst.add(edgeTo[v]);
        return mst;
    }

    public double weight(){
        return 0.0;
    }
}
