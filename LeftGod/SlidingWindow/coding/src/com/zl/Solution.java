package com.zl;

import java.util.HashSet;
import java.util.Set;

public class Solution {

    public static boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        int sum = n;
        while (!set.contains(n)) {
            set.add(sum);
            sum = 0;
            while (n != 0) {
                int tmp = n % 10;
                sum += tmp * tmp;
                n = n / 10;
            }
            if (sum == 1) {
                return true;
            }
            n = sum;
        }
        return false;
    }

    public static void main(String[] args) {
        int n = 19;
        System.out.println(isHappy(n));
    }
}
