package com.zl.longestpalindromicsubsequence;


import com.zl.operatortarget.OperatorTarget;

public class Test {
    public static void main(String[] args) {
        LongestPalindromicSubsequence longestPalindromicSubsequence = new LongestPalindromicSubsequence();
        String a = "1a2a3a21";
        System.out.println(longestPalindromicSubsequence.longestPalindromicSubsequence(a));
        System.out.println(longestPalindromicSubsequence.dp(a));
    }
}
