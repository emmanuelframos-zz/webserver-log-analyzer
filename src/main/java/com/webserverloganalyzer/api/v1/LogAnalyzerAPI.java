package com.webserverloganalyzer.api.v1;

import com.webserverloganalyzer.api.v1.dto.LogAnalyzerRequestDTO;
import com.webserverloganalyzer.api.v1.dto.LogAnalyzerResponseDTO;
import com.webserverloganalyzer.service.LogAnalyzerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
public class LogAnalyzerAPI {

    @Autowired
    private LogAnalyzerService logAnalyzerService;

    @PostMapping(value = "/analyze")
    public List<LogAnalyzerResponseDTO> analyze(@Valid @RequestBody LogAnalyzerRequestDTO logAnalyzerRequestDTO) throws IOException, URISyntaxException {

        BigInteger accessLogFileId = logAnalyzerService.batchInsert("/home/webserver-log-analyzer/access.log");

        return logAnalyzerService.analyze(accessLogFileId, logAnalyzerRequestDTO);
    }
}