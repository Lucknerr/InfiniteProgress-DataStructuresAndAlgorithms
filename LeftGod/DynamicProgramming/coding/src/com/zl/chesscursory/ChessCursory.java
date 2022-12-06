package com.zl.chesscursory;

// 象棋走"马" 暴力递归->动态规划
public class ChessCursory {

    /**
     * 暴力递归
     * @param x x轴起始点
     * @param y y轴起始点
     * @param res 可以跳的次数
     * @param a x轴目标点
     * @param b y轴目标点
     * @return 总共有多少种方案跳到目标点
     */
    public int jump(int x, int y, int res, int a, int b) {
        return f(x,y,res,a,b);
    }

    /**
     *
     * @param x 当前所在x轴位置
     * @param y 当前所在y轴位置
     * @param res 还剩余可跳次数
     * @param a x轴目标点
     * @param b y轴目标点
     * @return 剩余步数有多少种方案到达目标点
     * 棋盘大小 9*10
     */
    public int f(int x, int y, int res, int a, int b) {
        // x y 越界情况
        if (x < 0 || y < 0 || x > 8 || y > 9) {
            // 越界了说明之前做过的觉得都是错的
            return 0;
        }
        // 没有可用步数了
        if (res == 0) {
            // 如果已经来到了目标点,说明直接做决定的方案是对的,成立一次,不在目标点,之前做过的觉得是不对的
            return x == a && y == b ? 1 : 0;
        }
        // 在所有点不考虑越界的情况下是有八个方向的移动选择,应为马走日
        return f(x+1,y+2,res-1,a,b) + // x+1,y+2
               f(x+2,y+1,res-1,a,b) + // x+2,y+1
               f(x+2,y-1,res-1,a,b) + // x+2,y-1
               f(x+1,y-2,res-1,a,b) + // x+1,y-2
               f(x-1,y-2,res-1,a,b) + // x-1,y-2
               f(x-2,y-1,res-1,a,b) + // x-2,y-1
               f(x-2,y+1,res-1,a,b) + // x-2,y+1
               f(x-1,y+2,res-1,a,b); // x-1,y+2
    }

    /**
     * 动态规划
     * @param x x轴起始点
     * @param y y轴起始点
     * @param res 可以跳的次数
     * @param a x轴目标点
     * @param b y轴目标点
     * @return 总共有多少种方案跳到目标点
     */
    public int jump1(int x, int y, int res, int a, int b) {
        // 动态规划表 依赖暴力递归
        int[][][] jump = new int[res+1][9][10];
        jump[0][a][b] = 1;
        for (int r = 1;r < res+1;r++) {
            for (int i = 0;i < 9;i++) {
                for (int j = 0;j < 10;j++){
                    jump[r][i][j] = checkSide(jump,i+1,j+2,r-1) + // x+1,y+2
                                    checkSide(jump,i+2,j+1,r-1) + // x+2,y+1
                                    checkSide(jump,i+2,j-1,r-1) + // x+2,y-1
                                    checkSide(jump,i+1,j-2,r-1) + // x+1,y-2
                                    checkSide(jump,i-1,j-2,r-1) + // x-1,y-2
                                    checkSide(jump,i-2,j-1,r-1) + // x-2,y-1
                                    checkSide(jump,i-2,j+1,r-1) + // x-2,y+1
                                    checkSide(jump,i-1,j+2,r-1); // x-1,y+2
                }
            }
        }
        return jump[res][x][y];
    }

    /**
     * 判断越界
     * @param dp 动态规划表
     * @param x 当前x轴位置
     * @param y 当前y轴位置
     * @param r 当前r轴位置
     * @return 依赖格子值
     */
    public int checkSide(int[][][] dp, int x, int y, int r) {
        if (x < 0 || y < 0 || x > 8 || y > 9) {
            // 越界了说明之前做过的觉得都是错的
            return 0;
        }
        return dp[r][x][y];
    }
}
