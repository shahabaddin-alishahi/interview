package com.signicat.interview.service.impl;

import com.signicat.interview.domain.AuditLog;
import com.signicat.interview.repository.AuditLogRepository;
import com.signicat.interview.service.AuditLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class AuditLogServiceImpl implements AuditLogService {

    private final AuditLogRepository auditLogRepository;

    public AuditLog save(String memberTitle, Long memberId, String requestUrl,
                         String actionMethod, String actionObjectType, String actionObjectId,
                         String responseStatusCode) {
        return auditLogRepository.save(AuditLog.builder()
                .memberTitle(memberTitle)
                .memberId(memberId)
                .requestUrl(requestUrl)
                .actionMethod(actionMethod)
                .actionObjectType(actionObjectType)
                .actionObjectId(actionObjectId)
                .responseStatusCode(responseStatusCode)
                .build());
    }

    @Async
    @Override
    public void save(AuditLog auditLog) {
        auditLogRepository.save(auditLog);
    }

    @Override
    public Page<AuditLog> find(LocalDateTime fromDate,
                               LocalDateTime toDate,
                               String operatorName,
                               String operatorId,
                               String actionType,
                               String actionObject,
                               String actionObjectId,
                               Integer PageIndex, Integer pageSize) {
        return auditLogRepository.find(fromDate, toDate, operatorName, operatorId, actionType,
                actionObject, actionObjectId, PageRequest.of(PageIndex, pageSize));
    }

    @Override
    public void getCumulativeAuditLogActionBased(LocalDateTime fromDate, LocalDateTime toDate) {

    }

    @Override
    public void getCumulativeAuditLogStatusBased(LocalDateTime fromDate, LocalDateTime toDate) {

    }
}
