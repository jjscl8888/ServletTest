package com.jjs.hash;

import java.util.*;

/**
 * @author jjs
 * @Version 1.0 2020/4/12
 */

/**
 *  使用虚拟节点。是的负载更加均衡
 */
public class ConsistentHashingWithVirtualNode {
    private static String[] groups = {
            "192.168.0.1:111","192.168.0.2:111",
            "192.168.0.3:111","192.168.0.4:111", "192.168.0.5:111"    };

    private static SortedMap<Integer, String> sortedMap = new TreeMap<>();

    private static List<String> realGroups = new ArrayList<>();

    private static final int VIRTUAL_NODE_NUM = 1000;

    static {
        for (String group : groups) {
            realGroups.add(group);
        }
        refreshHashCircle();
    }

    private static String getServer(String key) {
        int hash = HashUtils.getHash(key);
        SortedMap<Integer, String> subMap = sortedMap.tailMap(hash);
        String virtualNodeName ;
        if (subMap == null || subMap.isEmpty()) {
            virtualNodeName = sortedMap.get(sortedMap.firstKey());
        } else {
            virtualNodeName = subMap.get(subMap.firstKey());
        }
        return virtualNodeName.split("&&")[0];
    }

    private static void refreshHashCircle() {
        for (int i = 0; i < VIRTUAL_NODE_NUM; i++) {
            for (String realGroup : realGroups) {
                String virtualNodeName = realGroup + "&&VN" + i;
                int hash = HashUtils.getHash(virtualNodeName);
               // System.out.println("["+ virtualNodeName +"] launched @ " + hash);
                sortedMap.put(hash, virtualNodeName);
            }
        }
    }

    private static void addGroup(String group) {
        realGroups.add(group);
        refreshHashCircle();
    }

    private static void removeGroup(String group) {
        Iterator<String> iterator = realGroups.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().equalsIgnoreCase(group)) {
                iterator.remove();
            }
        }
        refreshHashCircle();
    }

    public static void main(String[] args) {
        print();
        addGroup("192.168.0.6:111");
        print();
        removeGroup("192.168.0.1:111");
        print();
    }

    private static void print() {
        Map<String, Integer> resMap = new HashMap<>();
        for (int i = 0; i < 10000; i++) {
            Integer key = (int) (Math.random() * 10000);
            String server = getServer(key.toString());
            if (resMap.containsKey(server)) {
                resMap.put(server,resMap.get(server) + 1);
            } else {
                resMap.put(server, 1);
            }
        }
        resMap.forEach((k, v) -> {
            System.out.println("group " + k + ": " + v + "("+ v/1000.0 +")");
        });
        System.out.println("=================");
    }
}
