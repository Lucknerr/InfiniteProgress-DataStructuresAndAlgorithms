package com.zl;

import sun.text.normalizer.UCharacter;

import java.util.ArrayList;

// 前缀树实现
public class Trie {
    // 记录节点个数
    private int N;
    // 记录根节点
    private Node root;
    // 节点内部类
    private class Node {
        // 通过什么来到此节点
        Character path;
        // 记录有多少字母通过此节点
        int pass;
        // 记录有多少节点以此节点停止
        int end;
        // 记录子节点
        Node[] nextS;
        // 构造方法
        public Node(Character path) {
            // 初始化通过数
            this.pass = 0;
            // 初始化结尾数
            this.end = 0;
            // 初始化来自谁
            this.path = path;
            // 初始化子节点
            this.nextS = new Node[26];
        }
    }

    // 构造方法
    public Trie() {
        // 初始化根节点
        this.root = new Node(null);
        // 初始化节点数
        N++;
    }

    // 向树中添加单词
    public boolean addWord(String word) {
        if (word == null || word.length() == 0) {
            return false;
        }
        // 将字符串转为字符数组
        char[] words = word.toCharArray();
        // 获取字符串长度
        int len = word.length();
        // 创建游标,从根节点开始
        Node cur = root;
        // 根节点通过量加一
        cur.pass++;
        for (int i = 0; i < len; i++) {
            // 判断去那个子节点
            int to = words[i] - 'a';
            // 如果要去的子节点为空
            if (cur.nextS[to] == null) {
                // 那么将信息存入这个子节点
                cur.nextS[to] = new Node(words[i]);
                // 节点数量加一
                N++;
            }
            // 游标来到下一个节点
            cur = cur.nextS[to];
            // 节点通过量加一
            cur.pass++;
        }
        // 当字符全部遍历完成,那么最后一个节点就是这个单词的结尾节点,这个节点结尾量加一
        cur.end++;
        // 添加成功
        return true;
    }

    // 查询树中有没有指定单词
    public boolean findWord(String word) {
        if (word == null || word.length() == 0 || N < word.length()) {
            return false;
        }
        char[] words = word.toCharArray();
        int len = word.length();
        Node cur = root;
        for (int i = 0; i < len; i++) {
            int to = words[i] - 'a';
            // 如果将要去的节点为空,说明字符不匹配,
            if (cur.nextS[to] == null) {
                // 查找失败
                return false;
            }
            cur = cur.nextS[to];
        }
        // 由于匹配的是完整的单词所以最后一个匹配到的一定是含结尾的节点
        return cur.end > 0;
    }

    // 查询树中是否有指定前缀
    public boolean findStartWord(String word) {
        if (word == null || word.length() == 0 || N < word.length()) {
            return false;
        }
        char[] words = word.toCharArray();
        int len = word.length();
        Node cur = root;
        for (int i = 0; i < len; i++) {
            int to = words[i] - 'a';
            if (cur.nextS[to] == null) {
                return false;
            }
            cur = cur.nextS[to];
        }
        // 由于只是匹配前缀,所以不用管是否他是一个完整的单词
        return true;
    }

    // 删除树中指定单词
    public boolean delWord(String word) {
        // 首先判断指定单词在树中是否存在
        if (!findWord(word)) {
            return false;
        }
        char[] words = word.toCharArray();
        int len = word.length();
        Node cur = root;
        // 根节点通过量减一
        cur.pass--;
        for (int i = 0; i < len; i++) {
            int to = words[i] - 'a';
            // 匹配到的子节点通过量减一
            cur.nextS[to].pass--;
            // 如果子节点通过量已经为零,说明不用在匹配下去了
            if (cur.nextS[to].pass == 0) {
                // 将子节点删除
                cur.nextS[to] = null;
                // 删除成功
                return true;
            }
            // 游标来到下一个目标节点
            cur = cur.nextS[to];
        }
        // 游标来到最后一个节点.那么也就是结尾节点,结尾数减一
        cur.end--;
        // 节点数减少单词长度
        N -= len;
        // 删除成功
        return true;
    }
}
