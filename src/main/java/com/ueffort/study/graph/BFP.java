package com.ueffort.study.graph;

import com.ueffort.study.base.Queue;
import com.ueffort.study.base.Stack;

/**
 * 广度优先搜索查找图中的路径
 * Created by GaoJie on 2016/7/27.
 */
public class BFP extends Paths{
    private boolean[] marked;  // 到达该顶点的最短路径是否已知
    private int[] edgeTo;  // 到达该顶点的已知路径上的最后一个顶点
    private final int s;

    public BFP(Graph G, int s){
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        bfs(G, s);
    }

    private void bfs(Graph G, int s){
        Queue<Integer> queue = new Queue<>();
        marked[s] = true;
        queue.enqueue(s);
        while (!queue.isEmpty()){
            int v = queue.dequeue();  // 从队列中删除下一个顶点
            for (int w : G.adj(v)){
                if(!marked[w]){
                    edgeTo[w] = v;  // 保存最短路径的最后一条边
                    marked[w] = true;  // 标记
                    queue.enqueue(w);  // 添加到队列中
                }
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
