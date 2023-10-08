package com.zhilianhua.ssicp.service;

import com.zhilianhua.ssicp.entity.db.FileInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {
    FileInfo upload(MultipartFile file);

    FileInfo findByCode(String code);

    void deleteByCode(String code);

    List<FileInfo> uploadMulti(List<MultipartFile> files);
}
