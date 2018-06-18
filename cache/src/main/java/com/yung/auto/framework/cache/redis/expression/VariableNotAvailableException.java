package com.yung.auto.framework.cache.redis.expression;

import org.springframework.expression.EvaluationException;

/**
 * Created by wangyj on 2018/6/18.
 */
@SuppressWarnings("serial")
public class VariableNotAvailableException extends EvaluationException {


    private final String name;

    public VariableNotAvailableException(String name) {
        super("Variable '" + name + "' is not available");
        this.name = name;
    }


    public String getName() {
        return this.name;
    }
}
