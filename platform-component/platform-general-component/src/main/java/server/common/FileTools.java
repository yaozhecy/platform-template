package server.common;

import java.io.Closeable;
import java.io.IOException;

/**
 * 文件工具
 *
 * @author 56807
 */
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
                e.printStackTrace();
            }
        }
    }
}
