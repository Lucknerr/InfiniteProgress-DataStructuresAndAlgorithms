package com.zl.sumnearsubgather;

// 两个累加和最接近的集合
public class SumNearSubGather {

    /**
     * 暴力递归
     * @param arr 正数数组
     * @return 最接近情况下较小累加和
     */
    public int sumNearSubGather(int[] arr) {
        int sum = 0;
        for (int a : arr) {
            sum += a;
        }
        return f(arr,0,sum >> 1);
    }

    private int f(int[] arr, int i, int res) {
        if (i == arr.length) {
            return 0;
        }
        int p1 = f(arr,i+1,res-arr[i]) + arr[i];
        int p2 = f(arr,i+1,res);
        return Math.max(p1,p2);
    }
}
