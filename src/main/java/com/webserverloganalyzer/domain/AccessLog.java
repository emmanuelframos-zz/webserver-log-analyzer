package com.webserverloganalyzer.domain;

import javax.persistence.*;

@Entity
@Table(schema = "log_analyzer", name = "access_log")
public class AccessLog {

    @Id
    @Column(name= "id")
    private Long id;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

}