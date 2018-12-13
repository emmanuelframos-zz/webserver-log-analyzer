package com.webserverloganalyzer.api.v1;

import com.webserverloganalyzer.api.v1.dto.LogAnalyzerDTO;
import com.webserverloganalyzer.service.AccessLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(
        value = "/api/v1",
        consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE
)
public class AccessLogAPI {


    @Autowired
    private AccessLogService accessLogService;

    @PostMapping(value = "/analyze")
    public List<String> analyze(@RequestBody LogAnalyzerDTO logAnalyzerDTO) throws IOException {

        accessLogService.analyze(logAnalyzerDTO.filePath);

        return Arrays.asList("Analyzing..");
    }
}