package com.betterlxc.eventBus;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import java.util.stream.IntStream;

/**
 * Created by liuxincheng on 2018/8/2.
 */
public class EventBusTest {

    public static void main(String... args) {
        EventBus eventBus = new EventBus("1111");
        eventBus.register(new EventListener());

        IntStream.range(0, 29).forEach(i -> eventBus.post(new Event("我是   " + i)));
    }

    public static class Event {
        String message;

        Event(String message) {
            this.message = message;
        }
    }

    public static class EventListener {
        @Subscribe
        public void listen(Event event) {
            System.out.println("listen——0  " + event.message);
        }

        @Subscribe
        public void listen1(Event event) {
            System.out.println("listen——1  " + event.message);
        }
    }
}
