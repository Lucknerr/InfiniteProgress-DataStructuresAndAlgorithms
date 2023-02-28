package com.zl.trappingrainwater;

import java.util.*;

public class Solution {
    static int[] row = new int[9];
    static int[] col = new int[9];
    static int[] box = new int[9];

    public static void solveSudoku(char[][] board) {
        for (int r = 0;r < 9;r++) {
            for (int c = 0;c <9;c++) {
                char tmp = board[r][c];
                if (tmp != '.') {
                    int bit = 1 << (tmp - '1');
                    row[r] ^= bit;
                    col[c] ^= bit;
                    box[r/3*3+c/3] ^= bit;
                }
            }
        }
        f(board,0,0);
    }

    public static boolean f(char[][] board, int r, int c) {
        System.out.println(r + "" + c);
        if (r == 9) {
            return true;
        }
        if (c == 9) {
            return f(board,r+1,0);
        }
        if (board[r][c] != '.') {
            return f(board,r,c+1);
        }
        for (int i = 0;i < 9;i++) {
            int bit =  1 << i;
            int b1 = row[r] & bit;
            int b2 = col[c] & bit;
            int b3 = box[r/3*3+c/3] & bit;
            if ((row[r] & bit) == 0 && (col[c] & bit) == 0 && (box[r/3*3+c/3] & bit) == 0) {
                board[r][c] = (char) (i + '1');
                row[r] ^= bit; col[c] ^= bit; box[r/3*3+c/3] ^= bit;
                if (f(board,r,c+1)) {
                    return true;
                }
                board[r][c] = '.';
                row[r] ^= bit; col[c] ^= bit; box[r/3*3+c/3] ^= bit;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        char[][] board = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        solveSudoku(board);
        System.out.println(board);

    }
}
