package com.zl.operatortarget;


public class Test {
    public static void main(String[] args) {
        OperatorTarget operatorTarget = new OperatorTarget();
        int[] arr = {1,1,1,1,1};
        int tar = 3;
        System.out.println(operatorTarget.operatorTarget(arr,tar));
        System.out.println(operatorTarget.operatorTarget1(arr,tar));
        System.out.println(operatorTarget.dp1(arr,tar));
        System.out.println(operatorTarget.dp2(arr,tar));
    }
}
