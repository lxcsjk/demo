package com.betterlxc.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * Created by LXC on 2017/5/10.
 */
@Slf4j
public class CacheTest {

    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static final Cache<String, String> CALLABLE_CACHE = CacheBuilder.newBuilder()
        .expireAfterWrite(1, TimeUnit.SECONDS)
        .maximumSize(1000)
        .recordStats() // 统计功能
        .removalListener(notification -> log.info("Remove a map entry which key is {},value is {},cause is {}.\n", notification.getKey(),
            notification.getValue(), notification.getCause().name()))
        .build();

    private static final LoadingCache<String, String> LOADER_CACHE = CacheBuilder.newBuilder()
        .expireAfterAccess(1, TimeUnit.SECONDS)
        .maximumSize(1000)
        .recordStats()
        .build(new CacheLoader<String, String>() {
            @Override
            public String load(String key) throws Exception {
                return key + SIMPLE_DATE_FORMAT.format(new Date());
            }
        });

    @Test
    public void callableCacheTest() {
        IntStream.range(0, 4).forEach(i -> {
            try {
                Thread.sleep(900);
                String keyCallable = "key" + i;
                String valueCallable = CALLABLE_CACHE.get(keyCallable, () -> SIMPLE_DATE_FORMAT.format(new Date()));
                log.info("Callable Cache ----->>>>> key is {},value is {} \n", keyCallable, valueCallable);
                log.info("Callable Cache ----->>>>> stat :{} \n", CALLABLE_CACHE.stats());
                log.info("Callable Cache ----->>>>> stat miss:{},stat hit:{}\n", CALLABLE_CACHE.stats().missRate(), CALLABLE_CACHE.stats().hitRate());
                log.info("Callable Cache ----->>>>> size is {}\n", CALLABLE_CACHE.size());

            } catch (Exception e) {
                log.error("{}", e);
            }
        });
    }

    /**
     * 支持自动加载value
     */
    @Test
    public void loaderCacheTest() {
        IntStream.range(0, 1).forEach(i -> {
            try {

                String valueLoader = LOADER_CACHE.get("key");
                log.info("Loader Cache ----->>>>> key is {},value is {}\n", "key", valueLoader);

                Thread.sleep(1000);
                log.info("Loader Cache ----->>>>> key is {},value is {}\n", "key", LOADER_CACHE.get("key"));

//        log.info("Loader Cache ----->>>>> key is {},value is {}\n", "key", valueLoader);
//        log.info("Callable Cache ----->>>>> stat :{} \n", LOADER_CACHE.stats());
//        log.info("Loader Cache ----->>>>> stat miss:{},stat hit:{}\n", LOADER_CACHE.stats().missRate(), LOADER_CACHE.stats().hitRate());
//        log.info("Callable Cache ----->>>>> size is {}\n", LOADER_CACHE.size());

            } catch (Exception e) {
                log.error("{}", e);
            }
        });
    }
}