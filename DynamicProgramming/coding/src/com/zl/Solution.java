package com.zl;

public class Solution {

    public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode l = new ListNode(0);
        return addTwoNumbers(l1,l2,0,l);
    }

    private ListNode addTwoNumbers(ListNode l1, ListNode l2,int carry,ListNode l) {
        if (l1 == null && l2 == null) {
            if (carry > 0) {
                l.next.val = carry;
            }
            return l;
        }
        l.next = new ListNode(0);
        if (l1 == null) {
            int tmp = l2.val + carry;
            carry = tmp / 10;
            l.val = tmp % 10;
            addTwoNumbers(l1,l2.next,carry,l.next);
        } else if (l2 == null) {
            int tmp = l1.val + carry;
            carry = tmp / 10;
            l.val = tmp % 10;
            addTwoNumbers(l1.next,l2,carry,l.next);
        } else {
            int tmp = l1.val + l2.val + carry;
            carry = tmp / 10;
            l.val = tmp % 10;
            addTwoNumbers(l1.next,l2.next,carry,l.next);
        }
        return l;
    }
}
