package com.zl.longestpalindromicsubsequence;

// 最长回文子序列
public class LongestPalindromicSubsequence {

    /**
     * 暴力递归
     * @param s 用户输入字符串
     * @return
     */
    public int longestPalindromicSubsequence(String s) {
        char[] chars = s.toCharArray();
        return f(chars,0,s.length()-1);
    }

    /**
     * 范围尝试模型
     * @param c 字符串数组
     * @param l 以l开头
     * @param r 以r结尾
     * @return 返回当前l-r范围的最长回文子序列
     */
    private int f(char[] c,int l, int r) {
        if (l == r) {
            return 1;
        }
        if (l == r-1) {
            return c[l] == c[r] ? 2 : 1;
        }
        // 以l开头不以r结尾
        int p1 = f(c,l,r-1);
        // 不以l开头以r结尾
        int p2 = f(c,l+1,r);
        // 不以l开头也不以r结尾
        int p3 = f(c,l+1,r-1);
        // 以l开头也以r结尾
        int p4 = c[l] == c[r] ? f(c,l+1,r-1) + 2 : 0;
        return Math.max(Math.max(p1,p2),Math.max(p3,p4));
    }

    /**
     *
     * @param s 用户输入字符串
     * @return 返回最长回文子序列的长度
     */
    public int dp(String s) {
        char[] c= s.toCharArray();
        int N = s.length();
        int[][] dpT = new int[N][N];
        for (int l = N; l >= 0; l--) {
            for (int r = l; r < N; r++) {
                if (l == r) {
                    dpT[l][r] = 1;
                } else if (l == r-1) {
                    dpT[l][r] = c[l] == c[r] ? 2 : 1;
                } else {
                    dpT[l][r] = Math.max(Math.max(dpT[l][r-1],dpT[l+1][r]),c[l]==c[r]?dpT[l+1][r-1]+2:0);
                }
            }
        }
        return dpT[0][N-1];
    }
}
