package com.xavier.xos.req;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wuyanfeng
 * @description
 * @date 2020/8/31 11:57
 */
@ApiModel(description = "上传请求")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UploadReq {

    @ApiModelProperty(name = "key", notes = "上传请求key", example = "d1005d09e38c416580e0e2041a6e8089")
    private String key;
}
