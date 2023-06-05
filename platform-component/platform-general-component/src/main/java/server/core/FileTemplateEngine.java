package server.core;

import cn.hutool.core.bean.BeanUtil;
import com.cy.platform.common.collect.CollectTools;
import com.cy.platform.generation.server.core.model.TableInfo;
import com.cy.platform.generation.server.core.transverter.TransverterEngine;
import com.cy.platform.generation.server.core.transverter.script.MyScriptEngine;
import com.cy.platform.generation.server.domain.vo.CommonDataVo;
import com.cy.platform.generation.server.domain.vo.field.FieldMapItemVo;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.FileResourceLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * 模板文件引擎（文件）
 *
 * @author develop
 */
@Component
public class FileTemplateEngine {
    /**
     * 模版引擎
     */
    private final VelocityEngine velocityEngine;
    /**
     * 脚本转换引擎
     */
    @Autowired
    private MyScriptEngine myScriptEngine;
    /**
     * 转换引擎
     */
    @Autowired
    private TransverterEngine transverterEngine;

    public FileTemplateEngine() {
        //属性声明，使用加载器使用文件加载器
        Properties properties = new Properties();
        properties.setProperty(RuntimeConstants.RESOURCE_LOADER, "file");
        properties.setProperty("classpath.resource.loader.class",
            FileResourceLoader.class.getName());

        this.velocityEngine = new VelocityEngine(properties);
    }

    /**
     * 获取模板上线文
     *
     * @param fieldMaps  字段映射集合
     * @param columnInfo 表数据
     * @param tableInfo  列数据
     * @return 模板上线文
     */
    public VelocityContext getVelocityContext(List<FieldMapItemVo> fieldMaps, TableInfo tableInfo, CommonDataVo columnInfo) {
        Map<String, Object> context = CollectTools.newHashMap();
        //通用日志
        context.put("author", "");
        //基础配置
        context.put("package", columnInfo.getPackagePath());
        context.put("moduleName", columnInfo.getModuleName());
        //类信息
        Map<String, Object> tableContext = BeanUtil.beanToMap(tableInfo);
        fillExtendField("table", fieldMaps, tableContext);
        context.putAll(tableContext);

        //列信息
        final List<Map<String, Object>> colunmnList = new ArrayList<>();
        tableInfo.getColumns().forEach(n -> {
            Map<String, Object> colunmnMap = BeanUtil.beanToMap(n);
            fillExtendField("colunmn", fieldMaps, colunmnMap);
            colunmnList.add(colunmnMap);
        });
        context.put("columns", colunmnList);
        return new VelocityContext(context);
    }

    private void fillExtendField(final String level, List<FieldMapItemVo> fieldMaps, Map<String, Object> tableContext) {
        fieldMaps.stream().filter(n -> level.equals(n.getFieldLevel())).forEach(n -> {
            String value = String.valueOf(tableContext.get(n.getOrgFieldCode()));
            tableContext.put(n.getFieldCode(), transverterEngine.transition(n, value));
        });
    }

    public String getString(String path, VelocityContext context) {
        Template template = this.velocityEngine.getTemplate(path, StandardCharsets.UTF_8.name());
        StringWriter stringWriter = new StringWriter();
        template.merge(context, stringWriter);
        stringWriter.flush();
        return stringWriter.toString();
    }
}
