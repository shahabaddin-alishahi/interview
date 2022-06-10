package com.signicat.interview.service;

import com.signicat.interview.domain.AuditLog;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

public interface AuditLogService {

    AuditLog save(String memberTitle, Long memberId, String requestUrl,
                  String actionMethod, String actionObjectType, String actionObjectId,
                  String responseStatusCode);

    void save(AuditLog auditLog);

    Page<AuditLog> find(LocalDateTime fromDate,
                        LocalDateTime toDate,
                        String operatorName,
                        String operatorId,
                        String actionType,
                        String actionObject,
                        String actionObjectId,
                        Integer PageIndex, Integer pageSize);

    void getCumulativeAuditLogActionBased(LocalDateTime fromDate,
                                          LocalDateTime toDate);

    void getCumulativeAuditLogStatusBased(LocalDateTime fromDate,
                                          LocalDateTime toDate);

}