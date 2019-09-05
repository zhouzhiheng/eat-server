package com.opsigte.e.common.core.utils;


/**
 * <p> @ClassName: <i>SnowFlake</i></p>
 * <p> @Description: <i>基于雪花算法生成的分布式系统下的唯一id</i></p>
 * <p> @Author: <i>opsigte</i></p>
 * <p> @Created date: <i>2019/9/3 15:26</i></p>
 * <p> @Version: <i>V1.0.0</i> </p>
 */
public class SnowFlakeIdWorker {

    // ==============================Fields===========================================
    /** 开始时间截 (2016-01-01) 毫秒级时间戳 */
    private final long twepoch = 1451577600000L;

    /** 机器id所占的位数 */
    private final long workerIdBits = 3L;

    /** 数据标识id所占的位数 */
    private final long datacenterIdBits = 3L;

    /**
     * 支持的最大机器id，结果是7 (这个移位算法可以很快的计算出几位二进制数所能表示的最大十进制数)
     * -1L ^ (-1L << n)表示占n个bit的数字的最大值是多少。举个栗子：-1L ^ (-1L << 2)等于10进制的3 ，即二进制的11表示十进制3。
     */
    private final long maxWorkerId = -1L ^ (-1L << workerIdBits);

    /**
     * 支持的最大数据标识id，结果是7
     *  -1L 原码 1000 0001   原码是在符号位加标志  正数 0 负数1
     *     反码1111 1110   正数原码是其本身 负数是符号位不变其余位取反
     *     补码1111 1111   原码-1再取反
     *  -1L << 3 1111 1000
     *  -1L ^ (1111 1000)   1111 1000 ^ 1111 1111 结果为7
     * */
    private final long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);

    /** 序列在id中占的位数 */
    private final long sequenceBits = 14L;

    /** 机器ID向左移14位 */
    private final long workerIdShift = sequenceBits;

    /** 数据标识id向左移17位(14+3) */
    private final long datacenterIdShift = sequenceBits + workerIdBits;

    /** 时间截向左移20位(3+3+14) 机器码+计数器 */
    private final long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;

    /** 生成序列的掩码，这里为16383  */
    private final long sequenceMask = -1L ^ (-1L << sequenceBits);

    /** 工作机器ID(0~7) */
    private long workerId;

    /** 数据中心ID(0~7) */
    private long datacenterId;

    /** 毫秒内序列(0~16383) */
    private long sequence = 0L;

    /** 上次生成ID的时间截 */
    private long lastTimestamp = -1L;



    /**
     * 类级的内部类，也就是静态的成员式内部类，该内部类的实例与外部类的实例没有绑定关系，而且只有被调用到才会装载，从而实现了延迟加载
     *
     */
    private static class SnowFlakeIdWorkerHolder {
        private static SnowFlakeIdWorker instance = new SnowFlakeIdWorker();
    }

    private SnowFlakeIdWorker() {}

    public static SnowFlakeIdWorker getInstance(){
        return SnowFlakeIdWorkerHolder.instance;
    }

    // ==============================Methods==========================================
    /**
     * 获得下一个ID (该方法是线程安全的)
     * @return SnowflakeId
     */
    public synchronized long nextId() {
        long timestamp = timeGen();

        //如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过这个时候应当抛出异常
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(
                String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }

        //如果是同一时间生成的，则进行毫秒内序列
        if (lastTimestamp == timestamp) {
            //序列号 = 上次序列号+1 与 生成序列的掩码
            sequence = (sequence + 1) & sequenceMask;
            //毫秒内序列溢出
            if (sequence == 0) {
                //阻塞到下一个毫秒,获得新的时间戳
                timestamp = tilNextMillis(lastTimestamp);
            }
        }
        //时间戳改变，毫秒内序列重置
        else {
            sequence = 0L;
        }

        //上次生成ID的时间截（保存时间戳）
        lastTimestamp = timestamp;

        //移位并通过或运算拼到一起组成64位的ID(或运算)
        return ((timestamp - twepoch) << timestampLeftShift) //当前时间戳-开始时间戳 左移22位相当于两个差的22位
            | (datacenterId << datacenterIdShift) //数据中心Id左移17位
            | (workerId << workerIdShift) // 机器id左移12位
            | sequence; //毫秒内序列
    }

    /**
     * 阻塞到下一个毫秒，直到获得新的时间戳
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
     * @return 当前时间(毫秒)
     */
    protected long timeGen() {
        return System.currentTimeMillis();
    }


    /**
     * 获取配置文件中机器相关信息并返回对象
     * @return
     */
    public static SnowFlakeIdWorker getSnowFlakeIdWorker(){
        //加载配置文件
        String workId = PublicReadConfigUtil.readConfig("workId");
        String dataId = PublicReadConfigUtil.readConfig("dataId");

        SnowFlakeIdWorker instance = SnowFlakeIdWorker.getInstance();
        instance.workerId = Long.parseLong(workId);
        instance.datacenterId = Long.parseLong(dataId);
        return instance;
    }

    /**
     * 获取id
     */
    public static Long getId(){
        SnowFlakeIdWorker snowFlakeIdWorker = getSnowFlakeIdWorker();
        return snowFlakeIdWorker.nextId();
    }
}
