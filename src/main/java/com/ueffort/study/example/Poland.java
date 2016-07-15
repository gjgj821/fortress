package com.ueffort.study.example;

import com.ueffort.study.base.Queue;
import com.ueffort.study.base.Stack;
import stdlib.StdIn;
import stdlib.StdOut;

/**
 * 逆波兰表达式计算
 * 逆波兰表达式是一种十分有用的表达式，它将复杂表达式转换为可以依靠简单的操作得到计算结果的表达式
 * 其优势: 在于只用两种简单操作，入栈和出栈就可以搞定任何普通表达式（仅包含：+-*\/和（）的表达式）的运算
 * Created by tutu on 16-7-15.
 */
public class Poland {
    /**
     * 将一个中序表达式转化成为逆波兰表达式。
     首先维护的是两个栈，我们这里暂且称为S1和S2，S1中的结果最后存的就是逆波兰表达式，S2中将用于暂时存放运算符并且在最终形成逆波兰表达式的时候，该栈是会清空的。
     在此首先定义一下运算符的优先级关系，从小到达排序，相同优先级没有用逗号隔开：（，+-，*\，负号，）。
     从左至右遍历一个给定的中序表达式，也就是我们常规的数学计算的表达式。
     （1）如果遇到的是数字，我们直接加入到栈S1中；
     （2）如果遇到的是左括号，则直接将该左括号加入到栈S2中；
     （3）如果遇到的是右括号，那么将栈S2中的运算符一次出栈加入到栈S1中，直到遇到左括号，但是该左括号出栈S2并不加入到栈S1中；
     （4）如果遇到的是运算符，包括单目运算符和双目运算符，我们按照下面的规则进行操作：
     （1）如果此时栈S2为空，则直接将运算符加入到栈S2中；
     （2）如果此时栈S2不为空，当前遍历的运算符的优先级大于等于栈顶运算符的优先级，那么直接入栈S2；
     （3）如果此时栈S2不为空，当前遍历的运算符的优先级小于栈顶运算符的优先级，则将栈顶运算符一直出栈加入到栈S1中，
         直到栈为空或者遇到一个运算符的优先级小于等于当前遍历的运算符的优先级，此时将该运算符加入到栈S2中；
     （5）直到遍历完整个中序表达式之后，栈S2中仍然存在运算符，那么将这些运算符依次出栈加入到栈S1中，直到栈为空。
     按照上面的五条操作反复进行完成，那么栈S1中存放的就是逆波兰表达式。
     * @param s
     * @return
     */
    public static Stack change(Queue<String> q){
        Stack a = new Stack();
        Stack b = new Stack();
        String s;
        char c;
        while(!q.isEmpty()){
            s = q.dequeue();
            if (Integer.getInteger(s) != null){
                a.push(Integer.parseInt(s));
            }else{
                c = s.charAt(0);
                switch(c){
                    case '(':
                        b.push(c);
                        break;
                    case ')':
                        while(!b.isEmpty()){
                            c = (char)b.pop();
                            if(c == 'c') break;
                            a.push(c);
                        }
                        break;
                    default:
                        if(b.isEmpty()) b.push(c);
                        else{

                        }
                }
            }
        }
        while(!a.isEmpty()){
            b.push(a.pop());
        }
        return b;
    }

    /**
     * 返回运算符优先级
     *      返回越大优先级越高
     * @param c char
     * @return int
     */
    private static int priority(char c){
        switch(c){
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
            case '%':
                return 3;
            case '(':
            case ')':
            default:
                return 0;
        }
    }

    /**
     * 单符号计算
     * @param c
     * @param left
     * @param right
     * @return
     * @throws Exception
     */
    private static int eval(char c, int left, int right) throws Exception {
        switch(c){
            case '+':
                return left + right;
            case '-':
                return left - right;
            case '*':
                return left * right;
            case '/':
                return left / right;
            case '^':
                return left ^ right;
            case '%':
                return left % right;
            default:
                throw new Exception("operator is error");
        }
    }

    /**
     * 基本运算方式：
     *      如果当前字符为变量或者为数字，则压栈，
     *      如果是运算符，则将栈顶两个元素弹出作相应运算，结果再入栈，
     *      最后当表达式扫描完后，栈里的就是结果。
     * @param s
     * @return
     */
    public static int compute(Stack s) throws Exception {
        Stack<Integer> s1 = new Stack<>();
        int a, b; char c;
        while(!s.isEmpty()){
            Object o = s.pop();
            if (o.getClass() == String.class){
                c = (char)o;
                b = s1.pop();
                a = s1.pop();
                s1.push(eval(c, a, b));
            } else if(o.getClass() == Integer.class){
                s1.push((int)o);
            }
        }
        return s1.pop();
    }

    public static void main(String[] args) {
        Queue<String> q = new Queue<>();
        while(!StdIn.isEmpty()){
            String o = StdIn.readString();
            q.enqueue(o);
        }
        Stack s = Poland.change(q);
        try {
            StdOut.println(Poland.compute(s));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
