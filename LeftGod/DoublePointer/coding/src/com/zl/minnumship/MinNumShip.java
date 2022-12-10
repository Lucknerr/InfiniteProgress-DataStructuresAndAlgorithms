package com.zl.minnumship;

import java.util.Arrays;

public class MinNumShip {

    /**
     *
     * @param arr 若干人的体重
     * @param k 单个船的载重
     * @return 用最少的船全部装走,返回船数
     */
    public int minShip(int[] arr, int k){
        if (arr == null || arr.length == 0) {
            return 0;
        }
        Arrays.sort(arr);
        int N = arr.length;
        if (N == 1) {
            return 1;
        }
        if (arr[N-1] > k) {
            return 0;
        }
        int L = diff(arr,0,N-1,k >> 1);
        int R = L + 1;
        int E = 0;
        int C = 0;
        int T = R;
        while (L > 0 || R < N) {
            if (L < 0) {
                return C + N - R + ((E + 1) >> 1);
            }
            if (R >= N) {
                return C + L + 1 + ((E + 1) >> 1);
            }
            if (arr[L] + arr[R] > k) {
                E++;
                L--;
            }
            if (arr[L] + arr[R] <= k) {
                C++;
                R++;
                L--;
            }
        }
        return 0;
    }

    public int diff (int[] arr, int l, int r, int D) {
        while (l <= r) {
            int M = l + ((r - l) >> 1);
            if (arr[M] < D) {
                l = M + 1;
            } else if (arr[M] > D) {
                r = M - 1;
            } else {
                return M;
            }
        }
        return r;
    }
}
