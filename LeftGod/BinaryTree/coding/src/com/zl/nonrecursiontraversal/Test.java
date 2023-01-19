package com.zl.nonrecursiontraversal;

import javax.swing.tree.TreeNode;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        Solution s = new Solution();
        int[] x = {1,0,1,1,0,1};
        System.out.println(s.findMaxConsecutiveOnes(x));

    }
}
