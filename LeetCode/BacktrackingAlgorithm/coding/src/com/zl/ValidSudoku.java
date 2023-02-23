package com.zl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// 36. 有效的数独: https://leetcode.cn/problems/valid-sudoku/description/
public class ValidSudoku {
    /**
     整体思路:
     1.每一行，每一列，每一九宫格内出现的数，行，列，九宫格，各自单独用一个容器标记
     2.每一个格子遍历一遍，遍历的过程中出现的数同时在三种容器中查看是否被标记，
     如果已被标记，说明其中某一行，某一列或某个九宫格存在重复数字，代表数独无效
     反之没被标记过的就标记起来，直到整个数独遍历完
     */

    /**
     * 利用哈希表
     * @param board 数独表
     * @return
     */
    public boolean isValidSudoku1(char[][] board) {
        // 用三个哈希表，键存某一行，某一列或某个九宫格(0-8)的标号或者说索引
        // 值用集合，某一行，某一列或某个九宫格内出现的数字
        Map<Integer,Set<Character>> toRow = new HashMap<>();
        Map<Integer,Set<Character>> toCol = new HashMap<>();
        Map<Integer,Set<Character>> toSud = new HashMap<>();
        // 初始化所有的哈希表
        for (int i = 0;i < 9;i++) {
            toRow.put(i,new HashSet<>());
            toCol.put(i,new HashSet<>());
            toSud.put(i,new HashSet<>());
        }
        // i 代表行 j 代表列 遍历每个格子
        for (int i = 0;i < 9;i++) {
            for (int j = 0;j < 9;j++) {
                // 当前字符
                char c = board[i][j];
                /**
                 如果字符为[.] 跳过

                 此时 如果判断是否被标记 利用set的特性不能存重复值，存储失败返回false

                 对于标记每行的容器来说 i 就是索引
                 对于标记每列的容器来说 j 就是索引
                 对于标记每个九宫格的容器来说 i/3*3+j/3 就是索引

                 举个例子 如果当前遍历到的格子为 i:6 j:7
                 可以这么理解 本题数独是3*3的九宫格，那么当前行看看是3的几倍向下取整，取整后的倍数就是所处第几行九宫格
                 乘个3表示成线性索引的最小范围，列除3代表精确到该行第几列
                 */
                if (c != '.' && (!toRow.get(i).add(c) || !toCol.get(j).add(c) || !toSud.get(i/3*3+j/3).add(c))) {
                    return false;
                }
            }
        }
        // 等到这一步说明前面没有碰到存储失败的时候
        return true;
    }

    /**
     * 利用数组
     * @param board 数独表
     * @return
     */
    public boolean isValidSudoku2(char[][] board) {
        // 利用布尔类型的数组
        // 也是行，列，九宫格各自标记
        boolean[][] toRow = new boolean[10][10], toCol = new boolean[10][10], toSud = new boolean[10][10];
        // 遍历每个格子
        for (int i = 0;i < 9;i++) {
            for (int j = 0;j < 9;j++) {
                // 当前字符
                char a = board[i][j];
                // 如果是[.]跳过
                if (a == '.') continue;
                // 索引转换
                int c = a - '0';
                // true为标记过，有一个为true就说明无效
                if ((toRow[j][c] || toCol[i][c] || toSud[i/3*3+j/3][c])) return false;
                // 没标记过就标记起来
                toRow[j][c] = true; toCol[i][c] = true; toSud[i/3*3+j/3][c] = true;
            }
        }
        return true;
    }

    /**
     * 位运算 状态压缩
     * @param board 数度表
     * @return
     */
    public boolean isValidSudoku3(char[][] board) {
        // 每个容器中的是1-9，而每个位置上要表达的信息不是已存在就是暂时还不存在
        // 那么可以将每个位置的状态标记为0和1，0代表未标记，1代表标记
        // 同样行，列，九宫格格子标记
        int[] toRow = new int[10], toCol = new int[10], toSud = new int[10];
        // 遍历每个格子
        for (int i = 0;i < 9;i++) {
            for (int j = 0;j < 9;j++) {
                char a = board[i][j];
                if (a == '.') continue;
                // 1向左移动对应的位置表示，用对应位置的1试探该位置之前是否被标记过
                int c = 1 << (a - '0');
                // 同样 运算后 得0表示还没被标记，不得0就代表被标记过
                if ((toRow[i] & c) != 0 || (toCol[j] & c) != 0 || (toSud[i/3*3+j/3] & c) != 0) return false;
                // 没被标记就标记
                toRow[i] ^= c; toCol[j] ^= c; toSud[i/3*3+j/3] ^= c;
            }
        }
        return true;
    }
}
