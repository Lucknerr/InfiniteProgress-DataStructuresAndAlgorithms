package com.zl.beatthebeast;

public class BeatTheBeast {

    /**
     * 暴力递归
     * @param N 怪兽总血量
     * @param M 一次最多砍M滴血 0-M范围等概率随机血量
     * @param k 最多可以砍k下
     * @return 返回怪兽被砍死的概率
     */
    public double beatTheBeast(int N, int M, int k) {
        return f(N,M,k) / Math.pow(M+1,k);
    }

    /**
     * 暴力递归
     * @param N 怪兽剩余血量
     * @param M 一次最多砍M滴血 0-M范围等概率随机血量
     * @param k 还有k次可以砍
     * @return 返回怪兽被砍死的所有情况数
     */
    public double f(int N, int M, int k) {
        if (k == 0) {
            return N <= 0 ? 1 : 0;
        }
        int tmp = 0;
        for (int i = 0; i <= M; i++) {
            tmp += f(N-i,M,k-1);
        }
        return tmp;
    }

    /**
     * 动态规划
     * @param N 怪兽总血量
     * @param M 一次最多砍M滴血 0-M范围等概率随机血量
     * @param k 最多可以砍k下
     * @return 返回怪兽被砍死的概率
     */
    public double dp(int N, int M, int k) {
        int[][] dpT = new int[k+1][N+1];
        dpT[0][0] = 1;
        for (int i = 1; i <= k; i++) {
            for (int n = 0; n <= N; n++) {
                for (int j = 0; j <= M; i++) {
                    dpT[i][n] += n-j <= 0 ? Math.pow(M+1,k) : dpT[i-1][n-j];
                }
            }
        }
        return dpT[k][N];
    }

}
