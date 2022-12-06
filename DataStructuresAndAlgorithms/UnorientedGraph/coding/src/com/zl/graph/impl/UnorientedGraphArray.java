package com.zl.graph.impl;

import com.zl.graph.UnorientedGraph;
import com.zl.queue.Queue;
import com.zl.queue.impl.QueueArray;

// 无向图实现
public class UnorientedGraphArray implements UnorientedGraph {
    // 记录顶点数
    private int peakSize;
    // 记录边数量
    private int sideSize;
    // 获取顶点数量
    private Queue<Integer>[] queues;

    public UnorientedGraphArray(int peakSize) {
        // 初始化顶点数
        this.peakSize = peakSize;
        // 初始化边数
        this.sideSize = 0;
        // 初始化主表
        this.queues = new QueueArray[peakSize];
        // 初始化侧链
        for (int i = 0; i < peakSize; i++) {
            queues[i] = new QueueArray<>();
        }
    }
    @Override
    public int peakSize() {
        return peakSize;
    }
    // 获取边数量
    @Override
    public int sideSize() {
        return sideSize;
    }
    // 增加一条边
    @Override
    public void addSide(int p1, int p2) {
        queues[p1].enqueue(p2);
        queues[p2].enqueue(p1);
        sideSize++;
    }
    // 获取指定节点的相邻所有节点
    @Override
    public Queue<Integer> getAdj(int peak) {
        return queues[peak];
    }

    // 非连通嵌套连通深度优先遍历,做到通用遍历
    @Override
    public Queue<Integer> depthFirstSearch(int peak) {
        Queue<Integer> help = new QueueArray<>();
        boolean[] unVirgin = new boolean[peakSize];
        help.enqueue(peak);
        unVirgin[peak] = true;
        depthFirstSearch(peak,help,unVirgin);
        for (int i = 0; i < queues.length; i++) {
            if (unVirgin[i]) {
                continue;
            }
            help.enqueue(i);
            unVirgin[i] = true;
            depthFirstSearch(i,help,unVirgin);
        }
        return help;
    }

    // 单纯连通版深度优先遍历
    private Queue<Integer> depthFirstSearch(int peak,Queue<Integer> help,boolean[] unVirgin) {
        while (!queues[peak].isEmpty()) {
            Integer adj = queues[peak].dequeue();
            if (unVirgin[adj]) {
                continue;
            }
            help.enqueue(adj);
            unVirgin[adj] = true;
            depthFirstSearch(adj,help,unVirgin);
        }
        return help;
    }

    // 非连通嵌套连通广度优先遍历,做到通用遍历
    @Override
    public Queue<Integer> breadthFirstSearch(int peak) {
        Queue<Integer> expr = new QueueArray<>();
        Queue<Integer> help = new QueueArray<>();
        boolean[] unVirgin = new boolean[peakSize];
        help.enqueue(peak);
        unVirgin[peak] = true;
        breadthFirstSearch(expr,help,unVirgin);
        for (int i = 0; i < queues.length; i++) {
            if (unVirgin[i]) {
                continue;
            }
            help.enqueue(i);
            unVirgin[i] = true;
            breadthFirstSearch(expr,help,unVirgin);
        }
        return expr;
    }

    // 单纯连通版广度优先遍历
    private Queue<Integer> breadthFirstSearch(Queue<Integer> expr,Queue<Integer> help,boolean[] unVirgin) {
        if (help.isEmpty()) {
            return expr;
        }
        int tar = help.dequeue();
        expr.enqueue(tar);
        while (!queues[tar].isEmpty()) {
            int inTar = queues[tar].dequeue();
            if (unVirgin[inTar]) {
                continue;
            }
            help.enqueue(inTar);
            unVirgin[inTar] = true;
        }
        return breadthFirstSearch(expr,help,unVirgin);
    }

    // 记录多少个点于指定节点相连通
    public int open(int peak) {
        boolean[] unVirgin = new boolean[queues.length];
        int count = 0;
        unVirgin[peak] = true;
        return open(peak,unVirgin,count);
    }

    public int open(int peak,boolean[] unVirgin,int count) {
        while (!queues[peak].isEmpty()) {
            int inTar = queues[peak].dequeue();
            if (unVirgin[inTar]) {
                continue;
            }
            unVirgin[inTar] = true;
            count = open(inTar,unVirgin,++count);
        }
        return count;
    }


//    @Override
//    public Queue<Integer> depthFirstSearch(int peak) {
//        Queue<Integer>[] proxy = queues;
//        Queue<Integer> help = new QueueArray<>();
//        boolean[] unVirgin = new boolean[peakSize];
//        help.enqueue(peak);
//        unVirgin[peak] = true;
//        depthFirstSearch(proxy,peak,help,unVirgin);
//        for (int i = 0; i < proxy.length; i++) {
//            if (unVirgin[i]) {
//                continue;
//            }
//            help.enqueue(i);
//            unVirgin[i] = true;
//            depthFirstSearch(proxy,i,help,unVirgin);
//        }
//        return help;
//    }
//
//    private Queue<Integer> depthFirstSearch(Queue<Integer>[] proxy,int peak,Queue<Integer> help,boolean[] unVirgin) {
//        while (!proxy[peak].isEmpty()) {
//            Integer adj = proxy[peak].dequeue();
//            if (unVirgin[adj]) {
//                continue;
//            }
//            help.enqueue(adj);
//            unVirgin[adj] = true;
//            depthFirstSearch(proxy,adj,help,unVirgin);
//        }
//        return help;
//    }
//
//    @Override
//    public Queue<Integer> breadthFirstSearch(int peak) {
//        Queue<Integer>[] proxy = queues;
//        Queue<Integer> expr = new QueueArray<>();
//        Queue<Integer> help = new QueueArray<>();
//        boolean[] unVirgin = new boolean[peakSize];
//        help.enqueue(peak);
//        unVirgin[peak] = true;
//        breadthFirstSearch(proxy,expr,help,unVirgin);
//        for (int i = 0; i < proxy.length; i++) {
//            if (unVirgin[i]) {
//                continue;
//            }
//            help.enqueue(i);
//            unVirgin[i] = true;
//            breadthFirstSearch(proxy,expr,help,unVirgin);
//        }
//        return expr;
//    }
//
//    private Queue<Integer> breadthFirstSearch(Queue<Integer>[] proxy,Queue<Integer> expr,Queue<Integer> help,boolean[] unVirgin) {
//        if (help.isEmpty()) {
//            return expr;
//        }
//        int tar = help.dequeue();
//        expr.enqueue(tar);
//        while (!proxy[tar].isEmpty()) {
//            int inTar = proxy[tar].dequeue();
//            if (unVirgin[inTar]) {
//                continue;
//            }
//            help.enqueue(inTar);
//            unVirgin[inTar] = true;
//        }
//        return breadthFirstSearch(proxy,expr,help,unVirgin);
//    }
}
