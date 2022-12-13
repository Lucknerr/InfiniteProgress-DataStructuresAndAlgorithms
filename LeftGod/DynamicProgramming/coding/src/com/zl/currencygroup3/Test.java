package com.zl.currencygroup3;

public class Test {
    public static void main(String[] args) {
        CurrencyGroup3 currencyGroup3 = new CurrencyGroup3();
        int[] arr = {1,2,3,4,5,6,7};
        int aim = 10;
        System.out.println(currencyGroup3.currencyGroup(arr,aim));
        System.out.println(currencyGroup3.dp(arr,aim));
        System.out.println(currencyGroup3.dp1(arr,aim));

    }
}
