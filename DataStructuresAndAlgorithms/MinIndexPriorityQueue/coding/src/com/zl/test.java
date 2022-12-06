package com.zl;

public class test {
    public static void main(String[] args) {
        MinIndexPriorityQueue<Integer> minIndexPriorityQueue = new MinIndexPriorityQueue<>(10);
        minIndexPriorityQueue.insert(3,9);
        System.out.println(minIndexPriorityQueue.toString());
        minIndexPriorityQueue.insert(5,6);
        System.out.println(minIndexPriorityQueue.toString());
        minIndexPriorityQueue.insert(9,10);
        System.out.println(minIndexPriorityQueue.toString());
        minIndexPriorityQueue.insert(4,3);
        System.out.println(minIndexPriorityQueue.toString());
        minIndexPriorityQueue.insert(1,8);
        System.out.println(minIndexPriorityQueue.toString());
        minIndexPriorityQueue.insert(1,8);
        System.out.println(minIndexPriorityQueue.toString());
        minIndexPriorityQueue.insert(1,7);
        System.out.println(minIndexPriorityQueue.toString());
        minIndexPriorityQueue.del(5);
        System.out.println(minIndexPriorityQueue.toString());
        minIndexPriorityQueue.del(1);
        System.out.println(minIndexPriorityQueue.toString());

    }
}
