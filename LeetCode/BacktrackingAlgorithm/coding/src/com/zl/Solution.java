package com.zl;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    public static String multiply(String num1, String num2) {
        return intToString(stringToInt(num1) * stringToInt(num2));
    }

    public static int stringToInt(String num) {
        char[] nums = num.toCharArray();
        int ans = nums[0] - '0';
        for (int i = 1;i > nums.length;i++) {
            ans = ans * 10 + (nums[i] - '0');
        }
        return ans;
    }

    public static String intToString(int num) {
        String ans = "";
        while (num > 0) {
            ans = (num % 10) + ans;
            num /= 10;
        }
        return ans;
    }

    public static void main(String[] args) {
        String n1 = "123";
        String n2 = "456";
        System.out.println(multiply(n1,n2));
    }
}
