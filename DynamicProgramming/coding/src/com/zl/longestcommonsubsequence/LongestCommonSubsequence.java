package com.zl.longestcommonsubsequence;

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
        char[] char1 = str1.toCharArray();
        char[] char2 = str2.toCharArray();
        return f1(char1,char2,str1.length()-1,str2.length()-1);
    }

    /**
     *
     * @param str1 串1
     * @param str2 串2
     * @param i 0到 i结尾
     * @param j 0到 j结尾
     * @return 最长公共子序列的长度
     */
    public int f(char[] str1, char[] str2, int i, int j) {
        if (i == 0 || j == 0) {
            if (str1[i] == str2[j]) {
                return 1;
            } else {
                return 0;
            }
        }
        int p1 = f(str1,str2,i,j-1);
        int p2 = f(str1,str2,i-1,j);
        int p3 = str1[i] != str2[j] ? 0 : f(str1,str2,i-1,j-1)+1;
        int ans = Math.max(Math.max(p1,p2),p3);
        return ans;
    }

    public int f1(char[] str1, char[] str2, int i, int j) {
        if (i == 0 && j == 0) {
            if (str1[i] == str2[j]) {
                return 1;
            } else {
                return 0;
            }
        } else if (i == 0) {
            if (str1[i] == str2[j]) {
                return 1;
            } else {
                return f1(str1, str2, i, j - 1);
            }
        } else if (j == 0) {
            if (str1[i] == str2[j]) {
                return 1;
            } else {
                return f1(str1, str2, i - 1, j);
            }
        } else {
            int p1 = f1(str1, str2, i, j - 1);
            int p2 = f1(str1, str2, i - 1, j);
            int p3 = str1[i] != str2[j] ? 0 : f1(str1, str2, i - 1, j - 1) + 1;
            int max = Math.max(Math.max(p1, p2), p3);
            return max;
        }
    }


}
