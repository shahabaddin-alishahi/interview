package com.signicat.interview.repository;

import com.signicat.interview.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {

    Optional<Member> findByUsername(String username);

    List<Member> findAllByIsWorking(Boolean isWorking);

    @Query("select m from MEMBER m where " +
            "(:nationalCode is null or m.nationalCode = :nationalCode) and " +
            "(:plainName is null or concat(m.firstName, ' ', m.lastName) like %:plainName% or " +
            "concat(m.firstName, ' ', m.lastName) like %:persianName% or " +
            "concat(m.firstName, ' ', m.lastName) like %:arabicName%)")
    Page<Member> findAllByNationalCodeAndFirstNameAndLastName(@Param("nationalCode") String nationalCode,
                                                              @Param("plainName") String plainName,
                                                              @Param("persianName") String persianName,
                                                              @Param("arabicName") String arabicName,
                                                              Pageable pageable);
}
