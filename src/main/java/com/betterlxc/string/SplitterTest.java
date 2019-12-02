package com.betterlxc.string;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import io.reactivex.Observable;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by LXC on 2017/4/24.
 */
@Slf4j
public class SplitterTest {

    private static final Splitter SPLITTER = Splitter.on(';');

    private static final Splitter SPLITTERS = Splitter.on(':');

    @Test
    public void splitterTest() {
        String str = "A";
        List<String> list = SPLITTER.splitToList(str);
        log.info("分割链接后的List  ----- > {}", list);


        Duration duration = Duration.between(
            LocalDateTime.of(2017, 10, 6, 16, 10, 30)
            , LocalDateTime.now());

        System.out.println(duration.toDays());
        System.out.println(duration.toHours());
        System.out.println(duration.toMinutes());

//    System.out.println(duration.to());
    }


    @Test
    public void test2() {
        String str = "149422948:888056195:材质成分:聚丙烯腈纤维(腈纶)55% 棉45%;20000:60256838:品牌:Othermix;122216586:29947:服装版型:直筒;13021751:700282045:货号:3MB4103P-1;122216507:3226292:厚薄:常规;20608:29934:风格:街头;18073248:125200612:街头:欧美;122216484:103422:款式:套头;10142888:3386071:组合形式:单件;122216562:3226292:衣长:常规款;122216348:29444:袖长:长袖;20663:32499857:领子:低圆领;2917380:3226292:袖型:常规;31611:103422:衣门襟:套头;20603:29454:图案:纯色;122216588:9142620:流行元素/工艺:拼接;13328588:492838730:成分含量:51%(含)-70%(含);20551:80664:面料:腈纶;20017:494072160:适用年龄:25-29周岁;122216347:380120406:上市年份/季节:2014年秋季;1627207:3232480:颜色分类:粉红色;1627207:28335:颜色分类:绿色;1627207:28338:颜色分类:蓝色;1627207:28341:颜色分类:黑色;20509:28314:尺码:S;20509:28315:尺码:M;20509:28316:尺码:L;20509:28317:尺码:XL";

        List<String> list = SPLITTER.splitToList(str);
        log.info("分割链接后的List  ----- > {}", list);

        list.forEach(mo -> System.out.println(SPLITTERS.splitToList(mo)));
    }

    @Test
    public void test3() {
        Observable
            .merge(Observable.fromIterable(Lists.newArrayList(1, 2, 3)), Observable.fromIterable(Lists.newArrayList(1, 2, 3)))
            .subscribe(list -> {
                System.out.println(list);
            });
    }
}
