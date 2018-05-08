package com.yung.auto.framework.cache.local.cacherefresh;

import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.util.MethodInvoker;

import java.lang.reflect.InvocationTargetException;

/**
 * @author wangyujing
 * @date 2018/5/7.
 */
public class CacheInvocationImpl implements CacheInvocation {

    private String[] cacheNames;
    private Object key;
    private Object[] args;
    private Object target;
    private MethodSignature signature;

    public CacheInvocationImpl(String[] cacheNames, Object key, Object target, MethodSignature signature, Object[] args) {
        this.cacheNames = cacheNames;
        this.key = key;
        this.target = target;
        this.args = args;
        this.signature = signature;
    }

    /**
     * 调用切点方法
     *
     * @param key
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     * @throws ClassNotFoundException
     */
    @Override
    public Object invoke(Object key) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, ClassNotFoundException {
        MethodInvoker invoker = new MethodInvoker();
        invoker.setArguments(args);
        invoker.setTargetMethod(signature.getMethod().getName());
        invoker.setTargetObject(target);
        invoker.prepare();
        return invoker.invoke();
    }
}
