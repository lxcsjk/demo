package com.betterlxc.concurrent;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author liuxincheng
 * @date 2019-06-17
 */
public class ForkJoinPoolTest {

    @Test
    public void test() {
        ForkJoinPool forkJoinPool = new ForkJoinPool(10);
        Task task = new Task();
        forkJoinPool.invoke(task);
    }

    @Test
    public void test01() {
        // 创建随机数组成的数组:
        List<Integer> array = Lists.newArrayList();
        IntStream.range(0, 400).forEach(array::add);

        int sum = array.stream().mapToInt(Integer::intValue).sum();
        System.out.println("和为  " + sum);

        // fork/join task:
        ForkJoinPool fjp = new ForkJoinPool(4);
        ForkJoinTask<Long> task = new SumTask(array, 0, array.size());
        long startTime = System.currentTimeMillis();
        Long result = fjp.invoke(task);
        long endTime = System.currentTimeMillis();
        System.out.println("Fork/join sum: " + result + " in " + (endTime - startTime) + " ms.");
    }

    @Test
    public void test02() throws InterruptedException {
        // 创建包含Runtime.getRuntime().availableProcessors()返回值作为个数的并行线程的ForkJoinPool
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        // 提交可分解的PrintTask任务
        forkJoinPool.submit(new MyRecursiveAction(0, 1000));

        while (!forkJoinPool.isTerminated()) {
            forkJoinPool.awaitTermination(2, TimeUnit.SECONDS);
        }
        // 关闭线程池
        forkJoinPool.shutdown();
    }

    @Test
    public void test03() throws InterruptedException {
        // 创建包含Runtime.getRuntime().availableProcessors()返回值作为个数的并行线程的ForkJoinPool
        ForkJoinPool forkJoinPool = new ForkJoinPool(10);
        // 提交可分解的PrintTask任务

        List<Integer> array = Lists.newArrayList();
        IntStream.range(0, 400).forEach(array::add);

        forkJoinPool.submit(new ForkJoinAction(0, array.size(), array));

        //阻塞当前线程直到 ForkJoinPool 中所有的任务都执行结束
        forkJoinPool.awaitTermination(2, TimeUnit.SECONDS);

        // 关闭线程池
        forkJoinPool.shutdown();
        // 关闭线程池
    }

    class Task extends RecursiveAction {

        @Override
        protected void compute() {
            System.out.println("Inside Compute method");
        }
    }

    class SumTask extends RecursiveTask<Long> {

        static final int THRESHOLD = 100;
        List<Integer> array;
        int start;
        int end;

        SumTask(List<Integer> array, int start, int end) {
            this.array = array;
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            if (end - start <= THRESHOLD) {
                // 如果任务足够小,直接计算:
                long sum = 0;
                for (int i = start; i < end; i++) {
                    sum += array.get(i);
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {
                }
                System.out.println(String.format("compute %d~%d = %d", start, end, sum));
                return sum;
            }
            // 任务太大,一分为二:
            int middle = (end + start) / 2;
            System.out.println(String.format("split %d~%d ==> %d~%d, %d~%d", start, end, start, middle, middle, end));
            SumTask subtask1 = new SumTask(this.array, start, middle);
            SumTask subtask2 = new SumTask(this.array, middle, end);
            invokeAll(subtask1, subtask2);
            Long subresult1 = subtask1.join();
            Long subresult2 = subtask2.join();
            Long result = subresult1 + subresult2;
            System.out.println("result = " + subresult1 + " + " + subresult2 + " ==> " + result);
            return result;
        }
    }

    class MyRecursiveAction extends RecursiveAction {

        /**
         * 每个"小任务"最多只打印20个数
         */
        private static final int MAX = 20;

        private int start;
        private int end;

        public MyRecursiveAction(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected void compute() {
            //当end-start的值小于MAX时，开始打印
            if ((end - start) < MAX) {
                for (int i = start; i < end; i++) {
                    System.out.println(Thread.currentThread().getName() + "-i的值" + i);
                }
            } else {
                // 将大任务分解成两个小任务
                int middle = (start + end) / 2;
                MyRecursiveAction left = new MyRecursiveAction(start, middle);
                MyRecursiveAction right = new MyRecursiveAction(middle, end);
                left.fork();
                right.fork();
            }
        }

    }

    class ForkJoinAction extends RecursiveAction {

        private static final int BRAND_NUM = 100;

        private int start;

        private int end;

        private List<Integer> brandIds;

        public ForkJoinAction(int start, int end, List<Integer> brandIds) {
            this.start = start;
            this.end = end;
            this.brandIds = brandIds;
        }

        /**
         * The main computation performed by this task.
         *
         * @return the result of the computation
         */
        @Override
        protected void compute() {
            if ((end - start) < BRAND_NUM) {
                System.out.println(brandIds);
                System.out.println(brandIds.stream().mapToInt(Integer::intValue).max());
            } else {
                // 将大任务分解成两个小任务
                int middle = (start + end) / 2;

                List<Integer> list1 = brandIds.subList(start, middle);
                List<Integer> list2 = brandIds.subList(middle, end);

                ForkJoinAction left = new ForkJoinAction(0, list1.size(), list1);
                ForkJoinAction right = new ForkJoinAction(0, list2.size(), list2);
                left.fork();
                right.fork();
            }
        }
    }
}
