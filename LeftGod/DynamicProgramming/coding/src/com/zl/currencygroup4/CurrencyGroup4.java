package com.zl.currencygroup4;

// 货币问题
public class CurrencyGroup4 {

    /**
     * 暴力递归
     * @param arr 货币
     * @param aim 面值
     * @return 最好少组合数
     */
    public int currencyGroup(int[] arr, int aim) {
        return f(arr,0,aim);
    }

    /**
     * 暴力递归 从左往右的尝试模型
     * @param arr 货币
     * @param i i位置货币
     * @param res 剩余面值
     * @return 最好少组合数
     */
    private int f(int[] arr, int i, int res) {
        if (res == 0) {
            return 0;
        }
        if (i == arr.length || res < 0) {
            return Integer.MAX_VALUE;
        }
        int min = Integer.MAX_VALUE;
        for (int z = 0; z*arr[i] <= res; z++) {
            int ans = f(arr,i+1,res-z*arr[i]);
            min = Math.min(min,ans == Integer.MAX_VALUE ? ans : ans+z);
        }
        return min;
    }

    /**
     * 动态规划 含枚举行为
     * @param arr 货币
     * @param aim 面值
     * @return 最好少组合数
     */
    public int dp(int[] arr, int aim) {
        int N = arr.length;
        int[][] dpT = new int[N+1][aim+1];
        for (int i = N; i >= 0; i--) {
            for (int r = 0; r <= aim; r++) {
                if (r == 0) {
                    dpT[i][r] = 0;
                } else if (i == N) {
                    dpT[i][r] = Integer.MAX_VALUE;
                } else {
                    int min = Integer.MAX_VALUE;
                    for (int z = 0; z*arr[i] <= r; z++) {
                        int ans = dpT[i+1][r-z*arr[i]];
                        min = Math.min(min,ans == Integer.MAX_VALUE ? ans : ans+z);
                    }
                    dpT[i][r] = min;
                }
            }
        }
        return dpT[0][aim];
    }
}
