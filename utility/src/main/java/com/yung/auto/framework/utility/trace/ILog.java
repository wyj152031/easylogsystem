package com.yung.auto.framework.utility.trace;

import java.util.Map;

/**
 * Created by wangyujing on 2018/1/26.
 */
public interface ILog {
    /**
     * 记录一条DEBUG<see cref="LogLevel.DEBUG"/>级别日志。
     *
     * @param title log title.
     * @param message log message.
     */
    void debug(String title, String message);

    /**
     * 记录一条DEBUG<see cref="LogLevel.DEBUG"/>级别的例外日志。
     *
     * @param title log title.
     * @param throwable a Throwable instance.
     */
    void debug(String title, Throwable throwable);

    /**
     * 记录一条DEBUG<see cref="LogLevel.DEBUG"/>级别的日志，附加健值对形式的额外信息。
     *
     * @param title log title
     * @param message log message
     * @param attrs kv pairs
     */
    void debug(String title, String message, Map<String, String> attrs);

    /**
     * 记录一条DEBUG<see cref="LogLevel.DEBUG"/>级别的例外日志，附加健值对形式的额外信息。
     *
     * @param title log title
     * @param throwable a Throwable instance to log
     * @param attrs kv pairs
     */
    void debug(String title, Throwable throwable, Map<String, String> attrs);


    /**
     * 记录一条DEBUG<see cref="LogLevel.DEBUG"/>级别的日志。
     *
     * @param message log message.
     */
    void debug(String message);

    /**
     * 记录一条DEBUG<see cref="LogLevel.DEBUG"/>级别的例外日志。
     *
     * @param throwable a Throwable instance.
     */
    void debug(Throwable throwable);

    /**
     * 记录一条DEBUG<see cref="LogLevel.DEBUG"/>级别的日志,附加健值对形式的额外信息。
     *
     * @param message log message
     * @param attrs kv pairs
     */
    void debug(String message, Map<String, String> attrs);

    /**
     * 记录一条DEBUG<see cref="LogLevel.DEBUG"/>级别的例外日志,附加健值对形式的额外信息。
     *
     * @param throwable a Throwable instance
     * @param attrs kv pairs
     */
    void debug(Throwable throwable, Map<String, String> attrs);


    /**
     * 记录一条ERROR<see cref="LogLevel.ERROR"/>级别日志。
     *
     * @param title log title.
     * @param message log message.
     */
    void error(String title, String message);

    /**
     * 记录一条ERROR<see cref="LogLevel.ERROR"/>级别的例外日志。
     *
     * @param title log title.
     * @param throwable a Throwable instance.
     */
    void error(String title, Throwable throwable);

    /**
     * 记录一条ERROR<see cref="LogLevel.ERROR"/>级别的日志, 附加健值对形式的额外信息。
     *
     * @param title log title
     * @param message log message
     * @param attrs kv pairs
     */
    void error(String title, String message, Map<String, String> attrs);

    /**
     * 记录一条ERROR<see cref="LogLevel.ERROR"/>级别的例外日志，附加健值对形式的额外信息。
     *
     * @param title log title
     * @param throwable a Throwable instance to log
     * @param attrs kv pairs
     */
    void error(String title, Throwable throwable, Map<String, String> attrs);


    /**
     * 记录一条ERROR<see cref="LogLevel.ERROR"/>级别的日志。
     *
     * @param message log message.
     */
    void error(String message);

    /**
     * 记录一条ERROR<see cref="LogLevel.ERROR"/>级别的例外日志。
     *
     * @param throwable a Throwable instance.
     */
    void error(Throwable throwable);

    /**
     * 记录一条ERROR<see cref="LogLevel.ERROR"/>级别的日志, 附加健值对形式的额外信息。
     *
     * @param message log message
     * @param attrs kv pairs
     */
    void error(String message, Map<String, String> attrs);

    /**
     * 记录一条ERROR<see cref="LogLevel.ERROR"/>级别的例外日志,附加健值对形式的额外信息。
     *
     * @param throwable a Throwable instance
     * @param attrs kv pairs
     */
    void error(Throwable throwable, Map<String, String> attrs);

    /**
     * 记录一条FATAL<see cref="LogLevel.FATAL"/>级别的日志。
     *
     * @param title log title.
     * @param message log message.
     */
    void fatal(String title, String message);

    /**
     * 记录一条FATAL<see cref="LogLevel.FATAL"/>级别的例外日志。
     *
     * @param title log title.
     * @param throwable a Throwable instance.
     */
    void fatal(String title, Throwable throwable);

    /**
     * 记录一条FATAL<see cref="LogLevel.FATAL"/>级别的日志, 附加健值对形式的额外信息。
     *
     * @param title log title
     * @param message log message
     * @param attrs kv pairs
     */
    void fatal(String title, String message, Map<String, String> attrs);

