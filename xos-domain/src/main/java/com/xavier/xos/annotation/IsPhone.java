package com.xavier.xos.annotation;

import com.xavier.xos.validator.PhoneValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author wuyanfeng
 * @description
 * @date 2020/9/23 16:25
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Constraint(validatedBy = {PhoneValidator.class})
public @interface IsPhone {
    /**
     * 是否需要校验
     * @return  boolean
     */
    boolean required() default true;

    String message() default "手机号码格式错误";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
