package com.cxy.customize.cache.impl.test;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 一致性hash
 */
public class ConsistentHashingWithTable {
    //自定义分表数量,注意后续维护时候保持统一
    private static int TRANSACTION_TABLE_NUM = 20;

    // 待添加入Hash环的交易表列表
    private static String[] transactionTable = new String[TRANSACTION_TABLE_NUM];

    //todo
    static{
        for(int ci=0;ci<TRANSACTION_TABLE_NUM;ci++){
            transactionTable[ci] = "tbl_account_transaction"+ci;
        }
    }

    // key表示交易表的hash值，value表示交易表
    private static SortedMap<Integer, String> sortedMap = new TreeMap<>();
    //虚拟节点的数目,一个真实结点对应10个虚拟节点
    private static final int VIRTUAL_NODES = 10;

    // 程序初始化，将所有的交易表放入sort交易表ap中
    static {
        for (int i = 0; i < transactionTable.length; i++) {
            int hash = getHash(transactionTable[i]);
            System.out.println("[" + transactionTable[i] + "]加入集合中, 其Hash值为"
                    + hash);
            sortedMap.put(hash, transactionTable[i]);
            //再添加虚拟节点
            for(int j=0; j<VIRTUAL_NODES; j++){
                String virtualNodeName = transactionTable[i] + "&&VN" + String.valueOf(j);
                int hashVN = getHash(virtualNodeName);
                System.out.println("虚拟节点[" + virtualNodeName + "]被添加, hash值为" + hashVN);
                sortedMap.put(hashVN, transactionTable[i]);
            }
        }
    }

    // 得到应当路由到的结点
    public static String getServer(String key) {
        // 得到该key的hash值
        int hash = getHash(key);
        // 得到大于该Hash值的所有Map,想象那个环
        SortedMap<Integer, String> subMap = sortedMap.tailMap(hash);
        if (subMap.isEmpty()) {
            // 如果没有比该key的hash值大的，则从第一个node开始
            Integer i = sortedMap.firstKey();
            // 返回对应的交易表
            return sortedMap.get(i);
        } else {
            // 第一个Key就是顺时针过去离node最近的那个结点
            Integer i = subMap.firstKey();
            // 返回对应的交易表
            return subMap.get(i);
        }
    }

    // 使用FNV1_32_HASH算法计算交易表的Hash值,这里不使用重写hashCode的方法，最终效果没区别
    private static int getHash(String str) {
        final int p = 16777619;
        int hash = (int) 2166136261L;
        for (int i = 0; i < str.length(); i++)
            hash = (hash ^ str.charAt(i)) * p;
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;

        // 如果算出来的值为负数则取其绝对值
        if (hash < 0)
            hash = Math.abs(hash);
        return hash;
    }

    public static void main(String[] args) {
        String[] keys = { "73968928317", "73099946651", "72563328728",
                "73967405000", "73968349990", "72112754519", "72088646347",
                "74728589363", "73955634071", "73099946613", "72563228728",
                "73967477000", "73968649990", "72112769519", "72088796347",
                "74728333363", "73955688071" };
        for (int i = 0; i < keys.length; i++)
            System.out.println("[" + keys[i] + "]的hash值为" + getHash(keys[i])+ ", 被路由到结点[" + getServer(keys[i]) + "]");
    }
}
