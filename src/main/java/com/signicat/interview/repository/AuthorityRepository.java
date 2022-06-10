package com.signicat.interview.repository;

import com.signicat.interview.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority,Long> {

}
