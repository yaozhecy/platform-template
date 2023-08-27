package com.cy.generate.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cy.generate.domain.model.DocNodePo;
import com.cy.generate.domain.vo.DocNodeVo;
import com.cy.generate.domain.vo.doc.DocBodyVo;

import java.util.List;

/**
 * 文档接口
 *
 * @author chenyang
 */
public interface IDocumentService extends IService<DocNodePo> {

    /**
     * 查询列表节点
     *
     * @param nodeId 节点ID
     * @return 查询节点列表
     */
    List<DocNodeVo> queryNodes(String nodeId);

    /**
     * 添加节点
     *
     * @param treeNodeVo 节点
     * @return 添加结果
     */
    String addNode(DocNodeVo treeNodeVo);

    /**
     * 删除节点
     *
     * @param id ID
     */
    void deleteNode(String id);

    /**
     * 保存文档实体
     *
     * @param body 实体
     * @return 结果
     */
    String saveBody(DocBodyVo body);

    /**
     * 查询文档
     *
     * @param id 文档ID
     * @return 文档内容
     */
    String queryDocBody(String id);
}
