package com.zl.graphs;

public class Edge implements Comparable<Edge>{
    // 顶点A
    private final int A;
    // 顶点B
    private final int B;
    // 标记边是否被访问过
    private boolean visit;
    // 权重值
    private double WEIGHT;
    // 构造方法
    public Edge(int A,int B,double WEIGHT) {
        this.A = A;
        this.B = B;
        this.visit = false;
        this.WEIGHT = WEIGHT;
    }

    // 获取A点
    public int getA() {
        return A;
    }

    // 获取B点
    public int getB() {
        return B;
    }

    // 获取指定点的另一个点
    public int getOther(int peak) {
        return peak == A ? B : A;
    }

    // 获取权重值
    public double getWeight() {
        return WEIGHT;
    }

    // 标记为被访问过
    public void setVisit() {
        this.visit = true;
    }

    // 查看边是否被标记为访问过
    public boolean getVisit() {
        return visit;
    }

    // 与指定边比较权重值
    @Override
    public int compareTo(Edge o) {
        if (this.WEIGHT > o.WEIGHT) {
            return 1;
        } else if (this.WEIGHT < o.WEIGHT) {
            return -1;
        } else {
            return 0;
        }
    }

    public String toString() {
        return A + "-" + B;
    }
}
