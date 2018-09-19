package com.imgeek.algorithm;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UndigraphTest {
    private Undigraph<Integer> undigraph;

    @Before
    public void setUp(){
        undigraph = new Undigraph<Integer>();
        UndigraphNode<Integer> node1 = new UndigraphNode<>();
        UndigraphNode<Integer> node2 = new UndigraphNode<>();
        UndigraphNode<Integer> node3 = new UndigraphNode<>();
        UndigraphNode<Integer> node4 = new UndigraphNode<>();

        node1.setNodeIndex(0);
        node1.setData(1);
        node2.setNodeIndex(1);
        node2.setData(2);
        node3.setNodeIndex(2);
        node3.setData(3);
        node4.setNodeIndex(3);
        node4.setData(4);

        Edge<Integer> edge1 = new Edge<>();
        Edge<Integer> edge2 = new Edge<>();
        Edge<Integer> edge3 = new Edge<>();
        Edge<Integer> edge4 = new Edge<>();
        Edge<Integer> edge5 = new Edge<>();
        Edge<Integer> edge6 = new Edge<>();
        Edge<Integer> edge7 = new Edge<>();
        Edge<Integer> edge8 = new Edge<>();

        edge1.setNode(node2); //node1 - node2
        edge2.setNode(node1);

        edge3.setNode(node3); //node2 - node3
        edge4.setNode(node2);

        edge5.setNode(node3); //node1 - node3
        edge6.setNode(node1);

        edge7.setNode(node4); //node1 - node4
        edge8.setNode(node1);

        //node1
        node1.setFirstEdge(edge1);
        edge1.setNextEdge(edge5);
        edge5.setNextEdge(edge7);

        //node2
        node2.setFirstEdge(edge3);
        edge3.setNextEdge(edge2);

        //node3
        node3.setFirstEdge(edge6);
        edge6.setNextEdge(edge4);

        //node4
        node4.setFirstEdge(edge8);

        undigraph.addNode(node1);
        undigraph.addNode(node2);
        undigraph.addNode(node3);
        undigraph.addNode(node4);
    }

    @Test
    public void depthFirstTraverseTest(){
        assertEquals("1 2 3 4 ", undigraph.depthFirstTraverse());
    }
}
