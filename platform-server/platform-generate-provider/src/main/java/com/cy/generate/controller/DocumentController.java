package com.cy.generate.controller;

import com.cy.generate.domain.vo.DocNodeVo;
import com.cy.generate.domain.vo.doc.DocBodyVo;
import com.cy.generate.service.IDocumentService;
import com.cy.platform.common.http.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 文档接口
 *
 * @author chenyang
 */
@RestController
@RequestMapping("/doc")
@Slf4j
public class DocumentController {
    @Autowired
    private IDocumentService documentService;

    @GetMapping("/node/query")
    public R<List<DocNodeVo>> queryNode(String id) {
        return R.success(documentService.queryNodes(id));
    }

    @PostMapping("/node/add")
    public R<String> addNode(@RequestBody DocNodeVo vo) {
        return R.success(documentService.addNode(vo));
    }

    @GetMapping("/node/delete")
    public R<String> deleteNode(String id) {
        documentService.deleteNode(id);
        return R.success();
    }

    @PostMapping("/save")
    public R<String> saveDoc(@RequestBody DocBodyVo body) {
        return R.success(documentService.saveBody(body));
    }

    @GetMapping("/body")
    public R<String> queryDocBody(String id) {
        return R.success(documentService.queryDocBody(id));
    }
}
