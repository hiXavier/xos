package com.xavier.xos.req;

import com.xavier.xos.annotation.IsPhone;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * @author wuyanfeng
 * @description
 * @date 2020/9/23 15:35
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserReq {
    @NotBlank(message = "用户名称不能为空。")
    private String name;

    @Range(max = 150, min = 1, message = "年龄范围应该在1-150内。")
    private Integer age;

    @NotEmpty(message = "密码不能为空")
    @Length(min = 6, max = 8, message = "密码长度为6-8位。")
    @Pattern(regexp = "[a-zA-Z]*", message = "密码不合法")
    private String password;

    @IsPhone
    private String phoneNo;


}
