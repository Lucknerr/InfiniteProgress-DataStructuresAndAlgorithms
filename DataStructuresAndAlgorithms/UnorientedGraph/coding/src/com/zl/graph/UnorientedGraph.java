package com.zl.graph;

import com.zl.queue.Queue;

// 无向图接口实现
public interface UnorientedGraph {

    // 获取图中顶点数量
    public int peakSize();
    // 获取图中边的数量
    public int sideSize();
    // 向图中添加一条边
    public void addSide(int p1,int p2);
    // 获取指定节点的相邻所有节点
    public Queue<Integer> getAdj(int peak);
    // 非连通,连通通用深度优先搜索
    public Queue<Integer> depthFirstSearch(int peak);
    // 非连通,连通通用广度优先搜索
    public Queue<Integer> breadthFirstSearch(int peak);
    // 记录多少个点于指定节点相连通
    public int open(int peak);
}
