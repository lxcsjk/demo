package com.betterlxc.leetcode;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;

/**
 * @author liuxincheng
 * @date 2021/8/17
 */
public class ReorderList {

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
        reorderList(listNode1);
        System.out.println(ListNode.print(listNode1));
    }


    private void reorderList(ListNode listNode) {

        List<ListNode> list = Lists.newArrayList();

        while (listNode != null) {
            list.add(listNode);
            listNode = listNode.next;
        }

        int i = 0, j = list.size() - 1;
        while (i < j) {
            list.get(i).next = list.get(j);
            i++;
            if (i == j) {
                break;
            }

            list.get(j).next = list.get(i);
            j--;
        }
        list.get(i).next = null;
    }

}
