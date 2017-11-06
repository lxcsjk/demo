package com.betterlxc.ip;

import org.lionsoul.ip2region.DataBlock;
import org.lionsoul.ip2region.DbConfig;
import org.lionsoul.ip2region.DbSearcher;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author LXC
 */
public class IpUtil {

  private static final File IP_PATH =
      new File("/Users/lxc/Github/Java/guava-demo/src/main/resources/17monipdb.dat");

  private static final String DBPATH = "/Users/lxc/Github/Java/guava-demo/src/main/resources/ip2region.db";

  private static DbSearcher DB_SEARCHER = null;

  private static int offset;

  private static int[] index = new int[256];

  private static ByteBuffer dataBuffer;

  private static ByteBuffer indexBuffer;

  private static ReentrantLock lock = new ReentrantLock();

  static {
    load();
    try {
      DB_SEARCHER = new DbSearcher(new DbConfig(), DBPATH);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static String find1(String ip) {
    if (ip == null || ip.length() < 5) {
      return "保留地址\t保留地址";
    }
    int ipPrefixValue = new Integer(ip.substring(0, ip.indexOf(".")));
    long ip2longValue = ip2long(ip);
    int start = index[ipPrefixValue];
    int maxCompLen = offset - 1028;
    long indexOffset = -1;
    int indexLength = -1;
    byte b = 0;
    for (start = start * 8 + 1024; start < maxCompLen; start += 8) {
      if (int2long(indexBuffer.getInt(start)) >= ip2longValue) {
        indexOffset = bytesToLong(b, indexBuffer.get(start + 6), indexBuffer.get(start + 5), indexBuffer.get(start + 4));
        indexLength = 0xFF & indexBuffer.get(start + 7);
        break;
      }
    }

    byte[] areaBytes;

//    lock.lock();
//    try {
    dataBuffer.position(offset + (int) indexOffset - 1024);
    areaBytes = new byte[indexLength];
    dataBuffer.get(areaBytes, 0, indexLength);
//    } finally {
//      lock.unlock();
//    }

    return new String(areaBytes, Charset.forName("UTF-8"));
  }

  private static void load() {
    FileInputStream fin = null;
    lock.lock();
    try {
      dataBuffer = ByteBuffer.allocate(Long.valueOf(IP_PATH.length()).intValue());
      fin = new FileInputStream(IP_PATH);
      int readBytesLength;
      byte[] chunk = new byte[4096];
      while (fin.available() > 0) {
        readBytesLength = fin.read(chunk);
        dataBuffer.put(chunk, 0, readBytesLength);
      }
      dataBuffer.position(0);
      int indexLength = dataBuffer.getInt();
      byte[] indexBytes = new byte[indexLength];
      dataBuffer.get(indexBytes, 0, indexLength - 4);
      indexBuffer = ByteBuffer.wrap(indexBytes);
      indexBuffer.order(ByteOrder.LITTLE_ENDIAN);
      offset = indexLength;

      int loop = 0;
      while (loop++ < 256) {
        index[loop - 1] = indexBuffer.getInt();
      }
      indexBuffer.order(ByteOrder.BIG_ENDIAN);
    } catch (IOException ioe) {
      ioe.printStackTrace();
    } finally {
      try {
        if (fin != null) {
          fin.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
      lock.unlock();
    }
  }

  private static long bytesToLong(byte a, byte b, byte c, byte d) {
    return int2long((((a & 0xff) << 24) | ((b & 0xff) << 16) | ((c & 0xff) << 8) | (d & 0xff)));
  }

  private static int str2Ip(String ip) {
    String[] ss = ip.split("\\.");
    int a, b, c, d;
    a = Integer.parseInt(ss[0]);
    b = Integer.parseInt(ss[1]);
    c = Integer.parseInt(ss[2]);
    d = Integer.parseInt(ss[3]);
    return (a << 24) | (b << 16) | (c << 8) | d;
  }

  private static long ip2long(String ip) {
    return int2long(str2Ip(ip));
  }

  private static long int2long(int i) {
    long l = i & 0x7fffffffL;
    if (i < 0) {
      l |= 0x080000000L;
    }
    return l;
  }

  public static DataBlock find2(String ip) {
    try {
      return DB_SEARCHER.memorySearch(ip);
    } catch (IOException e) {
      return new DataBlock(0,"其他|其他|其他|其他|其他");
    }
  }
}
