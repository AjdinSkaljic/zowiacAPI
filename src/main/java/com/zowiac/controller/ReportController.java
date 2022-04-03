package com.zowiac.controller;


import com.zowiac.model.ReportEntity;
import com.zowiac.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReportController {

    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }


    @GetMapping(path = "/reports", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<ReportEntity> findAll() {
        return getReportService().findAll();
    }


    @GetMapping(path = "/reports/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ReportEntity find(@PathVariable("id") Long id) {
        return getReportService().find(id);
    }


    @RequestMapping(value = {"/reports/{id}"}, method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long  id) {
        getReportService().delete(id);
    }


    @PostMapping("/reports")
    @ResponseBody
    public ReportEntity save(@RequestBody ReportEntity report) {
        getReportService().save(report);
        return report;
    }

    @PutMapping("/reports/{id}")
    @ResponseBody
    public ReportEntity create(@RequestBody ReportEntity report) {
        getReportService().save(report);
        return report;
    }


    public ReportService getReportService() {
        return reportService;
    }
}
