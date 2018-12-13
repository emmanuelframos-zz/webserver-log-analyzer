package com.webserverloganalyzer.api.v1;

import com.webserverloganalyzer.api.v1.dto.LogAnalyzerRequestDTO;
import com.webserverloganalyzer.service.LogAnalyzerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Set;

@RestController
@RequestMapping(
        value = "/api/v1",
        consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE
)
public class LogAnalyzerAPI {

    @Autowired
    private LogAnalyzerService logAnalyzerService;

    @PostMapping(value = "/analyze")
    public Set<String> analyze(@Valid @RequestBody LogAnalyzerRequestDTO logAnalyzerRequestDTO) throws IOException {

        logAnalyzerService.batchInsert(logAnalyzerRequestDTO.filePath);

        return logAnalyzerService.analyze(logAnalyzerRequestDTO);
    }
}