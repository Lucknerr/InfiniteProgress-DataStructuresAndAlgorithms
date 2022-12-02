package com.zl;

public class test {

    public static void main(String[] args) {
        String a = "my";
        String b = "name";
        String c = "her";
        String d = "love";
        Trie trie = new Trie();
        trie.addWord(a);
        trie.addWord(b);
        trie.addWord(c);
        trie.addWord(d);
//        System.out.println(trie.delWord("name"));
        System.out.println(trie.findWord("me"));
    }
}
