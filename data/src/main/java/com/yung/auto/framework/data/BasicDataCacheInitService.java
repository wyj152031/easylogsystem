package com.yung.auto.framework.data;

import com.yung.auto.framework.cache.local.cacheinit.CacheInitService;
import com.yung.auto.framework.data.model.Student;
import com.yung.auto.framework.utility.agent.LogManager;
import com.yung.auto.framework.utility.trace.ILog;
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
    ILog logger = LogManager.getLogger(BasicDataCacheInitService.class);

    @Autowired
    private StudentCacheRepositoryDemo studentCacheRepositoryDemo;

    @Override
    public boolean init() {
        Student st = studentCacheRepositoryDemo.getData("1");
        logger.info("Demo 缓存数据：" + st.toString());
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
