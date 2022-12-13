package com.zl.currencygroup3;

import java.util.HashMap;
import java.util.Map;

// 货币组合问题2
public class CurrencyGroup3 {

    /**
     * 暴力递归
     * @param arr 货币面值数组
     * @param aim 目标面值
     * @return 返回货币组合的最大方案
     */
    public int currencyGroup(int[] arr, int aim) {
        // 记录每种面值及每种面值出现的次数,并转换成对应的数组
        Map<Integer,Integer> map = new HashMap<>();
        for (int a : arr) {
            map.put(a,map.containsKey(a) ? map.get(a)+1 : 1);
        }
        int N = map.size();
        int[] counts = new int[N];
        int[] faces = new int[N];
        int i = 0;
        for (Map.Entry<Integer,Integer> entry : map.entrySet()) {
            counts[i] = entry.getValue();
            faces[i++] = entry.getKey();
        }
        return f(counts,faces,0,aim);
    }

    /**
     * 暴力递归 从左往右的尝试模型
     * @param counts 面值个数
     * @param faces 货币面值数组
     * @param i 从i位置及其往后的所有位置
     * @param res 剩余面值
     * @return 返回剩余货币组合成的面值的最大方案
     */
    private int f(int[] counts, int[] faces, int i, int res) {
        // 面值为零,之前做的决定是对的,存在这种1种方案
        if (res == 0) {
            return 1;
        }
        // 没有货币时返回0种方案
        if (i == counts.length) {
            return 0;
        }
        int tmp = 0;
        // 尝试当前i位置的w货币用z张进行组合并且z为实际存在的张数
        for (int z = 0; z*faces[i] <= res && z <= counts[i] ; z++) {
            tmp += f(counts,faces,i+1,res-z*faces[i]);
        }
        return tmp;
    }

    /**
     * 动态规划 严格位置依赖
     * @param arr 货币面值数组
     * @param aim 目标面值
     * @return 返回货币组合的最大方案
     */
    public int dp(int[] arr, int aim) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int a : arr) {
            map.put(a,map.containsKey(a) ? map.get(a)+1 : 1);
        }
        int N = map.size();
        int[] counts = new int[N];
        int[] faces = new int[N];
        int index = 0;
        for (Map.Entry<Integer,Integer> entry : map.entrySet()) {
            counts[index] = entry.getValue();
            faces[index++] = entry.getKey();
        }
        int[][] dpT = new int[N+1][aim+1];
        for (int i = N; i >= 0; i--) {
            for (int r = 0; r <= aim; r++) {
                if (r == 0) {
                    dpT[i][r] = 1;
                } else if (i == N) {
                    dpT[i][r] = 0;
                } else {
                    for (int z = 0; z*faces[i] <= r && z <= counts[i] ; z++) {
                        dpT[i][r] += dpT[i+1][r-z*faces[i]];
                    }
                }
            }
        }
        return dpT[0][aim];
    }

    /**
     * 动态规划 优化枚举行为
     * @param arr 货币面值数组
     * @param aim 目标面值
     * @return 返回货币组合的最大方案
     */
    public int dp1(int[] arr, int aim) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int a : arr) {
            map.put(a,map.containsKey(a) ? map.get(a)+1 : 1);
        }
        int N = map.size();
        int[] counts = new int[N];
        int[] faces = new int[N];
        int index = 0;
        for (Map.Entry<Integer,Integer> entry : map.entrySet()) {
            counts[index] = entry.getValue();
            faces[index++] = entry.getKey();
        }
        int[][] dpT = new int[N+1][aim+1];
        for (int i = N; i >= 0; i--) {
            for (int r = 0; r <= aim; r++) {
                if (r == 0) {
                    dpT[i][r] = 1;
                } else if (i == N) {
                    dpT[i][r] = 0;
                } else {
                    dpT[i][r] = dpT[i+1][r];
                    if (r-faces[i] >= 0) {
                        dpT[i][r] += dpT[i][r-faces[i]];
                        int tmp = r - (counts[i]+1)*faces[i];
                        if (tmp >= 0) {
                            dpT[i][r] -= dpT[i+1][tmp];
                        }
                    }
                }
            }
        }
        return dpT[0][aim];
    }
}
