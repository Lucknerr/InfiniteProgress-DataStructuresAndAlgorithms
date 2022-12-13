package com.zl.currencygroup1;


public class Test {
    public static void main(String[] args) {
        CurrencyGroup1 currencyGroup = new CurrencyGroup1();
        int[] arr = {1,2,7,8,9,5,3,1,1,2,4,3,1,1,5,6,0,4};
        int k = 6;
        System.out.println(currencyGroup.currencyGroup(arr,k));
        System.out.println(currencyGroup.dp(arr,k));

    }
}
