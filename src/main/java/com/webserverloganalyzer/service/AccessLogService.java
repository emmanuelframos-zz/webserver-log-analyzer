package com.webserverloganalyzer.service;

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
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccessLogService {

    private final Logger logger = LoggerFactory.getLogger( AccessLogService.class);

    @Autowired
    private FileReader fileReader;

    @Autowired
    private AccessLogParser accessLogParser;

    @Autowired
    private AccessLogFileRepository accessLogFileRepository;

    public void analyze(String path) throws IOException {

        long startTime = System.currentTimeMillis();

        logger.info("Heap {} MB", ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getUsed()/1024);
        logger.info("NonHeap {} MB", ManagementFactory.getMemoryMXBean().getNonHeapMemoryUsage().getUsed()/1024);

        AccessLogFile accessLogFile = new AccessLogFile();

        List<AccessLog> accessLogs = fileReader
               .readFile(path)
               .map(l -> accessLogParser.parse(l, accessLogFile))
               .collect(Collectors.toList());

        logger.info("Heap {} MB", ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getUsed()/1024);
        logger.info("NonHeap {} MB", ManagementFactory.getMemoryMXBean().getNonHeapMemoryUsage().getUsed()/1024);

        accessLogFile.setFilePath(path);
        accessLogFile.setSize(1);
        accessLogFile.setAccessLogs(accessLogs);

        accessLogFileRepository.batchInsert(accessLogFile);

        logger.info("Heap {} MB", ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getUsed()/1024);
        logger.info("NonHeap {} MB", ManagementFactory.getMemoryMXBean().getNonHeapMemoryUsage().getUsed()/1024);

        logger.info("Tempo gasto: {} segs", (System.currentTimeMillis() - startTime)/1000);
    }
}