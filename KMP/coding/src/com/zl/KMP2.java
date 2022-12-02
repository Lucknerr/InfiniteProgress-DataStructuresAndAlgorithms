package com.zl;

public class KMP2 {

    // 匹配子串与主串
    public int kmp(int start,String pri,String sub) {
        if (pri == null || sub == null) {
            return -1;
        }
        int priLen = pri.length();
        int subLen = sub.length();
        if (priLen == 0 || subLen == 0) {
            return -1;
        }
        if (priLen < subLen) {
            return -1;
        }
        int[] nextVal = setNextVal(sub);
        int i = start;
        int j = 0;
        while (i < priLen && j < subLen) {
            if (j == -1 || sub.charAt(j) == pri.charAt(i)) {
                i++;
                j++;
            } else {
                j = nextVal[j];
            }
        }
        if (j >= subLen) {
            return i - j;
        }
        return -1;
    }

    private int[] setNextVal(String sub) {
        int subLen = sub.length();
        int[] nextVal = new int[subLen];
        nextVal[0] = -1;
        if (subLen < 2) {
            return nextVal;
        }
        int[] next = new int[subLen];
        next[0] = -1;
        next[1] = 0;
        nextVal[1] = sub.charAt(0) == sub.charAt(1) ? -1 : 0;
        int j = 2;
        int k = 0;
        while (j < subLen) {
            if (k == -1 || sub.charAt(j-1) == sub.charAt(k)) {
                next[j] = ++k;
                if (sub.charAt(j) == sub.charAt(k)) {
                    nextVal[j] = next[k];
                } else {
                    nextVal[j] = k;
                }
                j++;
            } else {
                k = next[k];
            }
        }
        return nextVal;

    }
}
