package com.zl.survivalprobability;



public class Test {
    public static void main(String[] args) {
        SurvivalProbability survivalProbability = new SurvivalProbability();
        int N = 20;
        int M = 20;
        int row = 15;
        int col = 7;
        int k = 10;
        System.out.println(survivalProbability.survivalProbability(N,M,row,col,k));
        System.out.println(survivalProbability.dp(N,M,row,col,k));
    }
}
