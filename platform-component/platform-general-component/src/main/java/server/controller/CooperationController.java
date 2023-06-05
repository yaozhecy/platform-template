package server.controller;

import com.cy.platform.generation.server.common.PublicResult;
import com.cy.platform.generation.server.domain.dto.Track;
import com.cy.platform.generation.server.service.ICooperationDocService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
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
    public PublicResult<?> queryFileList() {
        return PublicResult.success(cooperationDocService.queryList());
    }

    @PostMapping("/upload")
    public PublicResult<?> upload(@RequestParam("file") MultipartFile file) {
        return PublicResult.success(cooperationDocService.saveFile(file));
    }

    @GetMapping(value = "/download")
    public void download(HttpServletResponse response, String type, String key) {
        long time = System.currentTimeMillis();
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
    public PublicResult<List<?>> queryHistory(String docCode) {
        return PublicResult.success(cooperationDocService.queryHistory(docCode));
    }

    @GetMapping(path = "/historydata")
    public PublicResult<?> queryHistoryData(String docCode, Integer version) {
        return PublicResult.success(cooperationDocService.queryHistoryData(docCode, version));
    }
}
