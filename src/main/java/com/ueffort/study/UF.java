package com.ueffort.study;

import stdlib.StdIn;
import stdlib.StdOut;

/**
 * union-find
 * Created by GaoJie on 2016/7/8.
 */
public class UF {
    private int[] id;  // 分量id（以触点作为索引）
    private int count;  // 分量数量
    public UF(int N){
        // 初始化分量id
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++){
            id[i] = i;
        }
    }
    public int count(){
        return count;
    }
    public boolean connected(int p, int q){
        return find(p) == find(q);
    }
    public int find(int p){
        return findQuickFind(p);
    }
    public void union(int p, int q){
        unionQuickFind(p, q);
    }
    public static void main(String[] args){
        // 解决由StdIn得到的动态连通性
        int N = StdIn.readInt();  // 读取触点数量
        UF uf = new UF(N);
        while(!StdIn.isEmpty()){
            int p = StdIn.readInt();
            int q = StdIn.readInt();  // 读取整数对
            if (uf.connected(p, q)) continue; // 如果已经连通则忽略
            uf.union(p, q);  // 归并分量
            StdOut.println(p + " " + q);  // 打印链接
        }
        StdOut.println(uf.count() + "components");
    }

    /**
     * quick-find:
     * 每次find()调用只需要访问数组一次
     * 而归并两个分量的union()操作访问数组的次数在（N+3）到（2N+1）之间。
     * 对每一对输入union()都需要扫描整个id[]数组
     */
    public int findQuickFind(int p){
        return id[p];
    }
    public void unionQuickFind(int p, int q){
        // 将p和q归并到相同的分量中
        int pId = find(p);
        int qId = find(q);

        // 如果p和q已经在相同的分量之中则不需要采取任何行动
        if (pId == qId) return ;

        // 将p的分量重命名为q的名称
        for (int i = 0; i < id.length; i++){
            if (id[i] == pId) id[i] = qId;
        }
        count--;
    }
}
