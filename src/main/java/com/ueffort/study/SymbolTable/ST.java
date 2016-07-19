package com.ueffort.study.SymbolTable;

/**
 * 有序泛型符号表
 * Created by GaoJie on 2016/7/18.
 */
abstract public class ST<Key extends Comparable<Key>, Value> {
    /**
     * 将键值存入表中（若值为空则将键key从表中删除）
     * @param key
     * @param value
     */
    abstract public void put(Key key, Value value);

    /**
     * 获取键key对应的值（若key不存在则返回空）
     * @param key
     * @return
     */
    abstract public Value get(Key key);

    /**
     * 从表中删除键key（及其对应的值）
     * @param key
     */
    abstract public void delete(Key key);

    /**
     * 键key是否存在于表中
     * @param key
     * @return
     */
    abstract public boolean contains(Key key);

    /**
     * 表是否为空
     * @return
     */
    abstract public boolean isEmpty();

    /**
     * 表中的键值对数量
     * @return
     */
    abstract public int size();

    /**
     * 最小的键
     * @return
     */
    abstract public Key min();

    /**
     * 最大的键
     * @return
     */
    abstract public Key max();

    /**
     * 小于等于key的最大键
     * @param key
     * @return
     */
    abstract public Key floor(Key key);

    /**
     * 大于等于key的最小键
     * @param key
     * @return
     */
    abstract public Key ceiling(Key key);

    /**
     * 小于key的键的数量
     * @param key
     * @return
     */
    abstract public int rank(Key key);

    /**
     * 排名为k的键
     * @param k
     * @return
     */
    abstract public Key select(int k);

    /**
     * 删除最小的键
     */
    public void deleteMin(){
        delete(min());
    }

    /**
     * 删除最大的键
     */
    public void deleteMax(){
        delete(max());
    }

    /**
     * [lo..hi]之间键的数量
     * @param lo
     * @param hi
     * @return
     */
    public int size(Key lo, Key hi){
        if(hi.compareTo(lo) < 0) return 0;
        else if (contains(hi)) return rank(hi) - rank(lo) + 1;
        else return rank(hi) - rank(lo);
    }

    /**
     * [lo..hi]之间所有键，已排序
     * @param lo
     * @param hi
     * @return
     */
    abstract public Iterable<Key> keys(Key lo, Key hi);

    /**
     * 表中所有键的集合，已排序
     * @return
     */
    public Iterable<Key> keys(){
        return keys(min(), max());
    }
}
