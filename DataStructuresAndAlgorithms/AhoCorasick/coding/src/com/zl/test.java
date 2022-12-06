package com.zl;

public class test {
    public static void main(String[] args) {
        AhoCorasick ahoCorasick = new AhoCorasick();
        String s = "ushershehis";
        ahoCorasick.addWord("she");
        ahoCorasick.addWord("his");
        ahoCorasick.addWord("he");
        ahoCorasick.addWord("hers");
        System.out.println(ahoCorasick.findWord(s));
    }
}
