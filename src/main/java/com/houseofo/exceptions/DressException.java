package com.houseofo.exceptions;

public class DressException extends Exception{
    public DressException() {
    }

    public DressException(String message) {
        super(message);
    }

    public DressException(String message, Throwable cause) {
        super(message, cause);
    }

    public DressException(Throwable cause) {
        super(cause);
    }
}
