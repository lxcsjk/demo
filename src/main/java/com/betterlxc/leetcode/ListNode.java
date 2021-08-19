package com.betterlxc.leetcode;

/**
 * @author liuxincheng
 * @date 2021/8/17
 */
public class ListNode {
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
