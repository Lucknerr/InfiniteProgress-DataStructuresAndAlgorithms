package com.zl.toplayerpoker;

// 绝顶玩家纸牌问题 暴力递归->动态规划
public class TopPlayerPoker {

    /**
     * 尝试暴力递归
     * 获取赢家分数
     * @param poker 排列好的纸牌
     * @return 赢家的分数
     */
    public int winner(int[] poker) {
        // 先手玩家和后手玩家谁的分高返回谁
        return Math.max(first(poker,0,poker.length-1),later(poker,0,poker.length-1));
    }

    /**
     * 先手函数
     * @param poker 排列好的纸牌
     * @param L 当前剩余纸牌中最左的那张牌
     * @param R 当前剩余纸牌中最左的那张牌
     * @return 当前剩余纸牌中先手方获取的最优分数
     */
    public int first(int[] poker, int L, int R) {
        // L==R说明当前一轮只剩最后一张牌了
        if (L == R) {
            // 所以当前这一轮的先手玩家得最后一张牌
            return poker[L];
        }
        // 尝试1: 先手玩家抓左边牌得的分 + 剩余牌后手姿态后续得分(由于先手玩家已经抓过牌,所以切换到剩下牌当中的后手姿态)
        // 尝试2: 先手玩家抓右边牌得的分 + 剩余牌后手姿态后续得分
        // 取尝试1和2的最大得分
        return Math.max(poker[L] + later(poker,L+1,R),poker[R] + later(poker,L,R-1));
    }

    /**
     * 后手函数
     * @param poker 排列好的纸牌
     * @param L 当前剩余纸牌中最左的那张牌
     * @param R 当前剩余纸牌中最左的那张牌
     * @return 当前剩余纸牌中先手方获取的最优分数
     */
    public int later(int[] poker, int L, int R) {
        // L==R说明当前一轮只剩最后一张牌了
        if (L == R) {
            // 由于当前这一轮的最后一张牌要归先手玩家,所以后手玩家在这种情况先不得分
            return 0;
        }
        // 因为是后手姿态所以当前牌组其中一张是被先手玩家首先挑走
        // 尝试1: 被挑走左边牌,那么将转换为剩余牌组中的先手姿态获取得分
        // 尝试2: 被挑走右边牌,那么将转换为剩余牌组中的先手姿态获取得分
        // 由于尝试1和2都是以先手姿态获取剩余牌组最优得分,
        // 又因为是以后手姿态参与当前牌组所以获取的得分应该是在后手姿态做最大努力的尝试下取最小尝试
        return Math.min(first(poker,L+1,R),first(poker,L,R-1));
    }

    /**
     * 初浅动态规划
     * 获取赢家分数
     * @param poker 排列好的纸牌
     * @return 赢家的分数
     */
    public int winner1(int[] poker) {
        // 添加傻缓存
        int N = poker.length;
        int[][] fMap = new int[N][N];
        int[][] lMap = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                fMap[i][j] = -1;
                lMap[i][j] = -1;
            }

        }
        return Math.max(first1(poker,0,N-1,fMap,lMap),later1(poker,0,N-1,fMap,lMap));
    }

    /**
     * 先手函数
     * @param poker 排列好的纸牌
     * @param L 当前剩余纸牌中最左的那张牌
     * @param R 当前剩余纸牌中最左的那张牌
     * @return 当前剩余纸牌中先手方获取的最优分数
     */
    public int first1(int[] poker, int L, int R, int[][] fMap, int[][] lMap) {
        if (fMap[L][R] != -1) {
            return fMap[L][R];
        }
        int ans = 0;
        if (L == R) {
            ans = poker[L];
        } else {
            ans = Math.max(poker[L] + later1(poker, L + 1, R, fMap, lMap), poker[R] + later1(poker, L, R - 1, fMap, lMap));
        }
        fMap[L][R] = ans;
        return ans;
    }

    /**
     * 后手函数
     * @param poker 排列好的纸牌
     * @param L 当前剩余纸牌中最左的那张牌
     * @param R 当前剩余纸牌中最左的那张牌
     * @return 当前剩余纸牌中先手方获取的最优分数
     */
    public int later1(int[] poker, int L, int R, int[][] fMap, int[][] lMap) {
        if (lMap[L][R] != -1) {
            return lMap[L][R];
        }
        int ans = 0;
        if (L != R) {
            ans = Math.min(first1(poker,L+1,R,fMap,lMap),first1(poker,L,R-1,fMap,lMap));
        }
        lMap[L][R] = ans;
        return lMap[L][R];
    }

    /**
     * 动态规划
     * 获取赢家分数
     * @param poker 排列好的纸牌
     * @return 赢家的分数
     */
    public int winner3(int[] poker) {
        // 依赖动态规划表
        int N = poker.length;
        int[][] f = new int[N][N];
        int[][] l = new int[N][N];
        for (int i = 0;i < N;i++) {
            f[i][i] = poker[i];
            int L = 0;
            int R = i+1;
            while (R < N) {
                f[L][R] = Math.max(poker[L] + l[L+1][R],poker[R] + l[L][R-1]);
                l[L][R] = Math.min(f[L+1][R],f[L][R-1]);
                L++;
                R++;
            }
        }
        return Math.max(f[0][N-1],l[0][N-1]);
    }

}
