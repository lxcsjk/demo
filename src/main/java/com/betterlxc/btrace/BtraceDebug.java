package com.betterlxc.btrace;

import com.sun.btrace.BTraceUtils;
import com.sun.btrace.annotations.BTrace;
import com.sun.btrace.annotations.Duration;
import com.sun.btrace.annotations.Kind;
import com.sun.btrace.annotations.Location;
import com.sun.btrace.annotations.OnMethod;
import com.sun.btrace.annotations.Return;
import com.sun.btrace.annotations.Self;

import java.util.List;

/**
 * @author LXC
 * @date 2017/5/10
 */
@BTrace
public class BtraceDebug {

//  @Export
//  private static long counter;

    @OnMethod(
        clazz = "mbxc.mvc.service.RegionService",
        method = "itemRegion",
        location = @Location(Kind.RETURN)
    )
    public static void run(@Self Object self, Long sellerId, Long numIid, Integer startTime, Integer endTime, @Return List result, @Duration long time) {

        BTraceUtils.println("sellerId =" + sellerId + ",numIid=" + numIid + ",startTime=" + startTime + ",endTime=" + endTime);
//    BTraceUtils.println("result = " + result);
        BTraceUtils.println("time = " + time / 1000000.0);

//    counter++;
    }

//  @OnTimer(1000)
//  public static void run() {
//    BTraceUtils.println("counter = " + counter);
//  }

}
