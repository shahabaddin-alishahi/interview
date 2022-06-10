package com.signicat.interview.config.exception;

public class TokenExpiredException extends SecurityException {
    {
        super.logStackTrace = ExceptionSetting.StackTraceDecision.PARTIAL;
    }
}
