package com.zl.stringswaptime;

public class StringSwapTime {

    /** 贪心
     * 滑动窗口
     * @param s 只含两种字符的字符串
     * @return 每种字符统一分布在两侧,至少需要交换的次数
     */
    public int swapTime(String s) {
        // 定义一个左指针
        int l = 0;
        // 记录交换次数
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            // 当i位置为G时
            if (s.charAt(i) == 'G') {
                // i - l 位置就是G要交换的次数
                // l指针向右滑动
                ans += i - l++;
            }
        }
        // 下文同上文一样,下文是获取(将所有的B放左边)的交换次数
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
