package com.yung.auto.framework.context;

/**
 * @author yungwang
 * @date 2020/4/11.
 */
public class WorkContextFactory {
    private final ThreadLocal<WorkContext> slot = new InheritableThreadLocal<>();

    public static WorkContextFactory INSTANCE = new WorkContextFactory();

    private WorkContextFactory() {

    }

    /**
     * 从当前线程槽获取{ @link WorkContext} 实例
     *
     * @return
     */
    public WorkContext getCurrent() {
        return slot.get();
    }

    /**
     * 创建 {@link WorkContext} 的新实例，并保存到当前线程的上下文中。
     *
     * @return
     */
    public WorkContext create() {
        WorkContext workContext = new DefaultWorkContext();
        slot.set(workContext);
        return workContext;
    }

    /**
     * 释放线程槽的数据。便于 GC 回收，防止内存泄漏
     */
    public void release() {
        slot.remove();
    }
}
