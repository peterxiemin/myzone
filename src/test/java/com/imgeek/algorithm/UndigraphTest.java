package com.imgeek.algorithm;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class UndigraphTest {
    private Undigraph<Integer> undigraph;
    private Undigraph<String> undigraph2;

    @Before
    public void setUp(){
        //init graph 1
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

        //init graph 2
        undigraph2 = new Undigraph<>();
        ArrayList<UndigraphNode<String>> nodeList = new ArrayList<>();

        for(int i = 0; i< 8; i++){
            UndigraphNode<String> node = new UndigraphNode<>();
            node.setNodeIndex(i);
            node.setData("V" + (i + 1));
            nodeList.add(node);
            undigraph2.addNode(node);
        }


        Edge<String> strEdge1 = new Edge<>();
        Edge<String> strEdge2 = new Edge<>();
        Edge<String> strEdge3 = new Edge<>();
        Edge<String> strEdge4 = new Edge<>();
        Edge<String> strEdge5 = new Edge<>();
        Edge<String> strEdge6 = new Edge<>();
        Edge<String> strEdge7 = new Edge<>();
        Edge<String> strEdge8 = new Edge<>();
        Edge<String> strEdge9 = new Edge<>();
        Edge<String> strEdge10 = new Edge<>();
        Edge<String> strEdge11 = new Edge<>();
        Edge<String> strEdge12 = new Edge<>();
        Edge<String> strEdge13 = new Edge<>();
        Edge<String> strEdge14 = new Edge<>();
        Edge<String> strEdge15 = new Edge<>();
        Edge<String> strEdge16 = new Edge<>();
        Edge<String> strEdge17 = new Edge<>();
        Edge<String> strEdge18 = new Edge<>();


        strEdge1.setNode(nodeList.get(1)); //node1 - node2
        strEdge2.setNode(nodeList.get(0));
        strEdge3.setNode(nodeList.get(2)); //node1 - node3
        strEdge4.setNode(nodeList.get(0));
        strEdge5.setNode(nodeList.get(3)); //node2 - node4
        strEdge6.setNode(nodeList.get(1));
        strEdge7.setNode(nodeList.get(4)); //node2 - node5
        strEdge8.setNode(nodeList.get(1));
        strEdge9.setNode(nodeList.get(5)); //node3 - node6
        strEdge10.setNode(nodeList.get(2));
        strEdge11.setNode(nodeList.get(6)); //node3 - node7
        strEdge12.setNode(nodeList.get(2));
        strEdge13.setNode(nodeList.get(6)); //node6 - node7
        strEdge14.setNode(nodeList.get(5));
        strEdge15.setNode(nodeList.get(7)); //node4 - node8
        strEdge16.setNode(nodeList.get(3));
        strEdge17.setNode(nodeList.get(7)); //node5 - node8
        strEdge18.setNode(nodeList.get(4));

        nodeList.get(0).setFirstEdge(strEdge1);
        strEdge1.setNextEdge(strEdge3);

        nodeList.get(1).setFirstEdge(strEdge2);
        strEdge2.setNextEdge(strEdge5);
        strEdge5.setNextEdge(strEdge7);

        nodeList.get(2).setFirstEdge(strEdge4);
        strEdge4.setNextEdge(strEdge9);
        strEdge9.setNextEdge(strEdge11);

        nodeList.get(3).setFirstEdge(strEdge6);
        strEdge6.setNextEdge(strEdge15);

        nodeList.get(4).setFirstEdge(strEdge8);
        strEdge8.setNextEdge(strEdge17);

        nodeList.get(5).setFirstEdge(strEdge10);
        strEdge10.setNextEdge(strEdge13);

        nodeList.get(6).setFirstEdge(strEdge12);
        strEdge12.setNextEdge(strEdge14);

        nodeList.get(7).setFirstEdge(strEdge16);
        strEdge16.setNextEdge(strEdge18);

    }

    @Test
    public void depthFirstTraverseTest(){
        assertEquals("1 2 3 4 ", undigraph.depthFirstTraverse());
        assertEquals("V1 V2 V4 V8 V5 V3 V6 V7 ", undigraph2.depthFirstTraverse());
    }

    @Test
    public void breadthFirstTraverseTest(){
        assertEquals("1 2 3 4 ", undigraph.breadthFirstTraverse());
        assertEquals("V1 V2 V3 V4 V5 V6 V7 V8 ", undigraph2.breadthFirstTraverse());
    }
}
