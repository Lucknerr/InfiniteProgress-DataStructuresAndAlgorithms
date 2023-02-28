package com.zl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
    /**
     本题两个串匹配会出现的几种情况:
     s与p两个串匹配的过程中,
     注意: 当前暂时不讨论这两个串最终能否匹配成功，
     我们只尽可能最大程度去匹配会产生的情况.
     (也就是讨论匹配到不能再匹配的情况,并且保证最大限度的匹配)
     情况:
     1. s 剩余未能匹配的字符
     2. p 剩余未能匹配的字符

     分析:
     1. 如果 s 串还剩未能匹配的字符 说明一定匹配不成功
     2. 如果 p 串还剩未能匹配的字符 要分情况讨论
     1) 可能剩下类似于 (.*) (a-z*) 这种常规字符或者单字符匹配字符后面紧接着*的组合
     并且可能存在若干个这种组合
     2) 不上 1) 中讨论的情况那么就可以断定不配不成功
     */

    /**
     * 暴力解 依赖下面f函数
     * @param s 主串
     * @param p 正则匹配串
     * @return 只要能匹配就返回true 否则返回false
     */
    public static boolean isMatch(String s, String p) {
        char[] s1 = s.toCharArray();
        char[] p1 = p.toCharArray();
        int n = s1.length;
        int m = p1.length;
        return f(s1,p1,n,m,18,13);
    }

    /**
     *
     * @param s 主串
     * @param p 正则匹配串
     * @param n 主串长度
     * @param m 匹配串长度
     * @param i 主串指针
     * @param j 匹配串指针
     * @return i位置及其往后 与 j位置及其往后 继续匹配 只要能匹配成功就返回true 否则false
     */
    public static boolean f(char[] s, char[] p, int n, int m, int i, int j) {
        System.out.println(i + " " + j);
        // 定义黑盒之前就应该定义好这个黑盒在什么情况下算是匹配成功
        if (i == n && j == m) {
            // 当i指针和j指针同时都在两串末尾位置时,我们说就是匹配成功
            return true;
        }
        // s 剩余未能匹配的字符
        if (j == m) {
            return false;
        }
        //
        if (j < m-1 && p[j+1] == '*') {
            if (f(s,p,n,m,i,j+2)) return true;
            if (i < n && (p[j] == s[i] && p[j] == '.') && f(s,p,n,m,i+1,j)) return true;
        } else if (i < n) {
            return p[j] == '.' || p[j] == s[i] ? f(s,p,n,m,i+1,j+1) : false;
        }
        return false;
    }


    /**
     * 动态规划
     * @param s 主串
     * @param p 正则匹配串
     * @return 只要能匹配就返回true 否则返回false
     */
    public static boolean dp(String s, String p) {
        char[] s1 = s.toCharArray(), p1 = p.toCharArray();
        int n = s1.length, m = p1.length;
        boolean[][] dpT = new boolean[n+2][m+1];
        for (int i = n;i >= 0;i--) {
            for (int j = m;j >= 0;j--) {
                if (i == n && j == m) {
                    dpT[i][j] = true;
                } else if (j == m) {
                    dpT[i][j] = false;
                } else if (j < m-1 && p1[j+1] == '*') {
                    if (dpT[i][j+2]) {
                        dpT[i][j] = true;
                        continue;
                    }
                    int t = i;
                    while (t >= n || s1[t] == p1[j] || p1[j] == '.') {
                        if (dpT[++t][j+2]) {
                            dpT[i][j] = true;
                            break;
                        }
                        if (t > n) {
                            dpT[i][j] = false;
                            break;
                        }
                    }
                } else if (i < n) {
                    dpT[i][j] = p1[j] == '.' || p1[j] == s1[i] ? dpT[i+1][j+1] : false;
                } else {
                    dpT[i][j] = false;
                }
            }
        }
        return dpT[0][0];
    }




    public static void main(String[] args) {
        String s1 = "acaabbaccbbacaabbbb", s2 = "a*.*b*.*a*aa*a*";
        System.out.println(isMatch(s1,s2));
    }
}
