package com.signicat.interview.config.exception;

public class NotAcceptableException extends BusinessException {
    {
        super.logStackTrace = ExceptionSetting.StackTraceDecision.FIRST;
    }
}
