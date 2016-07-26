package com.ueffort.study.graph;

import com.ueffort.study.base.Bag;
import stdlib.In;

/**
 * 图Graph数据类型
 * 邻接表数组实现
 * 1. 使用空间和V+E成正比
 * 2. 添加一条边所需的时间为常数
 * 3. 遍历顶点V的所有相邻顶点所需的时间和v的度数成正比
 * Created by GaoJie on 2016/7/26.
 */
public class Graph {
    private final int V;  // 顶点数目
    private int E;  // 边的数目
    private Bag<Integer>[] adj;  //邻接表
    public Graph(int V){
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++)
            adj[v] = new Bag<>();
    }

    public Graph(In in){
        this(in.readInt());  // 读取V并将图初始化
        int E = in.readInt();  // 读取E
        for (int i = 0; i < E; i++){
            int v = in.readInt();  // 读取一个顶点
            int w = in.readInt();  // 读取另一个顶点
            addEdge(v, w);  // 添加一条连接它们的边
        }
    }

    public int V(){
        return V;
    }

    public int E(){
        return E;
    }

    public void addEdge(int v, int w){
        adj[v].add(w);
        adj[w].add(v);
        E++;
    }

    public Iterable<Integer> adj(int v){
        return adj[v];
    }

    public static int degree(Graph G, int v){
        int degree = 0;
        for(int w :G.adj(v)) degree++;
        return degree;
    }

    public static int maxDegree(Graph G){
        int max = 0;
        for(int v = 0; v < G.V(); v++){
            if (degree(G, v) > max) max = degree(G, v);
        }
        return max;
    }

    public static double avgDegree(Graph G){
        return 2.0 * G.E() / G.V();
    }

    public static int numberOfSelfLoops(Graph G){
        int count = 0;
        for(int v = 0; v < G.V(); v++)
            for(int w : G.adj(v))
                if (v == w) count++;
        return count / 2;  // 每条边都被记过2次
    }

    public String toString(){
        String s = V + " vertices, " + E + " edges\n";
        for(int v = 0; v < V; v++){
            s += v + ": ";
            for (int w : this.adj(v))
                s += w + " ";
            s += "\n";
        }
        return s;
    }
}
