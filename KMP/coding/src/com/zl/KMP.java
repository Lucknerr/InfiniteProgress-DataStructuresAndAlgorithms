package com.zl;

// KMP实现
public class KMP {
    // KMP算法
    public int kmp(int start,String pri,String sub) {
        if(pri == null || sub == null) {
            return -1;
        }
        // 主串的长度
        int priLen = pri.length();
        // 子串的长度
        int subLen = sub.length();
        if (priLen < subLen) {
            return -1;
        }
        if (priLen == 0 || subLen == 0) {
            return -1;
        }
        if (start >= priLen) {
            return -1;
        }
        // 从主串的i位置开始匹配
        int i = start;
        // 子串0位置开始匹配
        int j = 0;
        // 获取next数组
        int[] next = setNext(sub);
        // 当主串和字串都没遍历玩,说明没有完成匹配
        while (j < subLen && i < pri.length()) {
            // 如果匹配指针j来到-1位置说明字串的第一个字符就已经和主串遍历指针位置的字符不匹配,
            // 或者匹配指针j位置的字符和遍历指针i位置字符匹配
            if (j == -1 || sub.charAt(j) == pri.charAt(i)) {
                // 那么i,j指针都向后走一位,表示匹配下一对字符
                i++;
                j++;
            } else { // 如果i位置与j位置不匹配
                // j指针来到当前j指针位置字符的最终回退位置
                j = next[j];
            }
        }
        // 如果子串遍历完成,说明匹配成功
        if (j >= subLen) {
            // 子串所在主串的起始位置就是i遍历过的长度减去字串长度
            return i-j;
        }
        // 上方条件都不成立则没有匹配成功返回-1
        return -1;

    }
    // 设置最终回退数组
    private int[] setNext(String sub) {
        // 字符串长度
        int len = sub.length();
        // 用来存放回退位置
        int[] next = new int[len];
        // next 0位置永远是-1
        next[0] = -1;
        if (len < 2) {
            return next;
        }
        // 用来存放最终回退位置
        int[] nextVal = new int[len];
        // nextVal 0位置永远是-1
        nextVal[0] = -1;
        // next 1位置永远是0
        next[1] = 0;
        // 判断第二个字符是否与第一个字符相等
        nextVal[1] = sub.charAt(1) == sub.charAt(0) ? -1 : 0;
        // 用来遍历字符串的指针
        int i = 2;
        // 用来记录i位置前一个字符的回退位置
        int k = 0;
        // 遍历字符串
        while (i < len) {
            // 如果i位置前一个字符的回退位置为-1,或者i位置前一个位置等于i位置前一个字符回退位置的字符
            if (k == -1 || sub.charAt(i-1) == sub.charAt(k)) {
                // 那么i位置的回退位置就是前一个回退字符的回退位置加一
                next[i] = ++k; // 并且k来到当前i位置的回退位置
                // 如果当前i位置的字符等于当前i位置回退位置的字符
                if (sub.charAt(i) == sub.charAt(k)) {
                    // 那么当前i位置的最终回退位置就是当前i位置字符回退位置上字符的最终回退位置
                    nextVal[i] = nextVal[k];
                } else { // 如果不相等
                    // 当前i位置的最终回退位置就是i本身的回退位置
                    nextVal[i] = k;
                }
                // i位置去到下一个
                i++;
            } else { // 上面的不成立
                // i位置前一个字符的回退位置就来到i位置前一个字符回退位置的回退位置
                k = next[k];
            }
        }
        // 返回最终回退位置数组
        return nextVal;
    }
}
