package com.betterlxc.string;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.util.AntPathMatcher;

/**
 * @author liuxincheng
 * @date 2019-08-23
 */
@Slf4j
public class AntPathMatcherTest {

    private static final AntPathMatcher ANT_PATH_MATCHER = new AntPathMatcher();

    @Test
    public void test() {
        System.out.println(ANT_PATH_MATCHER.match("/get/{id}.json", "/get/1.json"));
    }

    @Test
    public void test1() {
        ANT_PATH_MATCHER.setCachePatterns(true);
        ANT_PATH_MATCHER.setTrimTokens(true);
        ANT_PATH_MATCHER.setPathSeparator("/");

        Assert.assertTrue(ANT_PATH_MATCHER.match("a", "a"));
        Assert.assertTrue(ANT_PATH_MATCHER.match("a*", "ab"));
        Assert.assertTrue(ANT_PATH_MATCHER.match("a*/**/a", "ab/asdsa/a"));
        Assert.assertTrue(ANT_PATH_MATCHER.match("a*/**/a", "ab/asdsa/asdasd/a"));

        Assert.assertTrue(ANT_PATH_MATCHER.match("*", "a"));
        Assert.assertTrue(ANT_PATH_MATCHER.match("*/*", "a/a"));
    }
}
