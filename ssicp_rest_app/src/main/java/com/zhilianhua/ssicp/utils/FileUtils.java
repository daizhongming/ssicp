package com.zhilianhua.ssicp.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Slf4j
@Component
public class FileUtils {
    private static final String NAME_TYPE_SPLITTER = ".";
    private static final String FILE_PATH_AND_NAME_SPLITTER = "/";

    private static String filePathString;


    @Value("${app.file.path}")
    public void setFilePath(String value) {
        filePathString = value;
    }

    public static String getAllFilePath(String fileCode) {
        return filePathString + FILE_PATH_AND_NAME_SPLITTER + fileCode;
    }

    public static String getType(String fileName) {
        return fileName.substring(fileName.lastIndexOf(NAME_TYPE_SPLITTER) + 1);
    }

    public static String getCode(String originalFilename) {
        return IdUtils.uuid();
    }

    public static void delete(String fileCode) {
        File file = new File(getAllFilePath(fileCode));
        if (file.exists()) {
            file.delete();
        }
    }

    public static void saveFile(MultipartFile file, String fileCode) {
        String allFilePath = getAllFilePath(fileCode);
        File filePath = new File(filePathString);
        if (!filePath.exists() && !filePath.mkdirs()) {
            throw new RuntimeException("文件夹创建失败");
        }
        log.debug("allFilePath:{}", allFilePath);
        File fileName = new File(allFilePath);
        try {
            file.transferTo(fileName);
        } catch (Exception e) {
            throw new RuntimeException("文件保存失败");
        }
    }
}
