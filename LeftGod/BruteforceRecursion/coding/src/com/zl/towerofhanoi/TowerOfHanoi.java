package com.zl.towerofhanoi;

public class TowerOfHanoi {

    /**
     * 暴力递归 六函数相互依赖
     * @param n 圆盘编号,圆盘大小和编号大小成正比 编号从1-7
     */
    public void towerOfHanoi(int n) {
        leftToRight(n);
    }

    /**
     * 暴力递归
     * 从左到右
     * @param n 圆盘编号,圆盘大小和编号大小成正比 编号从1-7
     */
    private void leftToRight(int n) {
        // 当只剩一个圆盘1时
        if (n == 1) {
            // 圆盘1 直接去到目标位置
            System.out.println("move 1 from left to right");
            return;
        }
        // 要想从左到右就要拆分成三大步
        // 首先 从n-1圆盘开始往上整体 从左到中 是为最地下的圆盘腾出空间 因为要遵从小压大原则
        leftToMid(n-1);
        // 其次 当前圆盘去到目标位置
        System.out.println("move " + n + " from left to right");
        // 再 从n-1圆盘开始整体往上 从中到右
        midToRight(n-1);
    }

    /**
     * 暴力递归
     * 从左到中
     * @param n 圆盘编号,圆盘大小和编号大小成正比 编号从1-7
     */
    private void leftToMid(int n) {
        if (n == 1) {
            System.out.println("move 1 from left to mid");
            return;
        }
        // 要想从左到中就要拆分成三大步
        // 首先 从n-1圆盘开始往上整体 从左到右 是为最地下的圆盘腾出空间 因为要遵从小压大原则
        leftToRight(n-1);
        System.out.println("move " + n + " from left to mid");
        // 再 从n-1圆盘开始整体往上 从右到中
        rightToMid(n-1);
    }

    /**
     * 暴力递归
     * 从中到左
     * @param n 圆盘编号,圆盘大小和编号大小成正比 编号从1-7
     */
    private void midToLeft(int n) {
        if (n == 1) {
            System.out.println("move 1 from mid to left");
            return;
        }
        // 要想从左到中就要拆分成三大步
        // 首先 从n-1圆盘开始往上整体 从中到右 是为最地下的圆盘腾出空间 因为要遵从小压大原则
        midToRight(n-1);
        System.out.println("move " + n + " from mid to left");
        // 再 从n-1圆盘开始整体往上 从右到左
        rightToLeft(n-1);
    }

    /**
     * 暴力递归
     * 从中到右
     * @param n 圆盘编号,圆盘大小和编号大小成正比 编号从1-7
     */
    private void midToRight(int n) {
        if (n == 1) {
            System.out.println("move 1 from mid to right");
            return;
        }
        midToLeft(n-1);
        System.out.println("move " + n + " from mid to right");
        leftToRight(n-1);
    }

    /**
     * 暴力递归
     * 从右到左
     * @param n 圆盘编号,圆盘大小和编号大小成正比 编号从1-7
     */
    private void rightToLeft(int n) {
        if (n == 1) {
            System.out.println("move 1 from right to left");
            return;
        }
        rightToMid(n-1);
        System.out.println("move " + n + " from right to left");
        midToLeft(n-1);
    }

    /**
     * 暴力递归
     * 从右到中
     * @param n 圆盘编号,圆盘大小和编号大小成正比 编号从1-7
     */
    private void rightToMid(int n) {
        if (n == 1) {
            System.out.println("move 1 from right to mid");
            return;
        }
        rightToLeft(n-1);
        System.out.println("move " + n + " from right to mid");
        leftToMid(n-1);
    }

    /**
     * 暴力递归优化
     * @param n 圆盘编号,圆盘大小和编号大小成正比 编号从1-7
     */
    public void towerOfHanoi1(int n) {
        // 起始位置
        String from = "left";
        // 将要去的位置
        String to = "right";
        // 其他位置
        String other = "mid";
        fromTo(n,from,to,other);
    }

    /**
     * 暴力递归优化
     * 从 n 圆盘往上的所有圆盘整体从起始位置到目标位置
     * @param n 圆盘编号,圆盘大小和编号大小成正比 编号从1-7
     * @param from 当前位置
     * @param to 将要去的位置
     * @param other 位置
     */
    private void fromTo(int n,String from, String to, String other) {
        // 当只剩一个圆盘1时
        if (n == 1) {
            // 圆盘1 直接去到目标位置
            System.out.println("move 1 from " + from + " to " + to);
            return;
        }
        // 递归调用 从 n-1 圆盘往上的所有圆盘整体从起始位置到其他位置 整体为最低下圆盘腾出空间后
        fromTo(n-1,from,other,to);
        // 最低下直接去到目标位置
        System.out.println("move " + n + " from " + from + " to " + to);
        // 递归调用 从 n-1 圆盘往上的所有圆盘整体从其他位置到目标位置
        fromTo(n-1,other,to,from);
    }
}
