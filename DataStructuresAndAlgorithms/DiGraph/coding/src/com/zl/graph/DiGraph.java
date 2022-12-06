package com.zl.graph;

import com.zl.queue.Queue;
import com.zl.stack.Stack;

// 无向图接口实现
public interface DiGraph {
    // 获取图中顶点数量
    public int peakSize();
    // 获取图中边的数量
    public int sideSize();
    // 向图中添加一条p1指向p2的边
    public void addSide(int p1,int p2);
    // 非连通,连通通用深度优先搜索
    public Queue<Integer> depthFirstSearch(int peak);
    // 非连通,连通通用广度优先搜索
    public Queue<Integer> breadthFirstSearch(int peak);
    // 记录多少个点于指定节点相连通
    public int open(int peak);
    // 检测图中是否存在闭环,基于深度优先搜索
    public boolean isLoopForDepthFirstSearch();
    // 对整个图进行拓扑排序,基于深度优先搜索
    public Stack<Integer> toPoLogicalDepthFirstSort();
}
