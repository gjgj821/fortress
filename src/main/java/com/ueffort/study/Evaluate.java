package com.ueffort.study;

import stdlib.StdIn;
import stdlib.StdOut;

import java.util.Stack;

/**
 * E.W.Dijkstra 双栈算术表达式
 * 1. 将操作数压入操作数栈；
 * 2. 将运算符压入运算符栈；
 * 3. 忽略左括号；
 * 4. 在遇到右括号时，弹出一个运算符，弹出所需数量的操作数，并将运算符和操作数的运算结果压入操作数栈；
 * Created by GaoJie on 2016/7/1.
 */
public class Evaluate {
    public static void main(String[] args){
        Stack<String> ops = new Stack<String>();
        Stack<Double> vals = new Stack<Double>();
        while(!StdIn.isEmpty()){
            // 读取字符，如果是运算符则压入栈
            String s = StdIn.readString();
            if (s.equals("(")) ;
            else if (s.equals("+")) ops.push(s);
            else if (s.equals("-")) ops.push(s);
            else if (s.equals("*")) ops.push(s);
            else if (s.equals("/")) ops.push(s);
            else if (s.equals("sqrt")) ops.push(s);
            else if (s.equals(")")) {
                // 如果字符为“)”，弹出运算符和操作数，计算结果并压入栈
                String op = ops.pop();
                double v = vals.pop();
                if (op.equals("+")) v = vals.pop() + v;
                else if (op.equals("-")) v = vals.pop() - v;
                else if (op.equals("*")) v = vals.pop() * v;
                else if (op.equals("/")) v = vals.pop() / v;
                else if (op.equals("sqrt")) v = Math.sqrt(v);
                vals.push(v);
            }
            // 如果字符既非运算符也不是括号，将它作为double值压入栈
            else vals.push(Double.parseDouble(s));
        }
        StdOut.println(vals.pop());
    }
}
