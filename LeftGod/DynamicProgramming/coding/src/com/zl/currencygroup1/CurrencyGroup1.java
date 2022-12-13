package com.zl.currencygroup1;


// 货币组合问题1
public class CurrencyGroup1 {

    /**
     * 暴力递归
     * @param arr 货币数组
     * @param k 目标面值
     * @return 返回最多有多少种方案
     */
    public int currencyGroup(int[] arr, int k) {
        return f(arr,0,k);
    }

    /**
     * 暴力递归 从左往又的尝试模型
     * @param arr 货币数组
     * @param i i位置货币
     * @param res 剩余面值
     * @return 返回i位置及i位置以后货币数组最多有多少种方案
     */
    private int f(int[] arr, int i, int res) {
        // 剩余的目标面值为负数,说明这条决定线是错误的,返回0种方案
        if (res < 0) {
            return 0;
        }
        // 剩余目标面值正好为0,说明之前做的决定是对的,返回存在1种方案
        if (res == 0) {
            return 1;
        }
        // 场上没有可选货币
        if (i == arr.length) {
            // 返回0种方案
            return 0;
        }
        // 尝试要i位置的货币进组合方案的后续方案数+尝试不要i位置的货币进组合方案的后续方案数
        return f(arr,i+1,res) + f(arr,i+1,res-arr[i]);
    }

    /**
     *  动态规划 严格位置依赖
     * @param arr 货币数组
     * @param k 目标面值
     * @return 返回最多有多少种方案
     */
    public int dp(int[] arr, int k) {
        int N = arr.length;
        int[][] dpT = new int[N+1][k+1];
        for (int i = N; i >= 0; i--) {
            for (int res = 0; res <= k; res++) {
                if (res == 0) {
                    dpT[i][res] = 1;
                } else if (i == N) {
                    dpT[i][res] = 0;
                } else {
                    dpT[i][res] = dpT[i+1][res] + (res-arr[i] < 0 ? 0 : dpT[i+1][res-arr[i]]);
                }
            }
        }
        return dpT[0][k];
    }
}
