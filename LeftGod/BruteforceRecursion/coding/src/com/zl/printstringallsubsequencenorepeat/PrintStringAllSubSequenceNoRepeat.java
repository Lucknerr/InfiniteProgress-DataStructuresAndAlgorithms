package com.zl.printstringallsubsequencenorepeat;

import java.util.HashSet;
import java.util.Set;

// 打印字符串的所有子序列去重
public class PrintStringAllSubSequenceNoRepeat {

    /**
     * 利用set去重的特性
     * @param s 用户输入字符串
     */
    public void printAllSub(String s) {
        char[] c = s.toCharArray();
        Set<String> set = new HashSet<>();
        printSub(c,0,"",set);
        for (String i : set) {
            System.out.println(i);
        }
    }

    /**
     * 暴力递归
     * @param c 字符数组
     * @param i i位置字符
     */
    private void printSub(char[] c, int i, String ans, Set<String> set) {
        if (i == c.length) {
            set.add(ans);
            return;
        }
        printSub(c,i+1,ans,set);
        ans += c[i];
        printSub(c,i+1,ans,set);
    }
}