    /**
     * 记录一条FATAL<see cref="LogLevel.FATAL"/>级别的例外日志，附加健值对形式的额外信息。
     *
     * @param title log title
     * @param throwable a Throwable instance to log
     * @param attrs kv pairs
     */
    void fatal(String title, Throwable throwable, Map<String, String> attrs);


    /**
     * 记录一条FATAL<see cref="LogLevel.FATAL"/>级别的日志。
     *
     * @param message log message.
     */
    void fatal(String message);

    /**
     * 记录一条FATAL<see cref="LogLevel.FATAL"/>级别的例外日志。
     *
     * @param throwable a Throwable instance.
     */
    void fatal(Throwable throwable);

    /**
     * 记录一条FATAL<see cref="LogLevel.FATAL"/>级别的日志，附加健值对形式的额外信息。
     *
     * @param message log message
     * @param attrs kv pairs
     */
    void fatal(String message, Map<String, String> attrs);


    /**
     * 记录一条FATAL<see cref="LogLevel.FATAL"/>级别的例外日志,附加健值对形式的额外信息。
     *
     * @param throwable a Throwable instance
     * @param attrs kv pairs
     */
    void fatal(Throwable throwable, Map<String, String> attrs);

    /**
     * 记录一条INFO<see cref="LogLevel.INFO"/>级别的日志。
     *
     * @param title log title.
     * @param message log message.
     */
    void info(String title, String message);

    /**
     * 记录一条INFO<see cref="LogLevel.INFO"/>级别的例外日志。
     *
     * @param title log title.
     * @param throwable a Throwable instance.
     */
    void info(String title, Throwable throwable);

    /**
     * 记录一条INFO<see cref="LogLevel.INFO"/>级别的日志，附加健值对形式的额外信息。
     *
     * @param title log title
     * @param message log message
     * @param attrs kv pairs
     */
    void info(String title, String message, Map<String, String> attrs);

    /**
     * 记录一条INFO<see cref="LogLevel.INFO"/>级别的例外日志，附加健值对形式的额外信息。
     *
     * @param title log title
     * @param throwable a Throwable instance to log
     * @param attrs kv pairs
     */
    void info(String title, Throwable throwable, Map<String, String> attrs);


    /**
     * 记录一条INFO<see cref="LogLevel.INFO"/>级别的日志。
     *
     * @param message log message.
     */
    void info(String message);

    /**
     * 记录一条INFO<see cref="LogLevel.INFO"/>级别的例外日志。
     *
     * @param throwable a Throwable instance.
     */
    void info(Throwable throwable);

    /**
     * 记录一条INFO<see cref="LogLevel.INFO"/>级别的日志,附加健值对形式的额外信息。
     *
     * @param message log message
     * @param attrs kv pairs
     */
    void info(String message, Map<String, String> attrs);

    /**
     * 记录一条INFO<see cref="LogLevel.INFO"/>级别的例外日志,附加健值对形式的额外信息。
     *
     * @param throwable a Throwable instance
     * @param attrs kv pairs
     */
    void info(Throwable throwable, Map<String, String> attrs);

    /**
     * 记录一条WARN<see cref="LogLevel.WARN"/>级别的日志。
     *
     * @param title log title.
     * @param message log message.
     */
    void warn(String title, String message);

    /**
     * 记录一条WARN<see cref="LogLevel.WARN"/>级别的例外日志。
     *
     * @param title log title.
     * @param throwable a Throwable instance.
     */
    void warn(String title, Throwable throwable);

    /**
     * 记录一条WARN<see cref="LogLevel.WARN"/>级别的日志,附加健值对形式的额外信息。
     *
     * @param title log title
     * @param message log message
     * @param attrs kv pairs
     */
    void warn(String title, String message, Map<String, String> attrs);


    /**
     * 记录一条WARN<see cref="LogLevel.WARN"/>级别的例外日志，附加健值对形式的额外信息。
     *
     * @param title log title
     * @param throwable a Throwable instance to log
     * @param attrs kv pairs
     */
    void warn(String title, Throwable throwable, Map<String, String> attrs);


    /**
     * 记录一条WARN<see cref="LogLevel.WARN"/>级别的日志。
     *
     * @param message log message.
     */
    void warn(String message);

    /**
     * 记录一条WARN<see cref="LogLevel.WARN"/>级别的例外日志。
     *
     * @param throwable a Throwable instance.
     */
    void warn(Throwable throwable);

    /**
     * 记录一条WARN<see cref="LogLevel.WARN"/>级别的日志,附加健值对形式的额外信息。
     *
     * @param message log message
     * @param attrs kv pairs
     */
    void warn(String message, Map<String, String> attrs);

    /**
     * 记录一条WARN<see cref="LogLevel.WARN"/>级别的例外日志,附加健值对形式的额外信息。
     *
     * @param throwable a Throwable instance
     * @param attrs kv pairs
     */
    void warn(Throwable throwable, Map<String, String> attrs);
}
