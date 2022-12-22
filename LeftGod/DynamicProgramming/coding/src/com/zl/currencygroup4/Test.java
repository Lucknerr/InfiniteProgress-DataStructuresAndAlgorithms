package com.zl.currencygroup4;



public class Test {
    public static void main(String[] args) {
        CurrencyGroup4 currencyGroup4 = new CurrencyGroup4();
        int[] arr = {1,2,3,5,9};
        int aim = 99;
        System.out.println(currencyGroup4.currencyGroup(arr,aim));
        System.out.println(currencyGroup4.dp(arr,aim));

    }
}
