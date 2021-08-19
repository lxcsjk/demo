package com.betterlxc.leetcode;

import org.junit.Test;

/**
 * @author liuxincheng
 * @date 2021/8/17
 */
public class HasCycle {


    @Test
    public void test() {


    }

    private boolean hasCycle(ListNode headNode) {
        ListNode fast = headNode;
        ListNode slow = headNode;

        while (fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if (fast == slow) {
                return true;
            }

        }

        return false;
    }

    private ListNode deleteDuplicates(ListNode headNode) {
        ListNode a = headNode;
        while (a != null && a.next != null) {
            if (a.val == a.next.val) {
                a.next = a.next.next;
            } else {
                a = a.next;
            }
        }
        return headNode;
    }

    private ListNode deleteDuplicates2(ListNode headNode) {
        if (headNode == null) {
            return headNode;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = headNode;

        ListNode a = dummy;
        while (a.next != null && a.next.next != null) {
            if (a.next.val == a.next.next.val) {
                int val = a.next.val;

                while (a.next != null && a.next.val == val) {
                    a.next = a.next.next;
                }
            } else {
                a = a.next;
            }

        }
        return dummy.next;
    }


}
