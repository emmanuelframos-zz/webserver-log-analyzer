package com.webserverloganalyzer.service;

import com.webserverloganalyzer.api.v1.dto.LogAnalyzerRequestDTO;
import com.webserverloganalyzer.api.v1.dto.LogAnalyzerResponseDTO;
import com.webserverloganalyzer.domain.AccessLog;
import com.webserverloganalyzer.domain.AccessLogFile;
import com.webserverloganalyzer.parser.AccessLogParser;
import com.webserverloganalyzer.reader.FileReader;
import com.webserverloganalyzer.repository.AccessLogFileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LogAnalyzerService {

    private final Logger logger = LoggerFactory.getLogger( LogAnalyzerService.class);

    @Autowired
    private FileReader fileReader;

    @Autowired
    private AccessLogParser accessLogParser;

    @Autowired
    private AccessLogFileRepository accessLogFileRepository;

    private static final Integer MB = 1024 * 1024;

    public BigInteger batchInsert(String path) throws IOException {

        long startTime = System.currentTimeMillis();

        logger.debug("Heap {} MB", ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getUsed()/1024);
        logger.debug("NonHeap {} MB", ManagementFactory.getMemoryMXBean().getNonHeapMemoryUsage().getUsed()/1024);

        List<AccessLog> accessLogs = fileReader
               .readFile(path)
               .map(l -> accessLogParser.parse(l))
               .collect(Collectors.toList());

        AccessLogFile accessLogFile = new AccessLogFile();
        accessLogFile.setFilePath(path);
        accessLogFile.setSize(BigInteger.valueOf(fileReader.getSize(path)));
        accessLogFile.setAccessLogs(accessLogs);

        BigInteger accessLogFileId = accessLogFileRepository.batchInsert(accessLogFile);

        logger.debug("Heap {} MB", ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getUsed()/1024);
        logger.debug("NonHeap {} MB", ManagementFactory.getMemoryMXBean().getNonHeapMemoryUsage().getUsed()/1024);

        logger.info("File size approx. {} MB", accessLogFile.getSize().longValue()/MB);
        logger.info("Time spent {} secs", (System.currentTimeMillis() - startTime)/1000);

        return accessLogFileId;
    }

    public List<LogAnalyzerResponseDTO> analyze(BigInteger accessLogFileId, LogAnalyzerRequestDTO logAnalyzerRequestDTO){
        List<LogAnalyzerResponseDTO> responses = accessLogFileRepository.filterIPs(accessLogFileId, logAnalyzerRequestDTO);

        logger.info("Found {} results", responses.size());

        return responses;
    }
}