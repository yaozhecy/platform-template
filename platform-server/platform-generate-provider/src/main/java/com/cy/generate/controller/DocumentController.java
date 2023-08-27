package com.cy.generate.controller;

import com.cy.generate.common.PublicResult;
import com.cy.generate.domain.vo.DocNodeVo;
import com.cy.generate.domain.vo.doc.DocBodyVo;
import com.cy.generate.service.IDocumentService;
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
    public PublicResult<List<DocNodeVo>> queryNode(String id) {
        return PublicResult.success(documentService.queryNodes(id));
    }

    @PostMapping("/node/add")
    public PublicResult<String> addNode(@RequestBody DocNodeVo vo) {
        return PublicResult.success(documentService.addNode(vo));
    }

    @GetMapping("/node/delete")
    public PublicResult<String> deleteNode(String id) {
        documentService.deleteNode(id);
        return PublicResult.success();
    }

    @PostMapping("/save")
    public PublicResult<String> saveDoc(@RequestBody DocBodyVo body) {
        return PublicResult.success(documentService.saveBody(body));
    }

    @GetMapping("/body")
    public PublicResult<String> queryDocBody(String id) {
        return PublicResult.success(documentService.queryDocBody(id));
    }
}
