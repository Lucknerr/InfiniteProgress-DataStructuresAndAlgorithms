package com.zl;

import java.util.ArrayList;
import java.util.List;

public class SudokuSolver {
    /**
     思路: 将场上本就有的数放入状态容器中
     每个格子暴力尝试，一单走到后面得尝试不符合题意，
     回头重新尝试，直到尝试到最后一个格子并且尝试成功
     */

    // 定义 行，列，九宫格状态表
    int[] toRow = new int[10], toCol = new int[10], toSud = new int[10];

    /**
     * 回溯
     * @param board 数独表
     */
    public void solveSudoku(char[][] board) {
        // 初始化各种状态表
        for (int i = 0;i < 9;i++) {
            for (int j = 0;j < 9;j++) {
                char tmp = board[i][j];
                if (tmp == '.') continue;
                int bit = 1 << (tmp - '0');
                if ((toRow[i] & bit) != 0 || (toCol[j] & bit) != 0 || (toSud[i/3*3+j/3] & bit) != 0) return;
                toRow[i] ^= bit; toCol[j] ^= bit; toSud[i/3*3+j/3] ^= bit;
            }
        }
        // 从[0,0]位置开始填
        f(board,0,0);
    }

    /**
     * 黑盒原则:
     *  1.遍历位置逐行从上到下，每列从左到右依次遍历
     * @param board 数独表
     * @param r 行位置
     * @param c 列位置
     * @return 剩余数独如果能成功填完返回ture，否则返回false
     */
    public boolean f(char[][] board, int r, int c) {
        // 超出行范围，能到此表示成功填完整个数独
        if (r == 9) return true;
        // 超出列范围，去到下一行，重置列
        if (c == 9) return f(board,r+1,0);
        // 当前字符是[.] 到过去到下一个字符
        if (board[r][c] != '.') return f(board,r,c+1);
        // 1-9依次尝试
        for (int i = 1;i < 10;i++) {
            // 探针
            int bit = 1 << i;
            // 判断各表中是否当前尝试的数已经被标记
            if ((toRow[r] & bit) == 0 && (toCol[c] & bit) == 0 && (toSud[r/3*3+c/3] & bit) == 0) {
                // 尝试没出现重复，可以尝试填入
                board[r][c] = (char) (i + '0');
                // 并且各表在对应位置标记
                toRow[r] ^= bit; toCol[c] ^= bit; toSud[r/3*3+c/3] ^= bit;
                // 去到下一个字符做尝试
                if (f(board,r,c+1)) {
                    // 没有失败直接当成功返回
                    return true;
                }
                // 如果下一个尝试失败会来到这一步，说明之前做的觉得有问题
                // 还原现场
                board[r][c] = '.';
                toRow[r] ^= bit; toCol[c] ^= bit; toSud[r/3*3+c/3] ^= bit;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        List<Integer> tmp = new ArrayList<>();
        tmp.add(1);
        tmp.add(2);
        tmp.add(3);
        for (int a : tmp) {
            System.out.println(a);
        }
    }
}
