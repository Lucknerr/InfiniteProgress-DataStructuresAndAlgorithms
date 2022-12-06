package com.zl;

import com.zl.graph.DiGraph;
import com.zl.graph.impl.DiGraphArray;

public class test {
    public static void main(String[] args) {
        DiGraph graph = new DiGraphArray(12);
        graph.addSide(0,1);
        graph.addSide(0,2);
        graph.addSide(0,4);
        graph.addSide(4,3);
        graph.addSide(7,3);
        graph.addSide(9,3);
        graph.addSide(9,0);
        graph.addSide(7,5);
        graph.addSide(7,6);
        graph.addSide(4,5);
        graph.addSide(8,6);
//        graph.addSide(4,9);
        graph.addSide(10,11);
//        graph.addSide(3,4);
//        graph.addSide(5,4);
//        DiGraph graph = new DiGraphArray(5);
//        graph.addSide(0,1);
//        graph.addSide(0,2);
//        graph.addSide(1,2);
//        graph.addSide(1,3);
//        graph.addSide(2,3);
//        graph.addSide(3,4);
//        System.out.println(graph.peakSize());
//        System.out.println(graph.sideSize());
//        System.out.println(graph.depthFirstSearch(0).toString());
//        System.out.println(graph.breadthFirstSearch(0).toString());
        System.out.println(graph.open(7));
//        System.out.println(graph.isLoopForDepthFirstSearch());
//        System.out.println(graph.toPoLogicalDepthFirstSort());

    }
}
