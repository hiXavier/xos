package com.xavier.xos.utils;

import org.apache.commons.lang3.StringUtils;

import java.net.InetAddress;

/**
 * @author wuyanfeng
 * @description
 * @date 2020/9/17 14:53
 */
public class IdGenerator {
    //因为二进制里第一个 bit 为如果是 1，那么都是负数，但是我们生成的 id 都是正数，所以第一个 bit 统一都是 0
    /**
     * 机房ID 5bit 取值范围0-31
     */
    private long dataCenterId;
    /**
     * 机器ID 5bit 取值范围0-31
     */
    private long workerId;

    /**
     * 序号号 12bit 一毫秒内生成id的个数 取值范围0-4095
     */
    private long sequence;

    /**
     * 设置一个时间初始值 41bit 差不多可用69年
     */
    private long twepoch = 1585644268888L;
    /**
     * 5位的机器id(可按实际需要进行配置）
     */
    private long workerIdBits = 5L;
    /**
     * 5位的机房id(可按实际需要进行配置）
     */
    private long dataCenterIdBits = 5L;
    /**
     * 每毫秒内产生的id数 2的12次方
     */
    private long sequenceBits = 12L;
    /**
     * 5bit最多只能有31个数字，即机器id最多只能是32以内
     */
    private long maxWorkerId = -1L ^ (-1L << workerIdBits);
    /**
     * 机房id最多只能是32以内
     */
    private long maxDataCenterId = -1L ^ (-1L << dataCenterIdBits);

    private long workerIdShift = sequenceBits;
    private long dataCenterIdShift = sequenceBits + workerIdBits;
    private long timestampLeftShift = sequenceBits + workerIdBits + dataCenterIdBits;
    private long sequenceMask = -1L ^ (-1L << sequenceBits);
    /**
     * 记录产生时间毫秒数，判断是否是同1毫秒
     */
    private long lastTimestamp = -1L;

    /**
     * dataCenterId、workerId都自主配置
     *
     * @param dataCenterId
     * @param workerId
     * @param sequence
     */
    public IdGenerator(long dataCenterId, long workerId, long sequence) {
        // 检查机房id和机器id是否超过31 不能小于0
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(
                    String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        if (dataCenterId > maxDataCenterId || dataCenterId < 0) {
            throw new IllegalArgumentException(
                    String.format("datacenter Id can't be greater than %d or less than 0", maxDataCenterId));
        }
        this.workerId = workerId;
        this.dataCenterId = dataCenterId;
        this.sequence = sequence;
    }

    public IdGenerator getIdGenerator(Long dataCenterId, Long workerId, String appName) {
        String ip = getIp();
        if (dataCenterId != null && dataCenterId >= 0L && dataCenterId <= maxDataCenterId) {
            this.dataCenterId = dataCenterId;
        } else {
            this.dataCenterId = hash(ip);
        }

        if (workerId != null && workerId >= 0L && dataCenterId <= maxWorkerId) {
            this.workerId = workerId;
        } else if (StringUtils.isNotBlank(appName)) {
            this.workerId = hash(appName);
        } else {
            this.workerId = hash(ip);
        }
        return new IdGenerator(dataCenterId, workerId, 0);
    }

    /**
     * 利用snowflake算法生成全局唯一id
     *
     * @return
     */
    public synchronized long generateId() {
        long timestamp = System.currentTimeMillis();
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(
                    String.format("The machine clock goes backwards for %d milliseconds", lastTimestamp - timestamp));
        }
        if (lastTimestamp == timestamp) {
            //位运算保证毫秒内生成的id始终在4096这个范围内，避免你自己传递个sequence超过了4096这个范围
            sequence = (sequence + 1) & sequenceMask;
            //当某一毫秒的时间，产生的id数 超过4095，系统会进入等待，直到下一毫秒，系统继续产生ID
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0;
        }
        // 这儿记录一下最近一次生成id的时间戳，单位是毫秒
        lastTimestamp = timestamp;
        // 这儿就是最核心的二进制位运算操作，生成一个64bit的id
        // 先将当前时间戳左移，放到41 bit那儿；将机房id左移放到5 bit那儿；将机器id左移放到5 bit那儿；将序号放最后12 bit
        // 最后拼接起来成一个64 bit的二进制数字，转换成10进制就是个long型
        return ((timestamp - twepoch) << timestampLeftShift) |
                (dataCenterId << dataCenterIdShift) |
                (workerId << workerIdShift) | sequence;
    }

    /**
     * 当某一毫秒的时间，产生的id数 超过4095，系统会进入等待，直到下一毫秒，系统继续产生ID
     *
     * @param lastTimestamp
     * @return
     */
    private long tilNextMillis(long lastTimestamp) {
        long timestamp = System.currentTimeMillis();
        while (timestamp <= lastTimestamp) {
            timestamp = System.currentTimeMillis();
        }
        return timestamp;
    }

    /**
     * @return
     */
    public static String getIp() {
        String localhost = "127.0.0.1";
        try {
            localhost = InetAddress.getLocalHost().getHostAddress();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return localhost;
    }

    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    /**
     * main 测试类
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(Integer.toBinaryString(-1));
        System.out.println(Integer.toBinaryString(-1 << 5));
        System.out.println(-1L ^ (-1L << 5));
        System.out.println(1 & 4596);
        System.out.println(6 & 4596);
        IdGenerator worker = new IdGenerator(1, 1, 1);
        for (int i = 0; i < 22; i++) {
            System.out.println(worker.generateId());
        }
    }

}
