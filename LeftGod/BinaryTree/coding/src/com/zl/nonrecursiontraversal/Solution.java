package com.zl.nonrecursiontraversal;

import java.util.*;

public class Solution {


    public int findMaxConsecutiveOnes(int[] nums) {
        return f(nums,0,1);
    }

    public int f(int[] nums, int i, int k) {
        if (i == nums.length) {
            return 0;
        }
        int p1 = 0;
        if (nums[i] == 1) {
            p1 = f(nums,i+1,k) + 1;
        } else {
            p1 = k == 1 ? f(nums,i+1,0) + 1 : 0;
        }
        int p2 = f(nums,i+1,k);
        return Math.max(p1,p2);
    }
}
