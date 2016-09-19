package com.ueffort.study.example;

import java.util.Random;

/**
 * 跳跃表
 * Created by GaoJie on 2016/8/21.
 */
public class SkipList<Key extends Comparable<Key>> {
    private int level;
    private int N;
    private int[] powers;
    private Node<Key>[] root;
    private Random rd = new Random();

    private class Node<Key> {
        public Node<Key>[] next;
        public Key key;
        public int level;

        public Node(Key key, int level) {
            this.key = key;
            this.level = level;
        }
    }

    public SkipList(int level) {
        this.level = level;
        root = new Node[this.level];
        powers = new int[this.level];
        for (int j = 0; j < this.level; j++)
            root[j] = null;
        choosePowers();
    }

    public boolean isEmpty() {
        return root[0] == null;
    }

    /**
     * 确定每层的数量
     */
    private void choosePowers() {
        powers[level - 1] = (2 << (level - 1)) - 1;    // 2^maxLevel - 1
        for (int i = level - 2, j = 0; i >= 0; i--, j++)
            powers[i] = powers[i + 1] - (2 << j);           // 2^(j+1)
    }

    /**
     * 随机选择插入的层级
     * @return
     */
    public int chooseLevel() {
        int i, r = Math.abs(rd.nextInt()) % powers[level - 1] + 1;
        for (i = 1; i < level; i++)
            if (r < powers[i])
                return i-1; // return a level < the highest level;
        return i-1;         // return the highest level;
    }


    public Key search(Key key){
        int lvl;
        Node<Key> prev, curr;            // find the highest nonnull
        for (lvl = level - 1; lvl >= 0 && root[lvl] == null; lvl--); // level;
            prev = curr = root[lvl];
        while (true) {
            if (key.equals(curr.key))          // success if equal;
                return curr.key;
            else if (key.compareTo(curr.key) < 0) { // if smaller, go down,
                if (lvl == 0)                 // if possible
                    return null;
                else if (curr == root[lvl])   // by one level
                    curr = root[--lvl];      // starting from the
                else curr = prev.next[--lvl]; // predecessor which
            }                                  // can be the root;
            else {                             // if greater,
                prev = curr;                  // go to the next
                if (curr.next[lvl] != null)   // non-null node
                    curr = curr.next[lvl];   // on the same level
                else {                        // or to a list on a lower level;
                    for (lvl--; lvl >= 0 && curr.next[lvl] == null; lvl--);
                        if (lvl >= 0)
                            curr = curr.next[lvl];
                        else return null;
                }
            }
        }
    }

    public void insert(Key key){
        Node<Key>[] curr = new Node[level];
        Node<Key>[] prev = new Node[level];
        Node<Key> newNode;
        int lvl, i;
        curr[level - 1] = root[level - 1];
        prev[level - 1] = null;
        for (lvl = level - 1; lvl >= 0; lvl--) {
            while (curr[lvl] != null && curr[lvl].key.compareTo(key) < 0) {
                prev[lvl] = curr[lvl];           // go to the next
                curr[lvl] = curr[lvl].next[lvl]; // if smaller;
            }
            if (curr[lvl] != null && key.equals(curr[lvl].key)) // don't
                return;                          // include duplicates;
            if (lvl > 0)                         // go one level down
                if (prev[lvl] == null) {         // if not the lowest
                    curr[lvl - 1] = root[lvl - 1]; // level, using a link
                    prev[lvl - 1] = null;        // either from the root
                } else {                           // or from the predecessor;
                    curr[lvl-1] = prev[lvl].next[lvl - 1];
                    prev[lvl-1] = prev[lvl];
                }
        }
        lvl = chooseLevel();                // generate randomly level
        newNode = new Node<Key>(key, lvl+1); // for newNode;
        for (i = 0; i <= lvl; i++) {        // initialize next fields of
            newNode.next[i] = curr[i];      // newNode and reset to newNode
            if (prev[i] == null)            // either fields of the root
                root[i] = newNode;         // or next fields of newNode's
            else prev[i].next[i] = newNode; // predecessors;
        }
    }
}
