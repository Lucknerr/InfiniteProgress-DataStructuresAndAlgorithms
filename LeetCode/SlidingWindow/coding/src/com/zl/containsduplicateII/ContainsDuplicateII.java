package com.zl.containsduplicateII;

import java.util.HashSet;
import java.util.Set;

// https://leetcode.cn/problems/contains-duplicate-ii/description/?orderBy=most_relevant
public class ContainsDuplicateII {

    /**
     * 滑动窗口
     * @param nums 用户输入数组
     * @param k k距离
     * @return 是否在k距离内存在重复元素
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        // 用set集合不能添加重复元素的特性
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length;i++) {
            // 将前k个元素存入集合
            if (i > k) {
                // 如果i超过了k那么将将集合最早添加进来的元素删除
                // 这样可以保证集合内只有k-1个元素,超出k距离就被移除
                set.remove(nums[i - k - 1]);
            }
            // 如果添加失败说明有重复元素
            if (!set.add(nums[i])) {
                return true;
            }
        }
        return false;
    }
}

