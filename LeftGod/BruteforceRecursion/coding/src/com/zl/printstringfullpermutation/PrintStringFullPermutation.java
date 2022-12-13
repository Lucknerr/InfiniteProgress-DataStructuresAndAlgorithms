package com.zl.printstringfullpermutation;

import java.util.ArrayList;
import java.util.List;

// 打印一个字符串的全排列
public class PrintStringFullPermutation {

    /**
     * 暴力递归
     * @param s 用户输入字符串
     */
    public void printStringFullPermutation(String s) {
        // 转换成字符数组
        char[] c = s.toCharArray();
        // 申请一个动态数组
        List<Character> list = new ArrayList<>();
        // 将字符数组装进动态数组
        for (char i : c) {
            list.add(i);
        }
        // 全排列打印
        printFullPermutation(list,"");
    }

    /**
     * 暴力递归
     * @param list 字符数组
     * @param ans 之间做的决定下的结构
     */
    public void printFullPermutation(List<Character> list, String ans) {
        // 如果数组为空
        if (list.isEmpty()) {
            // 之间答应空结果
            System.out.println(ans);
            return;
        }
        // 遍历数组,遍历到的位置就是每一种决定线或子决定线的开头
        for (int j = 0; j < list.size(); j++) {
            // 移除j位置元素,避免该决定线上重复组合这个元素,并且捕获这个元素
            char cur = list.remove(j);
            // 打印移除j位置元素后剩下字符的全排列
            printFullPermutation(list,ans + cur);
            // 从子过程回来就要恢复现场,避免以其他位置开头的决定组合不了这个元素
            list.add(j,cur);
        }
    }
}
