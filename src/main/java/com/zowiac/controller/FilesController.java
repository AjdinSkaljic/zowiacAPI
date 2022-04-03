package com.zowiac.controller;


import com.zowiac.model.FilesEntity;
import com.zowiac.service.FilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Controller
public class FilesController {
    private final FilesService filesService;

    @Autowired
    public FilesController(FilesService filesService) {
        this.filesService = filesService;
    }

    @GetMapping("/files/{reportId}")
    void load(HttpServletResponse response, @PathVariable("reportId") UUID reportId) {
        FilesEntity file = getFilesService().loadReportFile(reportId);
        addFileToResponse(file, response);
    }

    public FilesService getFilesService() {
        return filesService;
    }

    public static void addFileToResponse(FilesEntity file, HttpServletResponse response) {
        if (file != null) {
            response.setContentType(file.getType());
            response.addHeader("Content-Disposition", "attachment; filename=" + file.getName());
            try {
                response.getOutputStream().write(file.getData());
                response.getOutputStream().flush();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
