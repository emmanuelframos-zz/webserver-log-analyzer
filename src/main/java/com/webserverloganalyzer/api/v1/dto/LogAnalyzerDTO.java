package com.webserverloganalyzer.api.v1.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class LogAnalyzerDTO implements Serializable {

    @NotNull
    public String filePath;

//    @NotNull
//    public Date startDate;
//
//    @NotNull
//    public Durations duration;
//
//    @NotNull
//    public Integer threshold;

}
