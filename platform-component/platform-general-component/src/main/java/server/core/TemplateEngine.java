package server.core;

import com.cy.platform.generation.server.core.model.TableInfo;
import com.cy.platform.generation.server.core.properties.PropertiesInfo;
import com.cy.platform.generation.server.domain.vo.CodeInfoVo;
import com.cy.platform.generation.server.domain.vo.CommonDataVo;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

/**
 * 模板引擎
 *
 * @author 56807
 */
@Component
public class TemplateEngine {
    private VelocityEngine velocityEngine;
    @Autowired
    private PropertiesInfo propertiesInfo;

    public TemplateEngine() {
        init();
    }

    public void init() {
        Properties properties = new Properties();
        properties.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        properties.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        properties.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        this.velocityEngine = new VelocityEngine(properties);
    }

    public CodeInfoVo generate(CommonDataVo columnInfo, TableInfo tableInfo) {
        VelocityContext context = getVelocityContext(columnInfo, tableInfo);
        CodeInfoVo codeInfoVo = new CodeInfoVo();
        codeInfoVo.setVo(getStringWriter("template/Vo.java.vm", context));
        codeInfoVo.setDto(getStringWriter("template/Dto.java.vm", context));
        codeInfoVo.setPo(getStringWriter("template/Po.java.vm", context));
        codeInfoVo.setTransfor(getStringWriter("template/Transfor.java.vm", context));
        codeInfoVo.setMapper(getStringWriter("template/Mapper.java.vm", context));
        codeInfoVo.setMapperXml(getStringWriter("template/Mapper.xml.vm", context));
        codeInfoVo.setService(getStringWriter("template/Service.java.vm", context));
        codeInfoVo.setServiceImpl(getStringWriter("template/ServiceImpl.java.vm", context));
        codeInfoVo.setController(getStringWriter("template/Controller.java.vm", context));
        return codeInfoVo;
    }

    private VelocityContext getVelocityContext(CommonDataVo columnInfo, TableInfo tableInfo) {
        Configuration config = getConfig();
        VelocityContext context = new VelocityContext();
        //通用日志
        context.put("author", config.getString("author"));
        //基础配置
        context.put("package", columnInfo.getPackagePath());
        context.put("moduleName", columnInfo.getModuleName());
        //类信息
        context.put("tableName", tableInfo.getTableName());
        context.put("comments", tableInfo.getComments());
        context.put("className", tableInfo.getClassName());
        context.put("englishName", tableInfo.getEnglishName());
        context.put("englishName2", JdbcToJavaHelper.smallName(tableInfo.getEnglishName()));

        tableInfo.getColumns().forEach(n -> {
            n.setGetName("get" + n.getEnglishName());
            n.setDataType(n.getDataType().toLowerCase());
        });
        context.put("columns", tableInfo.getColumns());
        return context;
    }

    public String generateSql(List<TableInfo> tableInfos) {
        VelocityContext context = new VelocityContext();
        for (TableInfo tableInfo : tableInfos) {
            tableInfo.setPkUuid(UUID.randomUUID().toString());
        }
        context.put("tables", tableInfos);
        return getStringWriter("template/CreateSql.sql.vm", context);
    }

    private String getStringWriter(String path, VelocityContext context) {
        Template template = this.velocityEngine.getTemplate(path, StandardCharsets.UTF_8.name());
        StringWriter stringWriter = new StringWriter();
        template.merge(context, stringWriter);
        stringWriter.flush();
        return stringWriter.toString();
    }

    /**
     * 获取配置信息
     */
    public static Configuration getConfig() {
        try {
            return new PropertiesConfiguration("generator.properties");
        } catch (ConfigurationException e) {
            throw new RuntimeException("获取配置文件失败，", e);
        }
    }
}
