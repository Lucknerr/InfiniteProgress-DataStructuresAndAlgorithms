package com.zl;

public class ReverseLinkedListII {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public static ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode ago = null;
        ListNode ans = head;
        while (right > 0) {
            if (left == 2) {
                ago = head;
            }
            left--;
            right--;
            head = head.next;
        }
        return f(ans,ago,ago == null ? ans : ago.next,head,head);
    }

    public static ListNode f(ListNode ans, ListNode ago, ListNode node, ListNode post, ListNode end) {
        if (node == end) {
            if (ago == null) {
                return post;
            }
            ago.next = post;
            return ans;
        }
        ListNode next = node.next;
        node.next = post;
        return f(ans,ago,next,node,end);
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
        ListNode newNode = reverseBetween(head,2,4);
    }
}
