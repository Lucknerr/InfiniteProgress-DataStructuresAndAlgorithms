package com.zl;

public class Solution {
    public static int reverse(int x) {
        int ans = 0;
        int sign = 1;
        if (x > 0) {
            x = -x;
            sign = -1;
        }
        int k = Integer.MIN_VALUE / 10;
        int l = Integer.MIN_VALUE % 10;
        while (x != 0) {
            if (ans < k || (ans == k && x % 10 < l)) {
                return 0;
            }
            ans = ans * 10 + x % 10;
            x = x / 10;
        }
        return ans * sign;
    }

    public static void main(String[] args) {
        int s = 1463847412;
        System.out.println(reverse(s));
//        System.out.println(Integer.MAX_VALUE);
    }

}
