package com.zhilianhua.ssicp.service.impl;

import com.zhilianhua.ssicp.entity.db.FileInfo;
import com.zhilianhua.ssicp.mapper.FileMapper;
import com.zhilianhua.ssicp.service.FileService;
import com.zhilianhua.ssicp.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private FileMapper fileMapper;

    @Override
    public FileInfo upload(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String type = FileUtils.getType(originalFilename);
        String code = FileUtils.getCode(originalFilename);
        FileInfo fileInfo = new FileInfo();
        fileInfo.setCode(code);
        fileInfo.setSize(file.getSize());
        fileInfo.setType(type);
        fileInfo.setName(originalFilename);
        FileUtils.saveFile(file, code);
        fileMapper.add(fileInfo);
        return fileInfo;
    }

    @Override
    public List<FileInfo> uploadMulti(List<MultipartFile> files) {
        return files.stream().map(this::upload).collect(Collectors.toList());
    }

    @Override
    public FileInfo findByCode(String code) {
        return fileMapper.get(code);
    }

    @Override
    public void deleteByCode(String code) {
        FileUtils.delete(code);
        fileMapper.deleteByCode(code);
    }

}
