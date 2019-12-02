package com.betterlxc.redis;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author liuxincheng
 * @date 2019-06-12
 */
public class HSETTest {

    public final static String SMAUG_SERVICE_COUPON_STOCK = "stock:";
    public final static String SMAUG_SERVICE_COUPON_STOCK_BUCKET = "stock:bucket:";
    private static Jedis JEDIS;

    static {
        JEDIS = new Jedis("localhost");
    }

    private int bucketCount = 640;

    @Test
    public void test1() {

//        IntStream.range(1, 640000).forEach(i -> JEDIS.hset(
//            buildBucketKey(buildBucketKey(i + "")),
//            SMAUG_SERVICE_COUPON_STOCK + i,
//            UUID.randomUUID().toString()));

        JEDIS.flushAll();
        IntStream.range(1, 640000).forEach(i -> JEDIS.set(
            SMAUG_SERVICE_COUPON_STOCK + i,
            UUID.randomUUID().toString()));
    }

    @Test
    public void test2() {
        String[] buckets = buildBuckets();

        for (String bucket : buckets) {
            ScanParams params = new ScanParams();
            params.count(10);
            ScanResult<Map.Entry<String, String>> hscan = JEDIS.hscan(bucket, "0", params);
            List<Map.Entry<String, String>> result = hscan.getResult();
            System.out.println(bucket);
            System.out.println(hscan.getCursor());
            System.out.println(result.stream().map(Map.Entry::getKey).collect(Collectors.joining(",")));

            System.out.println();
        }
    }

    @Test
    public void test3() {
        ScanParams params = new ScanParams();
        params.count(10);
        params.match(SMAUG_SERVICE_COUPON_STOCK + "*");
        ScanResult<String> scan = JEDIS.scan("0", params);

        List<String> result = scan.getResult();
        System.out.println(scan.getCursor());
        System.out.println(result);
    }

