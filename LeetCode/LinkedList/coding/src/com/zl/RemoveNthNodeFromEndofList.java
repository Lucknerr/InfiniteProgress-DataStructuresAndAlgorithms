package com.zl;

import java.util.Date;

public class RemoveNthNodeFromEndofList {
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next;}
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode end = head;
        for (int i = 0;i < n;i++) end = end.next;
        if (end == null) {
            return head.next;
        }
        ListNode node = head;
        while (end.next != null) {
            node = node.next;
            end = end.next;
        }
        node.next = node.next.next;
        return head;
    }

    static int N = 0;
    public static ListNode removeNthFromEnd1(ListNode head, int n) {
        N = n;
        return f(head,null,head);
    }

    public static ListNode f(ListNode ans, ListNode pre, ListNode node) {
        if (node == null) {
            return ans;
        }
        f(ans,node,node.next);
        N--;
        if (N == 0) {
            if (pre == null) {
                return node.next;
            }
            pre.next = node.next;
            return ans;
        }
        return ans;
    }


    public static void main(String[] args) {
        int[] tmp = {1,2,3,4,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5};
        ListNode head = new ListNode();
        ListNode node = head;
        int n = tmp.length;
        for (int i = 0;i < n;i++) {
            node.val = tmp[i];
            if (i < n-1) {
                node.next = new ListNode();
                node = node.next;
            }

        }
        long d1 = new Date().getTime();
        removeNthFromEnd1(head,2);
        long d2 = new Date().getTime();
        removeNthFromEnd(head,2);
        long d3 = new Date().getTime();
        System.out.println(d2-d1);
        System.out.println(d3-d2);
    }
}


