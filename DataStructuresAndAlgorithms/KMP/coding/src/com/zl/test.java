package com.zl;

public class test {
    public static void main(String[] args) {
        KMP2 kmp = new KMP2();
        String a = "abcdafgaccdbfa";
        String b = "da";
        int start = 0;
        int ans = kmp.kmp(start,a,b);
        System.out.println(ans);
    }
}
