package com.yung.auto.framework.data;

import com.yung.auto.framework.cache.local.cacheinit.CacheInitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author wangyujing
 * @date 2018/5/8.
 */
@Service
public class BasicDataCacheInitService implements CacheInitService {

    @Autowired
    private StudentCacheRepositoryDemo studentCacheRepositoryDemo;

    @Override
    public boolean init() {
        studentCacheRepositoryDemo.getData("1");
        return true;
    }

    /**
     * 采用异步线程执行方式
     *
     * @return
     */
    private boolean internalInit() {
        List<Runnable> tasks = new ArrayList<>();
        CountDownLatch latch = new CountDownLatch(1);
        tasks.add(() -> {
            studentCacheRepositoryDemo.getData("1");
            latch.countDown();
        });
        execute(tasks);
        try {
            latch.await();
        } catch (InterruptedException e) {
            return false;
        }
        return true;
    }

    private void execute(List<Runnable> tasks) {
        for (Runnable task : tasks) {
            ThreadPoolManager.submit(task);
        }
    }
}
