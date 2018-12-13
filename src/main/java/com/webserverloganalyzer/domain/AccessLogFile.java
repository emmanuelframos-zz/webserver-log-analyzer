package com.webserverloganalyzer.domain;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(schema = "log_analyzer", name = "access_log_file")
public class AccessLogFile {

    @Id
    @Column(name = "id")
    private BigInteger id;

    @Column(name= "file_path", nullable = false)
    private String filePath;

    @Column(name= "size", nullable = false)
    private Integer size;

    @Column(name= "processing_time", nullable = false)
    private BigInteger processingTime;

    @Transient
    private Set<AccessLog> accessLogs = new LinkedHashSet<>();

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public BigInteger getProcessingTime() {
        return processingTime;
    }

    public void setProcessingTime(BigInteger processingTime) {
        this.processingTime = processingTime;
    }

    public Set<AccessLog> getAccessLogs() {
        return accessLogs;
    }

    public void setAccessLogs(Set<AccessLog> accessLogs) {
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