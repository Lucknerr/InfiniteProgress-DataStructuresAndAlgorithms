package com.zl.stringswaptime;

public class StringSwapTime {

    /** 贪心
     * 滑动窗口
     * @param s 只含两种字符的字符串
     * @return 每种字符统一分布在两侧,至少需要交换的次数
     */
    public int swapTime(String s) {
        int l = 0;
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'G') {
                ans += i - l++;
            }
        }
        int ll = 0;
        int tmp = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'B') {
                tmp += i - ll++;
            }
        }
        return Math.min(ans,tmp);
    }
}
