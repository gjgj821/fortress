package com.ueffort.study.graph;

import com.ueffort.study.base.Queue;
import stdlib.StdIn;
import stdlib.StdOut;

import java.util.ArrayList;
import java.util.Vector;

/**
 * 有向无环图
 * Created by tutu on 16-7-14.
 */
public class DAG<Item> {
    /**
     * DAG的拓扑排序
     * 一、从有向图中选取一个没有前驱的顶点，并输出之；
     * 二、从有向图中删去此顶点以及所有以它为尾的弧；
     * 重复上述两步，直至图空，或者图不空但找不到无前驱的顶点为止。没有前驱 -- 入度为零，删除顶点及以它为尾的弧-- 弧头顶点的入度减1。
     */
    private ArrayList<Vector<Integer>> vMap;
    private ArrayList<Item> l;
    private ArrayList<Integer> degree;
    public DAG(){
        vMap = new ArrayList<>();
        degree = new ArrayList<>();
        l = new ArrayList<>();
    }
    public Queue sort() throws Exception {
        Queue<Item> q = new Queue<>();
        Queue<Integer> Q = new Queue<>();
        int a, b;
        for(int i = 0; i < degree.size(); i++){
            a = degree.get(i);
            if(a == 0){
                Q.enqueue(i);  // 入度为0的点入队
            }
        }
        while(!Q.isEmpty()){
            b = Q.dequeue();
            q.enqueue(get(b));  // 出队顺序即为拓扑序列
            Vector<Integer> v = vMap.get(b);
            for (Integer aV : v) {
                a = degree.get(aV);
                a--;  // 删边
                if (a == 0) {
                    // 新的入度为0的点
                    Q.enqueue(aV);
                }
            }
        }
        for (Integer aDegree : degree) {
            if (aDegree != 0) { // 若存在入度不为0的点，则存在环
                throw new Exception("DAG sort is fail");
            }
        }
        return q;
    }

    /**
     * a 指向 b
     * @param a
     * @param b
     */
    public void add(Item a, Item b){
        // 建立映射
        int aI = map(a);
        int bI = map(b);
        // 计算入度
        int d = degree.get(bI);
        degree.add(bI, d + 1);
        // 保存边
        Vector<Integer> v = vMap.get(aI);
        if (v.isEmpty()){
            v = new Vector<>();
        }
        if (!v.contains(bI)){
            v.add(bI);
        }
        vMap.add(aI, v);
    }
    private Item get(int index){
        return l.get(index);
    }
    private int map(Item i){
        int iI;
        // 建立映射
        if (l.contains(i)){
            iI = l.indexOf(i);
        }else{
            l.add(i);
            iI = l.size() - 1;
        }
        return iI;
    }
    public static void main(String[] args){
        DAG<String> dag = new DAG<>();
        while(!StdIn.isEmpty()){
            String s = StdIn.readLine();
            String[] ss = s.split(" ");
            dag.add(ss[0], ss[1]);
        }
        try{
            Queue q = dag.sort();
            StdOut.print(q);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
