package com.signicat.interview.repository;

import com.signicat.interview.domain.AuditLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface AuditLogRepository extends JpaRepository<AuditLog,Long> {

    @Query("select l from AuditLog l where (:fromDate is null or l.createDate >= :fromDate) and " +
            "(:toDate is null or l.createDate <= :toDate) and " +
            "(:toDate is null or l.createDate = :toDate) and " +
            "(:operatorName is null or l.memberTitle = :operatorName) and " +
            "(:operatorId is null or l.memberId = :operatorId) and " +
            "(:actionType is null or l.actionObjectType = :actionType) and " +
            "(:actionObject is null or l.actionObjectType = :actionObject) and " +
            "(:actionObjectId is null or l.actionObjectId = :actionObjectId)" +
            " order by l.createDate desc")
    Page<AuditLog> find(@Param("fromDate") LocalDateTime fromDate,
                        @Param("toDate") LocalDateTime toDate,
                        @Param("operatorName") String operatorName,
                        @Param("operatorId") String operatorId,
                        @Param("actionType") String actionType,
                        @Param("actionObject") String actionObject,
                        @Param("actionObjectId") String actionObjectId,
                        Pageable pageable);
}
