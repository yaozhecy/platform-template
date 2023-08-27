package com.cy.generate.common;

import lombok.extern.slf4j.Slf4j;

import java.io.Closeable;
import java.io.IOException;

/**
 * 文件工具
 *
 * @author 56807
 */
@Slf4j
public final class FileTools {

    /**
     * 关闭文件
     *
     * @param closeable 可关闭
     */
    public static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                log.error("关闭文件失败", e);
            }
        }
    }
}
