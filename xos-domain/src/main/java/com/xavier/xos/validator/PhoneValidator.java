package com.xavier.xos.validator;

import com.xavier.xos.annotation.IsPhone;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * @author wuyanfeng
 * @description
 * @date 2020/9/23 16:26
 */
public class PhoneValidator implements ConstraintValidator<IsPhone, String> {
    private boolean required = false;

    @Override
    public void initialize(IsPhone constraintAnnotation) {
        required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        // value为空时进行校验
        if (StringUtils.isBlank(value)) {
            if (required) {
                return false;
            }
            return true;
        } else {
            // 不为空时进行校验
            return isMobile(value);
        }
    }

    /**
     * 校验手机号码格式是否正确
     * @param phone  需要校验的手机号码
     * @return  boolean
     */
    private boolean isMobile(String phone) {
        String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";
        if (phone.length() != 11) {
            return false;
        } else {
            boolean isMatch =  Pattern.matches(regex, phone);
            if (isMatch) {
                return true;
            } else {
                return false;
            }
        }
    }
}
