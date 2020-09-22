package com.xavier.xos.api.service;

import com.xavier.xos.api.domain.Result;
import com.xavier.xos.api.domain.req.UploadRequest;

/**
 * @author wuyanfeng
 * @description
 * @date 2020/8/26 17:29
 */
public interface XosService {

    /**
     * 上传对象
     *
     * @param uploadRequest
     * @return
     */
    Result<String> upload(UploadRequest uploadRequest);

    /**
     * 下载对象
     *
     * @param uploadRequest
     * @return
     */
    Result<String> download(UploadRequest uploadRequest);
}
