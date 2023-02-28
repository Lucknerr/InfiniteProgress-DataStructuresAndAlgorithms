package com.zl.driversmaxincome;

import java.sql.SQLOutput;

public class Test {
    public static void main(String[] args) {
        right(10,50,10000);
    }

    public static void right(int size, int range, int time) {
        for (int i = 0;i < time;i++) {
            int[][] d = createNums(size,range);
            DriversMaxIncome driversMaxIncome = new DriversMaxIncome();
            int a1 = driversMaxIncome.driversMaxIncome(d);
            int a2 = driversMaxIncome.driversMaxIncome1(d);
            int a3 = driversMaxIncome.dp(d);
            int a4 = driversMaxIncome.dp1(d);
            if (a1 != a2 && a1 != a3 && a1 != a4) {
                System.out.println(a1 + " " + a2 + " " + a3 + " " + a4);
            }
        }
        System.out.println("Done!");
    }


    public static int[][] createNums(int size , int range) {
        int[][] nums = new int[size][2];
        for (int i = 0;i < size;i++) {
            nums[i][0] = (int) (Math.random() * range);
            nums[i][1] = (int) (Math.random() * range);
        }
        return nums;
    }

}
