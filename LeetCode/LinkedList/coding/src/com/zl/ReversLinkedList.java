package com.zl;

// 反转链表
public class ReversLinkedList {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public static ListNode reverseList(ListNode head) {
        return f(null,head);
    }

    public static ListNode f(ListNode pre, ListNode node) {
        if (node == null) {
            return null;
        }
        ListNode next = node.next;
        node.next = pre;
        return node.next == null ? node : f(node,next);
    }

    public static ListNode reverseList1(ListNode head) {
        ListNode pre = null;
        ListNode next = head.next;
        while (head != null) {
            head.next = pre;
            pre = head;
            head = next;
            next = head.next;
        }
        return pre;
    }

    public static void main(String[] args) {
        int[] tmp = {1,2,3,4,5};
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
        System.out.println(reverseList1(head));
    }
}
