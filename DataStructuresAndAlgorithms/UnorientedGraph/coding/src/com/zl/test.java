package com.zl;


import com.zl.graph.UnorientedGraph;
import com.zl.graph.impl.UnorientedGraphArray;
import com.zl.list.List;
import com.zl.list.impl.SingleLinkedList;
import com.zl.queue.Queue;
import com.zl.queue.impl.QueueArray;

public class test {
    public static void main(String[] args) {
        UnorientedGraph graph = new UnorientedGraphArray(12);
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
        graph.addSide(4,9);
        graph.addSide(10,11);
//        System.out.println(graph.getAdj(3).toString());
        System.out.println(graph.peakSize());
        System.out.println(graph.sideSize());
//        System.out.println(graph.depthFirstSearch(0).toString());
//        System.out.println(graph.breadthFirstSearch(0).toString());
        System.out.println(graph.open(0));

    }
}
