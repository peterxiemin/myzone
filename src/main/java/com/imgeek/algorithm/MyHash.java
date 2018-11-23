package com.imgeek.algorithm;

import java.util.*;
import java.util.zip.CRC32;

/**
 * @author: xiemin
 * @date: 2018/11/21 11:47
 */
public abstract class MyHash {
    public abstract long hash(String key);
}

class ConsistenceHash extends MyHash {

    public class Node implements Comparable<Node> {
        String ip;
        int port;
        long hash;

        @Override
        public String toString() {
            return "Node{" +
                    "ip='" + ip + '\'' +
                    ", port=" + port +
                    ", hash=" + hash +
                    '}';
        }

        public Node(String ip, int port, long hash) {
            this.ip = ip;
            this.port = port;
            this.hash = hash;
        }

        public String getIp() {
            return ip;
        }

        public long getHash() {
            return hash;
        }


        @Override
        public int compareTo(Node o) {
            return (int) (this.hash - o.hash);
        }
    }

    private Map<Long, Node> nodeMap;

    ConsistenceHash() {
        nodeMap = new TreeMap<>();
    }

    public void addNodeInfo(Node node) {
        nodeMap.put(node.getHash(), node);
    }

    public Node getNode(String key) {
        long hash = hash(key);
        Iterator<Map.Entry<Long, Node>> iterator = nodeMap.entrySet().iterator();
        Map.Entry<Long, Node> p = null;
        Map.Entry<Long, Node> q = null;
        Map.Entry<Long, Node> f = null;
        while (iterator.hasNext()) {
            if (p == null && q == null) {
                f = iterator.next();
                p = q = f;
            }
            q = iterator.next();
            if (hash > p.getKey() && hash <= q.getKey()) {
                return q.getValue();
            }
            p = q;
        }
        return f.getValue();
    }

    @Override
    public long hash(String key) {
        CRC32 crc32 = new CRC32();
        crc32.update(key.getBytes());
        return crc32.getValue();
    }
}
