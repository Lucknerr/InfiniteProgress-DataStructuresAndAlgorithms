package com.zl.graph.impl;

import com.zl.graph.DiGraph;
import com.zl.queue.Queue;
import com.zl.queue.impl.QueueArray;
import com.zl.stack.Stack;
import com.zl.stack.impl.StackArray;

// 有向图实现
public class DiGraphArray implements DiGraph {
    // 记录顶点个数
    private int peakSize;
    // 记录边数
    private int sideSize;
    // 主要存储结构,队列数组,构成主表和侧链
    private Queue<Integer>[] queues;
    // 构造函数
    public DiGraphArray(int peakSize) {
        this.peakSize = peakSize;
        this.sideSize = 0;
        this.queues = new QueueArray[peakSize];
        for (int i = 0; i < peakSize; i++) {
            queues[i] = new QueueArray<>();
        }
    }
    @Override
    public int peakSize() {
        return peakSize;
    }

    @Override
    public int sideSize() {
        return sideSize;
    }

    // 单向图插添加单向边
    @Override
    public void addSide(int p1, int p2) {
        // 添加一条p1指向p2的边
        queues[p1].enqueue(p2);
        // 边数量加一
        sideSize++;
    }

    // 深度优先遍历方法入口
    @Override
    public Queue<Integer> depthFirstSearch(int peak) {
        // 辅助队列,用于存储遍历的元素
        Queue<Integer> help = new QueueArray<>();
        // 辅助布尔型数组,用于标记元素是否被访问过,避免重复遍历
        boolean[] unVirgin = new boolean[peakSize];
        // 深度优先遍历,从peak开始遍历
        depthFirstSearch(peak,help,unVirgin);
        // 如果是非连通图则遍历剩下没有遍历到的
        for (int i = 0; i < peakSize; i++) {
            // 继续深度优先遍历未遍历的
            depthFirstSearch(i,help,unVirgin);
        }
        // 返回以深度优先遍历顺序入队列的元素
        return help;
    }

    // 深度优先遍历核心方法
    private Queue<Integer> depthFirstSearch(int peak,Queue<Integer> help,boolean[] unVirgin) {
        // 判断当前来到的顶点是否被访问过
        if (unVirgin[peak]) {
            // 如果访问过直接返回
            return help;
        }
        // 来到这一步说明之前没有被访问过,将当前顶点入队列
        help.enqueue(peak);
        // 再将当前顶点标记为被访问过
        unVirgin[peak] = true;
        // 如果当前顶点所指向的相邻顶点未被拿空,则继续依次访问当前顶点的相邻顶点直到拿空
        while (!queues[peak].isEmpty()) {
            // 拿出当前顶点下剩余的第一个相邻顶点
            int inTar = queues[peak].dequeue();
            // 递归调用深度优先遍历方法,继续以当前顶点剩下的第一个相邻顶点为其实遍历起始点开始向它所指向的所有顶点以深度优先遍历的方式遍历
            depthFirstSearch(inTar,help,unVirgin);
        }
        // 当当前点的所指向的顶点被拿空时,返回辅助队列,当前遍历完成,
        return help;
    }

    // 广度优先遍历入口方法
    @Override
    public Queue<Integer> breadthFirstSearch(int peak) {
        // 声明两个辅助队列
        // expr 用来存放最后的结果
        Queue<Integer> expr = new QueueArray<>();
        // help 用来暂存拿出的顶点
        Queue<Integer> help = new QueueArray<>();
        // 辅助布尔型数组,用于标记元素是否被访问过,避免重复遍历
        boolean[] unVirgin = new boolean[peakSize];
        // 将其实顶点入队列
        help.enqueue(peak);
        // 将其实顶点入队列
        expr.enqueue(peak);
        // 将顶点标记为被访问过
        unVirgin[peak] = true;
        // 开始以peak为其实顶点进行广度优先遍历
        breadthFirstSearch(peak,expr,help,unVirgin);
        // 如果存在非连通图,则是对剩下的进行广度优先遍历
        for (int i = 0; i < peakSize; i++) {
            // 跳过已经被遍历过的
            if (unVirgin[i]) {
                continue;
            }
            // 将剩余的其实顶点入队列
            help.enqueue(i);
            // 将剩余的其实顶点入队列
            expr.enqueue(i);
            // 将顶点标记为被访问过
            unVirgin[i] = true;
            // 对剩余顶点进行广度优先遍历
            breadthFirstSearch(i,expr,help,unVirgin);
        }
        //返回结果队列
        return expr;
    }

    // 广度优先遍历核心方法
    private Queue<Integer> breadthFirstSearch(int peak,Queue<Integer> expr,Queue<Integer> help,boolean[] unVirgin) {
        // 判断辅助队列是否为空,如果为空说明没有要遍历的顶点了
        if (help.isEmpty()) {
            // 如果为空直接返回
            return expr;
        }
        // 如果当前顶点所指向的相邻顶点未被拿空,继续依次遍历相邻顶点直到被拿空为止
        while (!queues[peak].isEmpty()) {
            // 拿出当前顶点所指向顶点的剩下最前面
            int inTar = queues[peak].dequeue();
            // 拿出的如果被访问过直接跳过
            if (unVirgin[inTar]) {
                continue;
            }
            // 将拿出的入队列
            help.enqueue(inTar);
            // 将拿出的入队列
            expr.enqueue(inTar);
            // 来到这一步说明刚拿出的没有被访问过,将他标记为访问过
            unVirgin[inTar] = true;
        }
        // 当前顶点所指向的所有顶点全部拿空,继续遍历辅助队列中第一个入队列的顶点所指向的所有顶点
        return breadthFirstSearch(help.dequeue(),expr,help,unVirgin);
    }

