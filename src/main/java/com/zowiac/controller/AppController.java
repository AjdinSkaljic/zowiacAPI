package com.zowiac.controller;

import com.zowiac.commons.BusinessException;
import com.zowiac.model.*;
import com.zowiac.service.*;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.UUID;

@RestController
public class AppController {

    private final ApplicationService applicationService;
    private final ReportService reportService;
    private final FilesService filesService;
    private final AnimalService animalService;
    private final TextService textService;
    private final ShootingSeasonService shootingSeasonService;
    private final AuthorityService authorityService;
    private final HideService hideService;
    private final UserService userService;
    private final FeedbackService feedbackService;

    @Autowired
    public AppController(ApplicationService applicationService, ReportService reportService, FilesService filesService, AnimalService animalService, TextService textService,
                         ShootingSeasonService shootingSeasonService, AuthorityService authorityService, HideService hideService, UserService userService,
                         FeedbackService feedbackService) {
        this.applicationService = applicationService;
        this.reportService = reportService;
        this.filesService = filesService;
        this.animalService = animalService;
        this.textService = textService;
        this.shootingSeasonService = shootingSeasonService;
        this.authorityService = authorityService;
        this.hideService = hideService;
        this.userService = userService;
        this.feedbackService = feedbackService;

    }

    @GetMapping(path = "/app/login", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ApplicationData login(HttpServletRequest request) {
        return getApplicationService().loadApplicationData(request.getRemoteUser());
    }


    @PostMapping("/app/users")
    @ResponseBody
    public UserEntity save(@RequestBody UserEntity user) {
        getUserService().updateUser(user);
        return user;
    }


    @PostMapping("/public/regiseterUser")
    @ResponseBody
    public UserEntity registerUser(@RequestBody UserEntity user) throws Exception {
        getUserService().registerUser(user);
        return user;
    }


    @GetMapping("/public/forgotPassword/{user}/")
    @ResponseBody
    public String forgotPassword(@PathVariable(name = "user") String user) throws Exception {
        try {
            getUserService().forgotPassword(user);
        } catch (BusinessException e) {
            return "error";
        }
        return "ok";
    }


    @PostMapping("/app/userChangePassword")
    public void changePassword(HttpServletRequest request, @RequestParam(name = "newPassword") String newPassword) throws Exception {
        getUserService().changePassword(request.getRemoteUser(), newPassword);
    }


    @GetMapping(path = "/app/reports/{user}/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<ReportEntity> findAllReports(@PathVariable("user") String user) {
        return getReportService().findAllByUser(user);
    }

    @PostMapping("/app/feedback")
    public void saveFeedback(HttpServletRequest request) {
        try {
            String text = IOUtils.toString(request.getReader());
            getFeedbackService().saveFeedback(text, request.getRemoteUser());
        } catch (Exception ignore) {
        }
    }


    @PostMapping("/app/reports")
    @ResponseBody
    public ReportEntity save(@RequestBody ReportEntity report) {
        getReportService().save(report);
        return report;
    }

    @DeleteMapping(value = {"/app/reports/{refId}"})
    public void delete(@PathVariable("refId") UUID refId) {
        getReportService().deleteByRefId(refId);
    }


    @GetMapping("/app/files/report/{originId}")
    void loadReportFile(HttpServletResponse response, @PathVariable("originId") UUID originId) {
        FilesEntity file = getFilesService().loadReportFile(originId);
        FilesController.addFileToResponse(file, response);
    }

    @GetMapping("/app/files/hide/{originId}")
    void loadHideFile(HttpServletResponse response, @PathVariable("originId") UUID originId) {
        FilesEntity file = getFilesService().loadHideFile(originId);
        FilesController.addFileToResponse(file, response);
    }


    @PostMapping("/app/files/report/{originId}")
    public @ResponseBody
    String saveReportFile(@RequestParam("file") MultipartFile file, @PathVariable("originId") UUID originId) {
        try {
            FilesEntity filesEntity = createFileEntityFromRequest(file);
            getFilesService().saveReportFile(filesEntity, originId);
        } catch (Exception e) {
            //do nothing
        }
        return "Ok";
    }

    @PostMapping("/app/files/hide/{originId}")
    public @ResponseBody
    String saveHideFile(@RequestParam("file") MultipartFile file, @PathVariable("originId") UUID originId) {
        try {
            FilesEntity filesEntity = createFileEntityFromRequest(file);
            getFilesService().saveHideFile(filesEntity, originId);
        } catch (Exception e) {
            //do nothing
        }
        return "Ok";
    }

    private FilesEntity createFileEntityFromRequest(MultipartFile file) throws IOException {
        FilesEntity filesEntity = new FilesEntity();

        filesEntity.setName(file.getOriginalFilename());
        filesEntity.setData(file.getBytes());
        filesEntity.setType("image/jpeg");

        filesEntity.setCreateDate(new Date(System.currentTimeMillis()));
        filesEntity.setCreateTime(new Time(System.currentTimeMillis()));
        return filesEntity;

    }

    @GetMapping(path = "/app/animals/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<AnimalEntity> findAllAnimals() {
        return getAnimalService().findOnlyReporting(null);
    }

    @GetMapping(path = "/app/animals/{reportType}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<AnimalEntity> findAllAnimalsByType(@PathVariable("reportType") String reportTypeIn, HttpServletRequest request) {
        String reportType = reportTypeIn;
        if (reportType != null && !reportType.equals(AnimalEntity.REPORT_TYPE_ZOWIAC)) {
            UserEntity user = getUserService().findUser(request.getRemoteUser());
            if (user != null && user.isHunter())
                reportType = AnimalEntity.REPORT_TYPE_HUNTING;
        }
        return getAnimalService().findOnlyReporting(reportType);
    }

    @GetMapping(path = "/app/text/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public TextEntity findText(@PathVariable("id") String id) {
        return getTextService().find(id);
    }

    @GetMapping(path = "/app/shootingSeasons/{bundesland}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<ShootingSeasonEntity> findAllShootingsSeasons(@PathVariable("bundesland") String bundesland) {
        return getShootingSeasonService().findByBundesland(bundesland);
    }

    @GetMapping(path = "/app/authorities", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<AuthorityEntity> findAllAuthorities(@RequestParam(name = "searchString") String searchString) {
        return getAuthorityService().findBySearchSting(searchString);
    }

    @GetMapping(path = "/public/authorities", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<AuthorityEntity> findAllWebAuthorities(HttpServletRequest request) {
        String param = request.getParameter("search[value]");
        return getAuthorityService().findAllWithPermittedPhone(param);
    }


    @GetMapping(path = "/public/evidences/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<EvidenceTypeEntity> findAllEvidences() {
        return getReportService().getEvidenceTypeRepository().findAll();
    }

    @GetMapping(path = "/public/animals/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<AnimalEntity> findAllAnimalsPublic() {
        return getAnimalService().findOnlyReporting(AnimalEntity.REPORT_TYPE_ZOWIAC);
    }


    @PostMapping("/public/report")
    @ResponseBody
    public ReportEntity savePublic(@RequestBody ReportEntity report) {
        getReportService().save(report);
        return report;
    }


    @PostMapping("/public/files/report/{originId}")
    public @ResponseBody
    String saveReportFilePublic(@RequestParam("file") MultipartFile file, @PathVariable("originId") UUID originId) {
        try {
            FilesEntity filesEntity = createFileEntityFromRequest(file);
            getFilesService().saveReportFile(filesEntity, originId);
        } catch (Exception e) {
            //do nothing
        }
        return "Ok";
    }


    @GetMapping(path = "/app/hides", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<HideEntity> findAllHides(HttpServletRequest request) {
        return getHideService().findByUser(request.getRemoteUser());
    }


    @PostMapping("/app/hides")
    @ResponseBody
    public HideEntity save(HttpServletRequest request, @RequestBody HideEntity hide) {
        hide.setUserName(request.getRemoteUser());
        getHideService().save(hide);
        return hide;
    }

    public ApplicationService getApplicationService() {
        return applicationService;
    }


    public ReportService getReportService() {
        return reportService;
    }

    public FilesService getFilesService() {
        return filesService;
    }

    public AnimalService getAnimalService() {
        return animalService;
    }

    public TextService getTextService() {
        return textService;
    }

    public ShootingSeasonService getShootingSeasonService() {
        return shootingSeasonService;
    }

    public AuthorityService getAuthorityService() {
        return authorityService;
    }

    public HideService getHideService() {
        return hideService;
    }

    public UserService getUserService() {
        return userService;
    }

    public FeedbackService getFeedbackService() {
        return feedbackService;
    }
}
