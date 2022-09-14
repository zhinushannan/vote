package club.kwcoder.vote.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DCSLockUtils {

    private final Integer defaultExpire = 60;

    @Autowired
    private RedisUtils redisUtils;

    /**
     * 获取锁，锁的有效时间为60秒
     *
     * @param lockName 锁名
     * @param timeout  超时时间（单位毫秒）
     * @return 返回是否获取成功
     */
    public boolean lock(String lockName, int timeout) {
        return lock(lockName, timeout, defaultExpire);
    }


    /**
     * 获取锁
     *
     * @param lockName 锁名
     * @param expire   锁的有效时间，若为0，则使用默认时间，即60秒
     * @return 返回是否获取成功
     */
    public boolean lock(String lockName, int timeout, int expire) {
        if (expire <= 0) {
            expire = defaultExpire;
        }

        int time = 0;
        while (null != redisUtils.get("lock:" + lockName)) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            time += 50;
            if (time >= timeout) {
                return false;
            }
        }
        // 获取锁成功
        redisUtils.set("lock:" + lockName, 1, expire);
        return true;
    }

    /**
     * 释放锁
     *
     * @param lockName 锁名
     */
    public void unlock(String lockName) {
        redisUtils.del("lock:" + lockName);
    }

}