    // 获取知道顶点的连通点数量入口方法
    @Override
    public int open(int peak) {
        // 辅助布尔型数组,用于标记元素是否被访问过,避免重复访问
        boolean[] unVirgin = new boolean[peakSize];
        // 获取数量
        return open(peak,unVirgin,0);
    }

    // 获取知道顶点的连通点数量核心方法
    private int open(int peak,boolean[] unVirgin,int count) {
        // 判断当前来到的顶点是否被访问过
        if (unVirgin[peak]) {
            // 如果被访问过连通点的数减一
            return --count;
        }
        // 将当前顶点标记为被访问
        unVirgin[peak] = true;
        // 如果当前顶点所指向的相邻顶点未被拿空,依次访问他的相邻顶点
        while (!queues[peak].isEmpty()) {
            // 拿出当前顶点所指向顶点的剩下最前面
            int inTar = queues[peak].dequeue();
            // 获取刚拿出顶点所指向所有顶点的数量,并且当前顶点连通数量加一
            count = open(inTar,unVirgin,++count);
        }
        // 如果当前顶点所指向的相邻顶点被拿空,说明当前顶点没有其他连通点了,返回当前连通数量
        return count;
    }

    // 以深度优先遍历检查图中是否有闭环入口方法
    @Override
    public boolean isLoopForDepthFirstSearch() {
        // 将所有点都以起始点遍历一遍,有一个起始点遍历下去存在闭环就返回true
        for (int i = 0; i < peakSize; i++) {
            // 辅助布尔型数组,用于标记当前路线元素是否被访问过,避免重复访问
            boolean[] unVirgin = new boolean[peakSize];
            // 将起始点标记为被访问过
            unVirgin[i] = true;
            // 点用核心方法检查闭环,如果存在闭环,返回true
            if(isLoopForDepthFirstSearch(i,unVirgin)) {
                return true;
            }
        }
        return false;
    }

    // 以深度优先遍历检查图中是否有闭环核心方法
    private boolean isLoopForDepthFirstSearch(int peak,boolean[] unVirgin) {
        // 判断当前顶点有没有指向其他顶点
        if (queues[peak].isEmpty()) {
            // 没有则说明从起始点遍历到此没有下一个点可走,说明这条路没有闭环返回false
            return false;
        } else {
            // 存在指向其他顶点,那就拿出第一个相邻顶点
            int tar = queues[peak].dequeue();
            // 判断拿出的这个点是否被访问过
            if (unVirgin[tar]) {
                // 如果被访问过,说明回到之前的某一个点,说明存在闭环,返回true
                return true;
            }
            // 能来到这一步说明之前没有被标记过,在此将他标记为被访问过
            unVirgin[tar] = true;
            // 继续以刚拿出点为起始点向下遍历,一旦碰到环逐层返回true
            return isLoopForDepthFirstSearch(tar, unVirgin);
        }

    }

    // 以深度优先遍历的拓扑排序入口方法
    @Override
    public Stack<Integer> toPoLogicalDepthFirstSort() {
        // 辅助栈
        Stack<Integer> help = new StackArray<>();
        // 辅助布尔型数组,用于标记元素是否被访问过,避免重复访问
        boolean[] unVirgin = new boolean[peakSize];
        // 对整个图进行拓扑排序,以每一个点为起始点进行拓扑排序
        for (int i = 0; i < peakSize; i++) {
            // 辅助布尔型数组,用于标记当前路线元素是否被访问过,避免重复访问
            boolean[] theUnVirgin = new boolean[peakSize];
            // 拓扑排序
            toPoLogicalDepthFirstSort(i,help,unVirgin,theUnVirgin);
        }
        // 返回结果栈
        return help;
    }

    // 以深度优先遍历的拓扑排序核心方法
    private Stack<Integer> toPoLogicalDepthFirstSort(int peak,Stack<Integer> help,boolean[] unVirgin,boolean[] theUnVirgin) {
        // 判断当前顶点在本路线上是否被访问,避免有环的存在
        if (theUnVirgin[peak]) {
            // 存在则无法进行拓扑排序
            throw new Error("存在闭环无法排序!");
        }
        // 判断当前顶点是否被访问
        if (unVirgin[peak]) {
            // 如果访问过直接返回
            return help;
        }
        // 将当前顶点在全图中标记为被访问过
        unVirgin[peak] = true;
        // 将当前顶点在当前路线中标记为被访问过
        theUnVirgin[peak] = true;
        // 如果当前顶点所指向的相邻顶点未被拿空,依次访问他的相邻顶点
        while (!queues[peak].isEmpty()) {
            // 拿出第一个相邻顶点
            int tar = queues[peak].dequeue();
            // 以刚拿出的顶点为起始点进行拓扑排序
            toPoLogicalDepthFirstSort(tar,help,unVirgin,theUnVirgin);
        }
        // 如果当前顶点所指向的相邻顶点被拿空了,那么将当前顶点入栈
        help.push(peak);
        // 返回结果栈
        return help;
    }
}
