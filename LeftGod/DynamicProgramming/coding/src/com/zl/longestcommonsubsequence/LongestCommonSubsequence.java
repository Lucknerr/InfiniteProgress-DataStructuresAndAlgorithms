package com.zl.longestcommonsubsequence;

// 最长公共子序列
public class LongestCommonSubsequence {

    /** 暴力递归
     * 最长公共子序列
     * @param str1 串1
     * @param str2 串2
     * @return 最长公共子序列的长度
     */
    public int longestCommonSubsequence(String str1, String str2) {
        if (str1 == null || str2 == null || str1.length() == 0 || str2.length() == 0) {
            return 0;
        }
        // 将字符串转换为字符数组
        char[] char1 = str1.toCharArray();
        char[] char2 = str2.toCharArray();
        return f1(char1,char2,str1.length()-1,str2.length()-1);
    }

    /**
     *
     * @param str1 串1
     * @param str2 串2
     * @param i 串1 0-i位置往后的最长公共子序列的长度
     * @param j 串2 0-j位置往后的最长公共子序列的长度
     * @return
     */
    public int f1(char[] str1, char[] str2, int i, int j) {
        // 如果i位置和j位置都为0
        if (i == 0 && j == 0) {
            // 并且 串1i位置和串2j位置字符相等
            if (str1[i] == str2[j]) {
                // 累计1长度
                return 1;
            } else {
                // 否则不累计长度
                return 0;
            }
        } else if (i == 0) { // 单纯 i 位置在0
            // 两位置字符相等
            if (str1[i] == str2[j]) {
                // 累计1长度
                return 1;
            } else {
                // 此时只能j位置动
                return f1(str1, str2, i, j - 1);
            }
        } else if (j == 0) { // 单纯 j 位置在0
            if (str1[i] == str2[j]) { // 两位置字符相等
                // 累计1长度
                return 1;
            } else {
                // 此时只能i位置动
                return f1(str1, str2, i - 1, j);
            }
        } else {
            // 尝试1 j动
            int p1 = f1(str1, str2, i, j - 1);
            // 尝试2 i动
            int p2 = f1(str1, str2, i - 1, j);
            // 尝试3 当两字符不相等时 不累计长度,如果相等i j 同时动并累计1长度
            int p3 = str1[i] != str2[j] ? 0 : f1(str1, str2, i - 1, j - 1) + 1;
            // 抓取几个尝试的最大长度
            int max = Math.max(Math.max(p1, p2), p3);
            return max;
        }
    }
}
