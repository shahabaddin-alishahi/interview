package com.signicat.interview.config.exception;

public class UnAuthorizedException extends SecurityException {
    {
        super.logStackTrace = ExceptionSetting.StackTraceDecision.PARTIAL;
    }
}
