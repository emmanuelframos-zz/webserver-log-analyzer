package com.webserverloganalyzer.domain;

import java.math.BigInteger;
import java.util.Objects;

public class AccessLog {

    private BigInteger id;

    private String requestDate;

    private String ip;

    private String request;

    private String status;

    private String userAgent;

    private BigInteger accessLogFile;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setDate(String requestDate) {
        this.requestDate = requestDate;
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
        return requestDate.equals(accessLog.requestDate) &&
                ip.equals(accessLog.ip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requestDate, ip);
    }
}