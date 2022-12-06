package com.zl.graphs;

import com.zl.queue.Queue;
import com.zl.queue.impl.QueueArray;

// 加权无向图实现
public class WeightedUndirectedGraphs {
    // 记录顶点数
    private int peakSize;
    // 记录边数
    private int sideSize;
    // 邻接表,存储加权边
    private Queue<Edge>[] queues;
    // 构造方法
    public WeightedUndirectedGraphs(int peakSize) {
        // 初始化顶点数
        this.peakSize = peakSize;
        // 初始化边数
        this.sideSize = 0;
        // 初始化邻接表
        this.queues = new Queue[peakSize];
        for (int i = 0; i < peakSize; i++) {
            // 初始化每个队列
            queues[i] = new QueueArray<>();
        }
    }

    // 获取顶点数
    public int getPeakSize() {
        return peakSize;
    }

    // 获取边数
    public int getSideSize() {
        return sideSize;
    }

    // 向图中添加一条边
    public boolean addSide(Edge edge) {
        // 获取edge其中的一个顶点
        int A = edge.getA();
        // 获取edge其中的另一个顶点
        int B = edge.getB();
        // 将B添加到A的邻接表中
        queues[A].enqueue(edge);
        queues[B].enqueue(edge);
        sideSize++;
        return true;
    }

    // 获取图中所有加权边基于深度优先遍历
    public Queue<Edge> getAllEdgeForDFS() {
        return DFS(0);
    }
    // 获取顶点A相关联的边
    public Queue<Edge> getEdgeForA(int peak) {
        return queues[peak];
    }

    // 深度优先遍历入口方法
    public Queue<Edge> DFS(int peak) {
        // 用于存放遍历后的加权边
        Queue<Edge> ans = new QueueArray<>();
        // 进行深度优先遍历
        DFS(peak,ans);
        // 遍历剩下的边,如果存在非连通图的情况下
        for (int i = 0; i < peakSize; i++) {
            DFS(i,ans);
        }
        // 返回遍历完成的加权边存放队列
        return ans;
    }

    // 深度优先遍历核心方法
    private Queue<Edge> DFS(int peak,Queue<Edge> ans) {
        // 遍历当前顶点相邻的所有加权边
        while (!queues[peak].isEmpty()) {
            // 拿出当前顶点最先出来的加权边
            Edge edge = queues[peak].dequeue();
            // 获取这个加权边对应的另一个顶点
            int other = edge.getOther(peak);
            // 判断此边是否之前被访问过
            if (edge.getVisit()) {
                // 若被访问过直接跳过
                continue;
            }
            // 将此边放入结果队列
            ans.enqueue(edge);
            // 将此边标记为被访问过
            edge.setVisit();
            // 递归调用深度优先遍历,以同样的方式遍历当前边的另一个顶点相邻的边
            DFS(other,ans);
        }
        // 返回结果表
        return ans;
    }
}
