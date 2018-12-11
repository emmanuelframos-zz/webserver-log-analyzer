package com.webserverloganalyzer.domain;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(schema = "log_analysis", name = "access_log_file")
public class AccessLogFile {

    @Id
    @Column(name = "id")
    private BigInteger id;

    @Column(name= "file_path", nullable = false)
    private String filePath;

    @Column(name= "size", nullable = false)
    private Integer size;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name= "start_date", nullable = false)
    private Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name= "finish_date", nullable = false)
    private Date finishDate;

    @OneToMany(mappedBy = "accessLogFile", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AccessLog> accessLogs;

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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
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