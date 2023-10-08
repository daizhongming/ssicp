package com.zhilianhua.ssicp.controller;

import com.zhilianhua.ssicp.controller.share.ShareControllerHandler;
import com.zhilianhua.ssicp.entity.db.FileInfo;
import com.zhilianhua.ssicp.privilege.Privilege;
import com.zhilianhua.ssicp.service.FileService;
import com.zhilianhua.ssicp.utils.ResponseEntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/files")
@Privilege("文件管理")
public class FileController {
    @Autowired
    private FileService fileService;
    @Autowired
    private ShareControllerHandler shareControllerHandler;

    @GetMapping("/{fileCode}/message")
    @ResponseBody
    @Privilege(value = "查看文件信息")
    public ResponseEntity<FileInfo> message(@PathVariable("fileCode") String fileCode) {
        return ResponseEntityUtils.success(fileService.findByCode(fileCode));
    }

    @PostMapping("")
    @ResponseBody
    @Privilege(value = "上传文件")
    public ResponseEntity<FileInfo> upload(@RequestParam("file") MultipartFile file) {
        return shareControllerHandler.upload(file);
    }

    @GetMapping("/{fileCode}")
    @Privilege(value = "下载文件")
    public ResponseEntity<Resource> downloadFile(@PathVariable("fileCode") String fileCode) throws IOException {
        return shareControllerHandler.downloadFile(fileCode);
    }
}
