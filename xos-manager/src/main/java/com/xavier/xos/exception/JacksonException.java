package com.xavier.xos.exception;

/**
 * @author wuyanfeng
 * 2019/4/10 22:35
 */
public class JacksonException extends FormativeException {
    public JacksonException() {
        super();
    }

    public JacksonException(String message) {
        super(message);
    }

    public JacksonException(Throwable cause) {
        super(cause);
    }

    public JacksonException(String format, Object... arguments) {
        super(format, arguments);
    }
}
