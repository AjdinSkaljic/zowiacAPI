package com.zowiac.service;


import com.zowiac.model.FilesEntity;
import com.zowiac.model.HideEntity;
import com.zowiac.model.ReportEntity;
import com.zowiac.respository.FilesRepository;
import com.zowiac.respository.HideRepository;
import com.zowiac.respository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class FilesService {

    private final FilesRepository filesRepository;
    private final ReportRepository reportRepository;
    private final HideRepository hideRepository;

    @Autowired
    public FilesService(FilesRepository filesRepository, ReportRepository reportRepository, HideRepository hideRepository) {
        this.filesRepository = filesRepository;
        this.reportRepository = reportRepository;
        this.hideRepository = hideRepository;
    }

    public FilesEntity loadReportFile(UUID refId) {
        ReportEntity report = getReportRepository().findByRefId(refId);
        if (report != null) {
            List<FilesEntity> files = getFilesRepository().findByOrigin(report.getId(), FilesEntity.ORIGIN_TYPE_REPORT);

            if (files != null && !files.isEmpty())
                return files.get(0);
        }
        return null;
    }

    public FilesEntity loadHideFile(UUID refId) {
        HideEntity hide = getHideRepository().findByRefId(refId);
        if (hide != null) {
            List<FilesEntity> files = getFilesRepository().findByOrigin(hide.getId(), FilesEntity.ORIGIN_TYPE_HIDE);

            if (files != null && !files.isEmpty())
                return files.get(0);
        }
        return null;
    }


    public void saveReportFile(FilesEntity filesEntity, UUID refId) {
        ReportEntity report = getReportRepository().findByRefId(refId);
        if (report != null) {
            filesEntity.setOriginId(report.getId());
            filesEntity.setOriginType(FilesEntity.ORIGIN_TYPE_REPORT);
            getFilesRepository().saveAndFlush(filesEntity);
        }
    }

    public void saveHideFile(FilesEntity filesEntity, UUID refId) {
        HideEntity hide = getHideRepository().findByRefId(refId);
        if (hide != null) {
            filesEntity.setOriginId(hide.getId());
            filesEntity.setOriginType(FilesEntity.ORIGIN_TYPE_HIDE);
            getFilesRepository().saveAndFlush(filesEntity);
        }
    }


    public FilesRepository getFilesRepository() {
        return filesRepository;
    }

    public ReportRepository getReportRepository() {
        return reportRepository;
    }

    public HideRepository getHideRepository() {
        return hideRepository;
    }
}
