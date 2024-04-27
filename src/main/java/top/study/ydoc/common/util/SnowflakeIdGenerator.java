package top.study.ydoc.common.util;

import java.util.Objects;

/**
 * @author tjy
 * @date 2024/04/13
 */
public class SnowflakeIdGenerator {
    // 起始的时间戳
    private final static long START_TIMESTAMP = 1713021218711L;

    // 每一部分占用的位数
    private final static long SEQUENCE_BIT = 12; // 序列号占用的位数
    private final static long MACHINE_BIT = 5;   // 机器标识占用的位数
    private final static long DATA_CENTER_BIT = 5;// 数据中心占用的位数

    // 每一部分的最大值
    private final static long MAX_SEQUENCE = ~(-1L << SEQUENCE_BIT);
    private final static long MAX_MACHINE_NUM = ~(-1L << MACHINE_BIT);
    private final static long MAX_DATA_CENTER_NUM = ~(-1L << DATA_CENTER_BIT);

    // 每一部分向左的位移
    private final static long MACHINE_LEFT = SEQUENCE_BIT;
    private final static long DATA_CENTER_LEFT = SEQUENCE_BIT + MACHINE_BIT;
    private final static long TIMESTAMP_LEFT = DATA_CENTER_LEFT + DATA_CENTER_BIT;

    private long dataCenterId;  // 数据中心
    private long machineId;     // 机器标识
    private long sequence = 0L; // 序列号
    private long lastTimestamp = -1L; // 上一次时间戳

    public SnowflakeIdGenerator(long dataCenterId, long machineId) {
        if (dataCenterId > MAX_DATA_CENTER_NUM || dataCenterId < 0) {
            throw new IllegalArgumentException("Data center Id can't be greater than " + MAX_DATA_CENTER_NUM + " or less than 0");
        }
        if (machineId > MAX_MACHINE_NUM || machineId < 0) {
            throw new IllegalArgumentException("Machine Id can't be greater than " + MAX_MACHINE_NUM + " or less than 0");
        }
        this.dataCenterId = dataCenterId;
        this.machineId = machineId;
    }

    public SnowflakeIdGenerator(long dataCenterId) {
        if (dataCenterId > MAX_DATA_CENTER_NUM || dataCenterId < 0) {
            throw new IllegalArgumentException("Data center Id can't be greater than " + MAX_DATA_CENTER_NUM + " or less than 0");
        }
        this.dataCenterId = dataCenterId;
        this.machineId = Long.parseLong(Objects.requireNonNull(IpUtils.getLocalIPAddress()).replace(".", ""));
    }

    public synchronized long nextId() {
        long currentTimestamp = getTimestamp();
        if (currentTimestamp < lastTimestamp) {
            throw new RuntimeException("Clock moved backwards. Refusing to generate id");
        }

        if (currentTimestamp == lastTimestamp) {
            sequence = (sequence + 1) & MAX_SEQUENCE;
            if (sequence == 0) {
                currentTimestamp = getNextTimestamp();
            }
        } else {
            sequence = 0L;
        }

        lastTimestamp = currentTimestamp;

        return (currentTimestamp - START_TIMESTAMP) << TIMESTAMP_LEFT | dataCenterId << DATA_CENTER_LEFT | machineId << MACHINE_LEFT | sequence;
    }

    private long getNextTimestamp() {
        long currentTimestamp = getTimestamp();
        while (currentTimestamp <= lastTimestamp) {
            currentTimestamp = getTimestamp();
        }
        return currentTimestamp;
    }

    private long getTimestamp() {
        return System.currentTimeMillis();
    }

    // 测试
    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
        SnowflakeIdGenerator idGenerator = new SnowflakeIdGenerator(1);
        for (int i = 0; i < 10; i++) {
            long id = idGenerator.nextId();
            System.out.println("Generated Id: " + id);
        }
    }
}
