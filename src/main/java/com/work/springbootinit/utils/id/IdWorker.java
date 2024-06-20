package com.work.springbootinit.utils.id;

/**
 * snowflake的改造版,基于原型1.0版本修改扩展
 * 支持64个机器位，年限支持 96年，每毫秒序列支持4096位
 *
 * @author lingj29786
 * @since 2021-02-02
 */
public class IdWorker {

    /**
     * 机器Id所占的位数 0 - 63
     */
    public final static long WORKER_ID_BITS = 6L;
    /**
     * 工作机器ID(0~63)
     */
    private static long workerId;
    /**
     * 占位前缀，将所有的id补全到18位数字，避免印象历史表结构只支持到18位数字
     * 为了避免与之前版本的前缀重复所以设置超出之前的最高设置量
     */
    private static long PREFIX_NUM = 200000000000000000L;
    /**
     * 开始时间截 (2013-02-25) 服务一旦运行过之后不能修改。会导致ID生成重复
     * 原先时间戳
     */
    private final long TWEPOCH = 1361753741828L;
    /**
     * 支持的最大机器id，结果是63 (这个移位算法可以很快的计算出几位二进制数所能表示的最大十进制数)
     */
    private final long MAX_WORKER_ID = -1L ^ (-1L << WORKER_ID_BITS);
    /**
     * 序列在id中占的位数
     */
    private final long SEQUENCE_BITS = 12L;
    /**
     * 机器ID向左移12位
     */
    private final long WORKER_ID_SHIFT = SEQUENCE_BITS;
    /**
     * 时间截向左移22位(5+5+12)
     */
    private final long TIMESTAMP_LEFT_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS;
    /**
     * 生成序列的掩码，这里为4095 (0b111111111111=0xfff=4095)
     */
    private final long SEQUENCE_MASK = -1L ^ (-1L << SEQUENCE_BITS);
    /**
     * 毫秒内序列(0~4095)
     */
    private long sequence = 0L;
    /**
     * 上次生成ID的时间截
     */
    private long lastTimestamp = -1L;

    /**
     * 构造函数
     *
     * @param workerId 工作ID (0~63)
     */
    public IdWorker(long workerId) {
        if (workerId > MAX_WORKER_ID || workerId < 0) {
            throw new IllegalArgumentException(String.format("机器ID必须小于 %d 且大于 0", MAX_WORKER_ID));
        }
        IdWorker.workerId = workerId;
    }

    /**
     * 构造函数
     */
    public IdWorker() {
        IdWorker.workerId = 0;
    }

    /**
     * 获得下一个ID (该方法是线程安全的)
     *
     * @return SnowFlakeId
     */
    public synchronized long nextId() {
        long timestamp = timeGen();

        // 如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过这个时候应当抛出异常
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(
                    String.format("系统时间戳发生了回退，请检查系统时间是否设置正确！【回退了 %d 毫秒数】", lastTimestamp - timestamp));
        }

        // 如果是同一时间生成的，则进行毫秒内序列
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & SEQUENCE_MASK;
            // 毫秒内序列溢出
            if (sequence == 0) {
                // 阻塞到下一个毫秒,获得新的时间戳
                timestamp = tilNextMillis(lastTimestamp);
            }
        }
        // 时间戳改变，毫秒内序列重置
        else {
            sequence = 0L;
        }

        // 上次生成ID的时间截
        lastTimestamp = timestamp;

        // 移位并通过或运算拼到一起组成18位十进制的ID
        return (((timestamp - TWEPOCH) << TIMESTAMP_LEFT_SHIFT)
                | (workerId << WORKER_ID_SHIFT)
                | sequence) + PREFIX_NUM;
    }

    /**
     * 阻塞到下一个毫秒，直到获得新的时间戳
     *
     * @param lastTimestamp 上次生成ID的时间截
     * @return 当前时间戳
     */
    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 返回以毫秒为单位的当前时间
     *
     * @return 当前时间(毫秒)
     */
    protected long timeGen() {
        return System.currentTimeMillis();
    }

    public long getWorkerId() {
        return workerId;
    }

    public void setWorkerId(long workerId) {
        IdWorker.workerId = workerId;
    }
}
