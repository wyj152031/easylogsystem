package com.yung.auto.framework.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author yungwang
 * @date 2020/4/11.
 */
@Configuration
public class WorkContextAutoConfigure {

    @Autowired
    private ApplicationContext applicationContext;

    private static final WorkContext emptyWorkContext = new DefaultWorkContext();

    @Bean
    public WorkContextFactory getWorkContextFactory() {
        return WorkContextFactory.INSTANCE;
    }

    @Bean
    public WorkContext getWorkContext() {
        // jdk 代理
        WorkContext workContext = (WorkContext) Proxy.newProxyInstance(this.getClass().getClassLoader(),
                new Class[]{WorkContext.class}, (o, method, args) -> {
                    WorkContextFactory workContextFactory = WorkContextFactory.INSTANCE;
                    WorkContext wc = workContextFactory.getCurrent();
                    return wc != null ? method.invoke(wc, args) : method.invoke(emptyWorkContext, args);
                });
        // cglib代理
//        workContext = getCglibWorkContext();
        return workContext;
    }

    private WorkContext getCglibWorkContext() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(WorkContext.class);
        enhancer.setCallback(new CglibProxy());
        return (WorkContext) enhancer.create();
    }

    class CglibProxy implements MethodInterceptor {

        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            WorkContextFactory workContextFactory = WorkContextFactory.INSTANCE;
            WorkContext wc = workContextFactory.create();
            return methodProxy.invokeSuper(wc, objects);
        }
    }
}
