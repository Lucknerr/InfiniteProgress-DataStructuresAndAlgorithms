package com.zl.noduplicationcharsubstring;

import java.util.HashSet;
import java.util.Set;

public class NoDuplicationCharSubString {

    /**
     * 滑动窗口
     * @param s 用户输入字符串
     * @return 最长无重复字符子串的长度
     */
    public int noDuplicationCharSubString(String s) {
        // 边界条件
        if (s == null || s.length() == 0) {
            return 0;
        }
        // 利用set不能添加重复元素的原则
        Set<Character> set = new HashSet<>();
        // 慢指针
        int l = 0;
        // 快指针
        int r = 0;
        // 记录全局最大长度
        int len = 0;
        // 开始滑动窗口
        while (r < s.length()) {
            // 如果当前字符不能添加到集合中,说明集合存在元素
            if (!set.add(s.charAt(r))) {
                // 获取捕捉到的重复元素与集合中最早存入元素之间的距离,并且与全局最大距离之间取最大值
                len = Math.max(r-l,len);
                // 将当前集合中最早存入的元素删除
                set.remove(s.charAt(l++));
            } else {
                // 如期存入元素,快指针去到下一个位置
                r++;
            }
        }
        // 返回最大长度
        return len;
    }
}
