package com.zl.minnumship;

public class Test {
    public static void main(String[] args) {
        MinNumShip minNumShip = new MinNumShip();
        int[] arr = {2,4,1,3,8,6,7,6,1,2,9,4,10};
        int k = 10;
        System.out.println(minNumShip.minShip(arr,k));
    }
}
