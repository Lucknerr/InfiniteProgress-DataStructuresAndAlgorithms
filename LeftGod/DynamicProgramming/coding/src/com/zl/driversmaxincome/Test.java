package com.zl.driversmaxincome;

import java.sql.SQLOutput;

public class Test {
    public static void main(String[] args) {
        DriversMaxIncome driversMaxIncome = new DriversMaxIncome();
        int[][] d = {{8,10},{10,50},{13,1000},{11,12}};
        System.out.println(driversMaxIncome.driversMaxIncome(d));
        System.out.println(driversMaxIncome.driversMaxIncome1(d));
        System.out.println(driversMaxIncome.dp(d));
        System.out.println(driversMaxIncome.dp1(d));

    }
}
