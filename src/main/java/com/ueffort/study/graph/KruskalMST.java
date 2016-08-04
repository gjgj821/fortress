package com.ueffort.study.graph;

import com.ueffort.study.base.MinPQ;
import com.ueffort.study.base.Queue;
import com.ueffort.study.base.UF;

/**
 * 最小生成树的Kruskal算法
 * Created by GaoJie on 2016/8/4.
 */
public class KruskalMST {
    private Queue<Edge> mst;

    public KruskalMST(EdgeWeightedGraph G){
        mst = new Queue<>();
        MinPQ<Edge> pq = new MinPQ<>(G.V());
        for (Edge e: G.edges()) pq.insert(e);
        UF uf = new UF(G.V());

        while(!pq.isEmpty() && mst.size() < G.V() - 1){
            Edge e = pq.delMin();  // 从pq得到权重最小的边和它的顶点
            int v = e.either(), w = e.other(v);
            if (uf.connected(v, w)) continue;  //忽略失效的边
            uf.union(v, w);  // 合并分量
            mst.enqueue(e);
        }
    }

    public Iterable<Edge> edges(){
        return mst;
    }

    public double weight(){
        return 0.0;
    }
}
