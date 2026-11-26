package com.qiao.usermanagement.model;

import jakarta.validation.constraints.*;

public record User(

        Long id,

        @NotBlank(message="姓名不能为空")
        @Size(max=100)
        String name,

        @Email(message = "邮箱格式不正确")
        String email,

        @Min(value = 18,message = "年龄不能小于18")
        @Max(value = 180,message = "年龄不能大于180")
        Integer age
) {
}