package com.webserverloganalyzer.repository;

import com.webserverloganalyzer.domain.AccessLog;
import com.webserverloganalyzer.domain.AccessLogFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

@Repository
public class AccessLogFileRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private DataSource dataSource;

    @Transactional
    public int[] batchInsert(AccessLogFile accessLogFile) {

        BigInteger accessLogFileId = save(accessLogFile);


        return jdbcTemplate.batchUpdate("INSERT INTO log_analyzer.access_log(request_date, ip, status, request, user_agent, access_log_file_id)" +
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
                        System.out.println("Size " + accessLogFile.getAccessLogs().size());
                        return accessLogFile.getAccessLogs().size();
                    }
                });
    }

    public BigInteger save(AccessLogFile accessLogFile){
        GeneratedKeyHolder holder = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            PreparedStatement statement = con.prepareStatement("INSERT INTO log_analyzer.access_log_file(file_path, size) VALUES (?, ?) ", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, accessLogFile.getFilePath());
            statement.setInt(2, accessLogFile.getSize());
            return statement;
        }, holder);

        return BigInteger.valueOf(holder.getKey().longValue());
    }
}