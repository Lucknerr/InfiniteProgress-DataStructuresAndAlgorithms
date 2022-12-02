package com.zl;

import com.zl.stack.Stack;
import com.zl.stack.impl.StackArray;
import com.zl.stack.impl.StackLink;

public class test01 {
    public static void main(String[] args) {
        Stack stack = new StackLink();
//        stack.pop();
//        System.out.println(stack.toString());
        stack.push(1);
        System.out.println(stack.toString());
        stack.push(3);
        System.out.println(stack.toString());
        stack.push(7);
        System.out.println(stack.toString());
        System.out.println(stack.pop());
        System.out.println(stack.toString());
        System.out.println(stack.peek());

    }
}
