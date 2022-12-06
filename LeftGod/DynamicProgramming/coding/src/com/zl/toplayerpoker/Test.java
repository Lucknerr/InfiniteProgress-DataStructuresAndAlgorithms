package com.zl.toplayerpoker;

import com.zl.robotmove.RobotMove;

public class Test {
    public static void main(String[] args) {
        TopPlayerPoker topPlayerPoker = new TopPlayerPoker();
        int[] poker = {5,7,4,5,8,1,6,0,3,4,6,1,7};
//        int[] poker = {4,30,2,1,5};
        System.out.println(topPlayerPoker.winner(poker));
        System.out.println(topPlayerPoker.winner1(poker));
        System.out.println(topPlayerPoker.winner3(poker));
    }
}
