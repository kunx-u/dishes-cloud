package com.etoak.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginDTO {

    /** 登陆用户名 */
    @NotBlank(message = "username不能为空")
    private String username;

    @NotBlank(message = "password不能为空")
    private String password;

    @NotBlank(message = "uuid不能为空")
    private String uuid;

    /**
     * 验证码
     */
    @NotBlank(message = "code不能为空")
    private String code;
}
