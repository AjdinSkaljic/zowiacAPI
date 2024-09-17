package com.zowiac.service;

import com.zowiac.commons.DateUtils;
import com.zowiac.model.AddressPointData;
import com.zowiac.model.AnimalEntity;
import com.zowiac.model.ChartPointData;
import com.zowiac.model.ReportEntity;
import com.zowiac.respository.AnimalRepository;
import com.zowiac.respository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class MapService {


    private final AnimalRepository animalRepository;
    private final ReportRepository reportRepository;

    @Autowired
    public MapService(AnimalRepository animalRepository, ReportRepository reportRepository) {
        this.animalRepository = animalRepository;
        this.reportRepository = reportRepository;
    }

    public List<AnimalEntity> listAnimals() {
        return getAnimalRepository().findByReportType(AnimalEntity.REPORT_TYPE_ZOWIAC);
    }


    public List<AddressPointData> listAddressPoints(Long animalId, Date from, Date to) {
        List<AddressPointData> addressPointDataList = new ArrayList<>();
        List<ReportEntity> reportList = getReportRepository().findByAnimalAndPeriod(animalId, from, to);
        if (reportList != null) {
            for (ReportEntity report : reportList) {
                if (report.getLatitude() != null && report.getLongitude() != null
                        && report.getLatitude().doubleValue() != 0 && report.getLongitude().doubleValue() != 0) {
                    AddressPointData addressPointData = new AddressPointData();
                    addressPointData.setId(report.getId());
                    addressPointData.setAnimalId(report.getAnimalId());
                    addressPointData.setDate(report.getDate());
                    addressPointData.setTime(report.getTime());
                    addressPointData.setLatitude(report.getLatitude());
                    addressPointData.setLongitude(report.getLongitude());


                    addressPointDataList.add(addressPointData);
                }
            }
        }

        return addressPointDataList;
    }

    public List<ChartPointData> listChartPoints(Long animalId, Date from, Date to) {
        List<ChartPointData> chartPointDataList = new ArrayList<>();
        List<ReportEntity> reportList = getReportRepository().findByAnimalAndPeriod(animalId, from, to);

        Map<String, ChartPointData> chartPointDataMap = new HashMap<>();
        //Months between from and to

        if (DateUtils.countMonths(from, to) > 24) {
            DateUtils.getQuartals(from, to).forEach(quartal -> {
                ChartPointData pointData = new ChartPointData(quartal, 0);
                chartPointDataList.add(pointData);
                chartPointDataMap.put(quartal, pointData);
            });

            if (reportList != null) {
                for (ReportEntity report : reportList) {
                    ChartPointData chartPointData = chartPointDataMap.get(DateUtils.getQuartal(report.getDate()));
                    if (chartPointData != null)
                        chartPointData.setCount(chartPointData.getCount() + 1);
                }
            }

        } else {
            List<String> days = DateUtils.getDays(from, to);

            if (days.size() <= 31) {
                days.forEach(day -> {
                    ChartPointData pointData = new ChartPointData(day, 0);
                    chartPointDataList.add(pointData);
                    chartPointDataMap.put(day, pointData);
                });

                if (reportList != null) {
                    for (ReportEntity report : reportList) {
                        ChartPointData chartPointData = chartPointDataMap.get(DateUtils.formatDay(report.getDate()));
                        if (chartPointData != null)
                            chartPointData.setCount(chartPointData.getCount() + 1);
                    }
                }
            } else {

                Date current = from;
                while (current.before(to)) {
                    ChartPointData pointData = new ChartPointData(DateUtils.getMonth(current), 0);
                    chartPointDataList.add(pointData);
                    chartPointDataMap.put(DateUtils.getMonth(current), pointData);
                    current = DateUtils.addMonths(current, 1);
                }


                if (reportList != null) {
                    for (ReportEntity report : reportList) {
                        ChartPointData chartPointData = chartPointDataMap.get(DateUtils.getMonth(report.getDate()));
                        if (chartPointData != null)
                            chartPointData.setCount(chartPointData.getCount() + 1);
                    }
                }
            }
        }

        return chartPointDataList;
    }


    public AnimalRepository getAnimalRepository() {
        return animalRepository;
    }

    public ReportRepository getReportRepository() {
        return reportRepository;
    }
}
