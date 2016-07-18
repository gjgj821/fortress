package com.ueffort.study.example;

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
        // todo
        return true;
    }
}