    @Test
    public void test4() {
        JEDIS.set("chengzhisb", "{\n" +
            "    \"data\": {\n" +
            "        \"total\": 5,\n" +
            "        \"dataList\": [\n" +
            "            {\n" +
            "                \"id\": 82102,\n" +
            "                \"mobile\": \"17602159327\",\n" +
            "                \"shopId\": 3443,\n" +
            "                \"manageShopId\": 3443,\n" +
            "                \"employeeName\": \"周伟\",\n" +
            "                \"roleId\": -1,\n" +
            "                \"wangZhaRoleId\": 0,\n" +
            "                \"employeeType\": 2,\n" +
            "                \"state\": 0,\n" +
            "                \"remark\": \"\",\n" +
            "                \"createTime\": \"2019-02-21 17:25:07\",\n" +
            "                \"createUser\": \"XbAdmin\",\n" +
            "                \"updateTime\": \"2019-02-21 17:25:07\",\n" +
            "                \"updateUser\": \"XbAdmin\",\n" +
            "                \"wechatDayEnable\": 0,\n" +
            "                \"wechatPayEnable\": 0,\n" +
            "                \"fsUserId\": \"\",\n" +
            "                \"nodeId\": -1\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": 81653,\n" +
            "                \"mobile\": \"18621797521\",\n" +
            "                \"shopId\": 3443,\n" +
            "                \"manageShopId\": 3443,\n" +
            "                \"employeeName\": \"李波\",\n" +
            "                \"roleId\": -1,\n" +
            "                \"wangZhaRoleId\": 0,\n" +
            "                \"employeeType\": 2,\n" +
            "                \"state\": 0,\n" +
            "                \"remark\": \"\",\n" +
            "                \"createTime\": \"2018-12-28 15:32:57\",\n" +
            "                \"createUser\": \"18621797521\",\n" +
            "                \"updateTime\": \"2018-12-28 15:32:57\",\n" +
            "                \"updateUser\": \"18621797521\",\n" +
            "                \"wechatDayEnable\": 1,\n" +
            "                \"wechatPayEnable\": 1,\n" +
            "                \"fsUserId\": \"\",\n" +
            "                \"nodeId\": -1\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": 80989,\n" +
            "                \"mobile\": \"15755364923\",\n" +
            "                \"shopId\": 3443,\n" +
            "                \"manageShopId\": 3443,\n" +
            "                \"employeeName\": \"\",\n" +
            "                \"roleId\": -1,\n" +
            "                \"wangZhaRoleId\": 0,\n" +
            "                \"employeeType\": 2,\n" +
            "                \"state\": 0,\n" +
            "                \"remark\": \"\",\n" +
            "                \"createTime\": \"2018-10-29 14:31:35\",\n" +
            "                \"createUser\": \"15021849282\",\n" +
            "                \"updateTime\": \"2018-10-29 14:31:35\",\n" +
            "                \"updateUser\": \"15021849282\",\n" +
            "                \"wechatDayEnable\": 0,\n" +
            "                \"wechatPayEnable\": 0,\n" +
            "                \"fsUserId\": \"\",\n" +
            "                \"nodeId\": -1\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": 79301,\n" +
            "                \"mobile\": \"18482216400\",\n" +
            "                \"shopId\": 3443,\n" +
            "                \"manageShopId\": 3443,\n" +
            "                \"employeeName\": \"\",\n" +
            "                \"roleId\": -1,\n" +
            "                \"wangZhaRoleId\": 0,\n" +
            "                \"employeeType\": 2,\n" +
            "                \"state\": 0,\n" +
            "                \"remark\": \"\",\n" +
            "                \"createTime\": \"2018-08-30 17:11:20\",\n" +
            "                \"createUser\": \"15021849282\",\n" +
            "                \"updateTime\": \"2018-08-30 17:11:20\",\n" +
            "                \"updateUser\": \"15021849282\",\n" +
            "                \"wechatDayEnable\": 1,\n" +
            "                \"wechatPayEnable\": 1,\n" +
            "                \"fsUserId\": \"\",\n" +
            "                \"nodeId\": -1\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": 79300,\n" +
            "                \"mobile\": \"19900000004\",\n" +
            "                \"shopId\": 3443,\n" +
            "                \"manageShopId\": 3443,\n" +
            "                \"employeeName\": \"\",\n" +
            "                \"roleId\": -1,\n" +
            "                \"wangZhaRoleId\": 0,\n" +
            "                \"employeeType\": 2,\n" +
            "                \"state\": 0,\n" +
            "                \"remark\": \"\",\n" +
            "                \"createTime\": \"2018-08-30 17:09:39\",\n" +
            "                \"createUser\": \"15021849282\",\n" +
            "                \"updateTime\": \"2018-08-30 17:09:39\",\n" +
            "                \"updateUser\": \"15021849282\",\n" +
            "                \"wechatDayEnable\": 1,\n" +
            "                \"wechatPayEnable\": 1,\n" +
            "                \"fsUserId\": \"\",\n" +
            "                \"nodeId\": -1\n" +
            "            }\n" +
            "        ]\n" +
            "    },\n" +
            "    \"errorCode\": 0,\n" +
            "    \"errorDesc\": \"\",\n" +
            "    \"elapsedMilliseconds\": 13,\n" +
            "    \"success\": true\n" +
            "}");
    }


    protected byte buildBucketNo(String jobId) {
        return (byte) (Math.abs(jobId.hashCode()) % this.bucketCount);
    }

    protected String buildBucketKey(String jobId) {
        return SMAUG_SERVICE_COUPON_STOCK_BUCKET + this.buildBucketNo(jobId);
    }

    protected String[] buildBuckets() {
        String[] buckets = new String[this.bucketCount];
        for (int i = 0; i < this.bucketCount; i++) {
            buckets[i] = SMAUG_SERVICE_COUPON_STOCK_BUCKET + i;
        }
        return buckets;
    }

}
