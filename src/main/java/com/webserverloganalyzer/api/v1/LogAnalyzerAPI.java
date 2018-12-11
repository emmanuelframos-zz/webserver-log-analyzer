package com.webserverloganalyzer.api.v1;

import com.webserverloganalyzer.api.v1.dto.LogAnalyzerDTO;
import com.webserverloganalyzer.service.LogAnalyzerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class LogAnalyzerAPI {

    @Autowired
    private LogAnalyzerService logAnalyzerService;

    @GetMapping(value = "/analyze")
    public List<String> analyze(LogAnalyzerDTO logAnalyzerDTO){

        logAnalyzerService.analyze();

        return Arrays.asList("Analyzing..");
    }

}
