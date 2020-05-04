package com.jjs.hash;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author jjs
 * @Version 1.0 2020/4/12
 */

/**
 * 该一致hash，没有使用虚拟节点
 */
public class ConsistentHashingWithoutVirtualNode {

    private static String[] groups = {
            "192.168.0.1:111","192.168.0.2:111",
            "192.168.0.3:111","192.168.0.4:111", "192.168.0.5:111"     };

    private static SortedMap<Integer, String> sortedMap = new TreeMap<>();

    static {
        for (String group : groups) {
            int hash = HashUtils.getHash(group);
            System.out.println("["+ group +"] launched @ " + hash);
            sortedMap.put(hash, group);
        }
    }

    private static String getServer(String key) {
        int hash = HashUtils.getHash(key);
        SortedMap<Integer, String> subMap = sortedMap.tailMap(hash);
        if (subMap == null || subMap.isEmpty()) {
            return sortedMap.get(sortedMap.firstKey());
        }
        return subMap.get(subMap.firstKey());
    }

    public static void main(String[] args) {
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

    }

}
