package com.yung.auto.framework.foundation.spi.parser;

/**
 * @author yungwang
 * @date 2020/4/11.
 */
public class ParserException extends Exception {
    public ParserException(String message) {
        super(message);
    }

    public ParserException(String message, Throwable cause) {
        super(message, cause);
    }
}
