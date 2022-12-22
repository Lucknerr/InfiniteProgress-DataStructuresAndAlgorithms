package com.zl.beatthebeast;



public class Test {
    public static void main(String[] args) {
        BeatTheBeast beatTheBeast = new BeatTheBeast();
        int N = 20;
        int M = 4;
        int k = 5;
        System.out.println(beatTheBeast.beatTheBeast(N,M,k));
        System.out.println(beatTheBeast.dp(N,M,k));
    }
}
