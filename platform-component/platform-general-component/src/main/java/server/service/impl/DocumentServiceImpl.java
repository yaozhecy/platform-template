package server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cy.platform.generation.server.common.FileTools;
import com.cy.platform.generation.server.domain.mapper.DocBodyMapper;
import com.cy.platform.generation.server.domain.mapper.DocNodeMapper;
import com.cy.platform.generation.server.domain.model.DocBodyPo;
import com.cy.platform.generation.server.domain.model.DocNodePo;
import com.cy.platform.generation.server.domain.transfor.DataSourceTransfor;
import com.cy.platform.generation.server.domain.vo.DocNodeVo;
import com.cy.platform.generation.server.domain.vo.doc.DocBodyVo;
import com.cy.platform.generation.server.service.IDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 文档服务实现
 *
 * @author chenyang
 */
@Service
public class DocumentServiceImpl extends ServiceImpl<DocNodeMapper, DocNodePo>
    implements IDocumentService {
    @Autowired
    private DocBodyMapper docBodyMapper;

    @Override
    public List<DocNodeVo> queryNodes(String nodeId) {
        LambdaQueryWrapper<DocNodePo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(DocNodePo::getSuperId, "0".equals(nodeId) ? "-1" : nodeId);
        return baseMapper.selectList(wrapper).stream().map(
            DataSourceTransfor.INSTANCE::toVo).collect(Collectors.toList());
    }

    @Override
    public String addNode(DocNodeVo treeNodeVo) {
        DocNodePo po = DataSourceTransfor.INSTANCE.toPo(treeNodeVo);
        baseMapper.insert(po);
        return po.getId();
    }

    @Override
    public void deleteNode(String id) {
        baseMapper.deleteById(id);
    }

    @Override
    public String saveBody(DocBodyVo body) {
        FileWriter writer = null;
        try {
            String path = "D:\\workspace" + File.separator + "doc" + File.separator + body.getName();
            File file = new File(path);
            if (!file.exists()) {
                file.mkdir();
            }
            path += File.separator + System.currentTimeMillis() + ".txt";
            writer = new FileWriter(path);
            writer.write(body.getBody());
            writer.flush();

            //step 2.删除原文档
            LambdaUpdateWrapper<DocBodyPo> uw = new LambdaUpdateWrapper<>();
            uw.set(DocBodyPo::getStatus, 1).eq(DocBodyPo::getNodeId, body.getNodeId());
            docBodyMapper.update(null, uw);

            //step 3.保存记录数据
            DocBodyPo bodyPo = new DocBodyPo();
            bodyPo.setNodeId(body.getNodeId());
            bodyPo.setNodeName(body.getName());
            bodyPo.setStatus(0);
            bodyPo.setBodyPath(path);
            docBodyMapper.insert(bodyPo);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            FileTools.close(writer);
        }
        return "";
    }

    @Override
    public String queryDocBody(String id) {
        //step 1.查询数据
        LambdaQueryWrapper<DocBodyPo> qw = new LambdaQueryWrapper<>();
        qw.eq(DocBodyPo::getNodeId, id).eq(DocBodyPo::getStatus, 0);
        List<DocBodyPo> poList = docBodyMapper.selectList(qw);
        if (poList.isEmpty()) {
            return "";
        }
        DocBodyPo bodyPo = poList.get(0);
        //step 2.数据读取
        StringBuilder doc = new StringBuilder();
        FileReader reader = null;
        try {
            reader = new FileReader(bodyPo.getBodyPath());
            char[] buf = new char[2048];
            int len;
            while ((len = reader.read(buf)) != -1) {
                doc.append(new String(buf, 0, len));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            FileTools.close(reader);
        }
        return doc.toString();
    }
}
