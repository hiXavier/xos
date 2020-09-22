package com.xavier.xos.api.domain.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;

/**
 * @author wuyanfeng
 * @description
 * @date 2020/8/26 17:23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UploadRequest implements Serializable {

    private static final long serialVersionUID = 7281253820194888011L;

    private String endPoint;

    private String accessKey;

    private String secretKey;

    private String bucket;

    private InputStream stream;

    private File file;

    private String fileName;
}
