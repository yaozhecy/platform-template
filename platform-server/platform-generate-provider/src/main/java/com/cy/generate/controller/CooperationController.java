package com.cy.generate.controller;

import com.cy.generate.domain.dto.Track;
import com.cy.generate.service.ICooperationDocService;
import com.cy.platform.common.http.R;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 协作服务接口
 *
 * @author 56807
 */
@Slf4j
@RestController
@RequestMapping("/cooperation")
public class CooperationController {
    @Autowired
    private ICooperationDocService cooperationDocService;

    @GetMapping("/list")
    public R<?> queryFileList() {
        return R.success(cooperationDocService.queryList());
    }

    @PostMapping("/upload")
    public R<?> upload(@RequestParam("file") MultipartFile file) {
        return R.success(cooperationDocService.saveFile(file));
    }

    @GetMapping(value = "/download")
    public void download(HttpServletResponse response, String type, String key) {
        Long time = System.currentTimeMillis();
        System.out.println("文档下载开始：");
        cooperationDocService.downloadDoc(response, type, key);
        System.out.println("文档下载时间：" + (System.currentTimeMillis() - time));
    }

    @PostMapping(path = "/track")
    public String track(@RequestBody Track body) throws Exception {
        cooperationDocService.trackInfo(body);
        return "{\"error\":" + 0 + "}";
    }

    @GetMapping(path = "/history")
    public R<List<?>> queryHistory(String docCode) {
        return R.success(cooperationDocService.queryHistory(docCode));
    }

    @GetMapping(path = "/historydata")
    public R<?> queryHistoryData(String docCode, Integer version) {
        return R.success(cooperationDocService.queryHistoryData(docCode, version));
    }
}
