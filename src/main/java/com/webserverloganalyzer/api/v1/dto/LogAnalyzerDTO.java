package com.webserverloganalyzer.api.v1.dto;

import com.webserverloganalyzer.domain.Durations;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class LogAnalyzerDTO {

    @NotNull
    public Date startDate;

    @NotNull
    public Durations duration;

    @NotNull
    public Integer threshold;

}
