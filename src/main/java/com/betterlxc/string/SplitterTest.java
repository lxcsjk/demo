package com.betterlxc.string;

import com.google.common.base.Splitter;
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

  private static final Splitter SPLITTER = Splitter.on(',');

  @Test
  public void splitterTest() {
    String str = "A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z";
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
}
