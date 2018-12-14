package com.webserverloganalyzer.repository;

import com.webserverloganalyzer.api.v1.dto.LogAnalyzerRequestDTO;
import com.webserverloganalyzer.api.v1.dto.LogAnalyzerResponseDTO;
import com.webserverloganalyzer.domain.AccessLog;
import com.webserverloganalyzer.domain.AccessLogFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

@Repository
public class AccessLogFileRepository {

    private final Logger logger = LoggerFactory.getLogger(AccessLogFileRepository.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public BigInteger save(AccessLogFile accessLogFile) {
        GeneratedKeyHolder holder = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            PreparedStatement statement = con.prepareStatement("INSERT INTO log_analyzer.access_log_file(file_path, size) VALUES (?, ?) ", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, accessLogFile.getFilePath());
            statement.setLong(2, accessLogFile.getSize().longValue());
            return statement;
        }, holder);

        return BigInteger.valueOf(holder.getKey().longValue());
    }

    @Transactional
    public BigInteger batchInsert(AccessLogFile accessLogFile) {

        BigInteger accessLogFileId = save(accessLogFile);

        jdbcTemplate.batchUpdate("INSERT INTO log_analyzer.access_log(request_date, ip, status, request, user_agent, access_log_file_id)" +
                        " VALUES (?, ?, ?, ?, ?, ?)",
                new BatchPreparedStatementSetter() {

                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        AccessLog accessLog = accessLogFile.getAccessLogs().get(i);
                        ps.setString(1, accessLog.getRequestDate());
                        ps.setString(2, accessLog.getIp());
                        ps.setString(3, accessLog.getStatus());
                        ps.setString(4, accessLog.getRequest());
                        ps.setString(5, accessLog.getUserAgent());
                        ps.setInt(6, accessLogFileId.intValue());
                    }

                    @Override
                    public int getBatchSize() {
                        logger.info("Batch Size {}", accessLogFile.getAccessLogs().size());
                        return accessLogFile.getAccessLogs().size();
                    }
                });

        return accessLogFileId;
    }

    public List<LogAnalyzerResponseDTO> filterIPs(BigInteger accessLogFileId, LogAnalyzerRequestDTO logAnalyzerRequestDTO) {

        Date endDate = logAnalyzerRequestDTO.duration.getEndDate(logAnalyzerRequestDTO.startDate);

        logger.info("Filtering for --StartDate {}  --EndDate {}  --Threshold {}" , logAnalyzerRequestDTO.startDate, endDate, logAnalyzerRequestDTO.threshold);

        return jdbcTemplate.query("SELECT ip, count(*) FROM log_analyzer.access_log WHERE access_log_file_id = ? AND request_date BETWEEN ? AND ? GROUP BY ip HAVING count(*) >= ?",
                (rs, rowNum) -> {
                    LogAnalyzerResponseDTO logAnalyzerResponseDTO = new LogAnalyzerResponseDTO();
                    logAnalyzerResponseDTO.ip = rs.getString(1);
                    return logAnalyzerResponseDTO;
                },
                new Object[] {
                        accessLogFileId,
                        logAnalyzerRequestDTO.startDate,
                        endDate,
                        logAnalyzerRequestDTO.threshold
                }
        );
    }
}