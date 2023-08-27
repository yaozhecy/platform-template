package com.cy.generate.core.transverter.script;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * Java脚本引擎
 *
 * @author develop
 */
@Component
@Slf4j
public class MyScriptEngine {
    private final ScriptEngine jsEngine;

    public MyScriptEngine() {
        ScriptEngineManager manager = new ScriptEngineManager();
        jsEngine = manager.getEngineByMimeType("text/javascript");
    }

    /**
     * 数据解析接口
     *
     * @param script 脚本
     * @param value  值
     * @return 解析结果
     */
    public String eval(String script, String value) {
        try {
            return (String) jsEngine.eval("(function(data){" + script + "})('" + value + "')");
        } catch (ScriptException e) {
            log.error(e.getMessage(), e);
        }
        return StringUtils.EMPTY;
    }
}
