package com.webserverloganalyzer.api.v1.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.webserverloganalyzer.domain.Durations;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

public class LogAnalyzerRequestDTO implements Serializable {

    @NotNull(message = "File path is mandatory")
    public String filePath;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd.HH:mm:ss", timezone="America/Sao_Paulo")
    @NotNull(message = "Start date is mandatory")
    public Date startDate;

    @NotNull(message = "Duration is mandatory")
    public Durations duration;

    @NotNull(message = "Threshold is mandatory")
    public Integer threshold;

    public boolean isDaily(){
        return Durations.DAILY.equals(duration);
    }
}