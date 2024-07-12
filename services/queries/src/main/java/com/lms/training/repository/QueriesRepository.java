package com.lms.training.repository;

import com.lms.training.entity.Queries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QueriesRepository extends JpaRepository<Queries, Long> {

}
