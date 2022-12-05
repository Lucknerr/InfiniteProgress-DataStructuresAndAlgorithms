package com.zl.ropepindot;

import com.zl.ropepindot.RopePinDot;

public class Test {
    public static void main(String[] args) {
        RopePinDot ropePinDot = new RopePinDot();
        int[] arr = {1,3,4,7,13,16,17,18,19,25,26,29,34,35,36,39,40};
        int k = 5;
        System.out.println(ropePinDot.pinDot(arr,k));
        System.out.println(ropePinDot.maxPoint2(arr,k));
        System.out.println(ropePinDot.pinDot1(arr,k));
    }
}
