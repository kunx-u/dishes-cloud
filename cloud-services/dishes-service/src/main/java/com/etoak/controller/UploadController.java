package com.etoak.controller;

import com.etoak.api.UploadService;
import com.etoak.common.core.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    UploadService uploadService;

    @PostMapping("/image")
    public ResultVO<String> upload(MultipartFile file) throws IOException {
        return uploadService.upload(file);
    }

}
