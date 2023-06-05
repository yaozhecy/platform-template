package server.controller;

import com.cy.platform.generation.server.common.PublicResult;
import com.cy.platform.generation.server.domain.transfor.CommonTransfor;
import com.cy.platform.generation.server.domain.vo.template.TemplateGroupVo;
import com.cy.platform.generation.server.domain.vo.template.TemplateInfoVo;
import com.cy.platform.generation.server.service.ITemplateService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 自定义模板
 *
 * @author 56807
 */
@RestController
@RequestMapping("/template")
public class TemplateController {
    @Autowired
    private ITemplateService templateService;

    @GetMapping("/list")
    public PublicResult<List<TemplateGroupVo>> queryTables() {
        return PublicResult.success(templateService.list().stream()
            .map(CommonTransfor.INSTANCE::toVo).collect(Collectors.toList()));
    }

    @GetMapping("/group/list")
    public PublicResult<List<TemplateGroupVo>> queryGroupList() {
        return PublicResult.success(templateService.queryGroupInfo());
    }

    @PostMapping("/group/add")
    public PublicResult<String> addGroup(@RequestBody TemplateGroupVo vo) {
        return PublicResult.success(templateService.addGroup(vo));
    }

    @PostMapping("/group/edit")
    public PublicResult<String> editGroup(@RequestBody TemplateGroupVo vo) {
        return PublicResult.success();
    }

    @GetMapping("/info/list")
    public PublicResult<List<TemplateInfoVo>> queryInfoList(@RequestParam String groupId) {
        return PublicResult.success(templateService.queryInfoList(groupId));
    }

    @PostMapping("/info/save")
    public PublicResult<String> saveTemplate(@RequestBody TemplateInfoVo vo) {
        return PublicResult.success(templateService.saveTemplate(vo));
    }

    @GetMapping("/info/query")
    public PublicResult<TemplateInfoVo> saveTemplate(@RequestParam String id) {
        return PublicResult.success(templateService.queryTemplate(id));
    }
}
