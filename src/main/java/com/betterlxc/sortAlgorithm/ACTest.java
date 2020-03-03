package com.betterlxc.sortAlgorithm;

/**
 * @author liuxincheng
 * @date 2020/2/12
 */
public class ACTest {
    static int N = 20;
    static int M = 3;
    static int[] a = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
    static int[] b = new int[M];

    public static void main(String[] args) {
        for (;;) {

            System.out.println(1);
        }

//        C(N, M);
    }

    static void C(int m, int n) {
        int i, j;
        for (i = n; i <= m; i++) {
            b[n - 1] = i - 1;
            if (n > 1) {
                C(i - 1, n - 1);
            } else {
                for (j = 0; j <= M - 1; j++) {
                    System.out.print(a[b[j]] + "  ");
                }
                System.out.println();
            }
        }
    }
}
