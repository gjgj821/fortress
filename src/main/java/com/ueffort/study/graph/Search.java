package com.ueffort.study.graph;

/**
 * 图搜索API
 * Created by GaoJie on 2016/7/26.
 */
abstract public class Search {
    // Search(Graph G, int s)  // 找到和起点s连通的所有顶点
    // v 和 s 是否连通
    abstract public boolean marked(int v);
    // 与 s连通的顶点总数
    abstract public int count();
}
