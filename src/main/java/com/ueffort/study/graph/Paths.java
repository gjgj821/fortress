package com.ueffort.study.graph;

/**
 * 路径API
 * Created by GaoJie on 2016/7/26.
 */
abstract public class Paths {
    // Paths(Graph G, int s)  // 在G中找出所有起点为s的路径

    // 是否存在从s到v的路径
    abstract public boolean hasPathTo(int v);
    // s 到 v的路径，如果不存在则返回null
    abstract public Iterable<Integer> pathTo(int v);
}
