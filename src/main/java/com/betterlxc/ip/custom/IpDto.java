package com.betterlxc.ip.custom;

import org.jetbrains.annotations.NotNull;

/**
 * @author LXC
 * @date 2017/5/10
 */
public class IpDto implements Comparable<IpDto> {
  private int pid;
  private int cid;
  private long start;
  private long offset;

  public IpDto(int pid, int cid, long start, long offset) {
    this.pid = pid;
    this.cid = cid;
    this.start = start;
    this.offset = offset;
  }

  public int getPid() {
    return pid;
  }

  public void setPid(int pid) {
    this.pid = pid;
  }

  public long getStart() {
    return start;
  }

  public void setStart(long start) {
    this.start = start;
  }

  public long getOffset() {
    return offset;
  }

  public void setOffset(long offset) {
    this.offset = offset;
  }

  public int getCid() {
    return cid;
  }

  public void setCid(int cid) {
    this.cid = cid;
  }

  @Override
  public int compareTo(@NotNull IpDto o) {
    if (start + offset < o.getStart()) {
      return -1;
    } else if (start > o.getStart() + o.getOffset()) {
      return 1;
    }
    return 0;
  }

  @Override
  public String toString() {
    return "IpDto{" +
        "pid=" + pid +
        ", cid=" + cid +
        ", start=" + start +
        ", offset=" + offset +
        '}';
  }
}