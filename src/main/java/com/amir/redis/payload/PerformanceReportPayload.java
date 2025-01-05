package com.amir.redis.payload;

import jakarta.validation.constraints.Min;

public record PerformanceReportPayload
        (String fromDate, String toDate, String receiverCountry, String shipmentType,
         @Min(0) int pageNo, @Min(10) int pageSize) {
}
