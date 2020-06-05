package com.betterlxc.leetcode;

import org.junit.Test;

/**
 * @author liuxincheng
 * @date 2020/5/28
 */

public class AddTwoNumbers {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        }

        ListNode dummy = new ListNode(0);
        ListNode head = dummy;
        int addOne = 0;
        while (l1 != null || l2 != null || addOne != 0) {
            int val1 = l1 == null ? 0 : l1.val;
            int val2 = l2 == null ? 0 : l2.val;

            int sum = val1 + val2 + addOne;

            head.next = new ListNode(sum % 10);
            head = head.next;
            addOne = sum / 10;

            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        return dummy.next;
    }

    @Test
    public void test() {
        ListNode listNode1 = new ListNode(2);
        ListNode listNode2 = new ListNode(4);
        ListNode listNode3 = new ListNode(3);

        ListNode listNode4 = new ListNode(5);
        ListNode listNode5 = new ListNode(6);
        ListNode listNode6 = new ListNode(4);

        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode4.next = listNode5;
        listNode5.next = listNode6;

        ListNode listNode = addTwoNumbers(listNode1, listNode4);
        System.out.println(ListNode.print(listNode));
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        public static String print(ListNode listNode) {
            StringBuilder s = new StringBuilder();
            while (listNode != null) {
                s.append(listNode.val);
                listNode = listNode.next;
            }
            return s.toString();
        }

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }
    }
}
