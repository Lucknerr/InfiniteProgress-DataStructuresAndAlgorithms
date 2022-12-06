package com.zl;

import com.zl.queue.Queue;
import com.zl.queue.impl.QueueArray;
import com.zl.queue.impl.QueueLink;

public class test {
    public static void main(String[] args) {

        Queue queue = new QueueLink();
        queue.enqueue(0);
        System.out.println(queue.toString());
        queue.enqueue(1);
        System.out.println(queue.toString());
        queue.enqueue(2);
        System.out.println(queue.toString());
        queue.enqueue(3);
        System.out.println(queue.toString());
        System.out.println(queue.dequeue());
        System.out.println(queue.toString());
    }
}
