package com.ueffort.study.example;

import com.ueffort.study.base.Stack;
import stdlib.StdIn;
import stdlib.StdOut;

/**
 * 直方图最大矩形面积
 * 在柱状图中找最大的矩形：给一组非负的整数来表示一个柱状图，设计一个算法找到最大面积的能适合到柱状图内的矩形。
 * Created by tutu on 16-7-19.
 */
public class MaxArea {
    /**
     * 思路: 求一个矩形面积是由最低高度 * (右边界 - 左边界 + 1) 进行计算
     *      从左边开始扫描, 遇到比之前低的就计算较高的面积
     *      右边界是当前索引,左边界为之前第二高的索引进行计算
     *      在计算中会计算一些不完整的矩形,可以证明这些矩形肯定小于完整矩形,所以没关系
     * 1.新建一个空堆栈；栈顶的值标记为top.
       2.扫描i，从0到n−1：
            如果堆栈为空，或者a[i]比栈顶a[top]的值大，那么把i入栈。这个就是上面所说的：
            如果还没有找到右边第一个比a[i]小的值的时候，就先把这个问题保存下来，暂时不算。
            如果a[i]比栈顶a[top]的值小，那么说明对于a[top]而言，已经找到了右边第一个比它小的值了，就是a[i]，
                什么，你问左边，栈顶往下数的下一个数必然就是左边第一个比它小的值啊！这样就可以算出对于a[top]而言，将其作为矩形最小值的话，最大的面积是多少了！这个时候你所需要做的事情就是：
            计算出这个面积，和当前找到的最大面积对比
            把栈顶的值弹出
            如果这个时候a[top]的值还比a[i]大，继续这么处理，直到a[top]比a[i]小为止。
            i入栈
        3.扫描完后，一般情况下你会剩下一个单调递增的堆栈，那么一个一个出栈计算面积就可以了。
            左边j1依旧还是栈顶下面那个值，右边j2这个时候就不存在了，说明每个栈顶的值都是矩形的最右边了
     * @param a
     * @return
     */
    public static int compute(int[] a){
        Stack<Integer> s = new Stack<>();
        s.push(0);  //保证左边界计算
        int t, max = 0;
        for(int i = 0; i < a.length; i++){
            while(s.size() > 1 && a[i] < a[s.top()]){
                t = s.pop();
                max = Math.max(max, a[t] * ((i - 1) - (s.top() + 1) + 1));
            }
            s.push(i);
        }
        while(s.size() > 1){
            t = s.pop();
            max = Math.max(max, a[t] * ((a.length - 1) - (s.top() + 1) + 1));
        }
        return max;
    }

    public static void main(String[] args) {
        String in = StdIn.readLine();
        String[] i = in.split(" ");
        int[] ii = new int[i.length];
        for(int j = 0; j < i.length; j ++){
            ii[j] = Integer.parseInt(i[j]);
        }
        StdOut.print(MaxArea.compute(ii));
    }
}
