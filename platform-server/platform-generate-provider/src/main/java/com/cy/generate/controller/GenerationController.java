package com.cy.generate.controller;

import com.cy.generate.domain.vo.CodeInfoVo;
import com.cy.generate.domain.vo.CodeParamsVo;
import com.cy.generate.domain.vo.code.CodeListVo;
import com.cy.generate.service.IGenerationService;
import com.cy.platform.common.http.R;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 代码生成接口
 *
 * @author 56807
 */
@RestController
@RequestMapping("/code")
public class GenerationController {
    @Autowired
    private IGenerationService generationService;

    @GetMapping("/generation")
    public R<String> generation(@RequestParam("id") String id) {
        generationService.generation(id);
        return R.success();
    }

    @GetMapping("/generation/code")
    public R<CodeInfoVo> generationCode(@RequestParam("ds") String dsId,
                                                   @RequestParam("table") String tableId) {
        return R.success(generationService.generationCode(dsId, tableId));
    }

    /**
     * 代码生成接口（根据模板生成）
     *
     * @param tgId    模板组ID
     * @param dsId    数据源
     * @param tableId 表
     * @return 生成结果
     */
    @GetMapping("/generation/code2")
    public R<CodeListVo> generationCode(@RequestParam("tg") String tgId, @RequestParam("ds") String dsId,
                                                   @RequestParam("table") String tableId) {
        return R.success(generationService.generationCode(tgId, dsId, tableId));
    }

    //todo:单模板代码生成

    /**
     * 生成代码
     */
    @PostMapping("/code")
    public void code(@RequestBody CodeParamsVo params, HttpServletResponse response) throws IOException {
        byte[] data = generationService.exportZip(params);
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"code.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");
        IOUtils.write(data, response.getOutputStream());
    }
}
