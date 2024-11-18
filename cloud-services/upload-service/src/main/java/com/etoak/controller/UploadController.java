package com.etoak.controller;

import cn.hutool.core.io.file.FileNameUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.etoak.common.core.properties.ImageProperties;
import com.etoak.common.core.vo.ResultVO;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    ImageProperties imageProperties;

    @PostMapping("/image")
    public ResultVO<String> upload(MultipartFile pic) throws IOException {
        if(ObjectUtils.isEmpty(pic) || pic.isEmpty() ){
            return ResultVO.failed("文件不能为空");
        }
        String contentType = pic.getContentType();
        if (!imageProperties.getTypes().contains(contentType)) {
            String types = imageProperties.getTypes().stream()
                    .map(x -> x.substring(x.lastIndexOf("/") + 1))
                    .collect(Collectors.joining("、"));
            return ResultVO.failed("仅支持" + types + "类型的图片!");
        }

        String originalFilename = pic.getOriginalFilename();
        String suffix = FileNameUtil.getSuffix(originalFilename);
        String filename = IdUtil.simpleUUID() + StrUtil.DOT + suffix;

        File uploadDir = new File(imageProperties.getLocation());
        if(!uploadDir.exists()){
            uploadDir.mkdirs();
        }

        File destFile = new File(uploadDir,filename);

        pic.transferTo(destFile);

        String path = imageProperties.getMapping().endsWith("/")?
                imageProperties.getMapping() + filename :
                imageProperties.getMapping() + "/" + filename;
        return ResultVO.success(path);
    }

}
