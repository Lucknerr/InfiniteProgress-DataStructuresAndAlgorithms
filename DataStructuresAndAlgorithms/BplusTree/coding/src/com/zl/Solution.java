package com.zl;

public class Solution {

    public static boolean isPalindrome(String s) {
        int L = 0;
        int R = s.length()-1;
        char[] chars = s.toCharArray();
        while (L <= R) {
            if (legalChar(chars[L])) {
                L++;
                continue;
            }
            if (legalChar(chars[R])) {
                R--;
                continue;
            }
            int tmp = Math.abs(chars[L] - chars[R]);
            if ((tmp == 32 && chars[L] > 57 && chars[R] > 57) || tmp == 0) {
                L++;
                R--;
            } else {
                return false;
            }
        }
        return true;


    }

    private static boolean legalChar(char c) {
        return c < 48 || c > 122 || (c > 57 && c < 65) || (c > 90 && c < 97);

    }

    public static void main(String[] args) {
        System.out.println(isPalindrome(",,,,,,,,,,,,acva"));
    }
}
