package com.xavier.xos.req;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wuyanfeng
 * @description
 * @date 2020/8/31 11:57
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UploadReq {
    private String key;
}
