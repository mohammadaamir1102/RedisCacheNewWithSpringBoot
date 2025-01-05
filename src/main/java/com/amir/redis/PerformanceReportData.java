package com.amir.redis;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class PerformanceReportData {
    private LocalDate date;
    private Long numberOfParcelArrived;
    private Long numberOfParcelCleared;
    private Long numberOfPuByOpsParcels;
    private Long numberOfDeliveredParcels;
    private Long numberOfRTOParcels;
}
