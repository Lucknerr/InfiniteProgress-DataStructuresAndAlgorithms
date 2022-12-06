package com.zl.concurrentlypk;



public class Test {
    public static void main(String[] args) {
        ConcurrentlyPK concurrentlyPK = new ConcurrentlyPK();
        int[] arr = {1,2,2,6,7,9,10,13,15};
        int k = 1;
        System.out.println(concurrentlyPK.PK(arr,k));
    }
}
