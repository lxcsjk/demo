package com.betterlxc.leetcode;

import org.junit.Test;

/**
 * @author liuxincheng
 * @date 2020/6/24
 */
public class ReverseList {
    @Test
    public void test() {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);

        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);
        ListNode listNode6 = new ListNode(6);

        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        listNode5.next = listNode6;

        System.out.println(ListNode.print(listNode1));
        System.out.println(ListNode.print(reverseList(listNode1)));
    }

    public ListNode reverseList(ListNode x) {
        ListNode pre = null;
        ListNode dummy = x;

        while (dummy != null) {
            ListNode next = dummy.next;
            dummy.next = pre;
            pre = dummy;
            dummy = next;
        }
        return pre;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        public static String print(ReverseList.ListNode listNode) {
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
