package com.webserverloganalyzer.api.v1;

import com.webserverloganalyzer.service.AccessLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class AccessLogAPI {


    @Autowired
    private AccessLogService accessLogService;

    @PostMapping(value = "/analyze")
    public void analyze() throws IOException {

        accessLogService.analyze("src/main/resources/access.log");

    }
}