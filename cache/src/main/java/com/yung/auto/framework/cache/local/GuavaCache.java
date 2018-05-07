package com.yung.auto.framework.cache.local;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheBuilderSpec;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;

import java.io.Serializable;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

/**
 * @author wangyujing
 * @date 2018/5/7.
 */
public class GuavaCache implements Cache, Serializable {
    private static final Object NULL_OBJECT = new NullObject();
    private final com.google.common.cache.Cache nativeCache;
    private final String name;
    private final boolean allowNullValue;

    public GuavaCache(CacheBuilder cacheBuilder, String name, boolean allowNullVaule) {
        this.nativeCache = cacheBuilder.build();
        this.name = name;
        this.allowNullValue = allowNullVaule;
    }

    public GuavaCache(CacheBuilderSpec spec, String name, boolean allowNullVaule) {
        this(CacheBuilder.from(spec).recordStats(), name, allowNullVaule);
    }

    public GuavaCache(String name, boolean allowNullValues) {
        this(CacheBuilder.newBuilder().recordStats(), name, allowNullValues);
    }

    public GuavaCache() {
        this.name = "";
        this.allowNullValue = true;
        this.nativeCache = null;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Object getNativeCache() {
        return this.nativeCache;
    }

    @Override
    public ValueWrapper get(Object key) {
        Object value = this.nativeCache.getIfPresent(key);
        return value != null ? new SimpleValueWrapper(wrapperValue(value)) : null;
    }

    /**
     * Convert the given value from the internal store to a user value
     * returned from the get method (adapting {@code null}).
     *
     * @param value the store value
     * @return the value to return to the user
     */
    private Object wrapperValue(Object value) {
        if (this.allowNullValue && NULL_OBJECT.equals(value)) {
            return NULL_OBJECT;
        }
        return value;
    }

    /**
     * Convert the given user value, as passed into the put method,
     * to a value in the internal store (adapting {@code null}).
     *
     * @param userValue the given user value
     * @return the value to store
     */
    protected Object toWarpperValue(Object userValue) {
        if (this.allowNullValue && userValue == null) {
            return null;
        }
        return userValue;
    }

    @Override
    public <T> T get(Object key, Class<T> type) {
        Object value = wrapperValue(this.nativeCache.getIfPresent(key));
        if (value != null && type != null && !type.isInstance(value)) {
            throw new IllegalStateException("Cache Value is not the type of [" + type.getName() + "]: " + value);
        }
        return (T) value;
    }

    @Override
    public <T> T get(Object key, Callable<T> valueLoader) {
        try {
            return (T) this.wrapperValue(this.nativeCache.get(key, new Callable<Object>() {
                @Override
                public Object call() {
                    try {
                        return GuavaCache.this.toWarpperValue(valueLoader.call());
                    } catch (Exception e) {
                        return null;
                    }
                }
            }));
        } catch (ExecutionException var4) {
            throw new ValueRetrievalException(key, valueLoader, var4.getCause());
        }
    }

    @Override
    public void put(Object key, Object value) {
        if (value != null && key != null) {
            this.nativeCache.put(key, this.toWarpperValue(value));
//            CacheManager.add(new ViGuavaCacheWatcher(key.toString(), this.nativeCache));
        }
    }

    @Override
    public ValueWrapper putIfAbsent(Object key, Object value) {
        try {
            GuavaCache.PutIfAbsentCallable callable = new GuavaCache.PutIfAbsentCallable(value);
            Object result = this.nativeCache.get(key, callable);
            return callable.called ? null : this.toValueWrapper(result);
        } catch (ExecutionException var5) {
            throw new IllegalStateException(var5);
        }
    }

    @Override
    public void evict(Object key) {
        this.nativeCache.invalidate(key);
    }

    @Override
    public void clear() {
        this.nativeCache.invalidateAll();
    }

    /**
     * Wrap the given store value with a {@link SimpleValueWrapper}, also going
     * through {@link #wrapperValue} conversion. Useful for {@link #get(Object)}
     * and {@link #putIfAbsent(Object, Object)} implementations.
     *
     * @param storeValue the original value
     * @return the wrapped value
     */
    protected ValueWrapper toValueWrapper(Object storeValue) {
        return (storeValue != null ? new SimpleValueWrapper(wrapperValue(storeValue)) : null);
    }

    private static class NullObject implements Serializable {
    }

    private class PutIfAbsentCallable implements Callable<Object> {
        private final Object value;
        private boolean called;

        public PutIfAbsentCallable(Object value) {
            this.value = value;
        }

        @Override
        public Object call() {
            this.called = true;
            return GuavaCache.this.toWarpperValue(this.value);
        }
    }
}
