package com.ueffort.study.example;

import com.ueffort.study.base.Stack;
import stdlib.StdOut;

import java.util.Arrays;

/**
 * 字符串的相关算法
 * Created by tutu on 16-7-13.
 */
public class string {
    /**
     * 给定字符串，仅包含左括号和右括号，设计算法，找出最长匹配的括号子串，返回该子串的长度。
         如：
         (() 2
         ()() 4
         ()(() 2
         ()(()) 6
         (()()) 6

     * “最长匹配的括号子串”的长度，等于“最长匹配的括号子串”的括号匹配数 * 2。所以只需求“最长匹配的括号子串”的括号匹配数。
     * 假设在给定字符串中，存在一个“匹配的括号子串”A：
     *      如果A的下一个字符为0，即字符串结束，则此子串结束匹配；
     *      如果A的下一个字符为)，)没有与之匹配的左括号，因此此子串结束匹配
     *      如果A的下一个字符为(，则此子串可能结束匹配，也可能未结束匹配。
     *          如果此子串未结束匹配，则说明后续的字符串中有一个右括号与之对应；反之说明后续的字符串中没有一个右括号与之对应，如“ ( ) ( ( ( ) ”。所以，可以在遇到)时，判断此)是否与所述(对应，如果对应，则两个子串合并为一个匹配子串，否则两个匹配子串由(隔开。
     * 用一个变量left记录子串未匹配的(的个数，每出现一个(，left加1，每出现一个)，left减1，则left为0时，子串匹配。
     * @param s String
     * @return int
     */
    public static int longestValidParentheses(String s){
        char[] c = s.toCharArray();
        int left = 0;
        int match = 0;
        int preMatch = 0;
        int maxMatch = 0;
        for (char cc : c) {
            if (cc == '('){
                left++;
            }else if(left == 0){
                maxMatch = Math.max(maxMatch, preMatch);
            }else{
                left--;
                match++;
                if(left == 0){
                    preMatch += match;
                    match = 0;
                }
            }
        }
        return Math.max(maxMatch, Math.max(preMatch, match)) * 2;
    }

    /**
     * 假设两个字符串中所含有的字符和个数都相同我们就叫这两个字符串匹配，
     * 比如：abcda和adabc,由于出现的字符个数都是相同，只是顺序不同，
     * 所以这两个字符串是匹配的。要求高效。
     * @param s1
     * @param s2
     * @return
     */
    public static boolean match(String s1, String s2){
        if (s1.length() != s2.length()) return false;
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        int[] count = new int[256];  // 假设字符是ascii
        Arrays.fill(count, 0);
        for(int i = 0; i < s1.length(); i++){
            count[c1[i]]++;
        }
        for(int i = 0; i < s2.length(); i++){
            count[c2[i]]--;
        }
        for(int i = 0; i < count.length; i++){
            if(count[i] > 0) return false;
        }
        return true;
    }

    /**
     * 给定字符串，输出括号是否匹配
     * @param s
     * @return
     */
    public static boolean bracketMatch(String s){
        Stack<Character> stack = new Stack<>();
        char[] c = s.toCharArray();
        for(char aC: c){
            switch(aC){
                case '(':
                    stack.push(')');
                    break;
                case '[':
                    stack.push(']');
                    break;
                case '{':
                    stack.push('}');
                    break;
                case ')':
                case ']':
                case '}':
                    char c1 = stack.pop();
                    if(c1 != aC) return false;
            }
        }
        return true;
    }

    /**
     * 比如shift("abcde",2)， 输出“deabc”.
     * 要求原地移位，空间复杂度为O(1)，时间复杂度不做要求
     * @param c
     * @param p
     */
    public static void rightLoop(char[] c, int p){
        p = p % c.length; // 重置真实位移距离
        char tmp;
        for(int i = 0; i < p; i ++){
            for(int j = 0; j < c.length; j++){
                tmp = c[j];
                c[j] = c[c.length - j - 1];
                c[c.length - j - 1] = tmp;
            }
            StdOut.println(c);
        }
    }

    /**
     * KMP: Knuth-Morris-Pratt 字符串查找算法
     * 子串pattern依次与目标串target中的字符比较，
     *      如果相等，继续比较下一个字符；
     *      如果不等，pattern右移一位，重新开始比较，直至匹配正确或超出target。
     * 演变:
     * KMP算法，是由KMP朴素算法演变而来的，主要分为两步：
     *      第一步，当字符串比较出现不等时，确定下一趟比较前，应该将子串pattern右移多少个字符（预处理）
     *      第二步，子串pattern右移后，应该从哪个字符开始和目标串target中刚才比较时不等的那个字符继续开始比较（查找）
     */
    public int kmp(String target, String pattern){
        int index = -1;
        kmpPre(target, pattern);
        index = kmpFind(target, pattern);
        return index;
    }
    private void kmpPre(String target, String pattern){

    }
    private int kmpFind(String target, String pattern){
        return 0;
    }
}
