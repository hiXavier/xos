package com.xavier.xos.api.service;

import com.xavier.xos.api.domain.Result;
import com.xavier.xos.api.domain.req.UploadRequest;

/**
 * @author wuyanfeng
 * @description
 * @date 2020/9/8 15:41
 */
public interface XosUploadService {
    /**
     * 上传对象
     *
     * @param uploadRequest
     * @return
     */
    Result<String> upload(UploadRequest uploadRequest);
}
