package com.betterlxc.rxjava2;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import org.junit.Test;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * @author liuxincheng
 * @date 2019-05-17
 */
public class ObservableTest {

    @Test
    public void test() throws InterruptedException {
        ArrayList<Observable<Long>> list = new ArrayList<>();

        list.add(Observable.intervalRange(6, 5, 0, 1, TimeUnit.SECONDS));
        list.add(Observable.intervalRange(1, 5, 2, 1, TimeUnit.SECONDS));

        Disposable subscribe = Observable.amb(list)
            .subscribe(System.out::println);
        System.out.println(subscribe.isDisposed());
        TimeUnit.SECONDS.sleep(1);
        subscribe.dispose();
        System.out.println(subscribe.isDisposed());
        TimeUnit.HOURS.sleep(1);
    }
}
