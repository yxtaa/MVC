package com.mvc.exceptions;

/**
 * 病案主页信息不存在异常
 */
public class NotFoundBazyException extends Exception{
    public NotFoundBazyException() {
    }

    public NotFoundBazyException(String message) {
        super(message);
    }
}
