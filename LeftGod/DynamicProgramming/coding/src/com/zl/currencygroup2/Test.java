package com.zl.currencygroup2;




public class Test {
    public static void main(String[] args) {
        CurrencyGroup2 currencyGroup2 = new CurrencyGroup2();
        int[] arr = {1,2,3,4,5};
        int aim = 55;
        System.out.println(currencyGroup2.currencygroup(arr,aim));
        System.out.println(currencyGroup2.dp(arr,aim));
        System.out.println(currencyGroup2.dp1(arr,aim));

    }
}
