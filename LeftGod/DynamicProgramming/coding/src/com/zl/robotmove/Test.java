package com.zl.robotmove;

public class Test {
    public static void main(String[] args) {
        RobotMove robotMove = new RobotMove();
        System.out.println(robotMove.violenceWays(2,4,4,4));
        System.out.println(robotMove.tryDynamicWays(2,4,4,4));
        System.out.println(robotMove.DynamicWays(2,4,4,4));
    }
}
