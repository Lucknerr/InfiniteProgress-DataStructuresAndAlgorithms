package com.zl.zerosumpacksack;

import com.zl.robotmove.RobotMove;

public class Test {
    public static void main(String[] args) {
        ZeroSumPacksack zeroSumPacksack = new ZeroSumPacksack();
        int[] w = {3,2,4,7,3,1,7};
        int[] v = {5,6,3,19,12,4,2};
        int bag = 15;
        System.out.println(zeroSumPacksack.maxVal(w,v,bag));
        System.out.println(zeroSumPacksack.maxValue(w,v,bag));
    }
}
