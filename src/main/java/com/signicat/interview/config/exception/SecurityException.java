package com.signicat.interview.config.exception;

public class SecurityException extends BusinessException {
    {
        super.logStackTrace = ExceptionSetting.StackTraceDecision.FULL;
    }
}
