package com.webserverloganalyzer.domain;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Objects;

@Entity
@Table(schema = "log_analyzer", name = "access_log")
public class AccessLog {

    @Id
    @Column(name= "id")
    private BigInteger id;

    @Column(name= "request_date", nullable = false)
    private String date;

    @Column(name= "ip", nullable = false)
    private String ip;

    @Column(name= "request", nullable = false)
    private String request;

    @Column(name= "status", nullable = false)
    private String status;

    @Column(name= "user_agent", nullable = false)
    private String userAgent;

    @Column(name= "access_log_file_id")
    private BigInteger accessLogFile;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public BigInteger getAccessLogFile() {
        return accessLogFile;
    }

    public void setAccessLogFile(BigInteger accessLogFile) {
        this.accessLogFile = accessLogFile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccessLog accessLog = (AccessLog) o;
        return date.equals(accessLog.date) &&
                ip.equals(accessLog.ip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, ip);
    }
}