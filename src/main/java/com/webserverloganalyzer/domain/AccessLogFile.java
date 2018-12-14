package com.webserverloganalyzer.domain;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class AccessLogFile {

    private BigInteger id;

    private String filePath;

    private BigInteger size;

    private List<AccessLog> accessLogs = new LinkedList<>();

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public BigInteger getSize() {
        return size;
    }

    public void setSize(BigInteger size) {
        this.size = size;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public List<AccessLog> getAccessLogs() {
        return accessLogs;
    }

    public void setAccessLogs(List<AccessLog> accessLogs) {
        this.accessLogs = accessLogs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccessLogFile that = (AccessLogFile) o;
        return filePath.equals(that.filePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(filePath);
    }
}