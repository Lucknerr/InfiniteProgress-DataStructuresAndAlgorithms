package com.zl.printstringallsubsequence;

// 打印字符串的所有子序列
public class PrintStringAllSubSequence {

    /**
     * 暴力递归
     * @param s 用户输入字符串
     */
    public void printAllSub(String s) {
        char[] c = s.toCharArray();
        printSub(c,0,"-");
    }

    /**
     *
     * @param c 字符数组
     * @param i i位置字符
     * @param ans 子序列
     */
    public void printSub(char[] c, int i,String ans) {
        // 当i越界也就是没有字符可以遍历的情况下
        if (i == c.length) {
            // 打印之前路线上多种决定下的成果
            System.out.println(ans);
            return;
        }
        // 决定不要i位置字符,然后继续到i+1位置做决定
        printSub(c,i+1,ans);
        // 这里就是添加了i位置的字符到决定里
        ans+=c[i];
        // 决定要i位置字符,然后继续到i+1位置做决定
        printSub(c,i+1,ans);
    }
}
