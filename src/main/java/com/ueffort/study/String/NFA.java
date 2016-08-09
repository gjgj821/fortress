package com.ueffort.study.String;

import com.ueffort.study.base.Bag;
import com.ueffort.study.base.Stack;
import com.ueffort.study.graph.Digraph;
import com.ueffort.study.graph.DirectedDFS;

/**
 * 非确定性有限状态自动机
 * Created by GaoJie on 2016/8/9.
 */
public class NFA {
    private char[] re;  // 匹配转换
    private Digraph G;  //epsilon转换
    private int M;  //状态数量

    public NFA(String regexp){
        Stack<Integer> ops = new Stack<>();
        re =regexp.toCharArray();
        M = re.length;
        G = new Digraph(M + 1);

        for(int i = 0; i < M; i++){
            int lp = i;
            if(re[i] == '(' || re[i] == '|')
                ops.push(i);
            else if(re[i] == ')'){
                int or = ops.pop();
                if(re[or] == '|'){
                    lp = ops.pop();
                    G.addEdge(lp, or + 1);
                    G.addEdge(or, i);
                }else lp = or;
            }
            if(i < M - 1 && re[i + 1] == '*'){
                G.addEdge(lp, i + 1);
                G.addEdge(i + 1, lp);
            }
            if(re[i] == '(' || re[i] == '*' || re[i] == ')')
                G.addEdge(i, i + 1);
        }
    }

    public boolean recognizes(String txt){
        Bag<Integer> pc = new Bag<>();
        DirectedDFS dfs = new DirectedDFS(G, 0);
        for(int v = 0; v < G.V(); v++)
            if(dfs.marked(v)) pc.add(v);

        for(int i = 0; i < txt.length(); i++){
            Bag<Integer> match = new Bag<>();
            for(int v : pc)
                if(v < M)
                    if(re[v] == txt.charAt(i) || re[v] == '.')
                        match.add(v + 1);
            pc = new Bag<>();
            dfs = new DirectedDFS(G, match);
            for(int v = 0; v < G.V(); v++)
                if(dfs.marked(v)) pc.add(v);
        }

        for(int v : pc)
            if(v == M) return true;
        return false;
    }
}
