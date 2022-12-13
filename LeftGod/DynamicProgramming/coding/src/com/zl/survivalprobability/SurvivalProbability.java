package com.zl.survivalprobability;

// 醉汉生存概率
public class SurvivalProbability {

    /**
     * 暴力递归
     * @param N 区域长
     * @param M 区域宽
     * @param row 纵坐标位置
     * @param col 横坐标位置
     * @param k 一共要走k步
     * @return 返回生存概率
     */
    public double survivalProbability(int N, int M, int row, int col, int k) {
        // 不论生死所有路径总数
        int tmp = 4;
        for (int i = 1; i < k; i++) {
            tmp *= 4;
        }
        return (double) f(N,M,row,col,k) / tmp;
    }

    /**
     * 暴力递归
     * @param N 区域长
     * @param M 区域宽
     * @param row 当前纵坐标位置
     * @param col 当前横坐标位置
     * @param k 还有k步要走
     * @return 返回当前位置还要走剩下k步的存活率
     */
    public int f(int N, int M, int row, int col, int k) {
        // 越界情况,不统计路径数
        if (row > N || row < 0 || col > M || col < 0) {
            return 0;
        }
        // 刚好用完k步,统计1次路径数
        if (k == 0) {
            return 1;
        }
        // 有四个方向的尝试,四个方向存在的路径数就是能活着走完k步的路径总数
        int ans = f(N,M,row-1,col,k-1) + f(N,M,row+1,col,k-1) +
                  f(N,M,row,col+1,k-1) + f(N,M,row,col-1,k-1);
        return ans;
    }

    /**
     * 动态规划 严格位置依赖
     * @param N 区域长
     * @param M 区域宽
     * @param row 纵坐标位置
     * @param col 横坐标位置
     * @param k 一共要走k步
     * @return 返回生存概率
     */
    public double dp(int N, int M, int row, int col, int k) {
        int tmp = 4;
        for (int i = 1; i < k; i++) {
            tmp *= 4;
        }
        int[][][] dpT = new int[k+1][N+1][M+1];
        for (int rk = 0; rk <= k; rk++) {
            for (int r = 0; r <= N; r++) {
                for (int c = 0; c <= M; c++) {
                    if (rk == 0) {
                        dpT[rk][r][c] = 1;
                    } else {
                        int up = r == N ? 0 : dpT[rk-1][r+1][c];
                        int down = r == 0 ? 0 : dpT[rk-1][r-1][c];
                        int left = c == 0 ? 0 : dpT[rk-1][r][c-1];
                        int right = c == M ? 0 : dpT[rk-1][r][c+1];
                        dpT[rk][r][c] = up + down + left + right;
                    }
                }
            }
        }
        return (double) dpT[k][row][col] / tmp;
    }
}
