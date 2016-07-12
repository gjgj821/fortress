package com.ueffort.study.base;

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

    /**
     * quick-union: 用节点表示触点，用从一个节点到另一个节点的箭头表示链接，结构是树
     * find最好情况只需访问一次数组，最坏的情况是2N+1次
     * union根据find性能，只需一次赋值就可链接2个分量
     */
    public int findQuickUnion(int p){
        // 找出分量的名称
        while(p != id[p]) p = id[p];
        return p;
    }
    public void unionQuickUnion(int p, int q) {
        // 将p和q的根节点统一
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) return;
        id[pRoot] = qRoot;
        count--;
    }

    /**
 * 加权quick-union
 * Created by GaoJie on 2016/7/12.
 */
public class WeightedQuickUnionUF {
    private int[] id;  // 父链接数组（由触点索引）
    private int[] sz;  //（由触点索引的）各个根节点所对应的分量大小
    private int count;  // 连通分量的数量
    public WeightedQuickUnionUF(int N){
        count = N;
        id = new int[N];
        for(int i = 0; i < N; i++) id[i] = i;
        sz = new int[N];
        for(int i = 0; i < N; i++) sz[i] = 1;
    }
    public int count(){
        return count;
    }
    public boolean counnected(int p, int q){
        return find(p) == find(q);
    }
    public int find(int p){
        // 跟随链接找到根节点
        while(p != id[p]) p = id[p];
        return p;
    }
    public void union(int p, int q){
        int i = find(p);
        int j = find(q);
        if (i == j) return ;
        // 将小数的根节点链接到大树的根节点
        if (sz[i] < sz[j]){
            id[i] = j; sz[j] += sz[i];
        }else{
            id[j] = i; sz[i] += sz[j];
        }
        count--;
    }
}
}
