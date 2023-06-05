package server.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.cy.platform.generation.server.common.PublicResult;
import com.cy.platform.generation.server.domain.vo.CommonDataVo;
import com.cy.platform.generation.server.domain.vo.DataSourceQueryParam;
import com.cy.platform.generation.server.domain.vo.DataSourceVo;
import com.cy.platform.generation.server.domain.vo.doc.DocParamVo;
import com.cy.platform.generation.server.service.IDataSourceService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * 数据源服务器
 *
 * @author 56807
 */
@RestController
@RequestMapping("/ds")
@Slf4j
public class DataSourceController {
    @Autowired
    private IDataSourceService dataSourceService;

    @PostMapping("/list")
    @ApiOperation(value = "查询列表2", notes = "")
    public PublicResult<List<DataSourceVo>> queryList(@RequestBody DataSourceQueryParam param) {
        return PublicResult.success(dataSourceService.queryDataSourceList(param));
    }

    @PostMapping("/page")
    public PublicResult<IPage<DataSourceVo>> queryPage(@RequestParam("current") long current,
        @RequestParam("size") long size, @RequestBody DocParamVo paramVo) {
        return PublicResult.success(dataSourceService.queryDataSourcePage(current, size, paramVo));
    }

    @PostMapping("/add")
    public PublicResult<?> addDataSource(@RequestBody DataSourceVo dataSourceVo) {
        dataSourceService.addSourceInfo(dataSourceVo);
        return PublicResult.success();
    }

    @GetMapping("/info")
    public PublicResult<DataSourceVo> queryInfo(@RequestParam("id") String id) {
        return PublicResult.success(dataSourceService.queryInfo(id));
    }

    @PostMapping("/valid")
    public PublicResult<String> validDb(@RequestBody DataSourceVo vo) {
        dataSourceService.validDb(vo);
        return PublicResult.success();
    }

    @GetMapping("/delete")
    public PublicResult<String> delete(@RequestParam("id") String id) {
        dataSourceService.delete(id);
        return PublicResult.success();
    }

    @GetMapping("/common/data")
    public PublicResult<CommonDataVo> queryCommonData(@RequestParam("id") String id) {
        return PublicResult.success(dataSourceService.queryCommonDataVo(id));
    }

    @PostMapping("/common/data/update")
    public PublicResult<String> updateCommonData(@RequestBody CommonDataVo vo) {
        dataSourceService.updateCommonDataVo(vo);
        return PublicResult.success();
    }

    @PostMapping("/upload")
    @ResponseBody
    public PublicResult<String> uploadFileDir(MultipartFile file) throws Exception {
        String id = IdWorker.getIdStr();
        File tempDir = new File("tempfile");
        if (!tempDir.exists() && tempDir.mkdirs()) {
            return PublicResult.failure("文件创建失败");
        }

        File dir = new File("tempfile" + File.separator + id + "_" + file.getOriginalFilename());
        try (FileOutputStream outputStream = new FileOutputStream(dir)) {
            outputStream.write(file.getBytes());
            outputStream.flush();
        }
        return PublicResult.success(id);
    }

    @GetMapping(value = "/download")
    public void downloadTemplate(HttpServletResponse response, HttpServletRequest request) {
        InputStream inputStream = null;
        ServletOutputStream servletOutputStream = null;
        try {
            String filename = "模板.xls";
            response.setContentType("application/vnd.ms-excel");
            response.addHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            response.addHeader("charset", "utf-8");
            response.addHeader("Pragma", "no-cache");
            String encodeName = URLEncoder.encode(filename, StandardCharsets.UTF_8.toString());
            response.setHeader("Content-Disposition", "attachment; filename=\"" + encodeName + "\"; filename*=utf-8''" + encodeName);

            inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("file/template.xlsx");
            servletOutputStream = response.getOutputStream();
            assert inputStream != null;
            IOUtils.copy(inputStream, servletOutputStream);
            response.flushBuffer();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (servletOutputStream != null) {
                    servletOutputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                // 召唤jvm的垃圾回收器
                System.gc();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
