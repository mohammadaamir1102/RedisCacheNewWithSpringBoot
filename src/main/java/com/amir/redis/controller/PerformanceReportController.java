package com.amir.redis.controller;

import com.amir.redis.PerformanceReportData;
import com.amir.redis.PerformanceReportResponse;
import com.amir.redis.exception.PerformanceReportException;
import com.amir.redis.payload.PerformanceReportPayload;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/performance")
public class PerformanceReportController {

    @PostMapping("/report")
    public ResponseEntity<?> performanceReport(@RequestBody PerformanceReportPayload performanceReportPayload) {
        validateRequest(performanceReportPayload);

        PerformanceReportResponse performanceReportResponse = new PerformanceReportResponse();
        List<PerformanceReportData> reports = getReports(performanceReportPayload, performanceReportResponse);
        if(CollectionUtils.isEmpty(reports)){
            throw new PerformanceReportException("No Content Found");

        }
        int totalRecords = performanceReportResponse.getCount();
        int totalPages = (int) Math.ceil((double) totalRecords / performanceReportPayload.pageSize());


        performanceReportResponse.setStatus(HttpStatus.OK.value());
        performanceReportResponse.setStatusMessage("SUCCESS");
        performanceReportResponse.setDataList(reports);

        performanceReportResponse.setTotalPage(totalPages);
        performanceReportResponse.setCount(totalRecords);

        return new ResponseEntity<>(performanceReportResponse, HttpStatus.OK);
    }

    private List<PerformanceReportData> getReports(PerformanceReportPayload performanceReportPayload, PerformanceReportResponse performanceReportResponse) {

        List<PerformanceReportData> dataList = new ArrayList<>();

        LocalDate startDate = LocalDate.of(2025, 1, 1);
        for (int i = 0; i < 100; i++) {
            LocalDate date = startDate.plusDays(i);
            PerformanceReportData reportData = new PerformanceReportData(date, 10L + i, 13L + i, 54L + i, 17L + i, 11L + i);
            dataList.add(reportData);
        }
        dataList.forEach(System.out::println);
        performanceReportResponse.setCount(dataList.size());

        LocalDate fromDate = LocalDate.parse(performanceReportPayload.fromDate());
        LocalDate toDate = LocalDate.parse(performanceReportPayload.toDate());

        return dataList.stream()
                .filter(data -> !data.getDate().isBefore(fromDate)
                && !data.getDate().isAfter(toDate))
                .skip(performanceReportPayload.pageNo())
                .limit(performanceReportPayload.pageSize()).toList();
    }

    private void validateRequest(PerformanceReportPayload performanceReportPayload) {
        if (StringUtils.isBlank(performanceReportPayload.fromDate()) && StringUtils.isBlank(performanceReportPayload.toDate())) {
            throw new PerformanceReportException("From date and To date are mandatory");
        }
        if (StringUtils.isBlank(performanceReportPayload.fromDate())) {
            throw new PerformanceReportException("From date is mandatory");
        }
        if (StringUtils.isBlank(performanceReportPayload.toDate())) {
            throw new PerformanceReportException("To date is mandatory");
        }
    }



}
