package com.qiao.usermanagement.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record User(

        Long id,

        @NotBlank(message="姓名不能为空")
        String name,

        @Email(message = "邮箱格式不正确")
        String email,

        @Min(value = 18,message = "年龄不能小于18")
        @Max(value = 180,message = "年龄不能大于180")
        Integer age
) {
}