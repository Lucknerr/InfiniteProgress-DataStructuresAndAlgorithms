package com.zl.numberswitchstring;

import com.zl.zerosumpacksack.ZeroSumPacksack;

public class Test {
    public static void main(String[] args) {
        NumberSwitchString numberSwitchString = new NumberSwitchString();
        String num = "7210231231232031203123"; //7210231231232031203123
        System.out.println(numberSwitchString.numberSwitchString(num));
        System.out.println(numberSwitchString.numberSwitchString1(num));
    }
}
