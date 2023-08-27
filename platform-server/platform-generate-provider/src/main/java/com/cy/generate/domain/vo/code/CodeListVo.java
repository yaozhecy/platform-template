package com.cy.generate.domain.vo.code;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 代码列表
 *
 * @author develop
 */
@Data
public class CodeListVo {
    /**
     * 代码列表
     */
    private List<CodeInfo> code;

    public static CodeListVo newCodeList() {
        CodeListVo codeListVo = new CodeListVo();
        codeListVo.setCode(new ArrayList<>());
        return codeListVo;
    }

    public static CodeInfo newCodeInfo(String name) {
        CodeInfo info = new CodeInfo();
        info.setFileName(name);
        return info;
    }

    public static CodeInfo newCodeInfo(String name, String fileDir) {
        CodeInfo info = new CodeInfo();
        info.setFileName(name);
        info.setFileDir(fileDir);
        return info;
    }

    @Data
    public static class CodeInfo {
        /**
         * 文件名称
         */
        private String fileName;
        /**
         * 文件目录
         */
        private String fileDir;
        /**
         * 文件内容
         */
        private String fileBody;
    }
}
