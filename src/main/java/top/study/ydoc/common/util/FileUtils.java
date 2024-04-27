package top.study.ydoc.common.util;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author tjy
 * @date 2024/4/23
 * @apiNote
 */
public class FileUtils {
    // 获取文件后缀的方法
    public static String getFileExtension(String fileName) {
        int lastDotIndex = fileName.lastIndexOf('.');
        if (lastDotIndex > 0) {
            return fileName.substring(lastDotIndex + 1);
        }
        return null;
    }

    public static boolean fileConvert(MultipartFile file, String afterTransfer) {

        return false;
    }

    public static void main(String[] args) {
     }
}
