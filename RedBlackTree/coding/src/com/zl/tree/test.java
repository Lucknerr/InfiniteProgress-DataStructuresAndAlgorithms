package com.zl.tree;



public class test {
    public static void main(String[] args) {
        RedBlackTree<Integer,String> redBlackTree = new RedBlackTree<>(1,"UU");
        System.out.println(redBlackTree.toString());
        
        redBlackTree.put(2,"pp");
        System.out.println(redBlackTree.toString());
        
        redBlackTree.put(3,"pp");
        System.out.println(redBlackTree.toString());
        
        redBlackTree.put(4,"pp");
        System.out.println(redBlackTree.toString());
        
        redBlackTree.put(5,"pp");
        System.out.println(redBlackTree.toString());
        
        redBlackTree.put(6,"pp");
        System.out.println(redBlackTree.toString());
        
        redBlackTree.put(7,"pp");
        System.out.println(redBlackTree.toString());
        
        redBlackTree.put(8,"pp");
        System.out.println(redBlackTree.toString());
        
        redBlackTree.put(9,"pp");
        System.out.println(redBlackTree.toString());
        
        redBlackTree.put(10,"pp");
        System.out.println(redBlackTree.toString());
        
//        redBlackTree.del(10);
        redBlackTree.unBreakDel(10);
        System.out.println(redBlackTree.toString());
        System.out.println("............");
//        redBlackTree.del(4);
        redBlackTree.unBreakDel(4);
        System.out.println(redBlackTree.toString());
        
        redBlackTree.put(10,"pp");
        System.out.println(redBlackTree.toString());
        
        redBlackTree.put(24,"pp");
        System.out.println(redBlackTree.toString());
        
        redBlackTree.put(18,"pp");
        System.out.println(redBlackTree.toString());
        
//        redBlackTree.del(7);
        redBlackTree.unBreakDel(7);
        System.out.println(redBlackTree.toString());
        

    }
}
