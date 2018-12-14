package com.webserverloganalyzer.parser;

import com.webserverloganalyzer.domain.AccessLog;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class AccessLogParser {

    private static final String TOKEN = "\\|";

    public AccessLog parse(String rawAccessLog){

        String[] rawAccessLogArray = StringUtils.deleteAny(rawAccessLog, "\"").split(TOKEN);

        AccessLog accessLog = new AccessLog();
        accessLog.setDate(rawAccessLogArray[0]);
        accessLog.setIp(rawAccessLogArray[1]);
        accessLog.setRequest(rawAccessLogArray[2]);
        accessLog.setStatus(rawAccessLogArray[3]);
        accessLog.setUserAgent(rawAccessLogArray[4]);

        return accessLog;
    }
}