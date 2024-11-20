package com.etoak.controller;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.etoak.common.core.CommonConstant;
import com.etoak.common.core.vo.ResultVO;
import com.etoak.common.redis.RedisService;
import com.etoak.vo.CodeVO;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/code")
public class CodeController {

    @Autowired
    RedisService redisService;

    @Autowired
    DefaultKaptcha defaultKaptcha;

    @GetMapping("/getCode")
    public ResultVO<CodeVO> getCode() throws IOException {
        String text = defaultKaptcha.createText();

        // 1*1=？@1 转成 ["1*1"=?,"1"]
        String[] textArray = text.split(StrUtil.AT);

        // 将 1*1=？ 写入图片
        BufferedImage image = defaultKaptcha.createImage(textArray[0]);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image,"png",outputStream);

        /* 将计算结果写入Redis */
        String uuid = IdUtil.simpleUUID();
        redisService.set(CommonConstant.REDIS_CODE_KEY_PREFIX + uuid,
                textArray[1],
                2,
                TimeUnit.MINUTES);

        String base64 = Base64.encode(outputStream.toByteArray());
        String codeUrl = "data:image/png;base64," + base64;

        CodeVO codeVO = new CodeVO(uuid,codeUrl);
        return ResultVO.success(codeVO);
    }
}
