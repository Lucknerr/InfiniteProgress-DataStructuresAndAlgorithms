package com.zl;

public class Solution {
    public static boolean isMatch(String s, String p) {
        char[] cs = s.toCharArray();
        char[] ps = p.toCharArray();
        return f(cs,ps,0,0);
    }

    public static boolean f(char[] s, char[] p, int i, int j) {
        if (i == s.length) {
            if ((j < p.length && p[j] != '.') && (j < p.length-1 && p[j+1] != '*')) {
                return false;
            }
            return true;
        }
        if (j == p.length) {
            return false;
        }
        if (p[j] == '.' && f(s,p,i+1,j+1)) {
            return true;
        }
        if (p[j] == '*') {
            if (p[j-1] == '.') {
                int t = 1;
                while (i+t-1 < s.length) {
                    if (f(s,p,i+t,j+1)) {
                        return true;
                    }
                    t++;
                }
            }
            if (f(s,p,i,j+1)) {
                return true;
            }
            int t = 1;
            while (i+t-1 < s.length && s[i+t-1] == p[j-1]) {
                if (f(s,p,i+t,j+1)) {
                    return true;
                }
                t++;
            }
        }
        if (s[i] == p[j]) {
            if(f(s,p,i+1,j+1)) {
                return true;
            }
        }
        if (j < p.length-1 && p[j+1] == '*') {
            if (f(s,p,i,j+2)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String s = "ab", p = ".*c";
        System.out.println(isMatch(s,p));
    }
}
