package com.ueffort.study.example;

import com.ueffort.study.base.Stack;
import stdlib.StdOut;

import java.util.Arrays;
import java.util.HashMap;

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
        int[] overlay = kmpPre(pattern);
        index = kmpFind(target, pattern, overlay);
        return index;
    }

    /**
     * 获取匹配字符串自身的相关性,覆盖度
     * @param pattern
     * @return
     */
    private int[] kmpPre(String pattern){
        char[] c = pattern.toCharArray();
        int[] overlay = new int[c.length];
        int i, preOverlay;

        overlay[0] = -1;
        for (i = 1; i < c.length; i++) {
            preOverlay = overlay[i - 1];

            while (preOverlay >= 0 && c[i] != c[preOverlay+1]) {
                preOverlay = overlay[preOverlay];
            }

            if (c[i] == c[preOverlay+1]) {
                overlay[i] = preOverlay + 1;
            } else {
                overlay[i] = -1;
            }
        }
        return overlay;
    }

    /**
     * 查找算法
     * @param target
     * @param pattern
     * @param overlay
     * @return
     */
    private int kmpFind(String target, String pattern, int[] overlay){
        int indexP = 0, indexT = 0;

        while (indexP < pattern.length() && indexT < target.length()) {
            if (target.indexOf(indexT) == pattern.indexOf(indexP)) {
                indexT++;
                indexP++;
            } else if (indexP == 0) {
                indexT++;
            } else {
                indexP = overlay[indexP - 1] + 1;
            }
        }

        if (indexP == pattern.length()) {
            return indexT - indexP;
        } else {
            return -1;
        }
    }

    /**
     * Boyer-Moore算法:
     *      在移动模式串的时候是从左到右，而进行比较的时候是从右到左的
     * @param target
     * @param pattern
     * @return
     */
    public int bm(String target, String pattern){
        int index = -1;
        char[] p = pattern.toCharArray();
        HashMap<Character, Integer> bad = bmBad(p);
        int[] good = bmGood(p);
        index = bmFind(target, pattern, bad, good);
        return index;
    }

    /**
     * 坏字符表:
     *      不匹配的字符在搜索词中的上一次出现的位置
     *      后移位数 = 坏字符的位置 - 搜索词中的上一次出现位置
     * @param p
     * @return
     */
    private HashMap<Character, Integer> bmBad(char[] p){
        HashMap<Character, Integer> hm = new HashMap<>();
        for (int i = 0; i < p.length - 1; ++i)
            // 有可能导致往前跳,没有真实记录每个阶段的上一次出现位置
             // 简化为每个字符的最后次出现位置
            hm.put(p[i], i);
        return hm;
    }

    /**
     * 好后缀表:
     *      所有尾部匹配的字符串在搜索词中上一次出现的位置
     *      索引位置以最后一个匹配字符
     *      后移位数 = 好后缀的位置 - 搜索词中的上一次出现的位置
     * 1. 选择最左边的完整匹配后缀
     * 2. 选择最长匹配前缀
     * @param p
     * @return
     */
    private int[] bmGood(char[] p){
        int[] g = new int[p.length];
        int[] suff = bmSuffix(p);
        int j = 0;
        for (int i = 0; i < p.length; ++i)
            g[i] = p.length;
        for (int i = p.length - 1; i >= 0; --i)
            if (suff[i] == i + 1)
                for (; j < p.length - 1 - i; ++j)
                    if (g[j] == p.length)
                        g[j] = p.length - 1 - i;
        for (int i = 0; i <= p.length - 2; ++i)
            g[p.length - 1 - suff[i]] = p.length - 1 - i;
        return g;
    }

    /**
     * suffix[i] = s 表示以i为边界，与模式串后缀匹配的最大长度
     * 满足P[i-s, i] == P[m-s, m]的最大长度s
     * @param p
     * @return
     */
    private int[] bmSuffix(char[] p){
        int[] suff = new int[p.length];
        int q = 0;
        suff[p.length - 1] = p.length;
        for (int i = p.length - 2; i >= 0; --i){
            q=i;
            while(q >= 0 && p[q] == p[p.length - 1 - i + q])
                --q;
            suff[i] = i - q;
        }
        return suff;
    }

    private int bmFind(String target, String pattern, HashMap<Character, Integer> bad, int[] good){
        int j = 0, n = target.length(), m = pattern.length(), b;
        while (j <= n - m) {
            int i = m -1;
            while(pattern.indexOf(i) == target.indexOf(i + j)) --i;

            if (i < 0) {
                return j;  // 匹对成功
                //j += good[0];

            } else {
                // 关键: 通过对j的加快跳跃,直接提高匹配效率
                b = bad.get((char)target.indexOf(i + j));
                // 如果未出现就是整体往后移动整个匹配串长度
                j += Math.max(good[i], i - (b > 0 ? b : -1));
            }
        }
        return -1;
    }

    /**
     * 给出一个只由小写英文字符a,b,c...y,z组成的字符串S,求S中最长回文串的长度.
     * 回文就是正反读都是一样的字符串,如aba, abba等
     *
     * 首先:用一个非常巧妙的方式，将所有可能的奇数/偶数长度的回文子串都转换成了奇数长度：
     *      在每个字符的两边都插入一个特殊的符号。比如 abba 变成 #a#b#b#a#， aba变成 #a#b#a#。
     *      为了进一步减少编码的复杂度，可以在字符串的开始加入另一个特殊字符，这样就不用特殊处理越界问题，比如$#a#b#a#
     *
     * 当 mx - i > P[j] 的时候，以S[j]为中心的回文子串包含在以S[id]为中心的回文子串中，
     *      由于 i 和 j 对称，以S[i]为中心的回文子串必然包含在以S[id]为中心的回文子串中，所以必有 P[i] = P[j]
     * 当 P[j] > mx - i 的时候，以S[j]为中心的回文子串不完全包含于以S[id]为中心的回文子串中，
     *      但是基于对称性可知，S[i]为中心的回文子串，其向右至少会扩张到mx的位置，也就是说 P[i] >= mx - i。
     *      至于mx之后的部分是否对称，就只能一个一个匹配了
     * 对于 mx <= i 的情况，无法对 P[i]做更多的假设，只能P[i] = 1，然后再去匹配了
     * 虽然取最小,单随着匹对mx的值会越来越大,减少不必要的再次匹对数量
     * @param target
     * @return
     */
    public int manacher(String target){
        int l = target.length() * 2 + 1;
        char[] t = new char[l];  // 填充字符,用于将回文都转换为奇数
        int[] p = new int[l];
        int max = 0, id = 0;
        for(int i = 0; i < target.length(); i++){
            t[i + i + 2] = t[i];
            t[i + i + 1] = '#';
        }
        t[0] = '$';  // 必须保证与首字符不相同
        for(int i = 2; i < 2 * target.length() + 1; ++i){
            if(p[id] + id > i) p[i] = Math.min(p[ 2 * id - i], p[id] + id - i);
            else p[i] = 1;
            while(t[i - p[i]] == t[i + p[i]]) ++p[i];
            if(id + p[id] < i + p[i]) id=i;
            if(max < p[i]) max = p[i];
        }
        return max - 1;
    }

    /**
     * 给定一个字符串，输出所有回文分割的可能性
     * @param s
     * @return
     */
    public int huiwenCount(String s){
        int c = 0;
        return c;
    }
}
