package com.signicat.interview.config.exception;

public class InvalidTokenException extends SecurityException {
    {
        super.logStackTrace = ExceptionSetting.StackTraceDecision.FIRST;
    }
}
