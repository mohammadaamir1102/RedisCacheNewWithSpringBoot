package com.amir.redis;

import lombok.Data;

import java.util.List;

@Data
public class PerformanceReportResponse {
    private int status;
    private String statusMessage;
    private List<PerformanceReportData> dataList;
    private int totalPage;
    private int count;
}
