package server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cy.platform.generation.server.domain.dto.Track;
import com.cy.platform.generation.server.domain.model.CooperationDocPo;
import com.cy.platform.generation.server.domain.vo.CooperationDocVo;
import com.cy.platform.generation.server.domain.vo.HistoryDataVo;
import com.cy.platform.generation.server.domain.vo.HistoryVo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 协作文档
 *
 * @author 56807
 */
public interface ICooperationDocService extends IService<CooperationDocPo> {
    /**
     * 查询文档列表
     *
     * @return 文档列表
     */
    List<CooperationDocVo> queryList();

    /**
     * 保存文件
     *
     * @param file 文件数据
     * @return ID
     */
    String saveFile(MultipartFile file);

    /**
     * 下载文档
     *
     * @param response response
     * @param type     文档类型
     * @param key      文档Key
     */
    void downloadDoc(HttpServletResponse response, String type, String key);

    /**
     * 文档跟踪
     *
     * @param track 文档跟踪信息
     * @throws Exception 异常
     */
    void trackInfo(Track track) throws Exception;

    /**
     * 查询修改历史
     *
     * @param docCode 文档编码
     * @return 历史列表
     */
    List<HistoryVo> queryHistory(String docCode);

    /**
     * 根据版本查询历史数据
     *
     * @param docCode 文档编码
     * @param version 版本
     * @return 历史数据
     */
    HistoryDataVo queryHistoryData(String docCode, Integer version);
}
