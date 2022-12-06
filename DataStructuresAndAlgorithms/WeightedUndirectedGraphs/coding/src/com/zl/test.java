package com.zl;

import com.zl.graphs.Edge;
import com.zl.graphs.WeightedUndirectedGraphs;

public class test {
    public static void main(String[] args) {
        WeightedUndirectedGraphs weightedUndirectedGraphs = new WeightedUndirectedGraphs(7);
        weightedUndirectedGraphs.addSide(new Edge(0,5,0.4));
        weightedUndirectedGraphs.addSide(new Edge(0,2,0.4));
        weightedUndirectedGraphs.addSide(new Edge(0,1,0.4));
        weightedUndirectedGraphs.addSide(new Edge(0,4,0.4));
        weightedUndirectedGraphs.addSide(new Edge(0,6,0.4));
        weightedUndirectedGraphs.addSide(new Edge(1,2,0.4));
        weightedUndirectedGraphs.addSide(new Edge(1,3,0.4));
        weightedUndirectedGraphs.addSide(new Edge(1,4,0.4));
        weightedUndirectedGraphs.addSide(new Edge(2,3,0.4));
        weightedUndirectedGraphs.addSide(new Edge(2,5,0.4));
        weightedUndirectedGraphs.addSide(new Edge(3,4,0.4));
        weightedUndirectedGraphs.addSide(new Edge(4,6,0.4));
        System.out.println(weightedUndirectedGraphs.getAllEdgeForDFS().toString());

    }
}
