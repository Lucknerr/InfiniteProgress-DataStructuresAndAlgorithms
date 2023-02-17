package com.zl;

import java.util.HashMap;
import java.util.Map;

// 回文数
public class PalindromeNumber {
    static Map<Integer,String> units = new HashMap<>();
    Map<Integer,String> tens = new HashMap<>();
    Map<Integer,String> hundreds = new HashMap<>();
    Map<Integer,String> Thousands = new HashMap<>();
    Map<Integer,Map<Integer,String>> placeMap = new HashMap<>();
    /**
     大思路
     先把整数反转
     再比较与原来是否相等
     贪心点: 只反转整数的一半,与另一半比较是否相等即可
     */
    
    public boolean isPalindrome(int x) {
        // 根据题意 负数都不算回文数 并且以0结尾的除0本身都不算回文数
        if (x < 0 || (x % 10) == 0 && x != 0) {
            return false;
        }
        int ans = 0;
        /**
         当 x > ans 时
         举个例子:
         例1：
            x = 121 ans = 0
            x = 12  ans = 1
            x = 1   ans = 12
            出现 x < ans 的时候 停止反转
            这个时候比较 x 是否等于 ans / 10
         例2：
            x = 22 ans = 0
            x = 2  ans = 2
            出现 x < ans 的时候 停止反转
            这个时候直接比较两个数是否相等
         所以总体来说 答案要开放到两种其中有一个相等就算做回文数了
         */
        while (x > ans) {
            ans = ans * 10 + x % 10;
            x /= 10;
        }
        return ans == x || ans / 10 == x;
    }

    public static void main(String[] args) {
        boolean[] a = new boolean[2];
    }
}
