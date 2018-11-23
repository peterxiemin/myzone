package com.imgeek.algorithm;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author: xiemin
 * @date: 2018/11/21 11:53
 */

@Slf4j
public class ConsistenceHashTest {

    private ConsistenceHash consistenceHash;
    private int MAX = 10000000;

    @Before
    public void setUp() {
        consistenceHash = new ConsistenceHash();
        ConsistenceHash.Node node1 = consistenceHash.new Node("10.0.1.1", 8080, 715827882);
        ConsistenceHash.Node node2 = consistenceHash.new Node("10.0.1.2", 8080, 1431655764);
        ConsistenceHash.Node node3 = consistenceHash.new Node("10.0.1.3", 8080, 2147483646);
        ConsistenceHash.Node node4 = consistenceHash.new Node("10.0.1.4", 8080, 2863311528L);
        ConsistenceHash.Node node5 = consistenceHash.new Node("10.0.1.5", 8080, 3579139410L);
        ConsistenceHash.Node node6 = consistenceHash.new Node("10.0.1.6", 8080, 4294967292L);
        consistenceHash.addNodeInfo(node1);
        consistenceHash.addNodeInfo(node2);
        consistenceHash.addNodeInfo(node3);
        consistenceHash.addNodeInfo(node4);
        consistenceHash.addNodeInfo(node5);
        consistenceHash.addNodeInfo(node6);
    }

    @Test
    public void hash() {
        Map<String, Integer> retMap = new HashMap<>();
        for (int i = 0; i < MAX; i++) {
            ConsistenceHash.Node node = consistenceHash.getNode("test" + i);
            if (retMap.containsKey(node.getIp())) {
                retMap.put(node.getIp(), retMap.get(node.ip) + 1);
            } else {
                retMap.put(node.getIp(), 0);
            }
        }

        retMap.forEach((k, v) -> {
            assertTrue(v < 1667000);
            assertTrue(v > 1666000);
        });
    }
}