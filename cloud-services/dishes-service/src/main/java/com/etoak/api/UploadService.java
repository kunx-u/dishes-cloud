package com.etoak.api;

import com.etoak.common.core.vo.ResultVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@FeignClient("upload-service")
public interface UploadService {

    @PostMapping(value = "/upload/image",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResultVO<String> upload(@RequestPart MultipartFile pic) throws IOException;

}
