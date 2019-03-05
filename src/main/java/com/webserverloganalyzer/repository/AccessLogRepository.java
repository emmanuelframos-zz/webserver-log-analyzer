package com.webserverloganalyzer.repository;

import com.webserverloganalyzer.domain.AccessLog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessLogRepository extends CrudRepository<AccessLog, Long> { }